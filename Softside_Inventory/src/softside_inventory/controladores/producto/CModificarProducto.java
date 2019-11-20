package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.ParseException;
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
import softside_inventory.vistas.producto.ModificarProducto;

/**
 * Controlador de la modificacion de Producto
 * 
 * Carga datos del Producto seleccionado, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarProducto implements IModificarProducto
{
    private ModificarProducto ventana;
    private Producto u;
    private Session user; 
    private String codigo;
    private ArrayList<Unidad> unidades;
    private ArrayList<Proveedor> proveedores;
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarProducto(Session user, String codigo)
    {
        //u = Producto.buscar(codigo);
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarProducto(this);

    }
    
    /**
     * Retorna a la ventana de Vista de Producto
     */
    @Override
    public void cancelar()
    {
        new CVistaProducto(user);
        ventana.dispose();
    }
    
    /**
     * Actualiza la interfaz con los datos del del Producto registrado a modificar
     * @param txtProdCod
     * @param txtProdNom
     * @param txtProdDes
     * @param txtFecVenc
     * @param jcbUnidad
     * @param jcbProveedor
     */
    @Override
    public void cargar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, 
            JDateChooser txtFecVenc, JComboBox jcbUnidad, JComboBox jcbProveedor)
    {
        // Solicita el Producto al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", codigo);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        ArrayList<Producto> producto = getUsersJSON(response);
        
        txtProdCod.setText(producto.get(0).getCodigo());
        txtProdNom.setText(producto.get(0).getNombre());
        txtProdDes.setText(producto.get(0).getDescripcion());
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formato.parse(producto.get(0).getFec_venc());
        } 
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        txtFecVenc.setDate(fecha);
        
        cargarUnidad(jcbUnidad, producto.get(0).getCodigo_uni());
        cargarProveedor(jcbProveedor, producto.get(0).getCodigo_prov());
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Producto>
     */
    private ArrayList<Producto> getUsersJSON(String json){
        Object jsonObject =JSONValue.parse(json);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Producto();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prod_id").toString());
            u.setNombre(row.get("prod_nombre").toString());
            u.setDescripcion(row.get("prod_descripcion").toString());
            u.setCodigo_uni(row.get("unidad_id").toString());
            u.setFec_venc(row.get("prod_fec_venc").toString());
            u.setCodigo_prov(row.get("proveedor_id").toString());
            u.setEstado(row.get("prod_est_reg").toString());
                     
            productos.add(u);
            u = null;
        }
        return productos;
    }
    
    public void cargarUnidad(JComboBox jcbUnidad, String uniCod){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        unidades = getUnidadesJSON(response);
        for(int i = 0; i < unidades.size(); i++)
        {
            jcbUnidad.insertItemAt(unidades.get(i).getDescripcion(), i);
            
            if (unidades.get(i).getCodigo().equals(uniCod)){
                jcbUnidad.setSelectedIndex(i);
            }
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
    
    public void cargarProveedor(JComboBox jcbProveedor, String provCod){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        proveedores = getProveedoresJSON(response);
        for(int i = 0; i < proveedores.size(); i++)
        {
            jcbProveedor.insertItemAt(proveedores.get(i).getRaz_soc(), i);
            
            if (proveedores.get(i).getCodigo().equals(provCod)){
                jcbProveedor.setSelectedIndex(i);
            }
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
                       
        String json = u.toJSON(3);
        
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
            JOptionPane.showMessageDialog(null, "Producto modificado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaProducto(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
