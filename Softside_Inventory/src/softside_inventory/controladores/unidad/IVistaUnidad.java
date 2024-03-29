package softside_inventory.controladores.unidad;

import softside_inventory.controladores.proveedor.*;
import softside_inventory.controladores.usuario.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de Unidad
 * 
 * Metodos disponibles para el controlador de gestion de Unidad
 *  
 * @author SOFTSIDE
 */

public interface IVistaUnidad
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
    public void buscarUnidad( JTextField buscar, JTable tablaProducto, JComboBox jbcBuscar);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto, JComboBox jbcBuscar);
}
