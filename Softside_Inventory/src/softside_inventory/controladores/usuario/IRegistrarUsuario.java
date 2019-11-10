package softside_inventory.controladores.usuario;

import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Interfaz de la insercion de usuario
 * 
 * Metodos disponibles para el controlador de insercion de usuario
 *  
 * @author SOFTSIDE
 */

public interface IRegistrarUsuario
{
    public void cancelar();
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo, JRadioButton rbAdmin);
    public void cargar(JTextField txtUsrCod);
}
