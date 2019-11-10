package softside_inventory.controladores.usuario;

import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de usuario
 * 
 * Metodos disponibles para el controlador de gestion de usuario
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IVistaUsuario
{
    public void menu();
    public void registrar();
    public void cargar(JTable tblRegistros);
    public void modificar(JTable tblRegistros);
    public void eliminar(JTable tblRegistros);
    public void buscarUsuario( JTextField buscar, JTable tablaProducto);
    public void seleccionarFila(JTextField buscar, JTable tablaProducto);
}
