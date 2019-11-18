/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import softside_inventory.controladores.usuario.CVistaUsuario;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import softside_inventory.controladores.producto.CVistaProducto;
import softside_inventory.controladores.proveedor.CVistaProveedor;
import softside_inventory.controladores.unidad.CVistaUnidad;
import softside_inventory.util.Session;
import softside_inventory.vistas.MenuPrincipal;

/**
 *
 * @author LENOVO
 */
public class CMenu implements IMenu{
    
    private MenuPrincipal ventana;
    private Session user;
    
    /**
     * Controlador de MenuPrincipal del Sistema
     * @param session: la sesión logeada en el Sistema
     */
    public CMenu(Session session)
    {
        this.user = session;
        ventana = new MenuPrincipal(this);
    }
    
    @Override
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
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida)
    {
        txtNombre.setText(user.getTipo() + " " + user.getName());
        txtDni.setText("DNI Nº " + user.getDni());
        String permiso = "";
        if(user.getTipo().equals("administrador")){
            permiso = "Administrador";
        }else{
            permiso = "Usuario";
        }
        lblPermisos.setText(permiso);
        if(user.getTipo().equals("usuario"))
        {
            btnUsuario.setEnabled(false);
            btnExistencia.setEnabled(false);
            btnEntrada.setEnabled(false);
            btnSalida.setEnabled(false);
        }
    }
    
    @Override
    /**
     * Acceso a la ventana de Gestión de Proveedor.
     */
    public void proveedor()
    {
        new CVistaProveedor(user);
        ventana.dispose();
    }
    
    @Override
     /**
     * Cierra la sesión y redirige a la ventana de LOGIN
     */
    public void cerrarSesion()
    {
        new CLogin();
        ventana.dispose();
    }
    
    @Override
     /**
     * Acceso a la ventana de Gestión de Usuario.
     */
    public void usuario()
    {
        
        new CVistaUsuario(user);
        ventana.dispose();
    }
    
    @Override
     /**
     * Acceso a la ventana de Gestión de Unidad.
     */
    public void unidad()
    {
        new CVistaUnidad(user);
        ventana.dispose();
    }
   
    @Override
     /**
     * Acceso a la ventana de Gestión de Producto.
     */
    public void producto()
    {
        new CVistaProducto(user);
        ventana.dispose();
    }
    
    @Override
     /**
     * Acceso a la ventana de Gestión de Inventario.
     */
    public void inventario()
    {
        //new CInventario();
        ventana.dispose();
    }

    @Override
    /**
     * Acceso a la ventana de Reporte de Existencia de Productos.
     */
    public void existenciaProducto()
    {
        //new CExistencia();
        ventana.dispose();
    }

    @Override
    /**
     * Acceso a la ventana de Reporte de Salida de Productos.
     */
    public void salida()
    {
        //new CSalida();
        ventana.dispose();
    }

    @Override
    /**
     * Acceso a la ventana de Reporte de Entrada de Productos.
     */
    public void entrada()
    {
        //new CEntrada();
        ventana.dispose();
    }
}
