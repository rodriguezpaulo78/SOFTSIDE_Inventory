/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.controladores.unidad;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Unidad;
import softside_inventory.util.Session;
import softside_inventory.vistas.unidad.VistaUnidad;

/**
 *
 * @author Stephany
 */
public class CVistaUnidad implements IVistaUnidad{
    
    private VistaUnidad ventana;
    private ArrayList<Unidad> proveedores;
    private Session user;
    
    /**
     * Constructor
     * @param user : sesión de Usuario logeado
     */
    public CVistaUnidad(Session user)
    {
        ventana = new VistaUnidad(this);
        this.user = user;
    }

    @Override
    public void menu() {
        new CMenu(user);
        ventana.dispose();
    }

    @Override
    public void registrar() {
        new CRegistrarUnidad(user);
        ventana.dispose();
    }

    @Override
    public void cargar(JTable tblRegistros) {
        /*
        // Solicitar lista de Proveedores al servidor
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
        */
    }

    @Override
    public void modificar(JTable tblRegistros) {
        /*
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
        */
    }

    @Override
    public void eliminar(JTable tblRegistros) {
        /*
        int i = tblRegistros.getSelectedRow();
        if(i != -1)
        {
            Proveedor u = proveedores.get(i);
            
            if(u.getEstado().equals("A"))
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    DefaultTableModel model = (DefaultTableModel) tblRegistros.getModel();
                    
                    // Enviar codigo del proveedor al servidor
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("metodo", 6);
                    jsonObj.put("codigo", u.getCodigo());
                    
                    String json = jsonObj.toString();
                    
                    HttpNetTask httpConnect = new HttpNetTask();
                    String response = httpConnect.sendPost(HostURL.PROVEEDORES, json);
                    getJsonDeleteProveedor(response);
                    u.setEstado("I");
                    
                    model.setValueAt("I", i, 6);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "El Proveedor ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Proveedor a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }

    @Override
    public void buscarUnidad(JTextField buscar, JTable tablaUnidad) {
        /*
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
        */
    }

    @Override
    public void seleccionarFila(JTextField buscar, JTable tablaUnidad) {
        /*
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
        */
    }
}
