package softside_inventory.controladores.inventario;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Interfaz de la gestion de Inventario
 * 
 * Métodos disponibles para el controlador de gestion de Inventario
 *  
 * @author SOFTSIDE
 */

public interface IVistaInventario
{
    public void cargar(JTable tblRegistrosInv_Cab);
    public void actualizar(JTable tblRegistrosInv_Cab, JTable tblRegistrosInv_Det);
    public void menu();
    public void insertarInv_Cab();
    public void actualizarInv_Det(JTable tblRegistrosInv_Cab, JTable tblRegistrosInv_Det, JTextField txtUsr, JTextField txtDoc, JTextArea txtObs, JTextField txtEst);
    public void insertarInv_Det(JTable tblRegistrosInv_Cab);
    public void eliminarInv_Cab(JTable tblRegistrosInv_Cab, JTable tblRegistrosInv_Det, JTextField txtEst);
    public void modificarInv_Det(JTable tblRegistrosInv_Cab);
    public void eliminarInv_Det(JTable tblRegistrosInv_Cab);

    public void generarReporte(JTable tblRegistrosKC);
}
