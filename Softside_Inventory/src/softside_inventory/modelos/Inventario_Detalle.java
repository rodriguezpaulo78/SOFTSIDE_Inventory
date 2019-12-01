package softside_inventory.modelos;

import org.json.simple.JSONObject;


/**
 * Representacion de la entidad Detalle de Inventario de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para el detalle del inventario
 * Incluye las funciones de insertar, modificar y eliminar.
 * 
 * 
 * @author SOFTSIDE
 */

public class Inventario_Detalle
{
    private String invDetCodigo;
    private String invCabCodigo;
    private String invDetMovimiento;
    private String invDetCantidad;
    private String invDetPrecioUnit;
    private String invDetPrecioTotal;
    private String invDetFecha;
    private String invDetSaldoCantidad;
    private String invDetObservacion;
    private String invDetEstado;

    public String getInvDetCodigo() {
        return invDetCodigo;
    }

    public void setInvDetCodigo(String invDetCodigo) {
        this.invDetCodigo = invDetCodigo;
    }

    public String getInvCabCodigo() {
        return invCabCodigo;
    }

    public void setInvCabCodigo(String invCabCodigo) {
        this.invCabCodigo = invCabCodigo;
    }

    public String getInvDetMovimiento() {
        return invDetMovimiento;
    }

    public void setInvDetMovimiento(String invDetMovimiento) {
        this.invDetMovimiento = invDetMovimiento;
    }

    public String getInvDetCantidad() {
        return invDetCantidad;
    }

    public void setInvDetCantidad(String invDetCantidad) {
        this.invDetCantidad = invDetCantidad;
    }

    public String getInvDetPrecioUnit() {
        return invDetPrecioUnit;
    }

    public void setInvDetPrecioUnit(String invDetPrecioUnit) {
        this.invDetPrecioUnit = invDetPrecioUnit;
    }

    public String getInvDetPrecioTotal() {
        return invDetPrecioTotal;
    }

    public void setInvDetPrecioTotal(String invDetPrecioTotal) {
        this.invDetPrecioTotal = invDetPrecioTotal;
    }

    public String getInvDetFecha() {
        return invDetFecha;
    }

    public void setInvDetFecha(String invDetFecha) {
        this.invDetFecha = invDetFecha;
    }

    public String getInvDetSaldoCantidad() {
        return invDetSaldoCantidad;
    }

    public void setInvDetSaldoCantidad(String invDetSaldoCantidad) {
        this.invDetSaldoCantidad = invDetSaldoCantidad;
    }

    public String getInvDetObservacion() {
        return invDetObservacion;
    }

    public void setInvDetObservacion(String invDetObservacion) {
        this.invDetObservacion = invDetObservacion;
    }

    public String getInvDetEstado() {
        return invDetEstado;
    }

    public void setInvDetEstado(String invDetEstado) {
        this.invDetEstado = invDetEstado;
    }
    
    public String toJSON(int metodo, String saldoValUnit, String saldoValTotal) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", metodo);
        jsonObj.put("det_codigo", getInvDetCodigo());
        jsonObj.put("cab_codigo", getInvCabCodigo());
        jsonObj.put("det_movimiento", getInvDetMovimiento());
        jsonObj.put("det_cantidad", getInvDetCantidad());
        jsonObj.put("det_precio_unit", getInvDetPrecioUnit());
        jsonObj.put("det_precio_total", getInvDetPrecioTotal());
        jsonObj.put("det_fecha", getInvDetFecha());
        jsonObj.put("det_saldo_cant", getInvDetSaldoCantidad());
        jsonObj.put("det_obs", getInvDetObservacion());
        jsonObj.put("cab_val_unit", saldoValUnit);
        jsonObj.put("cab_val_total", saldoValTotal);
        
        String json = jsonObj.toString();
        
        return json;
    }
}
