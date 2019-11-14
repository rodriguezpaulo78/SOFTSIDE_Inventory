package softside_inventory.controladores.usuario;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de usuario
 * 
 * Metodos disponibles para el controlador de gestion de usuario
 *  
 * @author SOFTSIDE
 */

public interface IVistaUsuario
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
    public void buscarUsuario( JTextField buscar, JTable tablaProducto, JComboBox jbcBuscar);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto);
}
