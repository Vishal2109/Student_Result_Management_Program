/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package result_management_project;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author visha
 */
public class adminIndex extends javax.swing.JFrame {

    /**
     * Creates new form adminIndex
     */
    public adminIndex() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        adminRegisterButton = new javax.swing.JButton();
        adminIndexBack = new javax.swing.JButton();
        adminLoginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));
        setPreferredSize(new java.awt.Dimension(700, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Username:");
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 18));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 100, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Password:");
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 18));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 100, 30));

        usernameField.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        usernameField.setText("username");
        usernameField.setPreferredSize(new java.awt.Dimension(70, 18));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 170, 30));

        passwordField.setText("password");
        passwordField.setPreferredSize(new java.awt.Dimension(70, 18));
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 170, 30));

        adminRegisterButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        adminRegisterButton.setText("Register");
        adminRegisterButton.setPreferredSize(new java.awt.Dimension(72, 10));
        adminRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRegisterButtonActionPerformed(evt);
            }
        });
        getContentPane().add(adminRegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 140, 30));

        adminIndexBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        adminIndexBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/result_management_project/left-25.png"))); // NOI18N
        adminIndexBack.setText("Back");
        adminIndexBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminIndexBackActionPerformed(evt);
            }
        });
        getContentPane().add(adminIndexBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 30));

        adminLoginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        adminLoginButton.setText("Login");
        adminLoginButton.setPreferredSize(new java.awt.Dimension(72, 10));
        adminLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLoginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(adminLoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 140, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void adminIndexBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminIndexBackActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new index().setVisible(true);
    }//GEN-LAST:event_adminIndexBackActionPerformed

//    admin login 
    private void adminLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLoginButtonActionPerformed
        // TODO add your handling code here:
        String userName = usernameField.getText();
        String password = passwordField.getText();

        try{
//          connects to data base
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srms", "root", "Qwerty@2109");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM credentials WHERE username='"+userName+"'");
//          checks if entered username and password exists in database
            if(rs.next()){
                if(userName.compareTo(rs.getString(1))==0 && password.compareTo(rs.getString(2))==0){
                    setVisible(false);
                    new addNewStudent().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_adminLoginButtonActionPerformed

//    register new admin button
    private void adminRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminRegisterButtonActionPerformed
        // TODO add your handling code here:
       JOptionPane.showMessageDialog(null, "Contact admin department.", "", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_adminRegisterButtonActionPerformed

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
            java.util.logging.Logger.getLogger(adminIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminIndexBack;
    private javax.swing.JButton adminLoginButton;
    private javax.swing.JButton adminRegisterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
