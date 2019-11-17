package softside_inventory.controladores.proveedor;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Proveedor;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.proveedor.RegistrarProveedor;

/**
 * Controlador de la insercion de proveedor
 * 
 * Recibe y valida datos sobre un nuevo registro de proveedor
 *  
 * @author SOFTSIDE
 */

public class CRegistrarProveedor implements IRegistrarProveedor
{
    private RegistrarProveedor ventana;
    private Session user; 
    
    /**
     * Constructor de Clase
     * @param user : sesión de usuario logeado
     */
    public CRegistrarProveedor(Session user)
    {
        this.user = user;
        ventana = new RegistrarProveedor(this);
    }
    
    /**
     * Retorna a la ventana de Vista de Proveedores
     */
    @Override
    public void cancelar()
    { 
        new CVistaProveedor(user);
        ventana.dispose();
        
    }
    
    /**
     * Actualiza la interfaz con el codigo del Proveedor
     * @param txtProvCod
     */
    @Override
    public void cargar(JTextField txtProvCod)
    {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        String cod = getJsonCod(response);
        txtProvCod.setText(cod);
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
                       
        String json = u.toJSON(2);
        
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
            JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaProveedor(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
