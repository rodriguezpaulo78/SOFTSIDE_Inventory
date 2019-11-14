package softside_inventory.controladores.usuario;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Usuario;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
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
        // Solicitar lista de Usuarios al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 4);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.USUARIOS, json);
        
        usuarios = getUsersJSON(response);
                
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < usuarios.size(); i++){
            model.addRow(new Object[]{  usuarios.get(i).getCodigo(),
                                        usuarios.get(i).getUsername(),
                                        usuarios.get(i).getDNI(),
                                        usuarios.get(i).getNombres(),
                                        usuarios.get(i).getApellidos(),
                                        usuarios.get(i).getFecha_nac(),
                                        usuarios.get(i).getCargo(),
                                        usuarios.get(i).getTipo(),
                                        usuarios.get(i).getEstado()});
            
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Usuario>
     */
    private ArrayList<Usuario> getUsersJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        Usuario u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            u = new Usuario();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("user_id").toString());
            u.setNombres(row.get("user_nombres").toString());
            u.setApellidos(row.get("user_apellidos").toString());
            u.setDNI(row.get("user_dni").toString());
            u.setFecha_nac(row.get("user_fec_nac").toString());
            u.setCargo(row.get("user_cargo").toString());
            u.setUsername(row.get("user_username").toString());
            u.setTipo(row.get("user_tipo_user").toString());
            u.setEstado(row.get("user_est_reg").toString());
           
            users.add(u);
            u = null;
        }
        return users;
    }
    
    /**
     * Acceso a la ventana de Modificación de usuario.
     */
    @Override
    public void modificar(JTable tblRegistros)
    {        
        int i = tblRegistros.getSelectedRow();
        if(i != -1) {
            Usuario u = usuarios.get(i);
            CModificarUsuario modificar;
            
            if(u.getEstado().equals("A")){
                modificar = new CModificarUsuario(user, u.getCodigo());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Realiza la eliminación del registro de usuario seleccionado en la tabla
     * @param tblRegistros
     */
    @Override
    public void eliminar(JTable tblRegistros)
    {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Usuario u = usuarios.get(i);
            
            if(u.getEstado().equals("A"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    
                    // Enviar codigo del usuario al servidor
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("metodo", 6);
                    jsonObj.put("codigo", u.getCodigo());
                    
                    String json = jsonObj.toString();
                    
                    HttpNetTask httpConnect = new HttpNetTask();
                    String response = httpConnect.sendPost(HostURL.USUARIOS, json);
                    getJsonDeleteUser(response);
                    u.setEstado("I");
                    
                    model.setValueAt("I", i, 8);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El usuario ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un usuario a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonDeleteUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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
        
        /*TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
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
        }*/
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
