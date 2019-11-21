/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softside_inventory.modelos;

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


    private String proCod;
    //private String almCod; //almCod
    private String almNom; //almNom
    private String InvCabEstReg;
    
    public Inventario_Cabecera(String proCod, String InvCabEstReg) {
        this.proCod = proCod;
        this.InvCabEstReg = InvCabEstReg;
        this.almNom = "TiendaPrincipal"; 
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
    
    public void insertar(){
        
    }
    
    public void eliminar(){
        
    }
    
    public void getLista(){
        
    }
    
    public void getDetalles(){
        
    }
    
    public void getDetallesActivos(){
        
    }
    
    public void getVista(){
        
    }
   
}
