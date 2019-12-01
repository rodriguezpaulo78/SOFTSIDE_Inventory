package softside_inventory.vistas.inventario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import softside_inventory.controladores.inventario.IVistaInventario;

/**
 * Vista principal de Gestion de Inventario
 * @author SOFTSIDE
 */
public class VistaInventario extends javax.swing.JFrame
{
    private IVistaInventario interfaz;
    
    public VistaInventario(IVistaInventario interfaz)
    {
        this.setVisible(true);
        this.setTitle("GESTIÓN DE INVENTARIO");
        initComponents();
        setLocationRelativeTo(null);
        
        this.interfaz = interfaz;
        interfaz.cargar(tblRegistrosInv_Cab);
        
        ListSelectionModel cellSelectionModel = tblRegistrosInv_Cab.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                interfaz.actualizar(tblRegistrosInv_Cab, tblRegistrosInv_Det);
            }
        });
        
        ListSelectionModel cellSelectionModel2 = tblRegistrosInv_Det.getSelectionModel();
        cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModel2.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                interfaz.actualizarInv_Det(tblRegistrosInv_Cab, tblRegistrosInv_Det, txtUsr, txtDoc, txtObs, txtEst);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrosInv_Cab = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if((column == 0 || column == 2)&& !isCellSelected(row, column))
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
        jPanel3 = new javax.swing.JPanel();
        btnInsertarInv_Cab = new javax.swing.JButton();
        btnEliminarInv_Cab = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistrosInv_Det = new javax.swing.JTable()
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
        btnInsertarInv_Det = new javax.swing.JButton();
        btnModificarInv_Det = new javax.swing.JButton();
        btnEliminarInv_Det = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtEst = new javax.swing.JTextField();
        txtUsr = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/icono.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("GESTIÓN DE INVENTARIO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario Cabecera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblRegistrosInv_Cab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblRegistrosInv_Cab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Producto", "Producto", "Almacen", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRegistrosInv_Cab);
        if (tblRegistrosInv_Cab.getColumnModel().getColumnCount() > 0) {
            tblRegistrosInv_Cab.getColumnModel().getColumn(0).setMinWidth(120);
            tblRegistrosInv_Cab.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblRegistrosInv_Cab.getColumnModel().getColumn(0).setMaxWidth(120);
            tblRegistrosInv_Cab.getColumnModel().getColumn(3).setMinWidth(70);
            tblRegistrosInv_Cab.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblRegistrosInv_Cab.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnInsertarInv_Cab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertarInv_Cab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/insertar.png"))); // NOI18N
        btnInsertarInv_Cab.setText("INSERTAR");
        btnInsertarInv_Cab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarInv_CabActionPerformed(evt);
            }
        });

        btnEliminarInv_Cab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminarInv_Cab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/eliminar.png"))); // NOI18N
        btnEliminarInv_Cab.setText("ELIMINAR");
        btnEliminarInv_Cab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInv_CabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarInv_Cab, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsertarInv_Cab, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsertarInv_Cab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarInv_Cab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventario Detalle"));

        tblRegistrosInv_Det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Movimiento", "Cantidad", "Val. Unitario", "Val. Total", "Fecha", "Saldo Cantidad", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRegistrosInv_Det);
        if (tblRegistrosInv_Det.getColumnModel().getColumnCount() > 0) {
            tblRegistrosInv_Det.getColumnModel().getColumn(0).setMinWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(0).setMaxWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(1).setMinWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(1).setMaxWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(5).setMinWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblRegistrosInv_Det.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        btnInsertarInv_Det.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertarInv_Det.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/insertar.png"))); // NOI18N
        btnInsertarInv_Det.setText("INSERTAR");
        btnInsertarInv_Det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarInv_DetActionPerformed(evt);
            }
        });

        btnModificarInv_Det.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnModificarInv_Det.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/modificar.png"))); // NOI18N
        btnModificarInv_Det.setText("MODIFICAR ÚLTIMO REGISTRO");
        btnModificarInv_Det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarInv_DetActionPerformed(evt);
            }
        });

        btnEliminarInv_Det.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEliminarInv_Det.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/eliminar.png"))); // NOI18N
        btnEliminarInv_Det.setText("ELIMINAR ÚLTIMO REGISTRO");
        btnEliminarInv_Det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInv_DetActionPerformed(evt);
            }
        });

        btnMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/softside_inventory/recursos/menu.png"))); // NOI18N
        btnMenu.setText("Volver al Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnReporte.setBackground(new java.awt.Color(0, 128, 213));
        btnReporte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnReporte.setText("Generar Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("DNI:");

        txtDoc.setEditable(false);
        txtDoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Observaciones:");

        txtObs.setEditable(false);
        txtObs.setColumns(20);
        txtObs.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtObs.setRows(4);
        jScrollPane3.setViewportView(txtObs);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Estado:");

        txtEst.setEditable(false);
        txtEst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtUsr.setEditable(false);
        txtUsr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Usuario:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MÁS DETALLES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsr)
                            .addComponent(txtEst)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtDoc)))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInsertarInv_Det, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarInv_Det)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarInv_Det))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenu)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnInsertarInv_Det, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(btnReporte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificarInv_Det, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarInv_Det, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarInv_CabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarInv_CabActionPerformed
        interfaz.insertarInv_Cab();
    }//GEN-LAST:event_btnInsertarInv_CabActionPerformed

    private void btnInsertarInv_DetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarInv_DetActionPerformed
        interfaz.insertarInv_Det(this.tblRegistrosInv_Cab);
    }//GEN-LAST:event_btnInsertarInv_DetActionPerformed

    private void btnModificarInv_DetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarInv_DetActionPerformed
        interfaz.modificarInv_Det(tblRegistrosInv_Cab);
    }//GEN-LAST:event_btnModificarInv_DetActionPerformed

    private void btnEliminarInv_DetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInv_DetActionPerformed
        interfaz.eliminarInv_Det(tblRegistrosInv_Cab);
    }//GEN-LAST:event_btnEliminarInv_DetActionPerformed

    private void btnEliminarInv_CabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInv_CabActionPerformed
        interfaz.eliminarInv_Cab(tblRegistrosInv_Cab, tblRegistrosInv_Det, txtEst);
    }//GEN-LAST:event_btnEliminarInv_CabActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMenuActionPerformed
    {//GEN-HEADEREND:event_btnMenuActionPerformed
        interfaz.menu();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReporteActionPerformed
    {//GEN-HEADEREND:event_btnReporteActionPerformed
        interfaz.generarReporte(tblRegistrosInv_Cab);
    }//GEN-LAST:event_btnReporteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        interfaz.menu();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarInv_Cab;
    private javax.swing.JButton btnEliminarInv_Det;
    private javax.swing.JButton btnInsertarInv_Cab;
    private javax.swing.JButton btnInsertarInv_Det;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificarInv_Det;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblRegistrosInv_Cab;
    private javax.swing.JTable tblRegistrosInv_Det;
    private javax.swing.JTextField txtDoc;
    private javax.swing.JTextField txtEst;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtUsr;
    // End of variables declaration//GEN-END:variables
}
