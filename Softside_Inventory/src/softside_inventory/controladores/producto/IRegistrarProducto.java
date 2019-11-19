package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Interfaz de la insercion de Producto
 * 
 * Metodos disponibles para el controlador de insercion de Producto
 *  
 * @author SOFTSIDE
 */

public interface IRegistrarProducto
{
    public void cancelar();
    public void aceptar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, JDateChooser txtFecVenc, JComboBox jcbUnidad, JComboBox jcbProveedor);
    public void cargar(JTextField txtProdCod, JComboBox jcbUnidad, JComboBox jcbProveedor);
}
