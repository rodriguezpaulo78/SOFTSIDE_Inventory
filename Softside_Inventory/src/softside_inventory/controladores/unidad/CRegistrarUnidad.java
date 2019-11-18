/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores.unidad;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Unidad;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.unidad.RegistrarUnidad;

/**
 *
 * @author Stephany
 */
public class CRegistrarUnidad implements IRegistrarUnidad {
    
    private RegistrarUnidad ventana;
    private Session user; 
    
    /**
     * Constructor de Clase
     * @param user : sesión de usuario logeado
     */
    public CRegistrarUnidad(Session user)
    {
        this.user = user;
        ventana = new RegistrarUnidad(this);
    }

    @Override
    public void cargar(JTextField txtUniCod) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.UNIDADES, json);
        
        String cod = getJsonCod(response);
        txtUniCod.setText(cod);
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

    @Override
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes) {
        
        Unidad u = new Unidad();
        u.setCodigo(txtUniCod.getText());
        u.setDescripcion(txtUniDes.getText());
                       
        String json = u.toJSON(2);
        
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
            JOptionPane.showMessageDialog(null, "Unidad registrada exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaUnidad(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cancelar() {
        new CVistaUnidad(user);
        ventana.dispose();
    }
}
