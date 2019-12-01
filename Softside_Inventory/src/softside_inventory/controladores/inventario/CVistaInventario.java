package softside_inventory.controladores.inventario;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.controladores.CMenu;
import softside_inventory.modelos.Inventario_Cabecera;
import softside_inventory.modelos.Inventario_Detalle;
import softside_inventory.modelos.Producto;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;

import softside_inventory.util.Session;
import softside_inventory.vistas.inventario.VistaInventario;
import softside_inventory.vistas.producto.VistaProducto;

/**
 * Controlador principal de la gestion de kardex
 * 
 * Carga los productos - almacenes existentes con sus datos, ademas de controlar el
 * redireccionamiento hacia las ventanas de inserción o modificación. La eliminacion
 * de kardex se realiza aqui.
 *  
 * @author SOFTSIDE
 */

public class CVistaInventario implements IVistaInventario
{
    /*
    private IVistaInventario ventana;
    private ArrayList<KardexCab> kc;
    private ArrayList<ArrayList<KardexDet>> kds;
    private ArrayList<ArrayList<KardexDet>> kds_activos;
    */
    private ArrayList<Inventario_Cabecera> invCabs;
    ArrayList<Inventario_Detalle> detalles;
    private String codigoProducto;
    private String codigoAlmacen;
    private VistaInventario ventana;
    private Session user;
    
    public CVistaInventario(Session user)
    {
        ventana = new VistaInventario(this);
        this.user = user;
        //kds = new ArrayList<>();
        //kds_activos = new ArrayList<ArrayList<KardexDet>>();
        //kc = KardexCab.getLista();
        //int kcSize = kc.size();
        
        /*
        for(int i = 0; i < kcSize; i++)
        {
            kds.add(KardexCab.getDetalles(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        }
        for(int i = 0; i < kcSize; i++)
        {
            kds_activos.add(KardexCab.getDetallesActivos(kc.get(i).getProCod(), kc.get(i).getAlmCod()));
        }
        */
    }
    
