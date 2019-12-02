package softside_inventory.controladores.inventario;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Interfaz de la insercion de kardex
 * 
 * Metodos disponibles para el controlador de insercion de kardex
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public interface IRegistrarCabeceraInventario
{
    public void cancelar();
    public void verProducto(JTextField txtProCod, JComboBox cbxProNom);
    public void verAlmacen(JTextField txtAlmCod, JComboBox cbxAlmNom);
    public void aceptar(JTextField txtProCod, JTextField txtAlmCod);
    public void cargar(JComboBox cbxProNom, JComboBox cbxAlmNom);
}
