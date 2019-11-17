package softside_inventory.controladores.proveedor;

import softside_inventory.controladores.usuario.*;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Proveedor;
import softside_inventory.modelos.Usuario;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.proveedor.VistaProveedor;
import softside_inventory.vistas.usuario.VistaUsuario;

/**
 * Controlador de la gestion de Proveedor
 * 
 * Carga los Proveedores existentes con sus datos, además de controlar el
 * redireccionamiento hacia las ventanas de insercion o modificacion.
 * La funcion eliminar es realizada aqui.
 *  
 * @author SOFTSIDE
 */
public class CVistaProveedor implements IVistaProveedor
{
    private VistaProveedor ventana;
    private ArrayList<Proveedor> proveedores;
    private Session user; 
    
    /**
     * Constructor
     * @param user : sesión de Proveedor
     */
    public CVistaProveedor(Session user)
    {
        ventana = new VistaProveedor(this);
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
     * Acceso a la ventana de Registro de Proveedor.
     */
    @Override
    public void registrar()
    {
       
        new CRegistrarProveedor(user);
        ventana.dispose();
        
    }
    
     /**
     * Actualiza la interfaz con la carga de Proveedor registrados
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
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        proveedores = getProveedoresJSON(response);
                
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < proveedores.size(); i++){
            model.addRow(new Object[]{  proveedores.get(i).getCodigo(),
                                        proveedores.get(i).getRaz_soc(),
                                        proveedores.get(i).getNombre_rep(),
                                        proveedores.get(i).getRuc(),
                                        proveedores.get(i).getRubro(),
                                        proveedores.get(i).getTelefono(),
                                        proveedores.get(i).getEstado()});
            
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Proveedor>
     */
    private ArrayList<Proveedor> getProveedoresJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Proveedor u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            u = new Proveedor();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prov_id").toString());
            u.setRaz_soc(row.get("prov_raz_soc").toString());
            u.setNombre_rep(row.get("prov_nombre_rep").toString());
            u.setRuc(row.get("prov_ruc").toString());
            u.setRubro(row.get("prov_rubro").toString());
            u.setTelefono(row.get("prov_telefono").toString());
            u.setEstado(row.get("prov_est_reg").toString());
           
            proveedores.add(u);
            u = null;
        }
        return proveedores;
    }
    
    /**
     * Acceso a la ventana de Modificación de Proveedor.
     */
    @Override
    public void modificar(JTable tblRegistros) {        
        int i = tblRegistros.getSelectedRow();
        if(i != -1) {
            Proveedor u = proveedores.get(i);
            CModificarProveedor modificar;
            
            if(u.getEstado().equals("A")){
                modificar = new CModificarProveedor(user, u.getCodigo());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Realiza la eliminación del registro de Proveedor seleccionado en la tabla
     * @param tblRegistros
     */
    @Override
    public void eliminar(JTable tblRegistros) {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Proveedor u = proveedores.get(i);
            
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
                    String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
                    getJsonDeleteProveedor(response);
                    u.setEstado("I");
                    
                    model.setValueAt("I", i, 8);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El Proveedor ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Proveedor a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonDeleteProveedor(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Proveedor eliminado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     * Realiza búsquedas de usuario y los muestra en la interfaz
     * @param buscar
     * @param tblRegistros
     * @param jbcBuscar
    */
    @Override
    public void buscarProveedor( JTextField buscar, JTable tblRegistros, JComboBox jbcBuscar)
    {       
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( buscar );
        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
        TableModel tableModel = tblRegistros.getModel();
        String filtro = jbcBuscar.getSelectedItem().toString();
        
        int i;
        int column = tableModel.getColumnCount();
        
	for(i = 0; i < column; i++)
        {
            if(filtro.equals(tableModel.getColumnName(i)))
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
     * @param tblRegistros
     * @param jbcBuscar
    */
    @Override
    public void seleccionarFila(JTextField buscar, JTable tblRegistros, JComboBox jbcBuscar)
    {
        TableModel tableModel = tblRegistros.getModel();
        String dato = buscar.getText();
        String filtro = jbcBuscar.getSelectedItem().toString();
        
        // Enviar los datos de búsqueda al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 7);
        jsonObj.put("dato", dato);
        jsonObj.put("filtro", filtro);

        String json = jsonObj.toString();

        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
        
        ArrayList<Proveedor> u = getJsonSearchUser(response);
        
        if (u.size() > 0) {
            int col = 0;

            int row;
            try {
                int rowC = tableModel.getRowCount();

                for (row = 0; row < rowC; row++) {
                    if (u.get(0).getCodigo().compareTo((String) tableModel.getValueAt(row, col)) == 0) {
                        break;
                    }
                }

                if (row == 0) {
                    tblRegistros.changeSelection(0, 0, false, true);
                } else {
                    tblRegistros.getSelectionModel().setSelectionInterval(row - 1, row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Proveedor>
     */
    private ArrayList<Proveedor> getJsonSearchUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Proveedor u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);            
            u = new Proveedor();
            u.setCodigo(row.get("prov_id").toString());
           
            proveedores.add(u);
            u = null;
        }
        return proveedores;
    }
}
