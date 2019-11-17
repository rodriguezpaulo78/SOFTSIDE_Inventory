package softside_inventory.controladores.proveedor;

import javax.swing.JTextField;

/**
 * Interfaz de la insercion de usuario
 * 
 * Metodos disponibles para el controlador de insercion de usuario
 *  
 * @author SOFTSIDE
 */

public interface IRegistrarProveedor
{
    public void cancelar();
    public void aceptar(JTextField txtProvCod, JTextField txtProvRazSoc, JTextField txtProvNomRep, JTextField txtProvRuc, JTextField txtProvRub, JTextField txtProvTel);
    public void cargar(JTextField txtProvCod);
}
