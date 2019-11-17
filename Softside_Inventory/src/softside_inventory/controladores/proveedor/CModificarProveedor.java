package softside_inventory.controladores.proveedor;

import softside_inventory.controladores.usuario.*;
import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.modelos.Usuario;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;
import softside_inventory.util.Session;
import softside_inventory.vistas.usuario.ModificarUsuario;

/**
 * Controlador de la modificacion de usuario
 * 
 * Carga datos del usuario seleccionado, recibe nuevos valores y los valida
 *  
 * @author SOFTSIDE
 */

public class CModificarProveedor implements IModificarProveedor
{
    private ModificarUsuario ventana;
    private Usuario u;
    private Session user; 
    private String codigo;
    
    /**
     * Constructor de clase
     * @param user
     * @param codigo 
     */
    public CModificarProveedor(Session user, String codigo)
    {
        //u = Usuario.buscar(codigo);
        this.user = user;
        this.codigo = codigo;
        ventana = new ModificarUsuario(this);

    }
    
    /**
     * Retorna a la ventana de Vista de Usuarios
     */
    @Override
    public void cancelar()
    {
        new CVistaProveedor(user);
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
        // Solicita el usuario al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", codigo);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.USUARIOS, json);
        
        ArrayList<Usuario> user = getUsersJSON(response);
        
        txtUsrCod.setText(user.get(0).getCodigo());
        txtUsrIde.setText(user.get(0).getUsername());
        txtDNI.setText(user.get(0).getDNI());
        txtUsrNom.setText(user.get(0).getNombres());
        txtUsrApe.setText(user.get(0).getApellidos());
        txtUsrCargo.setText(user.get(0).getCargo());
        
        Date fecha = new Date(user.get(0).getFecha_nac());
        txtFecNac.setDate(fecha);
        
        if(user.get(0).getTipo().equals("administrador"))
        {
            rbAdmin.setSelected(true);
            rbUsuario.setSelected(false);
        }
        else
        {
            rbAdmin.setSelected(false);
            rbUsuario.setSelected(true);
        }
        
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Usuario>
     */
    private ArrayList<Usuario> getUsersJSON(String json){
        Object jsonObject =JSONValue.parse(json);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        Usuario u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Usuario();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("user_id").toString());
            u.setNombres(row.get("user_nombres").toString());
            u.setApellidos(row.get("user_apellidos").toString());
            u.setDNI(row.get("user_dni").toString());
            u.setFecha_nac(row.get("user_fec_nac").toString());
            u.setCargo(row.get("user_cargo").toString());
            u.setUsername(row.get("user_username").toString());
            u.setTipo(row.get("user_tipo_user").toString());
            u.setEstado(row.get("user_est_reg").toString());
           
            users.add(u);
            u = null;
        }
        return users;
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
                       
        String json = u.toJSON(3);
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.USUARIOS, json);
        
        getJsonRespUser(response);
        
    }    
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonRespUser(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            new CVistaProveedor(user);
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
