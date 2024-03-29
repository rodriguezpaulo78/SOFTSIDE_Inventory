package softside_inventory.controladores.producto;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de Producto
 * 
 * Metodos disponibles para el controlador de gestion de Producto
 *  
 * @author SOFTSIDE
 */

public interface IVistaProducto
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
    public void buscarProducto( JTextField buscar, JTable tablaProducto, JComboBox jbcBuscar);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto, JComboBox jbcBuscar);
}
