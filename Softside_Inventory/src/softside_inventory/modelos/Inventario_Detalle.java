package softside_inventory.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
    private String invDetCod;
    private String proCod;
    private String almCod;
    private String invDetAnio;
    private String invDetMes;
    private String invDetDia;
    private String usrCod;
    private String docCod;
    private String invDetDocNum;
    private String invDetOpe;
    private String invDetCan;
    private String invDetValUni;
    private String invDetValTot;
    private String invDetSalCan;
    private String invDetObs;
    private String invDetEstReg;
    
    public Inventario_Detalle()
    {
        this("-1", "-1", "-1", "0", "0", "0", "-1", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "NULL", "0");
    }

    public Inventario_Detalle(String karDetCod, String proCod, String almCod, String karDetAnio, String karDetMes, String karDetDia, String usrCod, String docCod, String karDetDocNum, String karDetOpe, String karDetCan, String karDetValUni, String karDetValTot, String karDetSalCan, String karDetSalValUni, String karDetSalValTot, String karDetObs, String karDetEstReg)
    {
        this.invDetCod = karDetCod;
        this.proCod = proCod;
        this.almCod = almCod;
        this.invDetAnio = karDetAnio;
        this.invDetMes = karDetMes;
        this.invDetDia = karDetDia;
        this.usrCod = usrCod;
        this.docCod = docCod;
        this.invDetDocNum = karDetDocNum;
        this.invDetOpe = karDetOpe;
        this.invDetCan = karDetCan;
        this.invDetValUni = karDetValUni;
        this.invDetValTot = karDetValTot;
        this.invDetSalCan = karDetSalCan;
        this.invDetObs = karDetObs;
        this.invDetEstReg = karDetEstReg;
    }
    
    //Metodo que inserta en la BD
    
    public String insertar()
    {
    /*
        String msg = "";
        try
        {
            con.ejecutar("INSERT INTO KARDEX_DET VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            new String[] {
                                proCod,
                                almCod,
                                invDetAnio,
                                invDetMes,
                                invDetDia,
                                usrCod,
                                docCod,
                                invDetDocNum,
                                invDetOpe,
                                invDetCan,
                                invDetValUni,
                                invDetValTot,
                                invDetSalCan,
                                invDetObs,
                                invDetEstReg
                            },
                            false
                        );
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        
        return msg;
    */
        return "";
    }
    

    //Metodo que modifica en la BD
    
    public String modificar()
    {
        /*
        String msg = "";
        try
        {
            con.ejecutar("UPDATE KARDEX_DET SET KarDetAnio = ?, "
                                            + "KarDetMes = ?, "
                                            + "KarDetDia = ?, "
                                            + "UsrCod = ?, "
                                            + "DocCod = ?, "
                                            + "KarDetDocNum = ?, "
                                            + "KarDetOpe = ?, "
                                            + "KarDetCan = ?, "
                                            + "KarDetValUni = ?, "
                                            + "KarDetValTot = ?, "
                                            + "KarDetSalCan = ?, "
                                            + "KarDetSalValUni = ?, "
                                            + "KarDetSalValTot = ?, "
                                            + "KarDetObs = ? "
                                            + "WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)",
                        new String[] {
                                            invDetAnio,
                                            invDetMes,
                                            invDetDia,
                                            usrCod,
                                            docCod,
                                            invDetDocNum,
                                            invDetOpe,
                                            invDetCan,
                                            invDetValUni,
                                            invDetValTot,
                                            invDetSalCan,
                                            karDetSalValUni,
                                            karDetSalValTot,
                                            invDetObs,
                                            invDetCod,
                                            proCod,
                                            almCod
                        },
                        false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        
        return msg;
        */
        return "";
    }
    
    //Metodo que elimina de la BD
    public String eliminar(String codigo1, String codigo2, String codigo3)
    {
        /*
        String msg = "";
        try
        {
            this.setKarDetEstReg("3");
            con.ejecutar("UPDATE KARDEX_DET SET KarDetEstReg = 3 WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)", new String[] {codigo1, codigo2, codigo3}, false);
        }
        catch (SQLException ex)
        {
            msg = ex.getMessage();
        }
        return msg;
        */
        return "";
    }

    //Metodo para buscar en la BD
    
    public static Inventario_Detalle buscar(String codigo1, String codigo2, String codigo3)
    {
        
        Inventario_Detalle detalle = null;
        /*
        try
        {
            ResultSet rs = con.ejecutar("SELECT * FROM KARDEX_DET WHERE (KarDetCod = ? AND ProCod = ? AND AlmCod = ?)",
                                                new String[] {codigo1, codigo2, codigo3}, true);
            rs.next();
            detalle = new Inventario_Detalle();
            detalle.setKarDetCod(rs.getString("KarDetCod"));
            detalle.setProCod(rs.getString("ProCod"));
            detalle.setAlmCod(rs.getString("AlmCod"));
            detalle.setKarDetAnio(rs.getString("KarDetAnio"));
            detalle.setKarDetMes(rs.getString("KarDetMes"));
            detalle.setKarDetDia(rs.getString("KarDetDia"));
            detalle.setUsrCod(rs.getString("UsrCod"));
            detalle.setDocCod(rs.getString("DocCod"));
            detalle.setKarDetDocNum(rs.getString("KarDetDocNum"));
            detalle.setKarDetOpe(rs.getString("KarDetOpe"));
            detalle.setKarDetCan(rs.getString("KarDetCan"));
            detalle.setKarDetValUni(rs.getString("KarDetValUni"));
            detalle.setKarDetValTot(rs.getString("KarDetValTot"));
            detalle.setKarDetSalCan(rs.getString("KarDetSalCan"));
            detalle.setKarDetSalValUni(rs.getString("KarDetSalValUni"));
            detalle.setKarDetSalValTot(rs.getString("KarDetSalValTot"));
            detalle.setKarDetObs(rs.getString("KarDetObs"));
            detalle.setKarDetEstReg(rs.getString("KarDetEstReg"));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        */
        return detalle;
    }
    
    /*
    public static String sgteCodigo()
    {
        String codigo = "000000";
        try
        {
            ResultSet rs = con.ejecutar("SELECT LPAD((SELECT COUNT(*) + 1 FROM KARDEX_DET), 6, '0') AS nextCod", null, true);
            rs.next();
            codigo = rs.getString("nextCod");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codigo;
    }
    
    public static ArrayList<ArrayList<String>> getVista(String producto, String almacen)
    {
        ArrayList<ArrayList<String>> kardet = new ArrayList<>();
        try
        {        
            ResultSet resultado = con.ejecutar("SELECT * FROM VI_KarDet WHERE ProCod = ? AND AlmCod = ?", new String[] {producto, almacen}, true);
            String operacion = "";
            
            while(resultado.next())
            {
                if(resultado.getString("KarDetOpe").equals("1"))
                    operacion = "Entrada";
                else
                    operacion = "Salida";
                ArrayList<String> data = new ArrayList<>();
                String codigo = resultado.getString("KarDetCod");
                StringBuffer fecha = new StringBuffer(  resultado.getString("KarDetAnio").length() +
                                                        resultado.getString("KarDetMes").length() +
                                                        resultado.getString("KarDetDia").length() + 2
                                                    ).append(resultado.getString("KarDetAnio"))
                                                    .append('/')
                                                    .append(resultado.getString("KarDetMes"))
                                                    .append('/')
                                                    .append(resultado.getString("KarDetDia"));
                String documento = resultado.getString("DocNom");
                String numDoc = resultado.getString("KarDetDocNum");
                String cantidad = resultado.getString("KarDetCan");
                String valorU = resultado.getString("KarDetValUni");
                String total = resultado.getString("KarDetValTot");
                String s_cantidad = resultado.getString("KarDetSalCan");
                String s_valorU = resultado.getString("KarDetSalValUni");
                String s_total = resultado.getString("KarDetSalValTot");
                String observ = resultado.getString("KarDetObs");
                
                data.add(codigo);
                data.add(fecha.toString());
                data.add(documento);
                data.add(numDoc);
                data.add(operacion);
                data.add(cantidad);
                data.add(valorU);
                data.add(total);
                data.add(s_cantidad);
                data.add(s_valorU);
                data.add(s_total);
                data.add(observ);
                
                kardet.add(data);
            }
        
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return kardet;
    }
    */
}
