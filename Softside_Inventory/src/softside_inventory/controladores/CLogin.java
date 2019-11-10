/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.InicioSesion;

/**
 *
 * @author Stephany
 */
public class CLogin implements ILogin{
    
    private InicioSesion ventana;
    
    /**
     * Constructor
     */
    public CLogin()
    {
        this.ventana = new InicioSesion(this);
    }
    
    /**
     * Este método recibe los valores ingresados por el usuario en el formulario
     * Está encargado de verificar tales valores en la BD del Sistema
     * @param usuario
     * @param password
     */
    @Override
    public void logIn(String usuario, String password){
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("usuario", usuario);
        jsonObj.put("password", password);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INICIO_SESION, json);
        
        getJsonResponse(response);
    }
    
    @Override
    /**   
     * @param json
     * Este método recibe un objeto json que contendrá la respuesta a la validación del usuario
     * Si es correcta, asignara los valores de cada atributo del usuario al objeto Session y llamará a CMenu
     * Si no es correcta, mandará un mensaje al usuario
     */
    public void getJsonResponse(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        Session session = new Session();
        session.MESSAGE = row.get("message").toString();
        
        if (session.MESSAGE.equals("SUCCESS")){
            
            session.USER_ID = row.get("user_id").toString();
            session.USER_TIPO = row.get("user_tipo_user").toString();
            session.USER_NAME = row.get("user_nombres").toString();
            session.USER_DNI = row.get("user_dni").toString();
            
            new CMenu(session);
            ventana.dispose();
        } else {
            
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    /**
     * Salir del Sistema
     */
    public void salir()
    {
        ventana.dispose();
    }
}
