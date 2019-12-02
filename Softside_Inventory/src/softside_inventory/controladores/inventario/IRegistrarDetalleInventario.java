package softside_inventory.controladores.inventario;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Interfaz de la insercion de registro de detalle de Inventario
 * 
 * Metodos disponibles para el controlador de inserción de registro de detalle de Inventario
 *  
 * @author SOFTSIDE
 */

public interface IRegistrarDetalleInventario
{
    public void calcular(JTextField txtCan, JTextField txtValUni, JTextField txtValTot, int i);
    public void cancelar();
    public void cargar(JTextField txtDoc, JTextField txtInvDetCod, JTextField txtProCod, JTextField txtAlmCod);
    public void aceptar(JTextField txtInvDetCod, JTextField txtProCod, JTextField txtAlmCod, JDateChooser fecha, JTextField txtDocCod, JTextField txtNumDoc, JComboBox cbxOpe, JTextField txtCan, JTextField txtValUni, JTextField txtValTot, JTextArea txtObs);
}
