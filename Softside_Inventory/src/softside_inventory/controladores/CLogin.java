/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;

/**
 *
 * @author Stephany
 */
public class CLogin {
    
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
    
    private void getJsonResponse(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        Session.MESSAGE = row.get("message").toString();
        Session.USER_ID = row.get("user_id").toString();
        Session.USER_TIPO = row.get("user_tipo_user").toString();
    }
}
