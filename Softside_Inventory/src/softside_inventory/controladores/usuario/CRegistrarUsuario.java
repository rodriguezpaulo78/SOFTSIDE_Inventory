package softside_inventory.controladores.usuario;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import softside_inventory.modelos.Usuario;
import softside_inventory.util.Session;
import softside_inventory.vistas.usuario.RegistrarUsuario;

/**
 * Controlador de la insercion de usuario
 * 
 * Recibe y valida datos sobre un nuevo registro de usuario
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CRegistrarUsuario implements IRegistrarUsuario
{
    private RegistrarUsuario ventana;
    private Session user; 
    
    public CRegistrarUsuario(Session user)
    {
        this.user = user;
        ventana = new RegistrarUsuario(this);
    }
    
    @Override
    public void cancelar()
    {
        
        new CVistaUsuario(user);
        ventana.dispose();
        
    }
    
    @Override
    public void cargar(JTextField txtUsrCod)
    {
        //txtUsrCod.setText(Usuario.sgteCodigo());
    }
    
    @Override
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, JTextField txtUsrNom, JTextField txtUsrApe, JRadioButton rbAdmin)
    {
        String permiso = "0";
        if(rbAdmin.isSelected())
            permiso = "1";
        /*
        Usuario u = new Usuario(txtUsrCod.getText(),
                                txtUsrIde.getText(),
                                txtDNI.getText(),
                                txtUsrNom.getText(),
                                txtUsrApe.getText(),
                                permiso,
                                "1");
        
        if(String.valueOf(txtRepCon.getPassword()).equals(String.valueOf(txtCon.getPassword())))
        {
            if(String.valueOf(txtCon.getPassword()).length() >= 5 && String.valueOf(txtCon.getPassword()).length() <= 16)
            {
                String err = u.insertar(String.valueOf(txtRepCon.getPassword()));
                if(err.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
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
