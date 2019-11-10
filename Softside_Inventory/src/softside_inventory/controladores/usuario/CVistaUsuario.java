package softside_inventory.controladores.usuario;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Usuario;
import softside_inventory.util.Session;
import softside_inventory.vistas.usuario.VistaUsuario;

/**
 * Controlador de la gestion de usuario
 * 
 * Carga los usuarios existentes con sus datos, además de controlar el
 * redireccionamiento hacia las ventanas de insercion o modificacion.
 * La funcion eliminar es realizada aqui.
 *  
 * @author SOFTSIDE
 */
public class CVistaUsuario implements IVistaUsuario
{
    private VistaUsuario ventana;
    private ArrayList<Usuario> usuarios;
    private Session user; 
    
    /**
     * Constructor
     * @param user : sesión de usuario
     */
    public CVistaUsuario(Session user)
    {
        //usuarios = Usuario.getLista();
        ventana = new VistaUsuario(this);
        this.user = user;
    }
    
    /**
     * Retorna a la ventana de Menu Principal.
     */
    @Override
    public void menu()
    {
        new CMenu(user);
        ventana.dispose();
    }
    
    /**
     * Acceso a la ventana de Registro de usuario.
     */
    @Override
    public void registrar()
    {
       
        new CRegistrarUsuario(user);
        ventana.dispose();
        
    }
    
     /**
     * Actualiza la interfaz con la carga de usuarios registrados
     * @param tblRegistros
     */
    @Override
    public void cargar(JTable tblRegistros)
    {
        /*
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        String permiso = "";
        String estado = "";
        for(int i = 0; i < usuarios.size(); i++)
        {
            
            if(usuarios.get(i).getUsrPer().equals("1"))
                permiso = "Administrador";
            else
                permiso = "Usuario";
            
            if(usuarios.get(i).getUsrEstReg().equals("1"))
                estado = "A";
            else
                estado = "*";
            model.addRow(new Object[]{  usuarios.get(i).getUsrCod(),
                                        usuarios.get(i).getUsrIde(),
                                        usuarios.get(i).getUsrDni(),
                                        usuarios.get(i).getUsrNom(),
                                        usuarios.get(i).getUsrApe(),
                                        permiso,
                                        estado});
            
        }
        */
    }
    
    /**
     * Acceso a la ventana de Modificación de usuario.
     */
    @Override
    public void modificar(JTable tblRegistros)
    {
        //Solo para probar vista abajo es el controlador con este incluido
        CModificarUsuario modificar = new CModificarUsuario(user,"codigoejemplo");
        ventana.dispose();
        
        /*
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Usuario u = usuarios.get(i);
            CModificarUsuario modificar;
            
            if(true)
            //if(u.getUsrEstReg().equals("1"))
            {
                //modificar = new CModificarUsuario(u.getUsrCod());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }
    
    /**
     * Realiza la eliminación del registro de usuario seleccionado en la tabla
     * @param tblRegistros
     */
    @Override
    public void eliminar(JTable tblRegistros)
    {
        /*
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Usuario u = usuarios.get(i);
            
            if(!u.getUsrEstReg().equals("3"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    u.eliminar();
                    model.setValueAt("*", i, 6);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }
    
    /**
     * 
     * Realiza búsquedas de usuario y los muestra en la interfaz
     * @param buscar
     * @param tablaProducto
    */
    @Override
    public void buscarUsuario( JTextField buscar, JTable tablaProducto)
    {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tablaProducto.getModel();
        String filtro = "Nombre";
        
        int i;
        int column = tableModel.getColumnCount();
        
	for(i = 0; i < column; i++)
        {
            if(filtro.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        }
        int row = tableModel.getRowCount();
        for(int k = 0; k < row; k++)
        {
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
        }
    }
    
    /**
     * 
     * Selecciona la búsqueda realizada en la tabla de la interfaz
     * @param buscar
     * @param tablaProducto
    */
    @Override
    public void seleccionarFila(JTextField buscar, JTable tablaProducto)
    {
        TableModel tableModel = tablaProducto.getModel();
        String dato = buscar.getText();
        String filtro = "Nombre";
        int col;
		int column = tableModel.getColumnCount();
        for(col = 0; col < column; col++)
            if(filtro.compareTo(tableModel.getColumnName(col)) == 0)
                break;
        int row;
        try
        {
			int rowC = tableModel.getRowCount();
            for(row = 0; row < rowC; row++)
                if(dato.compareTo((String) tableModel.getValueAt(row, col)) == 0)
                    break;

            if(row == 0)
                tablaProducto.changeSelection(0,0,false,true);
            else
                tablaProducto.getSelectionModel().setSelectionInterval(row - 1, row);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
