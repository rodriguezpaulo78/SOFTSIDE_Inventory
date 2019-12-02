package softside_inventory.controladores.inventario;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Inventario_Detalle;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.inventario.ModificarDetalleInventario;

/**
 * Controlador de la modificacion de registro de detalle de kardex
 * 
 * Carga, recibe y valida datos sobre un registro existente de movimiento de
 * entrada o salida de un producto
 *  
 * @author SOFTSIDE
 */

public class CModificarDetalleInventario implements IModificarDetalleInventario
{
    private ModificarDetalleInventario ventana;
    private String codigoAlmacen;
    private String cantidad;
    private String valTot;
    private String codigoDetalle;
    private String codigoProducto;
    private String codigoCabecera;
    //private KardexDet kd;
    private Session user;
    
    public CModificarDetalleInventario(String codigoDetalle, String codigoProducto, String codigoCabecera, String cantidad, String valTot, Session user)
    {
        this.codigoDetalle = codigoDetalle;
        this.codigoProducto = codigoProducto;
        this.codigoCabecera = codigoCabecera;
        this.cantidad = cantidad;
        this.valTot = valTot;
        this.user = user;
        ventana = new ModificarDetalleInventario(this);
    }
    
    @Override
    public void cancelar()
    {
        new CVistaInventario(user);
        ventana.dispose();
    }
    
    @Override
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int s)
    {
        boolean canB    = !(txtCan.getText().length() == 0);
        boolean vUniB   = !(txtValUni.getText().length() == 0);
        boolean vTotB   = !(txtValTot.getText().length() == 0);
        
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
            vUniB = false;
        }
        
        try
        {
            vTot = Double.parseDouble(txtValTot.getText());
        }
        catch(NumberFormatException e)
        {
            vTotB = false;
        }
             
        if(canB && vUniB && s != 3)
        {
            vTot = can * vUni;
            txtValTot.setText(String.valueOf(vTot));
        }
        else if(canB && vTotB && s != 2)
        {
            vUni = vTot / can;
            if(!Double.isFinite(vUni))
                vUni = 0;
            txtValUni.setText(String.valueOf(vUni));
        }
        else if(vUniB && vTotB && s != 1)
        {
            can = vTot / vUni;
            if(!Double.isFinite(can))
                can = 0;
            txtCan.setText(String.valueOf(can));
        }
    }
    
    public void cargar(JTextField txtNumDoc, JTextField txtInvDetCod, JTextField txtProCod, JDateChooser fecha, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
    {
        // Solicita el usuario al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 7);
        jsonObj.put("codigoDet", codigoDetalle);
        jsonObj.put("codigoCab", codigoCabecera);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);
        
        ArrayList<Inventario_Detalle> detalles = getInvDetJSON(response);
        Inventario_Detalle det = detalles.get(0);
        
        txtInvDetCod.setText(codigoDetalle);
        txtProCod.setText(codigoProducto);
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fec = null;
        try {
            fec = formato.parse(det.getInvDetFecha());
        } 
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        fecha.setDate(fec);
        
        int ope = 0;
        if(det.getInvDetMovimiento().equals("salida"))
            ope = 1;
        cbxOpe.setSelectedIndex(ope);
        txtCan.setText(det.getInvDetCantidad());
        txtValUni.setText(det.getInvDetPrecioUnit());
        txtValTot.setText(det.getInvDetPrecioTotal());
        txtObs.setText(det.getInvDetObservacion());   
        txtNumDoc.setText(user.getDni());
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Usuario>
     */
    private ArrayList<Inventario_Detalle> getInvDetJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Inventario_Detalle> detalles = new ArrayList<Inventario_Detalle>();
        Inventario_Detalle det = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            det = new Inventario_Detalle();
            JSONObject row =(JSONObject)array.get(i);
            det.setInvDetCodigo(row.get("inv_det_id").toString());
            det.setInvCabCodigo(row.get("inventario_cab_id").toString());
            det.setInvDetMovimiento(row.get("inv_det_movimiento").toString());
            det.setInvDetCantidad(row.get("inv_det_cantidad").toString());
            det.setInvDetPrecioUnit(row.get("inv_det_precio_unit").toString());
            det.setInvDetPrecioTotal(row.get("inv_det_precio_total").toString());
            det.setInvDetFecha(row.get("inv_det_fec").toString());
            det.setInvDetSaldoCantidad(row.get("inv_det_saldo_cant").toString());
            det.setInvDetObservacion(row.get("inv_det_obs").toString());
            det.setInvDetEstado(row.get("inv_det_est_reg").toString());
           
            detalles.add(det);
            det = null;
        }
        return detalles;
    }
   
    @Override
    public void aceptar(JTextField txtInvDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs)
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
            detalle.setInvDetCodigo(txtInvDetCod.getText());
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
        
            String json = detalle.toJSON(3, salValUni, salValTot);

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
            JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
            new CVistaInventario(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
