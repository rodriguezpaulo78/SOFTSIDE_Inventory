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
import softside_inventory.modelos.Reportes;
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
    private ArrayList<Inventario_Detalle> invDets;
    private String codigoProducto;
    private String codigoCabecera;
    private VistaInventario ventana;
    private Session user;
      
    public CVistaInventario(Session user)
    {
        ventana = new VistaInventario(this);
        this.user = user;
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
        int i = tblRegistrosInv_Cab.getSelectedRow();
        codigoCabecera = invCabs.get(i).getInvCabCod();
                       
        DefaultTableModel model = (DefaultTableModel) tblRegistrosInv_Det.getModel();
        model.setRowCount(0);
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", 4);
        jsonObj.put("codigo", codigoCabecera);

        String json = jsonObj.toString();

        HttpNetTask httpConnect = new HttpNetTask();
        String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);

        invDets = getInvDetJSON(response);
        
        for(i = 0; i < invDets.size(); i++)
        {
            model.addRow(new Object[]{  invDets.get(i).getInvDetCodigo(),
                                        invDets.get(i).getInvDetMovimiento(),
                                        invDets.get(i).getInvDetCantidad(),
                                        invDets.get(i).getInvDetPrecioUnit(),
                                        invDets.get(i).getInvDetPrecioTotal(),
                                        invDets.get(i).getInvDetFecha(),
                                        invDets.get(i).getInvDetSaldoCantidad()
                                        });
        }
        
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
        int i = tblRegistrosInv_Cab.getSelectedRow();
        int j = tblRegistrosInv_Det.getSelectedRow();
        String estado = "";
        if(i != -1 && j != -1)
        {
            txtUsr.setText(user.getName());
            txtDoc.setText(user.getDni());
            txtObs.setText(invDets.get(j).getInvDetObservacion());
            if(invDets.get(j).getInvDetEstado().equals("A"))
                estado = "Activo";
            else
                estado = "Eliminado";
            txtEst.setText(estado);
        }
        else
        {
            txtUsr.setText("");
            txtDoc.setText("");
            txtObs.setText("");
            txtEst.setText("");
        }
        
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
                
                ArrayList<Inventario_Detalle> aux = getInvDetJSON(response);
                
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
    private ArrayList<Inventario_Detalle> getInvDetJSON(String json){
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
                
                if(tblRegistrosKD.getSelectedRow() != -1)
                    txtEst.setText("Eliminado");
                
                // Enviar codigo de cabecera al servidor
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("metodo", 6);
                jsonObj2.put("codigo", cab.getInvCabCod());

                String json2 = jsonObj2.toString();

                HttpNetTask httpConnect2 = new HttpNetTask();
                String response2 = httpConnect2.sendPost(HostURL.INVENTARIO_DETALLE, json2);
                if (getJsonDeleteInvDet(response2)){
                    new CVistaInventario(user);
                    ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                for(int j = 0; j < invDets.size(); j++)
                {
                    invDets.get(i).setInvDetEstado("I");                    
                }
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
    
    /**
     * Recibe y obtiene los datos de respuesta en JSON
     * @param json
     */
    public boolean getJsonDeleteInvDet(String json){
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject = JSONValue.parse(json);
        JSONObject row =(JSONObject) jsonObject;
        
        String mensaje = row.get("message").toString();
        boolean exito = false;
        
        if (mensaje.equals("SUCCESS")) {
            exito = true;
        } else {
            exito = false;
        }
        
        return exito;
    }
    
    @Override
    public void modificarInv_Det(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario_Cabecera cab = invCabs.get(i);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("metodo", 5);
            jsonObj.put("codigo", cab.getInvCabCod());

            String json = jsonObj.toString();

            HttpNetTask httpConnect = new HttpNetTask();
            String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);

            ArrayList<Inventario_Detalle> activos = getInvDetJSON(response);
                
            try
            {
                Inventario_Detalle det = activos.get(activos.size() - 1);
                if(det.getInvDetEstado().equals("A"))
                {
                        boolean nuevo = (activos.size() == 1);
                        String cantidad = "";
                        String vTot = "";
                        
                        if(nuevo)
                        {
                            cantidad = "0";
                            vTot = "0";
                        }
                        else
                        {
                            cantidad = activos.get(activos.size() - 2).getInvDetSaldoCantidad();
                            double saldoTotal = 0.0;
                            if (det.getInvDetMovimiento().equals("entrada")){
                                saldoTotal = Double.valueOf(cab.getValorTotal()) - Double.valueOf(det.getInvDetPrecioTotal());
                            } else {
                                saldoTotal = Double.valueOf(cab.getValorTotal()) + Double.valueOf(det.getInvDetPrecioTotal());
                            }
                            
                            vTot = String.valueOf(saldoTotal);
                        }
                        
                        new CModificarDetalleInventario(det.getInvDetCodigo(), cab.getProCod(), det.getInvCabCodigo(), cantidad, vTot, user);
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
            JOptionPane.showMessageDialog(null, "Seleccione un Inventario Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }
    
    @Override
    public void eliminarInv_Det(JTable tblRegistrosKC)
    {
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario_Cabecera cab = invCabs.get(i);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("metodo", 5);
            jsonObj.put("codigo", cab.getInvCabCod());

            String json = jsonObj.toString();

            HttpNetTask httpConnect = new HttpNetTask();
            String response = httpConnect.sendPost(HostURL.INVENTARIO_DETALLE, json);

            ArrayList<Inventario_Detalle> activos = getInvDetJSON(response);
            
            try
            {
                Inventario_Detalle det = activos.get(activos.size() - 1);
                
                if(det.getInvDetEstado().equals("A"))
                {
                    if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        boolean nuevo = (activos.size() == 1);
                        String cantidad = "";
                        String vTot = "";
                        String salValUni = "";
                        
                        if(nuevo)
                        {
                            cantidad = "0";
                            vTot = "0";
                        }
                        else
                        {
                            cantidad = activos.get(activos.size() - 2).getInvDetSaldoCantidad();
                            double saldoTotal = 0.0;
                            if (det.getInvDetMovimiento().equals("entrada")){
                                saldoTotal = Double.valueOf(cab.getValorTotal()) - Double.valueOf(det.getInvDetPrecioTotal());
                            } else {
                                saldoTotal = Double.valueOf(cab.getValorTotal()) + Double.valueOf(det.getInvDetPrecioTotal());
                            }
                            vTot = String.valueOf(saldoTotal);
                            
                            double saldoUnit = saldoTotal / Double.valueOf(cantidad);
                            if (!Double.isFinite(saldoUnit)) {
                                saldoUnit = 0.0;
                            }
                            salValUni = String.valueOf(saldoUnit);
                        }
                        
                        // Enviar codigo de detalle y de cabecera al servidor
                        JSONObject jsonObj2 = new JSONObject();
                        jsonObj2.put("metodo", 8);
                        jsonObj2.put("codigoDet", det.getInvDetCodigo());
                        jsonObj2.put("codigoCab", cab.getInvCabCod());
                        jsonObj2.put("saldoCantidad", cantidad);
                        jsonObj2.put("saldoUnit", salValUni);
                        jsonObj2.put("saldoTotal", vTot);

                        String json2 = jsonObj2.toString();

                        HttpNetTask httpConnect2 = new HttpNetTask();
                        String response2 = httpConnect2.sendPost(HostURL.INVENTARIO_DETALLE, json2);
                        
                        if (getJsonDeleteInvDet(response2)) {
                            new CVistaInventario(user);
                            ventana.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
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
            JOptionPane.showMessageDialog(null, "Seleccione un Inventario Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @Override
    public void generarReporte(JTable tblRegistrosKC)
    {
        
        int i = tblRegistrosKC.getSelectedRow();
        if(i != -1)
        {
            Inventario_Cabecera cab = invCabs.get(i);
            if(cab.getInvCabEstReg().equals("A"))
            {
                Reportes.generarReporteKardex(invCabs, invDets);
            }
            else
                JOptionPane.showMessageDialog(null, "El registro no está activo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else
             JOptionPane.showMessageDialog(null, "Seleccione un registro de Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE);
        
    }

}
