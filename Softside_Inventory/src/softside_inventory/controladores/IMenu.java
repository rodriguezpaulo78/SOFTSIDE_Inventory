/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author SOFTSIDE
 */
public interface IMenu {
    
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida);
    public void cerrarSesion(); 
    public void proveedor();
    public void usuario();
    public void unidad();
    public void producto();
    public void inventario();
    public void existenciaProducto();
    public void salida();
    public void entrada();
}
