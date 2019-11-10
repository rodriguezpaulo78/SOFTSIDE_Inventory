/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.util;

/**
 * 
 * @author SOFTSIDE
 */
public class Session {
    public static String USER_ID;
    public static String USER_TIPO;
    public static String MESSAGE;
    public static String USER_NAME;
    public static String USER_DNI;
    
    /**
     * Reinicia los valores de la sesión 
     */
    public static void remove() {
        Session.USER_ID = "";
        Session.USER_TIPO = "";
        Session.MESSAGE = "";
        Session.USER_NAME = "";
        Session.USER_DNI = "";
    }
    
    /**
     * Retorna el TIPO de usuario
     * @return USER_TIPO 
     */
    public String getTipo(){
        return USER_TIPO;
    }
    
    /**
     * Retorna el NOMBRE de usuario
     * @return USER_NAME
     */
    public String getName(){
        return USER_NAME;
    }
    
    /**
     * Retorna el DNI de usuario
     * @return USER_DNI
     */
    public String getDni(){
        return USER_DNI;
    }
    
}
