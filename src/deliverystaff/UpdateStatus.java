package deliverystaff;


import OODJAssignment.DataIO;
import OODJAssignment.Delivery;
import OODJAssignment.OODMSystem;
import javax.swing.ButtonGroup;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UpdateStatus extends javax.swing.JFrame {

    public UpdateStatus() { // Method for updating delivery status
        initComponents(); 
        statusTABLE(); 
        ButtonGroup status = new ButtonGroup();
        status.add(pending);
        status.add(outFORdelivery); 
        status.add(deliverySuccess);
        
        Delivery a = null;
        for(Delivery d : DataIO.allDelivery) {
            if(OODMSystem.onlineStaff.getId().equals(d.getStaffid().getId()) && !d.getStatus()) {
                a = d;
                break;
            }
        }
        if(a!= null) {
            switch (a.getNowstatus()) {
                case "Preparing":
                    pending.setSelected(true);
                    break;
                case "Out For Delivery":
                    outFORdelivery.setSelected(true);
                    break;
                case "Delivery Success":
                    deliverySuccess.setSelected(true);
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null,"No Delivery Found!");
        }
    }

    
    private void statusTABLE(){ // status table
    
        for(Delivery d : DataIO.allDelivery) {
            if(OODMSystem.onlineStaff.getId().equals(d.getStaffid().getId()) && !d.getStatus()) {
                      
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                String s1[]=new String []{"Current Status"};
                defaultTableModel.setColumnIdentifiers(s1);
                String[] data = new String[1];
                data[0] = d.getNowstatus();
                defaultTableModel.addRow(data);
                statusTable.setModel(defaultTableModel);
                statusTable.setVisible(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pending = new javax.swing.JRadioButton();
        deliverySuccess = new javax.swing.JRadioButton();
        outFORdelivery = new javax.swing.JRadioButton();
        UPDATE = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        statusTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        LOGINPAGE = new javax.swing.JButton();
        EXIT = new javax.swing.JButton();
        BACK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 23)); // NOI18N
        jLabel1.setText("Let's update status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(255, 102, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        pending.setBackground(new java.awt.Color(153, 153, 255));
        pending.setText("           Preparing");
        pending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingActionPerformed(evt);
            }
        });

        deliverySuccess.setBackground(new java.awt.Color(153, 153, 255));
        deliverySuccess.setText("       Delivery Complete");
        deliverySuccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliverySuccessActionPerformed(evt);
            }
        });

        outFORdelivery.setBackground(new java.awt.Color(153, 153, 255));
        outFORdelivery.setText("     Out for delivery");
        outFORdelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outFORdeliveryActionPerformed(evt);
            }
        });

        UPDATE.setText("UPDATE");
        UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEActionPerformed(evt);
            }
        });

        statusTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        statusTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        statusTable.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(statusTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pending, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(outFORdelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deliverySuccess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(UPDATE)
                        .addGap(97, 97, 97))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pending)
                .addGap(18, 18, 18)
                .addComponent(outFORdelivery)
                .addGap(18, 18, 18)
                .addComponent(deliverySuccess)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(UPDATE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        LOGINPAGE.setText("LOGIN PAGE");
        LOGINPAGE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINPAGEActionPerformed(evt);
            }
        });

        EXIT.setText("EXIT>>");
        EXIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXITActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(LOGINPAGE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EXIT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LOGINPAGE)
                    .addComponent(EXIT)))
        );

        BACK.setText("<<BACK");
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BACK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
          
    
    private void pendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingActionPerformed

    }//GEN-LAST:event_pendingActionPerformed

    
    private void outFORdeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outFORdeliveryActionPerformed

    }//GEN-LAST:event_outFORdeliveryActionPerformed

    private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEActionPerformed
        Delivery a = null; // when user clicked on update button
        // will check for the delivery and update the delivery according to the status selected by user
        for(Delivery d : DataIO.allDelivery) {
            if(OODMSystem.onlineStaff.getId().equals(d.getStaffid().getId()) && !d.getStatus()) {
                a = d;
                break;
            }
        }
        if(a!= null) {
            if(pending.isSelected()) {
                a.setNowstatus("Preparing");
            } else if (outFORdelivery.isSelected()) {
                a.setNowstatus("Out For Delivery");
            } else if (deliverySuccess.isSelected()) {
                a.setNowstatus("Delivery Success");
                a.setStatus(true);
                JOptionPane.showMessageDialog(null,"Delivery completed!!");
                systemExit();
                setVisible(false);
                feedback Info = new feedback(); 
                Info.setVisible(true);
                return;
            }
            JOptionPane.showMessageDialog(null,"Updated!");
            DataIO.writeToFile();
            statusTABLE();
        } else {
            JOptionPane.showMessageDialog(null,"No Delivery Found!");
        }
    }//GEN-LAST:event_UPDATEActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        systemExit();
        setVisible(false);
        deliverystaffmenu Info = new deliverystaffmenu(); 
        Info.setVisible(true);
    }//GEN-LAST:event_BACKActionPerformed

    private void LOGINPAGEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINPAGEActionPerformed
        systemExit();
        setVisible(false);
        OODMSystem.loginPage1.getJFrame().setVisible(true);
    }//GEN-LAST:event_LOGINPAGEActionPerformed

    private void EXITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXITActionPerformed
       System.exit(0);
    }//GEN-LAST:event_EXITActionPerformed

    private void deliverySuccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliverySuccessActionPerformed

    }//GEN-LAST:event_deliverySuccessActionPerformed


    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BACK;
    private javax.swing.JButton EXIT;
    private javax.swing.JButton LOGINPAGE;
    private javax.swing.JButton UPDATE;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton deliverySuccess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton outFORdelivery;
    private javax.swing.JRadioButton pending;
    private javax.swing.JTable statusTable;
    // End of variables declaration//GEN-END:variables
    private void systemExit(){
        WindowEvent winCloseing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    }
}
