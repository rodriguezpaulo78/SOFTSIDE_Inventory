package softside_inventory.vistas.inventario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

/**
 * Vista principal de Gestion de Inventario
 * @author SOFTSIDE
 */
public class VistaInventario extends javax.swing.JFrame
{
    //private IKardex interfaz;
    
    public VistaInventario()
    {
        this.setVisible(true);
        this.setTitle("GESTION DE INVENTARIO");
        initComponents();
        setLocationRelativeTo(null);
        
        //this.interfaz = interfaz;
        //interfaz.cargar(tblRegistrosKC);
        
        ListSelectionModel cellSelectionModel = tblRegistrosKC.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                //interfaz.actualizar(tblRegistrosKC, tblRegistrosKD, txtCan, txtValUni, txtValTot);
            }
        });
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrosKC = new javax.swing.JTable()
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
        btnInsertarKC = new javax.swing.JButton();
        btnEliminarKC = new javax.swing.JButton();

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
        jLabel19.setText("GESTIÓN DE INVENTARIOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario Cabecera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblRegistrosKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblRegistrosKC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblRegistrosKC);
        if (tblRegistrosKC.getColumnModel().getColumnCount() > 0) {
            tblRegistrosKC.getColumnModel().getColumn(0).setMinWidth(120);
            tblRegistrosKC.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblRegistrosKC.getColumnModel().getColumn(0).setMaxWidth(120);
            tblRegistrosKC.getColumnModel().getColumn(3).setMinWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnInsertarKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertarKC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/insertar.png"))); // NOI18N
        btnInsertarKC.setText("INSERTAR");
        btnInsertarKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarKCActionPerformed(evt);
            }
        });

        btnEliminarKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminarKC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/eliminar.png"))); // NOI18N
        btnEliminarKC.setText("ELIMINAR");
        btnEliminarKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsertarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsertarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                .addContainerGap(467, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        //interfaz.menu();
    }//GEN-LAST:event_formWindowClosing

    private void btnEliminarKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarKCActionPerformed
        //interfaz.eliminarKC(tblRegistrosKC, tblRegistrosKD, txtEst);
    }//GEN-LAST:event_btnEliminarKCActionPerformed

    private void btnInsertarKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarKCActionPerformed
        //interfaz.insertarKC();
    }//GEN-LAST:event_btnInsertarKCActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarKC;
    private javax.swing.JButton btnInsertarKC;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRegistrosKC;
    // End of variables declaration//GEN-END:variables
}
