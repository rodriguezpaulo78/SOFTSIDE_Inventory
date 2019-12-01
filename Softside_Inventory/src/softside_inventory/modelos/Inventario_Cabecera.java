/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.modelos;

import org.json.simple.JSONObject;

/**
 * Representacion de la entidad Cabecera de Inventario  de la base de datos
 * 
 * Controla y gestiona acceso a la base de datos, consultas y peticiones de 
 * manipulacion de datos para la tabla 'softside' en MySQL. 
 * Incluye las funciones de insertar y eliminar.
 * 
 * @author SOFTSIDE
 */
public class Inventario_Cabecera {

    private String invCabCod;
    private String proCod;
    private String almNom; //almNom
    private String InvCabEstReg;
    private String cantidad;
    private String valorUnit;
    private String valorTotal;

    public String getInvCabCod() {
        return invCabCod;
    }

    public void setInvCabCod(String invCabCod) {
        this.invCabCod = invCabCod;
    }

    public String getProCod() {
        return proCod;
    }

    public void setProCod(String proCod) {
        this.proCod = proCod;
    }

    public String getAlmNom() {
        return almNom;
    }

    public void setAlmNom(String almNom) {
        this.almNom = almNom;
    }
    
    public String getInvCabEstReg() {
        return InvCabEstReg;
    }

    public void setInvCabEstReg(String InvCabEstReg) {
        this.InvCabEstReg = InvCabEstReg;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(String valorUnit) {
        this.valorUnit = valorUnit;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String toJSON(int metodo) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("metodo", metodo);
        jsonObj.put("productoCod", getProCod());
        jsonObj.put("almacen", getAlmNom());
        
        String json = jsonObj.toString();
        
        return json;
    }
}
