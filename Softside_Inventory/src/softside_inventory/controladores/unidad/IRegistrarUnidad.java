/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores.unidad;

import javax.swing.JTextField;

/**
 * Interfaz de la insercion de unidad
 * 
 * Metodos disponibles para el controlador de insercion de unidad
 *  
 * @author SOFTSIDE
 */
public interface IRegistrarUnidad {
    
    public void cargar(JTextField txtUniCod);
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
    public void cancelar();
    
}
