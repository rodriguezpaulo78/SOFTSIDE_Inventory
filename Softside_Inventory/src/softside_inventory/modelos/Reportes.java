package softside_inventory.modelos;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import softside_inventory.net.HostURL;
import softside_inventory.net.HttpNetTask;

/**
 * Generador de reportes
 * 
 * Genera reportes para los resultados de las consultas especificas y no especificas
 * 
 * @author SOFTSIDE
 */

public class Reportes
{
    /*
    //REPORTE PARA GESTION DE PRODUCTOS
    public static void generarReporte(String titulo, String titulo2, ArrayList<ArrayList<String>> datos, ArrayList<String> cab)
    {
        JFileChooser save=new JFileChooser();
        
        save.setDialogTitle("Guardar Reporte PDF");
        FileFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf");
        save.setAcceptAllFileFilterUsed(false);
        save.setFileFilter(filter);
        
        int option=save.showSaveDialog(null);

        if(option==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                Document doc = new Document(PageSize.A4, 40, 40, 20, 20);
                String path = save.getSelectedFile().getAbsoluteFile() + "";
                if(!path.substring(path.length() - 3, path.length()).equals("pdf"))
                    path = path + ".pdf";
                                
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                
                doc.open();
                
                //Parrafo vacio
                Paragraph vacio = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
                
                // INICIO REPORTE
                
                // Titulo 2
                Paragraph parrafo = new Paragraph("REPORTE - " + titulo2 + " \n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK));
                parrafo.setAlignment(Element.ALIGN_CENTER);
                doc.add(parrafo);
                
                // Titulo 1
                PdfPTable tabla = new PdfPTable(1);
                tabla.setTotalWidth(PageSize.A4.getWidth() - 80);
                tabla.setLockedWidth(true);
                
                PdfPCell celda = new PdfPCell(new Paragraph(titulo, FontFactory.getFont(FontFactory.COURIER, 17, Font.BOLD, BaseColor.WHITE)));
                celda.setFixedHeight(40);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.BLACK);
                tabla.addCell(celda);
                doc.add(tabla);
                doc.add(vacio);
                
                // Datos
                
                tabla = new PdfPTable(1);
                tabla.setTotalWidth(PageSize.A4.getWidth() - 80);
                tabla.setLockedWidth(true);
                
                Calendar c = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' hh:mm a");
              
                celda = new PdfPCell(new Paragraph("Sistema de Control de Inventarios Kardex - Reporte\nGenerado el " + format.format(c.getTime()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setPadding(10);
                tabla.addCell(celda);
                doc.add(tabla);
                doc.add(vacio);
                
                // Cabecera de tabla
                tabla = new PdfPTable(cab.size());
                tabla.setTotalWidth(PageSize.A4.getWidth() - 80);
                tabla.setLockedWidth(true);

                for(int i = 0; i < cab.size(); i++)
                {
                    celda = new PdfPCell(new Paragraph(cab.get(i), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.WHITE)));
                    celda.setFixedHeight(30);
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celda.setBackgroundColor(new BaseColor(51, 139, 204));
                    celda.setBorderColor(BaseColor.WHITE);
                    celda.setBorderWidth(1);
                    tabla.addCell(celda);
                }
               
                // Datos de Tabla
                for(int i = 0; i < datos.size(); i++)
                {
                    for(int j = 0; j < datos.get(i).size(); j++)
                    {
                        celda = new PdfPCell(new Paragraph(datos.get(i).get(j), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK)));
                        celda.setFixedHeight(20);
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        
                        
                        if(i%2 == 0)
                        {
                            celda.setBackgroundColor(BaseColor.WHITE);
                            celda.setBorderColor(BaseColor.WHITE);
                        }
                        else
                        {
                            celda.setBackgroundColor(new BaseColor(228, 239, 248));
                            celda.setBorderColor(new BaseColor(228, 239, 248));
                        }
                        tabla.addCell(celda);
                    }
                }
                doc.add(tabla);
                doc.close();
            }
            catch (DocumentException ex)
            {
            }
            catch (IOException ex)
            {
            }
        }
    }
    */
    
