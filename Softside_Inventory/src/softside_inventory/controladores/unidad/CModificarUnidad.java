/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Stephany
 */
public class CModificarUnidad implements IModificarUnidad{
    
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
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarUnidad(this);

    }

    @Override
    public void cargar(JTextField txtUniCod, JTextField txtUniDes) {
        // Solicita el proveedor al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", codigo);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        ArrayList<Unidad> unidades = getUnidadesJSON(response);
        
        txtUniCod.setText(unidades.get(0).getCodigo());
        txtUniDes.setText(unidades.get(0).getDescripcion());
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Proveedor>
     */
    private ArrayList<Unidad> getUnidadesJSON(String json){
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

    @Override
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes) {
        Unidad u = new Unidad();
        u.setCodigo(txtUniCod.getText());
        u.setDescripcion(txtUniDes.getText());
                       
        String json = u.toJSON(3);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        getJsonRespUnid(response);
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonRespUnid(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Unidad modificada exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaUnidad(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cancelar() {
        new CVistaUnidad(user);
        ventana.dispose();
    }
}
