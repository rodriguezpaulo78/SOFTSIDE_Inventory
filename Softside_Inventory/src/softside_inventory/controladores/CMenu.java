/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import softside_inventory.util.Session;
import softside_inventory.vistas.MenuPrincipal;

/**
 *
 * @author LENOVO
 */
public class CMenu implements IMenu{
    
    private MenuPrincipal ventana;
    Session user;
    
    public CMenu(Session session)
    {
        this.user = session;
        ventana = new MenuPrincipal(this);
    }
    
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
    
    public void proveedor()
    {
        //new CProveedor();
        ventana.dispose();
    }
    
    @Override
    public void cerrarSesion()
    {
        new CLogin();
        ventana.dispose();
    }
    
    @Override
    public void usuario()
    {
        //new CUsuario();
        ventana.dispose();
    }
    
    @Override
    public void unidad()
    {
        //new CUnidad();
        ventana.dispose();
    }
   
    
    @Override
    public void producto()
    {
        //new CProducto();
        ventana.dispose();
    }
    
    public void inventario()
    {
        //new CInventario();
        ventana.dispose();
    }

    @Override
    public void existenciaProducto()
    {
        //new CExistencia();
        ventana.dispose();
    }

    @Override
    public void salida()
    {
        //new CSalida();
        ventana.dispose();
    }

    @Override
    public void entrada()
    {
        //new CEntrada();
        ventana.dispose();
    }
}
