package softside_inventory.controladores.consulta;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import softside_inventory.controladores.CMenu;
import softside_inventory.util.Session;
import softside_inventory.vistas.consulta.ConsultaSalida;

/**
 * Controlador de la consulta de salida de productos
 * 
 * Recibe parametros de consulta sobre la salida de productos y muestra los
 * resultados, adicionalmente permite generar reportes.
 *  
 * @author SOFTSIDE
 */

public class CSalida implements ISalida
{
    private Session user;
    private ConsultaSalida ventana;
    private ArrayList<ArrayList<String>> activosPro;
    private ArrayList<ArrayList<String>> resultado;
    private boolean consultaRealizada;
    
    private int iAnio;
    private int iMes;
    private ArrayList<String> meses;
    private ArrayList<String> anios;
    
    public CSalida(Session user)
    {
        this.user = user;
        consultaRealizada = false;
        resultado = new ArrayList<>();
        //activosPro = Producto.getActivos();
        iAnio = -1;
        iMes = -1;
        meses = null;
        anios = null;
        ventana = new ConsultaSalida(this);
    }

    @Override
    public void cargar(JComboBox cbxProNom)
    {
        for(int i = 0; i < activosPro.size(); i++)
        {
            cbxProNom.insertItemAt(activosPro.get(i).get(1), i);
        }
    }

    @Override
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom, JComboBox cbxAnio)
    {
        /*
        txtProCod.setText(activosPro.get(cbxProNom.getSelectedIndex()).get(0));
        anios = Consultas.getAnioSalida(txtProCod.getText());
        cbxAnio.removeAllItems();
        iAnio = -1;
        
        for(int i = 0; i < anios.size(); i++)
        {
            cbxAnio.insertItemAt(anios.get(i), i);
        }
        */
    }

    @Override
    public void menu()
    {
        new CMenu(user);
        ventana.dispose();
    }

    @Override
    public void consultar(JTable tblConsultas, JTextField txtProCod)
    {
        /*
        if(txtProCod.getText().length() != 0)
        {
            boolean a = anios != null;
            boolean m = meses != null;
            boolean iA = iAnio != -1;
            boolean iM = iMes != -1;
            
            if(a && m && iA && iM)
            {
                resultado = Consultas.salidas(txtProCod.getText(), anios.get(iAnio), meses.get(iMes));
                DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
                model.setRowCount(0);

                for(int i = 0; i < resultado.size(); i++)
                {
                    model.addRow(new Object[]   {
                                                    resultado.get(i).get(0),
                                                    resultado.get(i).get(1),
                                                    resultado.get(i).get(2),
                                                    resultado.get(i).get(3)
                                                });
                }
                consultaRealizada = true;
            }
            else
                JOptionPane.showMessageDialog(null, "Seleccione un año y mes", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un producto", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }

    @Override
    public void generarReporte(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        /*
        if(consultaRealizada)
        {
            Producto p = Producto.buscar(txtProCod.getText());
            Reporte.generarReporte2(p, (String) cbxMes.getSelectedItem(), (String)cbxAnio.getSelectedItem(), resultado);
        }
        else
            JOptionPane.showMessageDialog(null, "Realize primero una consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
*/
    }

    @Override
    public void actualizarAnio(JTextField txtProCod, JComboBox cbxAnio, JComboBox cbxMes)
    {
        /*
        meses = Consultas.getMesSalida(txtProCod.getText(), String.valueOf(cbxAnio.getSelectedItem()));
        iAnio = cbxAnio.getSelectedIndex();
        cbxMes.removeAllItems();
        iMes = -1;
        for(int i = 0; i < meses.size(); i++)
        {
            switch(meses.get(i))
            {
                case "01":
                    cbxMes.insertItemAt("Enero", i);
                break;
                    
                case "02":
                    cbxMes.insertItemAt("Febrero", i);
                break;
                    
                case "03":
                    cbxMes.insertItemAt("Marzo", i);
                break;
                    
                case "04":
                    cbxMes.insertItemAt("Abril", i);
                break;
                    
                case "05":
                    cbxMes.insertItemAt("Mayo", i);
                break;
                    
                case "06":
                    cbxMes.insertItemAt("Junio", i);
                break;
                    
                case "07":
                    cbxMes.insertItemAt("Julio", i);
                break;
                    
                case "08":
                    cbxMes.insertItemAt("Agosto", i);
                break;
                    
                case "09":
                    cbxMes.insertItemAt("Setiembre", i);
                break;
                    
                case "10":
                    cbxMes.insertItemAt("Octubre", i);
                break;
                    
                case "11":
                    cbxMes.insertItemAt("Noviembre", i);
                break;
                    
                case "12":
                    cbxMes.insertItemAt("Diciembre", i);
                break;
            }
        }
*/
    }
    
    @Override
    public void actualizarMes(JComboBox cbxMes)
    {
        iMes = cbxMes.getSelectedIndex();
    }
}
