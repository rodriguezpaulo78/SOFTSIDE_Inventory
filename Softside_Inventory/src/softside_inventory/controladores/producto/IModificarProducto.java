package softside_inventory.controladores.producto;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de Producto
 * 
 * Metodos disponibles para el controlador de moficacion de Producto
 *  
 * @author SOFTSIDE
 */

public interface IModificarProducto
{
    public void cancelar();
    public void cargar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, JDateChooser txtFecVenc, JTextField txtProdUni, JTextField txtProdProv);
    public void aceptar(JTextField txtProdCod, JTextField txtProdNom, JTextField txtProdDes, JDateChooser txtFecVenc, JTextField txtProdUni, JTextField txtProdProv);
}
