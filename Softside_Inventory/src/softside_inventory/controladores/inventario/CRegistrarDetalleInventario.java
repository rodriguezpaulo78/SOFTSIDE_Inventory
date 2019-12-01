package softside_inventory.controladores.inventario;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Inventario_Detalle;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.inventario.RegistrarDetalleInventario;

/**
 * Controlador de la insercion de registro de detalle de inventario
 * 
 * Recibe y valida datos sobre un nuevo registro de movimiento de entrada o
 * salida de un producto
 *  
 * @author SOFTSIDE
 */

public class CRegistrarDetalleInventario implements IRegistrarDetalleInventario
{
    private RegistrarDetalleInventario ventana;
    private ArrayList<ArrayList<String>> documentos;
    private String codigoProducto;
    private String codigoCabecera;
    private String cantidad;
    private String valTot;
    private Session user;
    
    public CRegistrarDetalleInventario(String codigoProducto, String codigoCabecera, String cantidad, String valTot, Session user)
    {
        this.user = user;
        this.codigoProducto = codigoProducto;
        this.codigoCabecera = codigoCabecera;
        this.cantidad = cantidad;
        this.valTot = valTot;
        
        ventana = new RegistrarDetalleInventario(this);
    }
    
    @Override
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int s)
    {
        boolean canB    = !(txtCan.getText().length() == 0);
        boolean valUniB = !(txtValUni.getText().length() == 0);
        boolean valTotB = !(txtValTot.getText().length() == 0);
        
        double can  = 0;
        double vUni = 0;
        double vTot = 0;
        
        try
        {
            can = Double.parseDouble(txtCan.getText());
        }
        catch(NumberFormatException e)
        {
            canB = false;
        }
        
        try
        {
            vUni = Double.parseDouble(txtValUni.getText());
        }
        catch(NumberFormatException e)
        {
            valUniB = false;
        }
        
        try
        {
            vTot = Double.parseDouble(txtValTot.getText());
        }
        catch(NumberFormatException e)
        {
            valTotB = false;
        }
             
        if(canB && valUniB && s != 3)
        {
            vTot = can * vUni;
            txtValTot.setText(String.valueOf(vTot));
        }
        else if(canB && valTotB && s != 2)
        {
            vUni = vTot / can;
            if(!Double.isFinite(vUni))
                vUni = 0;
            txtValUni.setText(String.valueOf(vUni));
        }
        else if(valUniB && valTotB && s != 1)
        {
            can = vTot / vUni;
            if(!Double.isFinite(can))
                can = 0;
            txtCan.setText(String.valueOf(can));
        }
    }
    
    @Override
    public void cancelar()
    {
        new CVistaInventario(user);
        ventana.dispose();
    }
    
    public void cargar(JTextField txtDoc, JTextField txtInvDetCod, JTextField txtProCod, JTextField txtAlmCod)
    {
        // obtener codigo de detalle
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);
        
        String cod = getJsonCod(response);
        txtInvDetCod.setText(cod);
        txtProCod.setText(codigoProducto);
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     * @return String
     */
    public String getJsonCod(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String cod = row.get("codigo").toString();
        return cod;
    }
    
    @Override
    public void aceptar(JTextField txtKarDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
    {
        try
        {
            String salCan = "0";
            String salValTot = "0";
            String salValUni = "0";
            if(cbxOpe.getSelectedIndex() == 0)
            {
                salCan = String.valueOf(Double.parseDouble(cantidad) + Double.parseDouble(txtCan.getText()));
                salValTot = String.valueOf(Double.parseDouble(valTot) + Double.parseDouble(txtValTot.getText()));
                Double saldoTotal = Double.parseDouble(salValTot)/Double.parseDouble(salCan);
                if(!Double.isFinite(saldoTotal))
                    saldoTotal = 0.0;
                salValUni = String.valueOf(saldoTotal);
            }
            else
            {
                salCan = String.valueOf(Double.parseDouble(cantidad) - Double.parseDouble(txtCan.getText()));
                salValTot = String.valueOf(Double.parseDouble(valTot) - Double.parseDouble(txtValTot.getText()));
                Double saldoTotal = Double.parseDouble(salValTot)/Double.parseDouble(salCan);
                if(!Double.isFinite(saldoTotal))
                    saldoTotal = 0.0;
                salValUni = String.valueOf(saldoTotal);
            }
            
            String ope = "";
            if(cbxOpe.getSelectedIndex() == 0)
                ope = "entrada";
            else
                ope = "salida";
            
            Inventario_Detalle detalle = new Inventario_Detalle();
            detalle.setInvDetCodigo(txtKarDetCod.getText());
            detalle.setInvCabCodigo(codigoCabecera);
            detalle.setInvDetMovimiento(ope);
            detalle.setInvDetCantidad(txtCan.getText());
            detalle.setInvDetPrecioUnit(txtValUni.getText());
            detalle.setInvDetPrecioTotal(txtValTot.getText());
            
            Date date = fecha.getDate();
            DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String fecha2 = f.format(date);
            detalle.setInvDetFecha(fecha2);
            
            detalle.setInvDetSaldoCantidad(salCan);
            detalle.setInvDetObservacion(txtObs.getText());

            String json = detalle.toJSON(2, salValUni, salValTot);

            HttpNetTask httpConnect = new HttpNetTask();
            String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);

            getJsonRespDet(response);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Cantidad o Valor Total inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonRespDet(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaInventario(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
