package softside_inventory.controladores.usuario;

import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import softside_inventory.modelos.Usuario;
import softside_inventory.util.Session;
import softside_inventory.vistas.usuario.ModificarUsuario;

/**
 * Controlador de la modificacion de usuario
 * 
 * Carga datos del usuario seleccionado, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarUsuario implements IModificarUsuario
{
    private ModificarUsuario ventana;
    private Usuario u;
    private Session user; 
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarUsuario(Session user, String codigo)
    {
        //u = Usuario.buscar(codigo);
        this.user = user;
        ventana = new ModificarUsuario(this);
    }
    
    /**
     * Retorna a la ventana de Vista de Usuarios
     */
    @Override
    public void cancelar()
    {
        new CVistaUsuario(user);
        ventana.dispose();
    }
    
    /**
     * Actualiza la interfaz con los datos del de usuario registrado a modificar
     * @param txtUsrCod
     * @param txtUsrIde
     * @param txtDNI
     * @param txtUsrNom
     * @param txtUsrApe
     * @param txtFecNac
     * @param txtUsrCargo
     * @param rbAdmin
     * @param rbUsuario
     */
    @Override
    public void cargar( JTextField txtUsrCod, JTextField txtUsrIde, JFormattedTextField txtDNI,
                        JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo,
                        JRadioButton rbAdmin, JRadioButton rbUsuario)
    {
        /*
        txtUsrCod.setText(u.getUsrCod());
        txtUsrIde.setText(u.getUsrIde());
        txtDNI.setText(u.getUsrDni());
        txtUsrNom.setText(u.getUsrNom());
        txtUsrApe.setText(u.getUsrApe());
        if(u.getUsrPer().equals("1"))
        {
            rbAdmin.setSelected(true);
            rbUsuario.setSelected(false);
        }
        else
        {
            rbAdmin.setSelected(false);
            rbUsuario.setSelected(true);
        }
        */
    }
    
     /**
     * Hace la validación de los campos ingresados en la interfaz
     * @param txtUsrCod
     * @param txtUsrIde
     * @param txtCon
     * @param txtRepCon
     * @param txtDNI
     * @param txtUsrNom
     * @param txtUsrApe
     * @param txtFecNac
     * @param rbAdmin
     * @param txtUsrCargo
     */
    @Override
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, 
            JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo, JRadioButton rbAdmin)
    {
        /*
        String permiso = "0";
        if(rbAdmin.isSelected())
            permiso = "1";
        u.setUsrIde(txtUsrIde.getText());
        u.setUsrDni(txtDNI.getText());
        u.setUsrNom(txtUsrNom.getText());
        u.setUsrApe(txtUsrApe.getText());
        u.setUsrPer(permiso);
        
        if(String.valueOf(txtRepCon.getPassword()).equals(String.valueOf(txtCon.getPassword())))
        {
            if(String.valueOf(txtCon.getPassword()).length() >= 5 && String.valueOf(txtCon.getPassword()).length() <= 16)
            {
                String err = u.modificar(String.valueOf(txtRepCon.getPassword()));
                if(err.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    new CVistaUsuario();
                    ventana.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 5 y 12 caracteres.", "ERROR", JOptionPane.ERROR_MESSAGE);
                txtCon.setText("");
                txtRepCon.setText("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.\nIntente de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCon.setText("");
            txtRepCon.setText("");
        }
        */
    }    
}
