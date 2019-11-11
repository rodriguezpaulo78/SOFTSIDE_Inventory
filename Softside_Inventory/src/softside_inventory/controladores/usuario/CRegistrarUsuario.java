package softside_inventory.controladores.usuario;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Usuario;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.usuario.RegistrarUsuario;

/**
 * Controlador de la insercion de usuario
 * 
 * Recibe y valida datos sobre un nuevo registro de usuario
 *  
 * @author SOFTSIDE
 */

public class CRegistrarUsuario implements IRegistrarUsuario
{
    private RegistrarUsuario ventana;
    private Session user; 
    
    /**
     * Constructor de Clase
     * @param user : sesión de usuario
     */
    public CRegistrarUsuario(Session user)
    {
        this.user = user;
        ventana = new RegistrarUsuario(this);
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
     * Actualiza la interfaz con el codigo del Usuario
     * @param txtUsrCod
     */
    @Override
    public void cargar(JTextField txtUsrCod)
    {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 1);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.USUARIOS, json);
        
        String cod = getJsonCod(response);
        txtUsrCod.setText(cod);
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     * @return String cod
     */
    public String getJsonCod(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String cod = row.get("codigo").toString();
        return cod;
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
     * @param txtUsrCargo
     * @param rbAdmin
     */
    @Override
    public void aceptar(JTextField txtUsrCod, JTextField txtUsrIde, JPasswordField txtCon, JPasswordField txtRepCon, JFormattedTextField txtDNI, 
            JTextField txtUsrNom, JTextField txtUsrApe, JDateChooser txtFecNac, JTextField txtUsrCargo, JRadioButton rbAdmin)
    {
        Usuario u = new Usuario();
        u.setCodigo(txtUsrCod.getText());
        u.setUsername(txtUsrIde.getText());
        
        String pass = new String(txtCon.getPassword());
        String passRep = new String(txtRepCon.getPassword());
        if (pass.equals(passRep)){
            if (pass.length() >= 5 && pass.length() <= 12) {
                u.setPassword(pass);
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 5 y 12 caracteres.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.\nIntente de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        u.setDNI(txtDNI.getText());
        u.setNombres(txtUsrNom.getText());
        u.setApellidos(txtUsrApe.getText());
        
        Date fecha = txtFecNac.getDate();
        DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        String fecha2=f.format(fecha);
        u.setFecha_nac(fecha2);
        
        u.setCargo(txtUsrCargo.getText());
        
        String permiso = "usuario";
        if(rbAdmin.isSelected())
            permiso = "administrador";
        
        u.setTipo(permiso);
        
        String response = u.toJSON();
        
        getJsonRespUser(response);
        
    }
    
    public void getJsonRespUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaUsuario(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