    //REPORTE PARA MOVIMIENTO INVENTARIO
    public static void generarReporteKardex(Inventario_Cabecera karCab, ArrayList<Inventario_Detalle> karDet)
    {
        JFileChooser save=new JFileChooser();
        
        save.setDialogTitle("Guardar Reporte PDF");
        FileFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf");
        save.setAcceptAllFileFilterUsed(false);
        save.setFileFilter(filter);
        
        int option=save.showSaveDialog(null);

        if(option==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                Document doc = new Document(PageSize.A4.rotate(), 40, 40, 20, 20);
                String path = save.getSelectedFile().getAbsoluteFile() + "";
                if(!path.substring(path.length() - 3, path.length()).equals("pdf"))
                    path = path + ".pdf";
                                
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                
                doc.open();
                
                //Parrafo vacio
                Paragraph vacio = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
                
                // INICIO REPORTE
                
                // Titulo
                Paragraph parrafo = new Paragraph("INVENTARIO DE ENTRADA Y SALIDA DE TIENDA CAYMA \n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK));
                parrafo.setAlignment(Element.ALIGN_CENTER);
                doc.add(parrafo);
                
                // Kardex_Cabecera
                PdfPTable tabla = new PdfPTable(3);
                tabla.setTotalWidth(PageSize.A4.getHeight() - 80);
                tabla.setLockedWidth(true);
                
                    // Titulo
                               
                PdfPCell celda = new PdfPCell(new Paragraph("DATOS PRINCIPALES", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                celda.setColspan(3);
                celda.setFixedHeight(30);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                    // Contenido
                
                celda = new PdfPCell(new Paragraph("Codigo de Producto: " + karCab.getProCod(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                // SOLICITAR PRODUCTO
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("metodo", 5);
                jsonObj.put("codigo", karCab.getProCod());

                String json = jsonObj.toString();

                HttpNetTask httpConnect = new HttpNetTask();
                String response = httpConnect.sendPost(HostURL.PRODUCTOS, json);

                ArrayList<Producto> producto = getProductoJSON(response);
                
                celda = new PdfPCell(new Paragraph("Nombre de Producto: " + producto.get(0).getNombre(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                // SOLICITAR UNIDAD
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("metodo", 5);
                jsonObj2.put("codigo", producto.get(0).getCodigo_uni());

                String json2 = jsonObj2.toString();

                HttpNetTask httpConnect2 = new HttpNetTask();
                String response2 = httpConnect2.sendPost(HostURL.UNIDADES, json2);

                ArrayList<Unidad> unidad = getUnidadJSON(response2);
                
                celda = new PdfPCell(new Paragraph("Unidad: " + unidad.get(0).getDescripcion(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Codigo de Almacen:  " + "00001", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Nombre de Almacen:  " + karCab.getAlmNom(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                Calendar c = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yy'/'MM'/'dd hh:mm a");
                
                celda = new PdfPCell(new Paragraph("Fecha de Generación: " + format.format(c.getTime()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.WHITE);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                doc.add(tabla);
                doc.add(vacio);
                
                // Cantidad y Precio
                
                tabla = new PdfPTable(4);
                tabla.setTotalWidth(PageSize.A4.getHeight()/2);
                tabla.setLockedWidth(true);
                
                celda = new PdfPCell(new Paragraph("Cantidad:", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph(karCab.getCantidad(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Precio Unitario:", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph(karCab.getValorUnit(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(BaseColor.WHITE);
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                doc.add(tabla);
                doc.add(vacio);
                
                // Kardex_Detalle
                
                tabla = new PdfPTable(8);
                tabla.setTotalWidth(PageSize.A4.getHeight() - 80);
                tabla.setLockedWidth(true);
                
                    //  Cabecera
                
                celda = new PdfPCell(new Paragraph("Codigo", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setRowspan(2);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setRowspan(2);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Operación", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setRowspan(2);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Movimiento", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setColspan(3);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Saldo", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setColspan(1);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Obs.", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setRowspan(2);
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Cantidad", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Val. Uni.", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Paragraph("Cantidad", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                celda.setFixedHeight(20);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celda.setBackgroundColor(new BaseColor(210, 210, 210));
                celda.setBorderColor(BaseColor.BLACK);
                celda.setBorderWidth(1);
                tabla.addCell(celda);

                    // Detalles
                
                for (int i = 0; i < karDet.size(); i++) {
                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetCodigo(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetFecha(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetMovimiento(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetCantidad(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetPrecioUnit(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetPrecioTotal(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetSaldoCantidad(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);

                    celda = new PdfPCell(new Paragraph(karDet.get(i).getInvDetObservacion(), FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                    celda.setFixedHeight(20);
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pintarCeldas(i, celda);
                    tabla.addCell(celda);
                }
               
                doc.add(tabla);
                doc.close();
                              
            }
            catch (DocumentException ex)
            {
            }
            catch (IOException ex)
            {
            }
        }
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Producto>
     */
    private static ArrayList<Producto> getProductoJSON(String json){
        Object jsonObject =JSONValue.parse(json);
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
        return productos;
    }
    
    /**
     * Recibe y obtiene la lista de datos de respuesta en JSON
     * @param json
     * @return ArrayList<Unidad>
     */
    private static ArrayList<Unidad> getUnidadJSON(String json){
        Object jsonObject =JSONValue.parse(json);
        JSONArray array = (JSONArray)jsonObject;
        
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();
        Unidad u = null;
        
        for(int i=0;i<array.size();i++){
            u = new Unidad();
            JSONObject row =(JSONObject)array.get(i);
            u.setCodigo(row.get("uni_id").toString());
            u.setDescripcion(row.get("uni_descripcion").toString());
            u.setEstado(row.get("uni_est_reg").toString());
                     
            unidades.add(u);
            u = null;
        }
        return unidades;
    }
    
    private static void pintarCeldas(int i, PdfPCell celda){
        if (i % 2 == 0) {
            celda.setBackgroundColor(BaseColor.WHITE);
            celda.setBorderColor(BaseColor.WHITE);
        } else {
            celda.setBackgroundColor(new BaseColor(230, 230, 230));
            celda.setBorderColor(new BaseColor(230, 230, 230));
        }
    }
    
    public static boolean generarReporte1(Producto myProducto, ArrayList<ArrayList<String>> resultados)
    {
        boolean ok = true;
        JFileChooser save=new JFileChooser();
        save.setDialogTitle("Guardar Reporte PDF");
        FileFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf");
        save.setAcceptAllFileFilterUsed(false);
        save.setFileFilter(filter);
        
        int option=save.showSaveDialog(null);

        if(option==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                ArrayList <String> cabPro = new ArrayList <>(); //cabecera del producto--> etiqueta
                ArrayList <String> detPro = new ArrayList <>(); //detalle del producto--> descripciom
                
                cabPro.add("Codigo");
                cabPro.add("Nombre");
                cabPro.add("Unidad");
                
                detPro.add(myProducto.getCodigo());
                detPro.add(myProducto.getNombre());
                detPro.add(myProducto.getCodigo_uni());
                
                Date fechaActual = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
                String fecha = format1.format(fechaActual);                
                SimpleDateFormat format2 = new SimpleDateFormat("hh:mm aa");
                String hora = format2.format(fechaActual);
        
                /*-------------------------------------------------------------------------------------------------------------------------*/

                Document doc = new Document();
                String path = "" + save.getSelectedFile().getAbsoluteFile()+"";
                if(!path.substring(path.length() - 3, path.length()).equals("pdf"))
                    path = path +".pdf";
                
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();
                
                //PRIMERA LINEA
                Paragraph prgReporte = new Paragraph("Reporte Productos Kardex\n\n",FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_CENTER);
                doc.add(prgReporte);
                
                //CUADRO DEL TITULO
                PdfPTable tbaReporte = new PdfPTable(1);
                PdfPCell cellReporte = new PdfPCell(new Paragraph("REPORTE", FontFactory.getFont(FontFactory.COURIER,22,Font.BOLD,BaseColor.WHITE)));
                cellReporte.setFixedHeight(50);
                cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellReporte.setBackgroundColor(BaseColor.RED);
                tbaReporte.addCell(cellReporte);
                doc.add(tbaReporte);

                prgReporte = new Paragraph("\n");
                doc.add(prgReporte);
                
                // FECHA
                prgReporte = new Paragraph("Fecha: " + fecha,FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_RIGHT);
                doc.add(prgReporte);
                //HORA 
                prgReporte = new Paragraph("Hora: " + hora,FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_RIGHT);
                doc.add(prgReporte);

                // DATOS DEL PRODUCTO
                prgReporte = new Paragraph("DATOS DEL PRODUCTO\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);
                
                // TABLA DEL PRODUCTO
                tbaReporte = new PdfPTable(2);
                
                for(int i = 0;i < cabPro.size();i++)
                {
                    cellReporte = new PdfPCell(new Paragraph(cabPro.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.WHITE)));
                    cellReporte.setFixedHeight(30);
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellReporte.setBackgroundColor(BaseColor.BLACK);
                    cellReporte.setBorderColor(BaseColor.WHITE);
                    tbaReporte.addCell(cellReporte);

                    cellReporte = new PdfPCell(new Paragraph(detPro.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                }

                doc.add(tbaReporte);

                prgReporte = new Paragraph("\nDETALLES\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);
                
                ///detalle de los almacenes
                
                ArrayList<String> cabAlm =new ArrayList<>();
                cabAlm.add("Codigo");
                cabAlm.add("Nombre");
                cabAlm.add("Cantidad");
                
                tbaReporte = new PdfPTable(3);
                
                //agreamos a la tabla
                for(int i = 0;i < cabAlm.size();i++)
                {
                    cellReporte = new PdfPCell(new Paragraph(cabAlm.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.WHITE)));
                    cellReporte.setFixedHeight(30);
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellReporte.setBackgroundColor(BaseColor.BLACK);
                    cellReporte.setBorderColor(BaseColor.WHITE);
                    tbaReporte.addCell(cellReporte);
                }
               
                // agregando los almacenes   
                
                for(int i = 0;i < resultados.size();i++)
                {
                    cellReporte = new PdfPCell(new Paragraph(resultados.get(i).get(0), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                    
                    cellReporte = new PdfPCell(new Paragraph(resultados.get(i).get(1), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                    
                    cellReporte = new PdfPCell(new Paragraph(resultados.get(i).get(2), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                }
                doc.add(tbaReporte);
                doc.close();
            } catch (DocumentException ex) {
                ok = false;
            } catch (IOException ex) {
                ok = false;
            }
        }
        return ok;
    }
    
    public static boolean generarReporte2(Producto myProducto, String mes, String anio, ArrayList<ArrayList<String>> detalles)
    {
        boolean ok = true;
        JFileChooser save=new JFileChooser();
        
        save.setDialogTitle("Guardar Reporte PDF");
        FileFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf");
        save.setAcceptAllFileFilterUsed(false);
        save.setFileFilter(filter);
        
        int option=save.showSaveDialog(null);

        if(option==JFileChooser.APPROVE_OPTION)
        {
            try {
                
                //PRODUCTO
                ArrayList <String> cabPro = new ArrayList <>(); //cabecera del producto--> etiqueta
                ArrayList <String> detPro = new ArrayList <>(); //detalle del producto--> descripciom
                
                cabPro.add("Codigo");
                cabPro.add("Nombre");
                cabPro.add("Unidad");
                
                detPro.add(myProducto.getCodigo());
                detPro.add(myProducto.getNombre());
                detPro.add(myProducto.getCodigo_uni());
                
                // FECHA DE CREACION DEL REPORTE         
                Date fechaActual = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
                String fecha = format1.format(fechaActual);                
                SimpleDateFormat format2 = new SimpleDateFormat("hh:mm aa");
                String hora = format2.format(fechaActual);
                
                /*-------------------------------------------------------------------------------------------------------------------------*/
                //CREACION DEL PDF
                Document doc = new Document();
                String path = "" + save.getSelectedFile().getAbsoluteFile()+"";
                if(!path.substring(path.length() - 3, path.length()).equals("pdf"))
                    path = path +".pdf";
                
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();
                
                //PRIMERA LINEA
                Paragraph prgReporte = new Paragraph("Reporte Productos Kardex\n\n",FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_CENTER);
                doc.add(prgReporte);
                
                //CUADRO DEL TITULO
                PdfPTable tbaReporte = new PdfPTable(1);
                PdfPCell cellReporte = new PdfPCell(new Paragraph("REPORTE", FontFactory.getFont(FontFactory.COURIER,22,Font.BOLD,BaseColor.WHITE)));
                cellReporte.setFixedHeight(50);
                cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellReporte.setBackgroundColor(BaseColor.ORANGE);
                tbaReporte.addCell(cellReporte);
                doc.add(tbaReporte);

                prgReporte = new Paragraph("\n");
                doc.add(prgReporte);
                
                // FECHA
                prgReporte = new Paragraph("Fecha: " + fecha,FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_RIGHT);
                doc.add(prgReporte);
                //HORA 
                prgReporte = new Paragraph("Hora: " + hora,FontFactory.getFont(FontFactory.COURIER,12,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_RIGHT);
                doc.add(prgReporte);

                // DATOS DEL PRODUCTO
                prgReporte = new Paragraph("DATOS DEL PRODUCTO\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);
                
                // TABLA DEL PRODUCTO
                tbaReporte = new PdfPTable(2);
                
                for(int i = 0;i < cabPro.size();i++)
                {
                    cellReporte = new PdfPCell(new Paragraph(cabPro.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.WHITE)));
                    cellReporte.setFixedHeight(30);
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellReporte.setBackgroundColor(BaseColor.BLACK);
                    cellReporte.setBorderColor(BaseColor.WHITE);
                    tbaReporte.addCell(cellReporte);

                    cellReporte = new PdfPCell(new Paragraph(detPro.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                }

                doc.add(tbaReporte);
                
                // DATOS DEL ALMACEN
                prgReporte = new Paragraph("DATOS DEL ALMACEN\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);
                
                               
                prgReporte = new Paragraph("REPORTE CORRESPONDIENTE A: "+mes+ " del "+anio+"\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.RED));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);

                prgReporte = new Paragraph("DETALLES\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,BaseColor.BLACK));
                prgReporte.setAlignment(Element.ALIGN_LEFT);
                doc.add(prgReporte);
                
                ///detalle de los almacenes
                
                ArrayList<String> tblCab =new ArrayList<>();
                tblCab.add("Dia");
                tblCab.add("Codigo");
                tblCab.add("Nombre");
                tblCab.add("Cantidad");
                
                tbaReporte = new PdfPTable(4);
                
                //agreamos a la tabla
                for(int i = 0;i < tblCab.size();i++)
                {
                    cellReporte = new PdfPCell(new Paragraph(tblCab.get(i), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.WHITE)));
                    cellReporte.setFixedHeight(30);
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellReporte.setBackgroundColor(BaseColor.BLACK);
                    cellReporte.setBorderColor(BaseColor.WHITE);
                    tbaReporte.addCell(cellReporte);
                }
               
                // agregando los almacenes   
                
                for(int i = 0;i < detalles.size();i++)
                {
                    
                    cellReporte = new PdfPCell(new Paragraph(detalles.get(i).get(0), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                    
                    cellReporte = new PdfPCell(new Paragraph(detalles.get(i).get(1), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                    
                    cellReporte = new PdfPCell(new Paragraph(detalles.get(i).get(2), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                    
                    cellReporte = new PdfPCell(new Paragraph(detalles.get(i).get(3), FontFactory.getFont(FontFactory.COURIER,11,Font.BOLD,BaseColor.BLACK)));
                    cellReporte.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellReporte.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tbaReporte.addCell(cellReporte);
                }
                doc.add(tbaReporte);
                doc.close();
            } catch (DocumentException ex) {
                ok = false;
            } catch (IOException ex) {
                ok = false;
            }
        }
        return ok;
    }
    
}