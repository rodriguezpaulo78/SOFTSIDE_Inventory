/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

/**
 * Interfaz del controlador de la vista del Login
 * @author SOFTSIDE
 */
public interface ILogin
{
    public void logIn(String usuario, String password);
    public void getJsonResponse(String json);
    public void salir(); 
}
