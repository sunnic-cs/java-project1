package pages;

import OODJAssignment.Admin;
import OODJAssignment.Customer;
import OODJAssignment.OODMSystem;
import OODJAssignment.Staff;
import OODJAssignment.User;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegistrationPage implements ActionListener {
    private final JFrame regPage;
    private final Button nextBtn,backBtn;
    
    private final JRadioButton admin_Role, customer_Role, staff_Role;
    private final JLabel label_pwd, label_userid;
    private final JTextField username,password;
    private final ButtonGroup bgRoles;
    
    public JFrame getJFrame() {
        return regPage;
    }
    
    public JTextField getUsernameField () {
        return username;
    }
    
    public JTextField getPasswordField() {
        return password;
    }
    
    public RegistrationPage() {
    
        regPage = new JFrame("Registration Page");
        regPage.setSize(800,400);
        regPage.setLocation(700,400);
        
        regPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regPage.setLocationRelativeTo(null);
        regPage.setLayout(null);
        
        label_userid = new JLabel("Username :");
        label_userid.setBounds(100,100,100,40);
        username = new JTextField();
        username.setBounds(170,100,260,40);
        
        label_pwd = new JLabel("Password :");
        label_pwd.setBounds(100,150,100,40);
        password = new JTextField();
        password.setBounds(170,150,260,40);
     
        customer_Role = new JRadioButton("Customer");
        customer_Role.setBounds(170,200,90,40);   
        staff_Role = new JRadioButton("Delivery Staff");
        staff_Role.setBounds(260,200,100,40);       
        admin_Role = new JRadioButton ("Admin");
        admin_Role.setBounds(370,200,100,40);
        bgRoles = new ButtonGroup();
        bgRoles.add(customer_Role);
        bgRoles.add(staff_Role);
        bgRoles.add(admin_Role);
        
        nextBtn = new Button("Next");
        nextBtn.setBounds(250,250,70,40);
        nextBtn.addActionListener(this);
        backBtn = new Button("Back");
        backBtn.setBounds (125,250,70,40);
        backBtn.addActionListener(this);
        
        regPage.add(nextBtn);
        regPage.add(label_userid);
        regPage.add(username);
        regPage.add(label_pwd);
        regPage.add(password);
        regPage.add(customer_Role);
        regPage.add(staff_Role);
        regPage.add(admin_Role);
        regPage.add(backBtn);
    }
    
    public boolean emptyFieldChecker() { 
        if(username.getText().equals("")){ // Checking for empty username field
            JOptionPane.showMessageDialog(null,"Please Input your Username!");
        } else if (password.getText().equals("")){ // Checking for empty password field
            JOptionPane.showMessageDialog(null,"Please Input your Password!");
        } else {
            return false;
        }
        return true; 
    }
    
    @Override
    public void actionPerformed (ActionEvent evt) {
        boolean clear = false;
        try {
            if(customer_Role.isSelected() && evt.getSource() == nextBtn){            
                if(emptyFieldChecker()==true){
                    return;
                }    
                Customer c = new Customer("", "Customer", "",
                "", "", "", "", "", 0, "");
                c.register();
                
                regPage.setVisible(false);
                clear = true;
                OODMSystem.loginPage1.getJFrame().setVisible(true);  
                
            } else if(staff_Role.isSelected() && evt.getSource() == nextBtn) {
                
                if(emptyFieldChecker()==true){
                    throw new Exception();
                }
                Staff s = new Staff("","Staff","","","","");
                s.register();
                regPage.setVisible(false);
                clear = true;
                OODMSystem.loginPage1.getJFrame().setVisible(true);
            } else if(admin_Role.isSelected() && evt.getSource() == nextBtn) {
                if(emptyFieldChecker()==true){
                    return;
                }
                Admin a = new Admin("","Admin","","","","");
                a.register();
                regPage.setVisible(false);
                clear = true;
                OODMSystem.loginPage1.getJFrame().setVisible(true);
            } else if(evt.getSource() == backBtn) {
                regPage.setVisible(false);
                clear = true;
                OODMSystem.loginPage1.getJFrame().setVisible(true);
            }
            if(clear == true) {
                username.setText("");
                password.setText("");
                bgRoles.clearSelection();
            }
            else {
                JOptionPane.showMessageDialog(null,"Please select a Role!");
            }      
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Invalid Input, Please Try Again!");
        }  
    }      
}
