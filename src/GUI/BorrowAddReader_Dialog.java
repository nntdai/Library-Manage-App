/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.ReaderBUS;
import MyDesign.ScrollBar;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author QUANG DIEN
 */
public class BorrowAddReader_Dialog extends javax.swing.JDialog {
   ReaderBUS readerBLL;
    /**
     * Creates new form AddReaderBorrow_Dialog
     */
    public BorrowAddReader_Dialog(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException {
        super(parent, modal);
        readerBLL= new ReaderBUS();
        initComponents();
        tbDocGia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showAvalableReader();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        txtDocGia = new MyDesign.MyTextField_Basic();
        try {
            btnChonDocGia =(MyDesign.MyButton)java.beans.Beans.instantiate(getClass().getClassLoader(), "GUI.BorrowAddReader_Dialog_btnChonDocGia");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        spTable = new javax.swing.JScrollPane();
        tbDocGia = new MyDesign.MyTable();
        try {
            jLabel3 =(javax.swing.JLabel)java.beans.Beans.instantiate(getClass().getClassLoader(), "GUI.BorrowAddReader_Dialog_jLabel3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtDocGia.setBackground(new java.awt.Color(229, 229, 229));
        txtDocGia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtDocGia.setPreferredSize(new java.awt.Dimension(188, 36));
        txtDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocGiaActionPerformed(evt);
            }
        });

        btnChonDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonDocGiaActionPerformed(evt);
            }
        });

        spTable.setBorder(null);

        tbDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã độc giả", "Tên độc giả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tbDocGia);

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btnChonDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChonDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonDocGiaActionPerformed
      int i=tbDocGia.getSelectedRow();
      if (i>=0)
      {
          dispose();
      }
      else
      {
          JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả !");
      }
        
    }//GEN-LAST:event_btnChonDocGiaActionPerformed

    private void txtDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocGiaActionPerformed
        String name =txtDocGia.getText().trim();
        ((DefaultTableModel)tbDocGia.getModel()).setRowCount(0);
       try {
           for (int i=0;i<readerBLL.getReaderByName(name).size();i++)
           {
               tbDocGia.addRow(new Object[]{i+1, readerBLL.getReaderByName(name).get(i).
                       getPersonID(),readerBLL.getReaderByName(name).get(i).getName()});
           }  } catch (ClassNotFoundException ex) {
           Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_txtDocGiaActionPerformed
 public String getNameReader()
 {
     int i = tbDocGia.getSelectedRow();
     return tbDocGia.getValueAt(i,2 ).toString();
 }
 public int getIDDocGia()
 {
     int i = tbDocGia.getSelectedRow();
     int idDocGia = Integer.parseInt(tbDocGia.getValueAt(i,1).toString());
     return idDocGia;
 }
    private void showAvalableReader() throws ClassNotFoundException, SQLException
{
    
    for (int i=0;i<readerBLL.getAll().size();i++)
    {
        tbDocGia.addRow(new Object[]{i+1, readerBLL.getAll().get(i).getPersonID(),readerBLL.getAll().get(i).getName()});
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BorrowAddReader_Dialog dialog = null;
                try {
                    dialog = new BorrowAddReader_Dialog(new javax.swing.JFrame(), true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(BorrowAddReader_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnChonDocGia;
    private javax.swing.JLabel jLabel3;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbDocGia;
    private MyDesign.MyTextField_Basic txtDocGia;
    // End of variables declaration//GEN-END:variables
}
