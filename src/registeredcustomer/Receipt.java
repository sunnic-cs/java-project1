/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registeredcustomer;

import OODJAssignment.Cart;
import OODJAssignment.CartDetails;
import OODJAssignment.Customer;
import OODJAssignment.DataIO;
import OODJAssignment.OODMSystem;
import OODJAssignment.Order;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaede
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Receipt
     */
    public Receipt() {
        initComponents();
        Order o = Customer.getPaidOrder();
        latestReceipt(o);
    }
  
    public final void latestReceipt(Order o) {
        receiptPRINT.setText("");
        receiptPRINT.append("***************************************************************************\n");  
        receiptPRINT.append("                                             Your Receipt                \n");
        receiptPRINT.append("***************************************************************************\n");  
        receiptPRINT.append("   Qty         |           Item                     |        Price(RM)      |    Sub amount  \n");
        receiptPRINT.append("------------------------------------------------------------------------------------\n\n");
        //Order o = Customer.getPaidOrder();
        if(o!=null) {
            Cart c = null;
            for(Cart ca : DataIO.allCart) {
                if(ca.getCart_id().equals(o.getOrdered_cart().getCart_id())) {
                    c = ca;
                    break;
                }
            }
            double sum = 0;
            for(CartDetails k : c.getOrderList()) {
                sum = sum + k.getTotal();
                String qty = (Integer.toString((int)k.getQty()));
                String items = k.getItem();
                double price = (k.getTotal()/k.getQty());
                String price2 = (Double.toString((int)price));
                double subtotal = k.getTotal();
                String subtotal2 = (Double.toString((int)subtotal));
                receiptPRINT.append(" "+qty+"\t"+items+"\t\t"+price2+"\t"+subtotal2+"\n\n");                                           
            }            
            double total = sum;    
            LocalDate today = LocalDate.now();               
            receiptPRINT.append("\n------------------------------------------------------------------------------------\n");
            receiptPRINT.append("\tTotal amount : \t\t"+Double.toString((double)total)); 
            receiptPRINT.append("\n\tOrder Date : \t\t"+today);
            receiptPRINT.append("\n------------------------------------------------------------------------------------\n"); 
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptPRINT = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        BACK = new javax.swing.JButton();
        BACKLOGINPAGE = new javax.swing.JButton();
        EXIT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        jLabel1.setText("RECEIPT");

        receiptPRINT.setEditable(false);
        receiptPRINT.setColumns(20);
        receiptPRINT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        receiptPRINT.setRows(5);
        jScrollPane1.setViewportView(receiptPRINT);

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 102));
        jLabel2.setText("Your order is successfull!!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        BACK.setText("<<BACK");
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });

        BACKLOGINPAGE.setText("LOGINPAGE");
        BACKLOGINPAGE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKLOGINPAGEActionPerformed(evt);
            }
        });

        EXIT.setText("EXIT>>");
        EXIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXITActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BACK, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(BACKLOGINPAGE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EXIT)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BACKLOGINPAGE)
                    .addComponent(BACK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EXIT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BACKLOGINPAGEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKLOGINPAGEActionPerformed
        
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to back login page?\nThis receipt can be seen only once!!\n"
            ,"FINAL CONFIRM",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            systemExit();
            setVisible(false);
            OODMSystem.loginPage1.getJFrame().setVisible(true);
        } 

    }//GEN-LAST:event_BACKLOGINPAGEActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to leave this page?\nThis receipt can be seen only once!!\n"
            ,"FINAL CONFIRM",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            systemExit();
            setVisible(false);
            regicustomermenu Menu = new regicustomermenu();
            Menu.setVisible(true); 
        }

        
       
    }//GEN-LAST:event_BACKActionPerformed

    private void EXITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXITActionPerformed
        
        if (JOptionPane.showConfirmDialog(null, "Are you sure want to exit?\nThis receipt can be seen only one time!!\n"
                ,"FINAL CONFIRM",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        System.exit(0);
        }

    }//GEN-LAST:event_EXITActionPerformed

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
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JButton BACKLOGINPAGE;
    private javax.swing.JButton EXIT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea receiptPRINT;
    // End of variables declaration//GEN-END:variables
    private void systemExit(){
        WindowEvent winCloseing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    }

}