package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import softside_inventory.controladores.proveedor.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Producto;
import softside_inventory.modelos.Proveedor;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.producto.ModificarProducto;
import softside_inventory.vistas.proveedor.ModificarProveedor;

/**
 * Controlador de la modificacion de proveedor
 * 
 * Carga datos del proveedor seleccionado, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarProducto implements IModificarProducto
{
    private ModificarProducto ventana;
    private Proveedor u;
    private Session user; 
    private String codigo;
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarProducto(Session user, String codigo)
    {
        //u = Proveedor.buscar(codigo);
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarProducto(this);

    }
    
    /**
     * Retorna a la ventana de Vista de Proveedor
     */
    @Override
    public void cancelar()
    {
        new CVistaProducto(user);
        ventana.dispose();
    }
    
    /**
     * Actualiza la interfaz con los datos del de proveedor registrado a modificar
        * @param txtProdCod
     * @param txtProdNom
     * @param txtProdDes
     * @param txtFecVenc
     * @param txtProdUni
     * @param txtProdProv
     */
    @Override
    public void cargar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, 
            JDateChooser txtFecVenc, JTextField txtProdUni, JTextField txtProdProv)
    {
        // Solicita el proveedor al servidor
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
        
        Date fecha = new Date(producto.get(0).getFec_venc());
        txtFecVenc.setDate(fecha);
        
        txtProdUni.setText(producto.get(0).getCodigo_uni());
        txtProdProv.setText(producto.get(0).getCodigo_prov());
               
        
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
