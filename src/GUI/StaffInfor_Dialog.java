/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import BLL.staffBLL;
import DTO.Readers;
import DTO.Staff;

/**
 *
 * @author QUANG DIEN
 */
public class StaffInfor_Dialog extends javax.swing.JDialog {
	staffBLL sBLL;
	Staff a;
    /**
     * Creates new form StaffAdd_Dialog
     */
    public StaffInfor_Dialog(java.awt.Frame parent, boolean modal,int id,MyDesign.MyTable tab,String roleID,int userID) {
        super(parent, modal);
        try {
			sBLL=new staffBLL();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
        initComponents(roleID,userID,tab);
        upData(id);
    }
    
    public void addDefault(MyDesign.MyTable tab,String roleID) throws Exception{
    	tab.setRowCount(0);
        if(roleID=="AD") {
        	addDefaultAD(tab);
        }
        if(roleID=="QL") {
        	addDefaultQL(tab);
        }
    }
    
    public void addDefaultAD(MyDesign.MyTable tab) throws Exception{
        Vector<Staff> arr=sBLL.getAllAD();
        for(int i=0;i<arr.size();i++){
            Staff acc=arr.get(i);
            int id=acc.getId();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            String username=acc.getUsername();
            String role=acc.getRole();
            Object row[] = {i+1,id,name,username,role,tel,address};
            tab.addRow(row);
        }
    }
    
    public void addDefaultQL(MyDesign.MyTable tab) throws Exception{
        Vector<Staff> arr=sBLL.getAllQL();
        for(int i=0;i<arr.size();i++){
            Staff acc=arr.get(i);
            int id=acc.getId();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            String username=acc.getUsername();
            String role=acc.getRole();
            Object row[] = {i+1,id,name,username,role,tel,address};
            tab.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void upData(int id) {
    	try {
			a=sBLL.findbyID(id);
			txtTen.setText(a.getName());
			txtSoDienThoai.setText(a.getTel());
			txtDiaChi.setText(a.getAddress());
			txtUsername.setText(a.getUsername());
			cbChucVu.setSelectedItem(a.getRole());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
    }
    
    public boolean checkDataVal(String name,String tel,String address,String username,String password) throws HeadlessException, FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	if(name.equals("")) {
    		JOptionPane.showMessageDialog(null,"Họ và tên không được để trống");
    		txtTen.requestFocus();
    		return false;
    	}
    	String nameReg = "^[\\p{L} \\.'\\-]+$";
    	if(!name.matches(nameReg)) {
    		JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ");
    		txtTen.requestFocus();
    		return false;
    	}
    	if(tel.equals("")) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại không được để trống");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	if(!sBLL.checkTelExcept(tel,a.getId())) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại đã có trong dữ liệu");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	String telReg = "^0[1-9][0-9]{8}$";
    	if(!tel.matches(telReg)) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	if(address.equals("")) {
    		JOptionPane.showMessageDialog(null,"Địa chỉ không được để trống");
    		txtDiaChi.requestFocus();
    		return false;
    	}
    	if(username.equals("")) {
    		JOptionPane.showMessageDialog(null,"Tên tài khoản không được để trống");
    		txtUsername.requestFocus();
    		return false;
    	}
    	String clName=(String) cbChucVu.getSelectedItem();
    	if(clName.trim().toUpperCase().equals("Chức vụ".toUpperCase())) {
    		JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ");
    		return false;
    	}
    	return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String roleID,int userID,MyDesign.MyTable tab) {

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtTen = new MyDesign.MyTextField_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtSoDienThoai = new MyDesign.MyTextField_Basic();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new MyDesign.MyTextField_Basic();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new MyDesign.MyTextField_Basic();
        jLabel12 = new javax.swing.JLabel();
        txtMatKhau = new MyDesign.MyTextField_Basic();
        jLabel13 = new javax.swing.JLabel();
        cbChucVu = new javax.swing.JComboBox<>();
        btnXoaNhanVien = new MyDesign.MyButton();
        btnSuaThongTin = new MyDesign.MyButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Tên");

        txtTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Số điện thoại");

        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Địa chỉ");

        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel22.setForeground(new java.awt.Color(127, 127, 127));
        jLabel22.setText("Thông tin cá nhân");

        jLabel23.setForeground(new java.awt.Color(127, 127, 127));
        jLabel23.setText("Thông tin tài khoản");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Username");

        txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Mật khẩu mới");

        txtMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("Chức vụ");

        cbChucVu.setBackground(new java.awt.Color(246, 250, 255));
        cbChucVu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ" }));
        cbChucVu.setBorder(null);
        cbChucVu.setOpaque(true);
        cbChucVu.setPreferredSize(new java.awt.Dimension(77, 28));
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbChucVu.getModel();
        try {
        	if(roleID=="QL") {
        		model.addAll(sBLL.getRoleAD());
        	}
        	if(roleID=="AD") {
        		model.addAll(sBLL.getRoleAll());
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
        cbChucVu.revalidate();
        cbChucVu.repaint();

        btnXoaNhanVien.setBackground(new java.awt.Color(255, 241, 241));
        btnXoaNhanVien.setForeground(new java.awt.Color(248, 67, 67));
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-delete-white.png"))); // NOI18N
        btnXoaNhanVien.setText("Xóa nhân viên");
        btnXoaNhanVien.setBorderColor(new java.awt.Color(255, 241, 241));
        btnXoaNhanVien.setColor(new java.awt.Color(255, 241, 241));
        btnXoaNhanVien.setColorOver(new java.awt.Color(255, 241, 241));
        btnXoaNhanVien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXoaNhanVien.addActionListener(new ActionListener() {
         	 @Override
            public void actionPerformed(ActionEvent e){
       		try {
       			if(!a.getRoleID().equals(roleID)) {
       				int diaRS=JOptionPane.showConfirmDialog(null,"Bạn có chắc xoá nhân viên này?");
						if(diaRS==JOptionPane.YES_OPTION){
							JOptionPane.showConfirmDialog(null,sBLL.eraShowStaff(a));
 							addDefault(tab,roleID);
 							dispose();
						}
       			}else {
						JOptionPane.showMessageDialog(null,"Bạn không có quyền xoá nhân viên này");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
       }});

        btnSuaThongTin.setBackground(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-refresh-white.png"))); // NOI18N
        btnSuaThongTin.setText("Sửa thông tin");
        btnSuaThongTin.setBorderColor(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setColor(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSuaThongTin.addActionListener(new ActionListener() {
          	 @Override
             public void actionPerformed(ActionEvent e){
        		 	String name=txtTen.getText().trim();
        		    String tel=txtSoDienThoai.getText().trim();
        		    String address=txtDiaChi.getText().trim();
        		    String username=txtUsername.getText().trim();
        		    String password=txtMatKhau.getText().trim();
        		    String role=(String) cbChucVu.getSelectedItem();
        		    try {
 						if(!a.compare(name, tel, address,username,password,role)) {
 							if(!a.getRoleID().equals(roleID) || (a.getRoleID().equals(roleID) && a.getId()==userID)) {
	 							int diaRS=JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn sửa thông tin nhân viên này?");
	 							if(diaRS==JOptionPane.YES_OPTION){
		 		 					if(checkDataVal(name,tel,address,username,password)) {
			 							a.setName(name);
			 							a.setTel(tel);
			 							a.setAddress(address);
			 							a.setUsername(username);
			 							a.setPassword(password);
			 							a.setRole(role);
			 							JOptionPane.showMessageDialog(null,sBLL.updateOneStaff(a));
			 							addDefault(tab,roleID);
			 							dispose();
		 		 					}
	 							}
 							}else {
 								JOptionPane.showMessageDialog(null,"Bạn không có quyền sửa nhân viên này");
 							}
 						}
 				} catch (Exception e1) {
 					// TODO Auto-generated catch block
 					JOptionPane.showMessageDialog(null,e1.getMessage());
 				}
        	 }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23)
                            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(27, 27, 27)
                                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel22)))
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(cbChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/staff-white.png"))); // NOI18N
        jLabel4.setText("Thông tin chi tiết");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                StaffInfor_Dialog dialog = new StaffInfor_Dialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnSuaThongTin;
    private MyDesign.MyButton btnXoaNhanVien;
    private javax.swing.JComboBox<String> cbChucVu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private MyDesign.MyTextField_Basic txtDiaChi;
    private MyDesign.MyTextField_Basic txtMatKhau;
    private MyDesign.MyTextField_Basic txtSoDienThoai;
    private MyDesign.MyTextField_Basic txtTen;
    private MyDesign.MyTextField_Basic txtUsername;
    // End of variables declaration//GEN-END:variables
}
