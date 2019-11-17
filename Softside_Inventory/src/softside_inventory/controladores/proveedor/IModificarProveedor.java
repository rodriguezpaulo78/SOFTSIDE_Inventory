package softside_inventory.controladores.proveedor;

import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de proveedor
 * 
 * Metodos disponibles para el controlador de moficacion de proveedor
 *  
 * @author SOFTSIDE
 */

public interface IModificarProveedor
{
    public void cancelar();
    public void cargar(JTextField txtProvCod, JTextField txtProvRazSoc, JTextField txtProvNomRep, JTextField txtProvRuc, JTextField txtProvRub, JTextField txtProvTel);
    public void aceptar(JTextField txtProvCod, JTextField txtProvRazSoc, JTextField txtProvNomRep, JTextField txtProvRuc, JTextField txtProvRub, JTextField txtProvTel);
}
