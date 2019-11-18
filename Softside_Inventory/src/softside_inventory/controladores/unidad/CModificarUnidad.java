package softside_inventory.controladores.unidad;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Unidad;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.unidad.ModificarUnidad;

/**
 * Controlador de la modificacion de Unidad
 * 
 * Carga datos de la Unidad seleccionada, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarUnidad implements IModificarUnidad
{
    private ModificarUnidad ventana;
    private Unidad u;
    private Session user; 
    private String codigo;
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarUnidad(Session user, String codigo)
    {
        //u = Unidad.buscar(codigo);
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarUnidad(this);

    }
    
    /**
     * Retorna a la ventana de Vista de Unidad
     */
    @Override
    public void cancelar()
    {
        new CVistaUnidad(user);
        ventana.dispose();
    }
    
    /**
     * Actualiza la interfaz con los datos de la Unidad registrada a modificar
     * @param txtProvCod
     * @param txtProvRazSoc
     * @param txtProvNomRep
     * @param txtProvRuc
     * @param txtProvRub
     * @param txtProvTel
     */
    @Override
    public void cargar( JTextField txtUniCod, JTextField txtUniDes)
    {
        // Solicita la Unidad al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", codigo);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        ArrayList<Unidad> unidad = getUsersJSON(response);
        
        txtUniCod.setText(unidad.get(0).getCodigo());
        txtUniDes.setText(unidad.get(0).getDescripcion());
               
        
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Unidad>
     */
    private ArrayList<Unidad> getUsersJSON(String json){
        Object jsonObject =JSONValue.parse(json);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();
        Unidad u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Unidad();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("uni_id").toString());
            u.setDescripcion(row.get("uni_descripcion").toString());
            u.setEstado(row.get("uni_est_reg").toString());
                     
            unidades.add(u);
            u = null;
        }
        return unidades;
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
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes)
    {
        
        Unidad u = new Unidad();
        u.setCodigo(txtUniCod.getText());
        u.setDescripcion(txtUniDes.getText());
   
                       
        String json = u.toJSON(3);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
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
            JOptionPane.showMessageDialog(null, "Unidad modificado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaUnidad(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
