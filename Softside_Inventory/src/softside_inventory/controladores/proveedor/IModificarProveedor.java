package softside_inventory.controladores.proveedor;

import softside_inventory.controladores.usuario.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de usuario
 * 
 * Metodos disponibles para el controlador de moficacion de usuario
 *  
 * @author SOFTSIDE
 */

public interface IModificarProveedor
{
    public void cancelar();
    public void cargar( JTextField txtUsrCod, JTextField txtUsrIde, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo, JRadioButton rbAdmin, JRadioButton rbUsuario);
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo, JRadioButton rbAdmin);
}
