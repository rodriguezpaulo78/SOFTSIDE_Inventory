package softside_inventory.controladores.unidad;

import softside_inventory.controladores.proveedor.*;
import javax.swing.JTextField;

/**
 * Interfaz de la insercion de Unidad
 * 
 * Metodos disponibles para el controlador de insercion de Unidad
 *  
 * @author SOFTSIDE
 */

public interface IRegistrarUnidad
{
    public void cancelar();
    public void aceptar(JTextField txtUniCod, JTextField txtUniDes);
    public void cargar(JTextField txtUniCod);
}
