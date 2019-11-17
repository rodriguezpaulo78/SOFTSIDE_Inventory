/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores.unidad;

import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de unidad
 * 
 * Metodos disponibles para el controlador de modificacion de unidad
 *  
 * @author SOFTSIDE
 */

public interface IModificarUnidad {
    
    public void cargar(JTextField txtUniCod, JTextField txtUniDes);
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
    public void cancelar();
}
