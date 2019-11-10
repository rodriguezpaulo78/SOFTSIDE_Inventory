/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory;
        
import java.io.IOException;
import softside_inventory.controladores.CLogin;
import softside_inventory.vistas.InicioSesion;
/**
 *
 * @author LENOVO
 */
public class SOFTSIDE_Inventory {
    
    public SOFTSIDE_Inventory(){
        new CLogin();
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new SOFTSIDE_Inventory();
    }
}
        
    
    


