/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores.unidad;

import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de unidad
 * 
 * Metodos disponibles para el controlador de gestion de unidad
 *  
 * @author SOFTSIDE
 */
public interface IVistaUnidad {
    
    public void menu();
    public void registrar();
    public void cargar(JTable tablaUnidad);    
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
    public void buscarUnidad( JTextField buscar, JTable tablaUnidad);
    public void seleccionarFila(JTextField buscar, JTable tablaUnidad);
    
}
