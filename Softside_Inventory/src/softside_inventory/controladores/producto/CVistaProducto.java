package softside_inventory.controladores.producto;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
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
import softside_inventory.modelos.Producto;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.producto.VistaProducto;

/**
 * Controlador de la gestion de Producto
 * 
 * Carga los Productos existentes con sus datos, además de controlar el
 * redireccionamiento hacia las ventanas de insercion o modificacion.
 * La funcion eliminar es realizada aqui.
 *  
 * @author SOFTSIDE
 */
public class CVistaProducto implements IVistaProducto
{
    private VistaProducto ventana;
    private ArrayList<Producto> productos;
    private Session user; 
    
    /**
     * Constructor
     * @param user : sesión de Usuario logeado
     */
    public CVistaProducto(Session user)
    {
        ventana = new VistaProducto(this);
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
     * Acceso a la ventana de Registro de Producto.
     */
    @Override
    public void registrar()
    {
       
        new CRegistrarProducto(user);
        ventana.dispose();
        
    }
    
     /**
     * Actualiza la interfaz con la carga de Productos registrados
     * @param tblRegistros
     */
    @Override
    public void cargar(JTable tblRegistros)
    {
        // Solicitar lista de Productos al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 4);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        productos = getProductosJSON(response);
                
        DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
        model.setRowCount(0);
        
        for(int i = 0; i < productos.size(); i++){
            model.addRow(new Object[]{  productos.get(i).getCodigo(),
                                        productos.get(i).getNombre(),
                                        productos.get(i).getDescripcion(),
                                        productos.get(i).getCodigo_uni(),
                                        productos.get(i).getFec_venc(),
                                        productos.get(i).getCodigo_prov(),
                                        productos.get(i).getEstado()});
            
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Producto>
     */
    private ArrayList<Producto> getProductosJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            u = new Producto();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prod_id").toString());
            u.setNombre(row.get("prod_nombre").toString());
            u.setDescripcion(row.get("prod_descripcion").toString());
            u.setCodigo_uni(row.get("unidad_id").toString());
            u.setFec_venc(row.get("prod_fec_venc").toString());
            u.setCodigo_prov(row.get("proveedor_id").toString());
            u.setEstado(row.get("prod_est_reg").toString());
           
            productos.add(u);
            u = null;
        }
        return productos;
    }
    
    /**
     * Acceso a la ventana de Modificación de Producto.
     */
    @Override
    public void modificar(JTable tblRegistros) {        
        int i = tblRegistros.getSelectedRow();
        if(i != -1) {
            Producto u = productos.get(i);
            CModificarProducto modificar;
            
            if(u.getEstado().equals("A")){
                modificar = new CModificarProducto(user, u.getCodigo());
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Realiza la eliminación del registro de Producto seleccionado en la tabla
     * @param tblRegistros
     */
    @Override
    public void eliminar(JTable tblRegistros) {
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Producto u = productos.get(i);
            
            if(u.getEstado().equals("A"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    
                    // Enviar codigo del Producto al servidor
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("metodo", 6);
                    jsonObj.put("codigo", u.getCodigo());
                    
                    String json = jsonObj.toString();
                    
                    HttpNetTask httpConnect = new HttpNetTask();
                    String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
                    getJsonDeleteProveedor(response);
                    u.setEstado("I");
                    
                    model.setValueAt("I", i, 6);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El Producto ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Producto a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
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
            JOptionPane.showMessageDialog(null, "Producto eliminado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     * Realiza búsquedas de Productos  y los muestra en la interfaz
     * @param buscar
     * @param tblRegistros
     * @param jbcBuscar
    */
    @Override
    public void buscarProducto( JTextField buscar, JTable tblRegistros, JComboBox jbcBuscar)
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
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        ArrayList<Producto> u = getJsonSearchUser(response);
        
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
     * @return ArrayList<Producto>
     */
    private ArrayList<Producto> getJsonSearchUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto u = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);            
            u = new Producto();
            u.setCodigo(row.get("prod_id").toString());
           
            productos.add(u);
            u = null;
        }
        return productos;
    }
}
