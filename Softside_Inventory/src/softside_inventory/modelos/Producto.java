/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author SOFTSIDE
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String codigo_uni;
    private String fec_venc;
    private String codigo_prov;
    private String estado;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo_uni() {
        return codigo_uni;
    }

    public void setCodigo_uni(String codigo_uni) {
        this.codigo_uni = codigo_uni;
    }

    public String getFec_venc() {
        return fec_venc;
    }

    public void setFec_venc(String fec_venc) {
        this.fec_venc = fec_venc;
    }

    public String getCodigo_prov() {
        return codigo_prov;
    }

    public void setCodigo_prov(String codigo_prov) {
        this.codigo_prov = codigo_prov;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toJSON(int metodo) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", metodo);
        jsonObj.put("codigo", getCodigo());
        jsonObj.put("nombre", getNombre());
        jsonObj.put("descripcion", getDescripcion());
        jsonObj.put("unidad_id", getCodigo_uni());
        jsonObj.put("fec_venc", getFec_venc());
        jsonObj.put("proveedor_id", getCodigo_prov());
        
        String json = jsonObj.toString();
        
        return json;
    }
    
}
