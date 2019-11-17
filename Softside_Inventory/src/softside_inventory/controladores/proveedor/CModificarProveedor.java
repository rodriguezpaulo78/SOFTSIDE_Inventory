package softside_inventory.controladores.proveedor;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Proveedor;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.proveedor.ModificarProveedor;

/**
 * Controlador de la modificacion de proveedor
 * 
 * Carga datos del proveedor seleccionado, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarProveedor implements IModificarProveedor
{
    private ModificarProveedor ventana;
    private Proveedor u;
    private Session user; 
    private String codigo;
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarProveedor(Session user, String codigo)
    {
        //u = Proveedor.buscar(codigo);
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarProveedor(this);

    }
    
    /**
     * Retorna a la ventana de Vista de Proveedor
     */
    @Override
    public void cancelar()
    {
        new CVistaProveedor(user);
        ventana.dispose();
    }
    
    /**
     * Actualiza la interfaz con los datos del de proveedor registrado a modificar
     * @param txtProvCod
     * @param txtProvRazSoc
     * @param txtProvNomRep
     * @param txtProvRuc
     * @param txtProvRub
     * @param txtProvTel
     */
    @Override
    public void cargar( JTextField txtProvCod, JTextField txtProvRazSoc, JTextField txtProvNomRep, 
            JTextField txtProvRuc, JTextField txtProvRub, JTextField txtProvTel)
    {
        // Solicita el proveedor al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", codigo);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        ArrayList<Proveedor> proveedor = getUsersJSON(response);
        
        txtProvCod.setText(proveedor.get(0).getCodigo());
        txtProvRazSoc.setText(proveedor.get(0).getRaz_soc());
        txtProvNomRep.setText(proveedor.get(0).getNombre_rep());
        txtProvRuc.setText(proveedor.get(0).getRuc());
        txtProvRub.setText(proveedor.get(0).getRubro());
        txtProvTel.setText(proveedor.get(0).getTelefono());
               
        
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Proveedor>
     */
    private ArrayList<Proveedor> getUsersJSON(String json){
        Object jsonObject =JSONValue.parse(json);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Proveedor u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Proveedor();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prov_id").toString());
            u.setRaz_soc(row.get("prov_raz_soc").toString());
            u.setNombre_rep(row.get("prov_nombre_rep").toString());
            u.setRuc(row.get("prov_ruc").toString());
            u.setRubro(row.get("prov_rubro").toString());
            u.setTelefono(row.get("prov_telefono").toString());
            u.setEstado(row.get("prov_est_reg").toString());
                     
            proveedores.add(u);
            u = null;
        }
        return proveedores;
    }
    
     /**
     * Hace la validación de los campos ingresados en la interfaz
     * @param txtProvCod
     * @param txtProvRazSoc
     * @param txtProvNomRep
     * @param txtProvRuc
     * @param txtProvRub
     * @param txtProvTel
     */
    @Override
    public void aceptar(JTextField txtProvCod, JTextField txtProvRazSoc, JTextField txtProvNomRep, 
            JTextField txtProvRuc, JTextField txtProvRub, JTextField txtProvTel)
    {
        
        Proveedor u = new Proveedor();
        u.setCodigo(txtProvCod.getText());
        u.setRaz_soc(txtProvRazSoc.getText());
        u.setNombre_rep(txtProvNomRep.getText());
        u.setRuc(txtProvRuc.getText());
        u.setRubro(txtProvRub.getText());
        u.setTelefono(txtProvTel.getText());
                       
        String json = u.toJSON(3);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
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
            JOptionPane.showMessageDialog(null, "Proveedor modificado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaProveedor(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
