/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author SOFTSIDE
 */
public class HttpNetTask {
    
    /**
     * Recibe la direccion url y los datos que enviara
     * @param url
     * @param json
     * @return String response
     */
    public String sendPost(String url, String json){
        StringBuilder response = new StringBuilder();
        
        try {
            //Creamos un nuevo objeto URL con la url donde queremos enviar el JSON
            URL obj = new URL(url);
            //Creamos un objeto de conexión
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //Añadimos la cabecera
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // Enviamos los datos por POST
            con.setDoOutput(true);
            con.setDoInput(true);
            
            OutputStream wr = con.getOutputStream();
            wr.write(json.getBytes());
            //wr.flush();
            wr.close();
            
            //Capturamos la respuesta del servidor
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //Mostramos la respuesta del servidor por consola
            System.out.println(response.toString());
            //cerramos la conexión
            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return response.toString();
    }
    
}
