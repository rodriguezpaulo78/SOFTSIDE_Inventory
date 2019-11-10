/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Interfaz del controlador de la vista del Login
 * 
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */
public interface ILogin
{
    public void logIn(String usuario, String password);
    public void getJsonResponse(String json);
    
    /**
    * Sale del software.
    */
    public void salir();
    
    
}
