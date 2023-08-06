package pages;

import OODJAssignment.Admin;
import OODJAssignment.Customer;
import OODJAssignment.DataIO;
import OODJAssignment.OODMSystem;
import OODJAssignment.Staff;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            boolean done = false;
            if(showPassword.isSelected()) {
                pwd.setEchoChar((char)0);
            } else {
                pwd.setEchoChar('\u2022');
            }
            if(evt.getSource()==Register) {
                lGPage.setVisible(false);
                OODMSystem.regPage1.getJFrame().setVisible(true);
            } else if(evt.getSource()==Login && customer_Role.isSelected()) {
                Customer found = DataIO.checkCustomerUsername(OODMSystem.loginPage1.getUserId().getText());
                if (found==null || !OODMSystem.loginPage1.getPwd().getText().equals(found.getPassword())) {
                    throw new Exception();
                }
                OODMSystem.onlineCustomer = found;
                lGPage.setVisible(false);
                OODMSystem.regicosutomer.setVisible(true);
                 
                done = true;
            } else if(evt.getSource()==Login && staff_Role.isSelected()) {
                Staff found = DataIO.checkStaffUsername(userid.getText());
                if (found==null || !pwd.getText().equals(found.getPassword())) {
                    throw new Exception();
                }
                lGPage.setVisible(false);
                OODMSystem.deliverymenu.setVisible(true);
                OODMSystem.onlineStaff = found;
                done=true;
            } else if(evt.getSource()==Login && admin_Role.isSelected()) {
                Admin found = DataIO.checkAdminUsername(userid.getText());
                if (found==null || !pwd.getText().equals(found.getPassword())) {
                    throw new Exception();
                }
                lGPage.setVisible(false);
                OODMSystem.adminPage1.getJFrame().setVisible(true);
                done=true;
            } else if(evt.getSource()==Guest) {
                OODMSystem.GUESTPAGE.setVisible(true);
                lGPage.setVisible(false);
            } else if(evt.getSource()==Reset) {
                done=true;
            }
            else if(evt.getSource()==Exit) {
                DataIO.writeToFile();
                System.exit(0);
            }
            if(done==true) {
                userid.setText("");
                pwd.setText("");
                bgRoles.clearSelection();
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(lGPage,"Username/Password Invalid!");
        }
    }
    
    
    private final JFrame lGPage;
    private final Button Register, Login, Guest, Exit, Reset;
    private final JPasswordField pwd;
    private final JTextField userid;
    private final JLabel label_pwd, label_userid,statusmsg,title,roles;
    private final JRadioButton customer_Role,staff_Role,admin_Role;
    private final JCheckBox showPassword;
    private final ButtonGroup bgRoles;
    
    public JFrame getJFrame(){
        return lGPage;
    }
    
    public JTextField getUserId() {
        return userid;
    }
    
    public JPasswordField getPwd() {
        return pwd;
    }
    
    public LoginPage () {
        lGPage = new JFrame("LOGIN PAGE");
        lGPage.setSize(800,400);
        lGPage.setLocation(700,400);
        
        lGPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lGPage.setLocationRelativeTo(null);    
        
        roles = new JLabel("Register AS");
        label_userid = new JLabel("Username :");
        label_userid.setBounds(100,100,100,40);
        
        label_pwd = new JLabel("Password :");
        label_pwd.setBounds(100,150,100,40);
        
        userid = new JTextField();
        userid.setBounds(170,100,300,40);
        
        pwd = new JPasswordField();
        pwd.setBounds(170,150,300,40);
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(168,193,300,20);
        showPassword.addActionListener(this);
        
        title= new JLabel("Online Order and Delivery Management System");
        title.setBounds(120,30,600,20);
        title.setFont(new Font("Consolas",Font.BOLD,20));
        
        statusmsg = new JLabel("Please Input your Username and Password !!");
        statusmsg.setForeground(Color.red);
        statusmsg.setBounds(170,70,320,40);
        
        Register = new Button("Register");
        Register.setBounds(170,220,70,30);
        Register.addActionListener(this);
        
        Login = new Button("Login");
        Login.setBounds(250,220,70,30);
        Login.addActionListener(this);
        
        Guest = new Button("Guest");
        Guest.setBounds(330,220,70,30);
        Guest.addActionListener(this);
        
        Reset = new Button("Reset");
        Reset.setBounds(410,220,70,30);
        Reset.addActionListener(this);
              
        Exit = new Button("Exit");
        Exit.setBounds(490,220,70,30);
        Exit.addActionListener(this);
        
        customer_Role = new JRadioButton("Customer");
        customer_Role.setBounds(500,100,90,20);   
        staff_Role = new JRadioButton("Delivery Staff");
        staff_Role.setBounds(500,130,100,20);       
        admin_Role = new JRadioButton ("Admin");
        admin_Role.setBounds(500,160,100,20);
        bgRoles = new ButtonGroup();
        bgRoles.add(customer_Role);
        bgRoles.add(staff_Role);
        bgRoles.add(admin_Role);
   
        // to show the button
        lGPage.setLayout(null); 
        
        // to add button layout
        lGPage.add(showPassword);
        lGPage.add(customer_Role);
        lGPage.add(staff_Role);
        lGPage.add(admin_Role);
        lGPage.add(Register);
        lGPage.add(Login);
        lGPage.add(Guest);
        lGPage.add(Reset);
        lGPage.add(Exit);
        
        lGPage.add(userid);
        lGPage.add(label_userid);
        lGPage.add(label_pwd);
        lGPage.add(pwd);
        lGPage.add(statusmsg);
        lGPage.add(title);
        lGPage.add(roles);
        lGPage.setVisible(true);
    }
}
