package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import softside_inventory.controladores.producto.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Producto;
import softside_inventory.modelos.Proveedor;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.producto.RegistrarProducto;
import softside_inventory.vistas.proveedor.RegistrarProveedor;

/**
 * Controlador de la insercion de proveedor
 * 
 * Recibe y valida datos sobre un nuevo registro de proveedor
 *  
 * @author SOFTSIDE
 */

public class CRegistrarProducto implements IRegistrarProducto
{
    private RegistrarProducto ventana;
    private Session user; 
    
    /**
     * Constructor de Clase
     * @param user : sesión de usuario logeado
     */
    public CRegistrarProducto(Session user)
    {
        this.user = user;
        ventana = new RegistrarProducto(this);
    }
    
    /**
     * Retorna a la ventana de Vista de Proveedores
     */
    @Override
    public void cancelar()
    { 
        new CVistaProducto(user);
        ventana.dispose();
        
    }
    
    /**
     * Actualiza la interfaz con el codigo del Proveedor
     * @param txtProdCod
     */
    @Override
    public void cargar(JTextField txtProdCod)
    {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        String cod = getJsonCod(response);
        txtProdCod.setText(cod);
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
    
    /**
     * Hace la validación de los campos ingresados en la interfaz
     * @param txtProdCod
     * @param txtProdNom
     * @param txtProdDes
     * @param txtFecVenc
     * @param txtProdUni
     * @param txtProdProv
     */
    @Override
    public void aceptar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, 
            JDateChooser txtFecVenc, JTextField txtProdUni, JTextField txtProdProv)
    {
        Producto u = new Producto();
        u.setCodigo(txtProdCod.getText());
        u.setNombre(txtProdNom.getText());
        u.setDescripcion(txtProdDes.getText());
        
        Date fecha = txtFecVenc.getDate();
        DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        String fecha2=f.format(fecha);
        
        u.setFec_venc(fecha2);
        
        u.setCodigo_uni(txtProdUni.getText());
        u.setCodigo_prov(txtProdProv.getText());
                       
        String json = u.toJSON(2);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        getJsonRespUser(response);
        
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonRespUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Producto registrado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaProducto(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
