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
public class Proveedor {

    private String codigo;
    private String raz_soc;
    private String nombre_rep;
    private String ruc;
    private String rubro;
    private String telefono;
    private String estado;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRaz_soc() {
        return raz_soc;
    }

    public void setRaz_soc(String raz_soc) {
        this.raz_soc = raz_soc;
    }

    public String getNombre_rep() {
        return nombre_rep;
    }

    public void setNombre_rep(String nombre_rep) {
        this.nombre_rep = nombre_rep;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        jsonObj.put("raz_soc", getRaz_soc());
        jsonObj.put("nombre_rep", getNombre_rep());
        jsonObj.put("ruc", getRuc());
        jsonObj.put("rubro", getRubro());
        jsonObj.put("telefono", getTelefono());
        
        String json = jsonObj.toString();
        
        return json;
    }
    
}