    @Override
    public void cargar(JTable tblRegistrosKC)
    {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 2);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_CABECERA, json);
        
        invCabs = getInvCabsJSON(response);
        
        DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
        model.setRowCount(0);
        
        Producto p = null;
       
        for(int i = 0; i < invCabs.size(); i++)
        {
            p = getProducto(invCabs.get(i).getProCod());
            
            model.addRow(new Object[]{  invCabs.get(i).getProCod(),
                                        p.getNombre(),
                                        invCabs.get(i).getAlmNom(),
                                        invCabs.get(i).getInvCabEstReg()});
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Inventario_Cabecera>
     */
    private ArrayList<Inventario_Cabecera> getInvCabsJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Inventario_Cabecera> inventarios = new ArrayList<Inventario_Cabecera>();
        Inventario_Cabecera inv = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            inv = new Inventario_Cabecera();
            JSONObject row =(JSONObject)array.get(i);
            inv.setInvCabCod(row.get("inv_cab_id").toString());
            inv.setProCod(row.get("producto_id").toString());
            inv.setAlmNom(row.get("inv_cab_almacen").toString());
            inv.setCantidad(row.get("inv_cab_cant").toString());
            inv.setValorUnit(row.get("inv_cab_val_unit").toString());
            inv.setValorTotal(row.get("inv_cab_val_total").toString());
            inv.setInvCabEstReg(row.get("inv_cab_est_reg").toString());
           
            inventarios.add(inv);
            inv = null;
        }
        return inventarios;
    }
    
    public Producto getProducto(String cod){
        // Solicita el Producto al servidor
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 5);
        jsonObj.put("codigo", cod);
        
        String json = jsonObj.toString();
        
        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);
        
        // Procesar respuesta del servidor
        Object jsonObject =JSONValue.parse(response);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Producto();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("prod_id").toString());
            u.setNombre(row.get("prod_nombre").toString());
            u.setDescripcion(row.get("prod_descripcion").toString());
            u.setCodigo_uni(row.get("unidad_id").toString());
            u.setFec_venc(row.get("prod_fec_venc").toString());
            u.setCodigo_prov(row.get("proveedor_id").toString());
            u.setEstado(row.get("prod_est_reg").toString());
                     
            productos.add(u);
            u = null;
        }
        return productos.get(0);
    }
    
    @Override
    public void actualizar(JTable tblRegistrosInv_Cab, JTable tblRegistrosInv_Det)
    {
        /*
        codigoProducto = kc.get(tblRegistrosKC.getSelectedRow()).getProCod();
        codigoAlmacen = kc.get(tblRegistrosKC.getSelectedRow()).getAlmCod();
        
        int i = tblRegistrosKC.getSelectedRow();
                       
        DefaultTableModel model = (DefaultTableModel) tblRegistrosKD.getModel();
        model.setRowCount(0);
        ArrayList<KardexDet> det = kds.get(i);
        
        txtCan.setText(kc.get(i).getKarCabCan());
        txtValUni.setText(kc.get(i).getKarCabValUni());
        txtValTot.setText(kc.get(i).getKarCabValTot());
        
	int detSize = det.size();
        String tipo = "";
        String estado = "";
        
        for(i = 0; i < detSize; i++)
        {
            if(det.get(i).getKarDetOpe().equals("1"))
                tipo = "Entrada";
            else
                tipo = "Salida";
            
            if(det.get(i).getKarDetEstReg().equals("1"))
                estado = "A";
            else
                estado = "*";

            model.addRow(new Object[]{  det.get(i).getKarDetCod(),
                                        new StringBuffer(   det.get(i).getKarDetDia().length() + 
                                                            det.get(i).getKarDetMes().length() +
                                                            det.get(i).getKarDetAnio() + 2
                                                            ).append(det.get(i).getKarDetDia())
                                                            .append('/')
                                                            .append(det.get(i).getKarDetMes())
                                                            .append('/')
                                                            .append(det.get(i).getKarDetAnio()),
                                        tipo,
                                        det.get(i).getKarDetCan(),
                                        det.get(i).getKarDetValUni(),
                                        det.get(i).getKarDetValTot(),
                                        det.get(i).getKarDetSalCan(),
                                        det.get(i).getKarDetSalValUni(),
                                        det.get(i).getKarDetSalValTot(),
                                        estado
                                        });
        }
        */
    }
    
    @Override
    public void menu()
    {
        new CMenu(user);
        ventana.dispose();
    }
    
    @Override
    public void insertarInv_Cab()
    {
        new CRegistrarCabeceraInventario(user);
        ventana.dispose();
    }
    
    public void actualizarInv_Det(JTable tblRegistrosInv_Cab, JTable tblRegistrosInv_Det, JTextField txtUsr, JTextField txtDoc, JTextArea txtObs, JTextField txtEst)
    {
        /*
        int i = tblRegistrosKC.getSelectedRow();
        int j = tblRegistrosKD.getSelectedRow();
        String estado = "";
        if(i != -1 && j != -1)
        {
            txtUsr.setText(kds.get(i).get(j).getUsrCod());
            txtDoc.setText(kds.get(i).get(j).getDocCod());
            txtNumDoc.setText(kds.get(i).get(j).getKarDetDocNum());
            txtObs.setText(kds.get(i).get(j).getKarDetObs());
            if(kds.get(i).get(j).getKarDetEstReg().equals("1"))
                estado = "Activo";
            else
                estado = "Eliminado";
            txtEst.setText(estado);
        }
        else
        {
            txtUsr.setText("");
            txtDoc.setText("");
            txtNumDoc.setText("");
            txtObs.setText("");
            txtEst.setText("");
        }
        */
    }
    
    @Override
    public void insertarInv_Det(JTable tblRegistrosKC)
    {
        // inventario_det_id, inventario_cab_id, inv_det_movimiento, inv_det_cantidad, inv_det_precio_unit,
        // inv_det_precio_total, inv_det_fec, inv_det_saldo_cant, inv_det_obs, inv_det_est_reg
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario_Cabecera cab = invCabs.get(i);
            if(cab.getInvCabEstReg().equals("A"))
            {
                String vTot = "";
                
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("metodo", 5);
                jsonObj.put("codigo", cab.getInvCabCod());

                String json = jsonObj.toString();

                HttpNetTask httpConnect = new HttpNetTask();
                String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);
                
                ArrayList<Inventario_Detalle> aux = getInvDetActivosJSON(response);
                
                if(aux.isEmpty())
                    vTot = "0";
                else
                    vTot = cab.getValorTotal();
                
                new CRegistrarDetalleInventario(cab.getProCod(), cab.getInvCabCod(), cab.getCantidad(), vTot, user);
                ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Solo se permite insertar en registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Inventario Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Inventario_Cabecera>
     */
    private ArrayList<Inventario_Detalle> getInvDetActivosJSON(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json);
        //Convertir el objeto JSON en un array
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Inventario_Detalle> detalles = new ArrayList<Inventario_Detalle>();
        Inventario_Detalle det = null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            det = new Inventario_Detalle();
            JSONObject row =(JSONObject)array.get(i);
            det.setInvDetCodigo(row.get("inv_det_id").toString());
            det.setInvCabCodigo(row.get("inventario_cab_id").toString());
            det.setInvDetMovimiento(row.get("inv_det_movimiento").toString());
            det.setInvDetCantidad(row.get("inv_det_cantidad").toString());
            det.setInvDetPrecioUnit(row.get("inv_det_precio_unit").toString());
            det.setInvDetPrecioTotal(row.get("inv_det_precio_total").toString());
            det.setInvDetFecha(row.get("inv_det_fec").toString());
            det.setInvDetSaldoCantidad(row.get("inv_det_saldo_cant").toString());
            det.setInvDetObservacion(row.get("inv_det_obs").toString());
            det.setInvDetEstado(row.get("inv_det_est_reg").toString());
           
            detalles.add(det);
            det = null;
        }
        return detalles;
    }
    
    @Override
    public void eliminarInv_Cab(JTable tblRegistrosKC, JTable tblRegistrosKD, JTextField txtEst)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario_Cabecera cab = invCabs.get(i);
            if(cab.getInvCabEstReg().equals("A") && 
                JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                // Enviar codigo del inventario cabecera al servidor
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("metodo", 3);
                jsonObj.put("codigo", cab.getInvCabCod());

                String json = jsonObj.toString();

                HttpNetTask httpConnect = new HttpNetTask();
                String response = httpConnect.sendPost(HostURL.INVENTARIO_CABECERA, json);
                getJsonDeleteInvCab(response);
                cab.setInvCabEstReg("I");

                DefaultTableModel model = (DefaultTableModel) tblRegistrosKC.getModel();
                model.setValueAt("I", i, 3);
                /*if(tblRegistrosKD.getSelectedRow() != -1)
                    txtEst.setText("Eliminado");
                int kdsSize = kds.get(i).size();
                int kdsActivosSize = kds_activos.get(i).size();

                for(int j = 0; j < kdsSize; j++)
                {
                    kds.get(i).get(j).eliminar(kds.get(i).get(j).getKarDetCod(), codigoProducto, codigoAlmacen);
                }

                for(int j = 0; j < kdsActivosSize; j++)
                {
                    kds_activos.get(i).get(j).setKarDetEstReg("3");
                }*/
            }
            else
                JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public void getJsonDeleteInvCab(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        
        if (mensaje.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Registro eliminado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void modificarInv_Det(JTable tblRegistrosKC)
    {
        /*
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            try
            {
                KardexDet d = kds_activos.get(i).get(kds_activos.get(i).size() - 1);
                if(d.getKarDetEstReg().equals("1"))
                {
                        boolean nuevo = (kds_activos.get(i).size() == 1);
                        String cantidad = "";
                        String vTot = "";
                        
                        if(nuevo)
                        {
                            cantidad = "0";
                            vTot = "0";
                        }
                        else
                        {
                            cantidad = kds_activos.get(i).get(kds_activos.get(i).size() - 2).getKarDetSalCan();
                            vTot = kds_activos.get(i).get(kds_activos.get(i).size() - 2).getKarDetSalValTot();
                        }
                        new CModificarDetalleInventario(d.getKarDetCod(), d.getProCod(), d.getAlmCod(), cantidad, vTot);
                        ventana.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(null, "Nada por modificar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }
    
    @Override
    public void eliminarInv_Det(JTable tblRegistrosKC)
    {
        /*
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            try
            {
                KardexDet d = kds_activos.get(i).get(kds_activos.get(i).size() - 1);
                if(!d.getKarDetEstReg().equals("3"))
                {
                    if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        d.eliminar(d.getKarDetCod(), d.getProCod(), d.getAlmCod());
                        new CVistaInventario();
                        ventana.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(null, "Nada por eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    @Override
    public void generarReporte(JTable tblRegistrosKC)
    {
        /*
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario cab = kc.get(i);
            if(cab.getKarCabEstReg().equals("1"))
            {
                ArrayList<String> karcab = KardexCab.getVista(codigoProducto, codigoAlmacen);
                ArrayList<ArrayList<String>> kardet = KardexDet.getVista(codigoProducto, codigoAlmacen);
                Reporte.generarReporteKardex(karcab, kardet);
            }
            else
                JOptionPane.showMessageDialog(null, "El registro no está activo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro de Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        */
    }

}
