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
 * @author LENOVO
 */
public interface IMenu {
    
    /**
     * Carga datos del usuario que ingresó al sistema (Nombre, DNI, permisos)
     * Además a aquellos usuarios no administradores se deshabilita ciertas
     * opciones (Gestión de Usuarios, Consultas de existencia, entrada y salida).
     * 
     * @param txtNombre     Nombre del usuario
     * @param txtDni        DNI del usuario
     * @param lblPermisos   Permisos del usuario (Usuario o Administrador)
     * @param btnUsuario    Botón de gestión de usuario
     * @param btnExistencia Botón de consulta de existencia de productos
     * @param btnEntrada    Botón de consulta de entrada de productos
     * @param btnSalida     Botón de consulta de salida de productos
     */
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida);
    
    /**
     * Cierra sesión del usuario actual, y retorna a la ventana de Login.
     */
    public void cerrarSesion();
    
    /**
     * Acceso a la ventana de Gestión de Almacen.
     */
    public void proveedor();
    
    /**
     * Acceso a la ventana de Gestión de Usuario.
     */
    public void usuario();
    
    /**
     * Acceso a la ventana de Gestión de Unidad.
     */
    public void unidad();
    
    
    /**
     * Acceso a la ventana de Gestión de Producto.
     */
    public void producto();
    
    
    /**
     * Acceso a la ventana de Gestión de Kardex.
     */
    public void inventario();
    
    /**
     * Acceso a la ventana de Consulta de Existencia de Productos.
     */
    public void existenciaProducto();
    
    /**
     * Acceso a la ventana de Consulta de Salida de Productos.
     */
    public void salida();
    
    /**
     * Acceso a la ventana de Consulta de Entrada de Productos.
     */
    public void entrada();
}
