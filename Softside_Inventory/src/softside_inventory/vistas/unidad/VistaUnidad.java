package softside_inventory.vistas.unidad;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import javax.swing.table.TableCellRenderer;
import softside_inventory.controladores.unidad.IVistaUnidad;

/**
 * Vista principal de Gestion de Unidad
 * @author SOFTSIDE
 */
public class VistaUnidad extends javax.swing.JFrame
{
    private IVistaUnidad interfaz;
    private ListSelectionModel cellSelectionModel;
    
    /**
     * Carga las unidades registradas en el sistema
     * @param interfaz
     */
    public VistaUnidad(IVistaUnidad interfaz)
    {
        initComponents();
        this.setVisible(true);
        this.setTitle("GESTION DE UNIDAD");
        setLocationRelativeTo(null);
        
        this.interfaz = interfaz;
        interfaz.cargar(this.tblUnidades);
        
        cellSelectionModel = tblUnidades.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        interfaz.buscarUnidad(txtBuscar, tblUnidades);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUnidades = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if(column == 0 && !isCellSelected(row, column))
                {
                    c.setBackground(new Color(228, 251, 219));
                }
                else if(isCellSelected(row, column))
                {
                    c.setBackground(Color.BLUE);
                }
                return c;
            }
        }
        ;
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jBsearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/icono.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/menu.png"))); // NOI18N
        btnMenu.setText("Volver al Menu");
        btnMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Buscar:");

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GESTIÓN DE UNIDAD - SOFTSIDE");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBuscar.setToolTipText("Descripción");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUnidades.setMinimumSize(new java.awt.Dimension(360, 0));
        tblUnidades.setPreferredSize(new java.awt.Dimension(600, 0));
        tblUnidades.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tblUnidades);
        if (tblUnidades.getColumnModel().getColumnCount() > 0) {
            tblUnidades.getColumnModel().getColumn(0).setMinWidth(50);
            tblUnidades.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblUnidades.getColumnModel().getColumn(0).setMaxWidth(50);
            tblUnidades.getColumnModel().getColumn(2).setMinWidth(60);
            tblUnidades.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblUnidades.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnInsertar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/insertar.png"))); // NOI18N
        btnInsertar.setText("REGISTRAR");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/modificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/search.png"))); // NOI18N
        jBsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMenu))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /**
     * Salir de la Interfaz de Unidad
     * Redirige a la interfaz del Menu Principal nuevamente
     */
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMenuActionPerformed
    {//GEN-HEADEREND:event_btnMenuActionPerformed
        interfaz.menu();
    }//GEN-LAST:event_btnMenuActionPerformed

    /**
     * Muestra la Interfaz del Registro de Unidad
     */
    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnInsertarActionPerformed
    {//GEN-HEADEREND:event_btnInsertarActionPerformed
        interfaz.registrar();
    }//GEN-LAST:event_btnInsertarActionPerformed

    /**
     * Muestra la Interfaz de Modificación de Unidad
     */
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnModificarActionPerformed
    {//GEN-HEADEREND:event_btnModificarActionPerformed
        interfaz.modificar(tblUnidades);
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * Eliminar la Unidad seleccionada en la tabla
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEliminarActionPerformed
    {//GEN-HEADEREND:event_btnEliminarActionPerformed
        interfaz.eliminar(tblUnidades);
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Salir de la Interfaz de Unidad
     * Redirige a la interfaz del Menu Principal nuevamente
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        interfaz.menu();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Buscar la Unidad según su descripcion
     */
    private void jBsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsearchActionPerformed
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No pueden estar el campo vacio");
        }
        else {
            interfaz.seleccionarFila(txtBuscar, tblUnidades);
        }
    }//GEN-LAST:event_jBsearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jBsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblUnidades;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
