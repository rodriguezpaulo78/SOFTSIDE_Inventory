package softside_inventory.controladores.consulta;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Consultas;
import softside_inventory.util.Session;
import softside_inventory.vistas.consulta.ConsultaExistencia;
import softside_inventory.modelos.Producto;
import softside_inventory.modelos.Inventario_Cabecera;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;

/**
 * Controlador de la consulta de existencia de productos
 * 
 * Recibe parametros de consulta sobre la existencia de productos y muestra los
 * resultados, adicionalmente permite generar reportes.
 *  
 * @author SOFTSIDE
 */

public class CExistencia implements IExistencia
{
    private Session user;
    private ConsultaExistencia ventana;
    private ArrayList<Producto> activosPro;
    private ArrayList<Inventario_Cabecera> resultado;
    private boolean consultaRealizada;
    
    public CExistencia(Session user)
    {
        this.user = user;
        consultaRealizada = false;
        resultado = new ArrayList<Inventario_Cabecera>();
        getProductosActivos();
        ventana = new ConsultaExistencia(this);
    }
    
    public void getProductosActivos(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 8);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        activosPro = getProductosJSON(response);
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
        Producto p = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            p = new Producto();
            JSONObject row =(JSONObject)array.get(i);
            p.setCodigo(row.get("prod_id").toString());
            p.setNombre(row.get("prod_nombre").toString());
           
            productos.add(p);
            p = null;
        }
        return productos;
    }

    @Override
    public void cargar(JComboBox cbxProNom)
    {
        for(int i = 0; i < activosPro.size(); i++)
        {
            cbxProNom.insertItemAt(activosPro.get(i).getNombre(), i);
        }
    }

    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom)
    {
        txtProCod.setText(activosPro.get(cbxProNom.getSelectedIndex()).getCodigo());
    }

    @Override
    public void menu()
    {
        new CMenu(user);
        ventana.dispose();
    }

    @Override
    public void consultar(JTable tblConsultas, JTextField txtProCod, JTextField txtTotal)
    {
        if(txtProCod.getText().length() != 0)
        {
            resultado = Consultas.existenciaProducto(txtProCod.getText());
            DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
            model.setRowCount(0);

            for(int i = 0; i < resultado.size(); i++)
            {
                model.addRow(new Object[]   {
                                                "00001",
                                                resultado.get(i).getAlmNom(),
                                                resultado.get(i).getCantidad()
                                            });
            }
            txtTotal.setText(Consultas.existenciaTotal(txtProCod.getText()));
            consultaRealizada = true;
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un producto", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void generarReporte(JTextField txtProCod)
    {
        if(consultaRealizada)
        {
            //Producto p = Producto.buscar(txtProCod.getText());
            //Reporte.generarReporte1(p, resultado);
        }
        else
            JOptionPane.showMessageDialog(null, "Realize primero una consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
}
