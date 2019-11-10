/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.util;

/**
 *
 * @author Stephany
 */
public class Session {
    public static String USER_ID;
    public static String USER_TIPO;
    public static String MESSAGE;
    public static String USER_NAME;
    public static String USER_DNI;
    
    public static void remove() {
        Session.USER_ID = "";
        Session.USER_TIPO = "";
        Session.MESSAGE = "";
        Session.USER_NAME = "";
        Session.USER_DNI = "";
    }
    
    public String getTipo(){
        return USER_TIPO;
    }
    public String getName(){
        return USER_NAME;
    }
    public String getDni(){
        return USER_DNI;
    }
    
}
