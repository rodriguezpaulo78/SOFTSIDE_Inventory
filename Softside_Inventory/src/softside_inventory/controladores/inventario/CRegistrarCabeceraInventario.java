package softside_inventory.controladores.inventario;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Inventario_Cabecera;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.inventario.RegistrarCabeceraInventario;
import softside_inventory.modelos.Producto;

/**
 * Controlador de la insercion de kardex
 * 
 * Recibe datos sobre un nuevo registro de kardex.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CRegistrarCabeceraInventario implements IRegistrarCabeceraInventario
{
    private RegistrarCabeceraInventario ventana;
    ArrayList<Producto> productos;
    ArrayList<ArrayList<String>> almacenes;
    private Session user;
    
    public CRegistrarCabeceraInventario(Session user)
    {
        this.user = user;
        ventana = new RegistrarCabeceraInventario(this);
    }
    
    @Override
    public void cancelar()
    {
        new CVistaInventario(user);
        ventana.dispose();
    }
    
    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(productos.get(cbxProNom.getSelectedIndex()).getCodigo());
    }
    
    @Override
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom)
    {
        //txtAlmCod.setText(almacenes.get(cbxAlmNom.getSelectedIndex()).get(0));
    }
    
    @Override
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod)
    {
        // inv_cab_id, producto_id, inv_cab_almacen, inv_cab_est_reg
        Inventario_Cabecera invCab = new Inventario_Cabecera();
        invCab.setProCod(txtProCod.getText());
        invCab.setAlmNom("Tienda Cayma");
                       
        String json = invCab.toJSON(1);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_CABECERA, json);
        
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
            JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaInventario(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom)
    {
        cargarProductosActivos(cbxProNom);
    }
    
    public void cargarProductosActivos(JComboBox cbxProNom){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        productos = getProductosJSON(response);
        for(int i = 0; i < productos.size(); i++)
        {
            cbxProNom.insertItemAt(productos.get(i).getNombre(), i);
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Unidad>
     */
    private ArrayList<Producto> getProductosJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto p = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            p = new Producto();
            JSONObject row =(JSONObject)array.get(i);
            p.setCodigo(row.get("prod_id").toString());
            p.setNombre(row.get("prod_nombre").toString());
           
            productos.add(p);
            p = null;
        }
        return productos;
    }
}
