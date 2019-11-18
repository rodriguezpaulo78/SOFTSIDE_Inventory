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
public class Unidad {
    
    private String codigo;
    private String descripcion;
    private String estado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        jsonObj.put("descripcion", getDescripcion());
        
        String json = jsonObj.toString();
        
        return json;
    }
    
}
