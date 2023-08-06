
package pages;

import OODJAssignment.Admin;
import OODJAssignment.Cart;
import OODJAssignment.CartDetails;
import OODJAssignment.Customer;
import OODJAssignment.DataIO;
import OODJAssignment.Delivery;
import OODJAssignment.OODMSystem;
import OODJAssignment.Order;
import OODJAssignment.Payment;
import OODJAssignment.Staff;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserManagementPage implements ActionListener{
    private final JFrame userMngPage;
    private final Button add,search,modify,delete,backBtn,view;
    private final JRadioButton customerR,staffR,adminR;
    private final JLabel title,msg;
    private JTable viewTable;
    private JScrollPane scp;
    private DefaultTableModel tableModel = new DefaultTableModel(); // new DefaultTableModel(data,tableHeader);
    private String[] tableHeader = null;
    private String[][] data = null;
    
    public UserManagementPage()  {
        
        userMngPage = new JFrame("USER MANAGEMENT PAGE");
        userMngPage.setSize(800,400);
        userMngPage.setLocation(700,400);
        userMngPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userMngPage.setLocationRelativeTo(null);
        userMngPage.setLayout(null);
        userMngPage.setVisible(false);
        
        view = new Button("View User");
        view.setBounds (460,110,80,30);
        view.addActionListener(this);
        userMngPage.add(view);
        
        backBtn = new Button("Back");
        backBtn.setBounds (160,110,80,30);
        backBtn.addActionListener(this);
        userMngPage.add(backBtn);
        
        title= new JLabel("User Management");
        title.setBounds(270,30,600,20);
        title.setFont(new Font("Consolas",Font.BOLD,20));
        
        msg = new JLabel("Select an User Type and Function!");
        msg.setBounds(255,60,200,20);
        msg.setForeground(Color.red);
        
        customerR = new JRadioButton("Customer");
        customerR.setBounds(230,170,90,40);
        staffR = new JRadioButton("Delivery Staff");
        staffR.setBounds(320,170,100,40);
        adminR = new JRadioButton ("Admin");
        adminR.setBounds(430,170,90,40);
        ButtonGroup bgRoles = new ButtonGroup();
        bgRoles.add(customerR);
        bgRoles.add(staffR);
        bgRoles.add(adminR);
        
        add = new Button("Add User");
        search = new Button("Search User");
        modify = new Button("Modify User");
        delete = new Button("Delete User");
        
        add.addActionListener(this);
        search.addActionListener(this);
        modify.addActionListener(this);
        delete.addActionListener(this);
        
        add.setBounds(260,90,80,30);
        search.setBounds(260,130,80,30);
        modify.setBounds(360,90,80,30);
        delete.setBounds(360,130,80,30);
        
        userMngPage.add(customerR);
        userMngPage.add(staffR);
        userMngPage.add(adminR);
        userMngPage.add(msg);
        userMngPage.add(title);
        userMngPage.add(add);
        userMngPage.add(search);
        userMngPage.add(modify);
        userMngPage.add(delete);
        
    }
    
    private void tableComponents() {
        tableModel = new DefaultTableModel(data,tableHeader);
        if(viewTable!=null) {
            userMngPage.remove(scp);
        }
        viewTable = new JTable(tableModel);
        viewTable.setBounds(50,230,700,120);
        viewTable.getTableHeader().setBounds(50,210,700,20); 
        scp = new JScrollPane(viewTable);
        scp.setBounds(50,230,700,130);
        userMngPage.add(scp);  
    }
  
    public JFrame getJFrame() {
        return userMngPage;
    }
            
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            if (evt.getSource() == add) {
                if (customerR.isSelected()) {
                    Admin.addUser("Customer");
                } else if (staffR.isSelected()) {
                    Admin.addUser("Staff");
                } else if (adminR.isSelected()) {
                    Admin.addUser("Admin");
                }
            } else if (evt.getSource() == search) {
                if (customerR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Customer ID : ");
                    Customer found = null;
                    for(Customer c : DataIO.allCustomer) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        data = new String[1][10];
                        data[0][0] = found.getId();
                        data[0][1] = found.getRole();
                        data[0][2] = found.getUsername();
                        data[0][3] = found.getPassword();
                        data[0][4] = found.getName();
                        data[0][5] = found.getGender();
                        data[0][6] = found.getEmail();
                        data[0][7] = found.getAddress();
                        data[0][8] = ""+found.getAge();
                        data[0][9] = found.getContactNumber();   
                        tableHeader = new String[] {"Customer ID", "Role", "Username", "Password", "Name","Gender","Email","Address", "Age", "ContactNumber"};
                    }
                    
                } else if (staffR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Staff ID : ");
                    Staff found = null;
                    for(Staff s : DataIO.allStaff) {
                        if(key.equals(s.getId())){
                            found = s;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        data = new String[1][6];
                        data[0][0] = found.getId();
                        data[0][1] = found.getRole();
                        data[0][2] = found.getUsername();
                        data[0][3] = found.getPassword();
                        data[0][4] = found.getName();
                        data[0][5] = found.getContactNum();   
                        tableHeader = new String[] {"Staff ID", "Role", "Username", "Password", "Name", "ContactNumber"};
                    }
                } else if (adminR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Admin ID : ");
                    Admin found = null;
                    for(Admin a : DataIO.allAdmin) {
                        if(key.equals(a.getId())){
                            found = a;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        data = new String[1][6];
                        data[0][0] = found.getId();
                        data[0][1] = found.getRole();
                        data[0][2] = found.getUsername();
                        data[0][3] = found.getPassword();
                        data[0][4] = found.getName();
                        data[0][5] = found.getEmail();   
                        tableHeader = new String[] {"Admin ID", "Role", "Username", "Password", "Name", "Email"};
                    }
                }
                tableComponents();
            } else if (evt.getSource() == modify) {
                if (customerR.isSelected()) {
                String key = JOptionPane.showInputDialog("Input Customer ID : ");
                Customer found = null;
                for(Customer c : DataIO.allCustomer) {
                    if(key.equals(c.getId())){
                        found = c;
                        break;
                    }
                }
                if (found == null) {
                    JOptionPane.showMessageDialog(null,"User not found!");
                } else {
                    int input = Integer.parseInt(JOptionPane.showInputDialog("What would like to change?"
                            + "\n1.Username"
                            + "\n2.Password"
                            + "\n3.Name"
                            + "\n4.Gender"
                            + "\n5.Email"
                            + "\n6.Address"
                            + "\n7.Age"
                            + "\n8.Contact Number"));
                    switch (input) {
                        case 1:
                            String newUsername = JOptionPane.showInputDialog("Current Username is "+ found.getUsername()+ "\nInput new Username");
                            if(!newUsername.isEmpty()) {
                                found.setUsername(newUsername);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Username is "+found.getUsername());
                            break;
                        case 2:
                            String newPassword = JOptionPane.showInputDialog("Current Password is "+ found.getPassword()+ "\nInput new Password");
                            if(!newPassword.isEmpty()) {
                                found.setPassword(newPassword);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Password is "+found.getPassword());
                            break;
                        case 3:
                            String newName = JOptionPane.showInputDialog("Current User Name is "+ found.getName()+ "\nInput new Name");
                            if(!newName.isEmpty()) {
                                found.setName(newName);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Name is "+found.getName());
                            break;
                        case 4:
                            Object[] option = {"Male","Female"};
                            int choice = JOptionPane.showOptionDialog(null, "Gender?", "Gender",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
                            switch (choice) {
                                case JOptionPane.YES_OPTION:
                                    found.setGender("Male");
                                    break;
                                case JOptionPane.NO_OPTION:
                                    found.setGender("Female");
                                    break;
                                default:
                                    throw new Exception();
                            }
                            JOptionPane.showMessageDialog(null, "Current Gender is "+found.getGender());
                            break;
                        case 5:
                            String newEmail = JOptionPane.showInputDialog("Current User Email is "+ found.getEmail()+ "\nInput new Address");
                            if(!newEmail.isEmpty()) {
                                found.setAddress(newEmail);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Address is "+found.getEmail());
                            break;
                        case 6:
                            String newAddress = JOptionPane.showInputDialog("Current User Address is "+ found.getAddress()+ "\nInput new Address");
                            if(!newAddress.isEmpty()) {
                                found.setAddress(newAddress);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Address is "+found.getAddress());
                            break;
                        case 7:
                            String newAge = JOptionPane.showInputDialog("Current User Age is "+ found.getAge()+ "\nInput new Age");
                            int age = Integer.parseInt(newAge);
                            if(!newAge.isEmpty()) {
                                found.setAge(age);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Age is "+found.getAge());
                            break;
                        case 8:
                            String newNumber = JOptionPane.showInputDialog("Current User Contact Number is "+ found.getContactNumber()+ "\nInput new Contact Number");
                            String num = newNumber;
                            if(!newNumber.isEmpty()) {
                                found.setContactNumber(num);
                            } 
                            JOptionPane.showMessageDialog(null, "Current Contact Number is "+found.getContactNumber());
                            break;
                        default:
                            throw new Exception();
                        }
                    DataIO.writeToFile();
                    }    
                } else if (staffR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Staff ID : ");
                    Staff found = null;
                    for(Staff c : DataIO.allStaff) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        int input = Integer.parseInt(JOptionPane.showInputDialog("What would like to change?"
                                + "\n1.Username"
                                + "\n2.Password"
                                + "\n3.Name"
                                + "\n4.Contact Number"));
                        switch (input) {
                            case 1:
                                String newUsername = JOptionPane.showInputDialog("Current Username is "+ found.getUsername()+ "\nInput new Username");
                                if(!newUsername.isEmpty()) {
                                    found.setUsername(newUsername);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Username is "+found.getUsername());
                                break;
                            case 2:
                                String newPassword = JOptionPane.showInputDialog("Current Password is "+ found.getPassword()+ "\nInput new Password");
                                if(!newPassword.isEmpty()) {
                                    found.setPassword(newPassword);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Password is "+found.getPassword());
                                break;
                            case 3:
                                String newName = JOptionPane.showInputDialog("Current User Name is "+ found.getName()+ "\nInput new Name");
                                if(!newName.isEmpty()) {
                                    found.setName(newName);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Name is "+found.getName());
                                break;
                            case 4:
                                String newNumber = JOptionPane.showInputDialog("Current User Contact Number is "+ found.getContactNum()+ "\nInput new Contact Number");
                                if(!newNumber.isEmpty()) {
                                    found.setContactNum(newNumber);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Contact Number is "+found.getContactNum());
                                break;
                            default:
                                throw new Exception();
                            }
                        DataIO.writeToFile();
                        }
                } else if (adminR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Admin ID : ");
                    Admin found = null;
                    for(Admin c : DataIO.allAdmin) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        int input = Integer.parseInt(JOptionPane.showInputDialog("What would like to change?"
                                + "\n1.Username"
                                + "\n2.Password"
                                + "\n3.Name"
                                + "\n4.Email"));
                        switch (input) {
                            case 1:
                                String newUsername = JOptionPane.showInputDialog("Current Username is "+ found.getUsername()+ "\nInput new Username");
                                if(!newUsername.isEmpty()) {
                                    found.setUsername(newUsername);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Username is "+found.getUsername());
                                break;
                            case 2:
                                String newPassword = JOptionPane.showInputDialog("Current Password is "+ found.getPassword()+ "\nInput new Password");
                                if(!newPassword.isEmpty()) {
                                    found.setPassword(newPassword);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Password is "+found.getPassword());
                                break;
                            case 3:
                                String newName = JOptionPane.showInputDialog("Current User Name is "+ found.getName()+ "\nInput new Name");
                                if(!newName.isEmpty()) {
                                    found.setName(newName);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Name is "+found.getName());
                                break;
                            case 4:
                                String newEmail = JOptionPane.showInputDialog("Current User Email is "+ found.getName()+ "\nInput new Email");
                                if(!newEmail.isEmpty()) {
                                    found.setEmail(newEmail);
                                } 
                                JOptionPane.showMessageDialog(null, "Current Email is "+found.getEmail());
                                break;
                            default:
                                throw new Exception();
                            }
                        DataIO.writeToFile();
                        }
                }
            } else if (evt.getSource() == delete) {
                if (customerR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Customer ID : ").trim();
                    Customer found = null;
                    for(Customer c : DataIO.allCustomer) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        // Remove related cart
                        Iterator<Cart> iterator = DataIO.allCart.iterator();
                        while (iterator.hasNext()) {
                            Cart c = iterator.next();
                            if (c.getOwner().getId().equals(key)) {
                                iterator.remove();
                            }
                        }
                        // Remove related Cart Details
                        Iterator<CartDetails> iterator2 = DataIO.allCartDetails.iterator();
                        while(iterator2.hasNext()) {
                            CartDetails cd = iterator2.next();
                            if(key.equals(cd.getCartid().getOwner().getId())) {
                                iterator2.remove();
                            }
                        }
                        // Remove related order and payment
                        Iterator<Order> orderIterator = DataIO.allOrder.iterator();
                        while (orderIterator.hasNext()) {
                            Order o = orderIterator.next();
                            if (key.equals(o.getOrdered_cart().getOwner().getId())) {
                                Iterator<Payment> paymentIterator = DataIO.allPayment.iterator();
                                while (paymentIterator.hasNext()) {
                                    Payment p = paymentIterator.next();
                                    if (o.getPayId().getId().equals(p.getId())) {
                                        paymentIterator.remove();
                                    }
                                }
                                orderIterator.remove();
                            }
                        }

                        // Remove related deliveries
                        Iterator<Delivery> deliveryIterator = DataIO.allDelivery.iterator();
                        while (deliveryIterator.hasNext()) {
                            Delivery d = deliveryIterator.next();
                            if (key.equals(d.getOrderid().getOrdered_cart().getOwner().getId())) {
                                deliveryIterator.remove();
                            }
                        }
                        
                        JOptionPane.showMessageDialog(null,"Deleted Succesfully");
                        DataIO.allCustomer.remove(found);
                        DataIO.writeToFile();
                    }
                } else if (staffR.isSelected()) {
                    String key = JOptionPane.showInputDialog("Input Staff ID : ");
                    Staff found = null;
                    for(Staff c : DataIO.allStaff) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        boolean isStaffUsed = false;
                        // Use iterator to avoid ConCurrentModification Exception
                        // Which triggered when Collection data are removed which change the content of the collection during the loop
                        Iterator<Delivery> deliveryIterator = DataIO.allDelivery.iterator();
                        while (deliveryIterator.hasNext()) {
                            Delivery d = deliveryIterator.next();
                            if (d.getStaffid().getId().equals(key)) {
                                if (d.getStatus() == false) {
                                    JOptionPane.showMessageDialog(null, "Invalid, Currently Staff is Assigned to Delivery");
                                    isStaffUsed = true;
                                    break;
                                } else {
                                    deliveryIterator.remove();
                                }
                            }
                        }

                        if (!isStaffUsed) {
                            JOptionPane.showMessageDialog(null,"Deleted Succesfully");
                            DataIO.allStaff.remove(found);
                            DataIO.writeToFile();
                        }
                    }
                } else if (adminR.isSelected()) {
                    // remove report generated by this admin later
                    String key = JOptionPane.showInputDialog("Input Admin ID : ");
                    Admin found = null;
                    for(Admin c : DataIO.allAdmin) {
                        if(key.equals(c.getId())){
                            found = c;
                            break;
                        }
                    }
                    if (found == null) {
                        JOptionPane.showMessageDialog(null,"User not found!");
                    } else {
                        JOptionPane.showMessageDialog(null,"Deleted Succesfully");
                        DataIO.allAdmin.remove(found);
                        DataIO.writeToFile();
                    }
                }
            } else if (evt.getSource() == view) {
                if(customerR.isSelected()) {
                    int size = DataIO.allCustomer.size();
                    data = new String[size][10];
                    for(int i=0; i<size; i++) {
                        Customer c = DataIO.allCustomer.get(i);
                        data[i][0] = c.getId();
                        data[i][1] = c.getRole();
                        data[i][2] = c.getUsername();
                        data[i][3] = c.getPassword();
                        data[i][4] = c.getName();
                        data[i][5] = c.getGender();
                        data[i][6] = c.getEmail();
                        data[i][7] = c.getAddress();                
                        data[i][8] = ""+c.getAge();
                        data[i][9] = c.getContactNumber();   
                    }
                    tableHeader = new String[] {"Customer ID", "Role", "Username", "Password", 
                        "Name","Gender","Email","Address", "Age", "ContactNumber"};
                } else if(staffR.isSelected()) {
                    int size = DataIO.allStaff.size();
                    data = new String[size][6];
                    for(int i=0; i<size; i++) {
                        Staff c = DataIO.allStaff.get(i);
                        data[i][0] = c.getId();
                        data[i][1] =  c.getRole();
                        data[i][2] =  c.getUsername();
                        data[i][3] = c.getPassword();
                        data[i][4] =  c.getName();
                        data[i][5] = c.getContactNum();   
                    }
                    tableHeader = new String[] {"Staff ID", "Role", "Username", "Password", "Name", "ContactNumber"};
                } else if(adminR.isSelected()) {
                    int size = DataIO.allAdmin.size();
                    data = new String[size][6];
                    for(int i=0; i<size; i++) {
                        Admin c = DataIO.allAdmin.get(i);
                        data[i][0] =  c.getId();
                        data[i][1] =  c.getRole();
                        data[i][2] = c.getUsername();
                        data[i][3] = c.getPassword();
                        data[i][4] = c.getName();
                        data[i][5] = c.getEmail();   
                    }
                    tableHeader = new String[] {"Admin ID", "Role", "Username", "Password", "Name", "Email"};
                }
                tableComponents();
            } else if (evt.getSource() == backBtn) {
                userMngPage.setVisible(false);
                OODMSystem.adminPage1.getJFrame().setVisible(true);
            }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Invalid Input, Try again");
        }
    }
}
