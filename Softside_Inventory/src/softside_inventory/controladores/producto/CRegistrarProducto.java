package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Producto;
import softside_inventory.modelos.Proveedor;
import softside_inventory.modelos.Unidad;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.producto.RegistrarProducto;

/**
 * Controlador de la insercion de Producto
 * 
 * Recibe y valida datos sobre un nuevo registro de Producto
 *  
 * @author SOFTSIDE
 */

public class CRegistrarProducto implements IRegistrarProducto
{
    private RegistrarProducto ventana;
    private Session user; 
    private ArrayList<Unidad> unidades;
    private ArrayList<Proveedor> proveedores;
    
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
     * Retorna a la ventana de Vista de Productos
     */
    @Override
    public void cancelar()
    { 
        new CVistaProducto(user);
        ventana.dispose();
        
    }
    
    /**
     * Actualiza la interfaz con el codigo del Producto
     * @param txtProdCod
     * @param jcbUnidad
     * @param jcbProveedor
     */
    @Override
    public void cargar(JTextField txtProdCod, JComboBox jcbUnidad, JComboBox jcbProveedor)
    {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        String cod = getJsonCod(response);
        txtProdCod.setText(cod);
        
        cargarUnidadesActivas(jcbUnidad);
        cargarProveedoresActivos(jcbProveedor);
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
    
    public void cargarUnidadesActivas(JComboBox jcbUnidad){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        unidades = getUnidadesJSON(response);
        for(int i = 0; i < unidades.size(); i++)
        {
            jcbUnidad.insertItemAt(unidades.get(i).getDescripcion(), i);
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Unidad>
     */
    private ArrayList<Unidad> getUnidadesJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();
        Unidad u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            u = new Unidad();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("uni_id").toString());
            u.setDescripcion(row.get("uni_descripcion").toString());
           
            unidades.add(u);
            u = null;
        }
        return unidades;
    }
    
    public void cargarProveedoresActivos(JComboBox jcbProveedor){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        proveedores = getProveedoresJSON(response);
        for(int i = 0; i < proveedores.size(); i++)
        {
            jcbProveedor.insertItemAt(proveedores.get(i).getRaz_soc(), i);
        }
    }
    
    private ArrayList<Proveedor> getProveedoresJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Proveedor u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            u = new Proveedor();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prov_id").toString());
            u.setRaz_soc(row.get("prov_raz_soc").toString());
           
            proveedores.add(u);
            u = null;
        }
        return proveedores;
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
            JDateChooser txtFecVenc, JComboBox jcbUnidad, JComboBox jcbProveedor)
    {
        Producto u = new Producto();
        u.setCodigo(txtProdCod.getText());
        u.setNombre(txtProdNom.getText());
        u.setDescripcion(txtProdDes.getText());
        
        Date fecha = txtFecVenc.getDate();
        DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        String fecha2=f.format(fecha);
        
        u.setFec_venc(fecha2);
        
        u.setCodigo_uni(unidades.get(jcbUnidad.getSelectedIndex()).getCodigo());
        u.setCodigo_prov(proveedores.get(jcbProveedor.getSelectedIndex()).getCodigo());
                       
        String json = u.toJSON(2);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        getJsonRespProd(response);
        
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonRespProd(String json){
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
