package softside_inventory.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Inventario_Cabecera;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;

/**
 * Clase de consultas
 * 
 * Incluye las consultas especificas del sistema. 
 * 
 * @author SOFTSIDE
 */

public class Consultas
{
    public static ArrayList<Inventario_Cabecera> existenciaProducto(String codigoProducto)
    {
        ArrayList<Inventario_Cabecera> existencias = new ArrayList<Inventario_Cabecera>();   
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 4);
        jsonObj.put("productoCod", codigoProducto);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_CABECERA, json);
        
        // PROCESAR RESPUEST
        Object jsonObject =JSONValue.parse(response);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        Inventario_Cabecera inv = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            inv = new Inventario_Cabecera();
            JSONObject row =(JSONObject)array.get(i);
            inv.setProCod(row.get("producto_id").toString());
            inv.setAlmNom(row.get("inv_cab_almacen").toString());
            inv.setCantidad(row.get("inv_cab_cant").toString());
           
            existencias.add(inv);
            inv = null;
        }
        
        return existencias;
    }
    
    public static String existenciaTotal(String codigoProducto)
    {
        String total = "0.00";
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("productoCod", codigoProducto);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_CABECERA, json);
        
        // PROCESAR RESPUESTA
        Object jsonObject = JSONValue.parse(response);
        JSONObject row =(JSONObject) jsonObject;
        
        total = row.get("SUM(inv_cab_cant)").toString();
        
        return total;
    }
    
    /*public static ArrayList<ArrayList<String>> entradas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> entradas = new ArrayList<>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProEntMes WHERE ProCod = ? AND KarDetAnio = ? AND KarDetMes = ?", new String[] {codigoProducto, anio, mes}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String almCod = resultado.getString("AlmCod");
                String almNom = resultado.getString("AlmNom");
                String cantidad = resultado.getString("KarDetCan");
                String dia = resultado.getString("KarDetDia");
                
                data.add(dia);
                data.add(almCod);
                data.add(almNom);
                data.add(cantidad);
                
                entradas.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return entradas;
    }
    
    public static ArrayList<ArrayList<String>> salidas(String codigoProducto, String anio, String mes)
    {
        ArrayList<ArrayList<String>> salidas = new ArrayList<>();       

        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_ProSalMes WHERE ProCod = ? AND KarDetAnio = ? AND KarDetMes = ? ORDER BY KarDetDia", new String[] {codigoProducto, anio, mes}, true);
            while(resultado.next())
            {
                ArrayList<String> data = new ArrayList<>();
                String almCod = resultado.getString("AlmCod");
                String almNom = resultado.getString("AlmNom");
                String cantidad = resultado.getString("KarDetCan");
                String dia = resultado.getString("KarDetDia");
                
                data.add(dia);
                data.add(almCod);
                data.add(almNom);
                data.add(cantidad);
                
                salidas.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return salidas;
    }
    
    public static ArrayList<String> getAnioEntrada(String producto)
    {
        ArrayList<String> anios = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetAnio FROM VI_ProEntMes WHERE ProCod = ? GROUP BY KarDetAnio", new String[] {producto}, true);
            while(resultado.next())
            {
                anios.add(resultado.getString("KarDetAnio"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return anios;
    }
    
    public static ArrayList<String> getMesEntrada(String producto, String anio)
    {
        ArrayList<String> meses = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetMes FROM VI_ProEntMes WHERE ProCod = ? AND KarDetAnio = ? GROUP BY KarDetMes", new String[] {producto, anio}, true);
            while(resultado.next())
            {
                meses.add(resultado.getString("KarDetMes"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return meses;
    }
    
    public static ArrayList<String> getAnioSalida(String producto)
    {
        ArrayList<String> anios = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetAnio FROM VI_ProSalMes WHERE ProCod = ? GROUP BY KarDetAnio", new String[] {producto}, true);
            while(resultado.next())
            {
                anios.add(resultado.getString("KarDetAnio"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return anios;
    }
    
    public static ArrayList<String> getMesSalida(String producto, String anio)
    {
        ArrayList<String> meses = new ArrayList<>();       
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT KarDetMes FROM VI_ProSalMes WHERE ProCod = ? AND KarDetAnio = ? GROUP BY KarDetMes;", new String[] {producto, anio}, true);
            while(resultado.next())
            {
                meses.add(resultado.getString("KarDetMes"));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return meses;
    }
    */
}
