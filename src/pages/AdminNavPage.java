package pages;

import OODJAssignment.OODMSystem;
import java.awt.Button;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminNavPage implements ActionListener {
    private final JFrame admNavPage;
    private Button userBtn,catBtn,deliverBtn,recordBtn,backBtn,repBtn;
    private final JLabel title;
    
    public AdminNavPage() {
    admNavPage = new JFrame("ADMIN PAGE");
    admNavPage.setSize(800,400);
    admNavPage.setLocation(700,400);
    admNavPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    admNavPage.setLocationRelativeTo(null);
    admNavPage.setLayout(null); 
    
    title= new JLabel("Admin Navigation Page");
    title.setBounds(265,30,600,20);
    title.setFont(new Font("Consolas",Font.BOLD,20));
    
    userBtn = new Button("User Management");
    userBtn.setBounds(150,120,200,40);
    userBtn.addActionListener(this);
    catBtn = new Button("Item/Category Management");
    catBtn.setBounds(400,120,200,40);
    catBtn.addActionListener(this);
    deliverBtn = new Button("Delivery Management");
    deliverBtn.setBounds(150,180,200,40);
    deliverBtn.addActionListener(this);
    recordBtn = new Button("Record Function");
    recordBtn.setBounds(400,180,200,40);
    recordBtn.addActionListener(this);
    backBtn = new Button("Back");
    backBtn.setBounds (125,250,70,40);
    backBtn.addActionListener(this);
    repBtn = new Button("Report Page");
    repBtn.setBounds(240,250,100,40);
    repBtn.addActionListener(this);
    
    admNavPage.add(repBtn);
    admNavPage.add(recordBtn);
    admNavPage.add(deliverBtn);
    admNavPage.add(userBtn);
    admNavPage.add(catBtn);
    admNavPage.add(title);
    admNavPage.add(backBtn);
    admNavPage.setVisible(false);
    }
    
    public JFrame getJFrame() {
        return admNavPage;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            if (evt.getSource()==userBtn) {
                admNavPage.setVisible(false);
                OODMSystem.userManagePage1.getJFrame().setVisible(true);
            } else if (evt.getSource()==catBtn) {
                admNavPage.setVisible(false);
                OODMSystem.catManagePage1.getJFrame().setVisible(true);
            } else if (evt.getSource()==deliverBtn) {
                admNavPage.setVisible(false);
                OODMSystem.deliveryManagePage1.updateComboBox();
                OODMSystem.deliveryManagePage1.getJFrame().setVisible(true);
            } else if (evt.getSource()==recordBtn) {
                String[] opt = {"Payment" , "Order"};
                int choice = JOptionPane.showOptionDialog(null,"Payment Record? or Order Record?","Payment/Order",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]);
                switch (choice) {
                    case JOptionPane.YES_OPTION:
                        // User chose "Payment"
                        OODMSystem.payManagePage1.getJFrame().setVisible(true);
                        admNavPage.setVisible(false);
                        break;
                    case JOptionPane.NO_OPTION:
                        OODMSystem.orderManagePage1.getJFrame().setVisible(true);
                        admNavPage.setVisible(false);
                        break;
                    default:
                        break;
                }
            } else if (evt.getSource()==backBtn) {
                admNavPage.setVisible(false);
                OODMSystem.loginPage1.getJFrame().setVisible(true);
            } else if(evt.getSource()==repBtn) {
                admNavPage.setVisible(false);
                AdminReportPage rep = new AdminReportPage();
                rep.getJFrame().setVisible(true);
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"Invalid Input!");
        }
    }
}
