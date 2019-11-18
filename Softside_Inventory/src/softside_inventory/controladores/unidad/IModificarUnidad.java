package softside_inventory.controladores.unidad;

import softside_inventory.controladores.proveedor.*;
import javax.swing.JTextField;

/**
 * Interfaz de la modificacion de Unidad
 * 
 * Metodos disponibles para el controlador de moficacion de Unidad
 *  
 * @author SOFTSIDE
 */

public interface IModificarUnidad
{
    public void cancelar();
    public void cargar(JTextField txtUniCod, JTextField txtUniDes);
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
}
