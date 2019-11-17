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
public class Usuario {
    
    private String codigo;
    private String username;
    private String password;
    private String DNI;
    private String nombres;
    private String apellidos;
    private String fecha_nac;
    private String cargo;
    private String tipo;
    private String estado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        jsonObj.put("username", getUsername());
        jsonObj.put("password", getPassword());
        jsonObj.put("dni", getDNI());
        jsonObj.put("nombres", getNombres());
        jsonObj.put("apellidos", getApellidos());
        jsonObj.put("fecha_nac", getFecha_nac());
        jsonObj.put("cargo", getCargo());
        jsonObj.put("tipo", getTipo());
        
        String json = jsonObj.toString();
        
        return json;
    }
    
}
