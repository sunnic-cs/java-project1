package pages;

import OODJAssignment.DataIO;
import OODJAssignment.Order;
import OODJAssignment.Staff;
import OODJAssignment.Delivery;
import OODJAssignment.OODMSystem;
import OODJAssignment.User;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeliveryManagePage implements ActionListener {

    private final JFrame deliverMngPage;
    private final JLabel title,staffLabel,deliveryLabel;
    private final JComboBox staffBox, orderBox;
    private final DefaultComboBoxModel staffModel, orderModel;
    private final Button back,add,view,modify;
    private JTable deliveryTable;
    private JScrollPane scp;
    private DefaultTableModel tableModel;
    private String[] tableHeader = null;
    private String[][] data = null;
    
    public DeliveryManagePage() {
        deliverMngPage = new JFrame("Delivery Management Page");
        deliverMngPage.setSize(400,600);
        deliverMngPage.setLocation(700,400);       
        deliverMngPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deliverMngPage.setLocationRelativeTo(null);
        deliverMngPage.setLayout(null);
        
        title= new JLabel("Delivery Management Page");
        title.setBounds(70,50,300,40);
        title.setFont(new Font("Consolas",Font.BOLD,17));
        
        // Assigning Order to Staff Delivery GUI
        staffModel = new DefaultComboBoxModel();
        orderModel = new DefaultComboBoxModel();
        staffLabel = new JLabel("Assign a Staff");
        staffLabel.setBounds(50,110,200,40);
        deliveryLabel = new JLabel("Assign Order");
        deliveryLabel.setBounds(50,230,200,40);
        staffBox = new JComboBox();
        staffBox.setBounds(50,150,200,40);
        orderBox = new JComboBox();
        orderBox.setBounds(50,265,200,40);
        staffBox.setModel(staffModel);
        orderBox.setModel(orderModel); 
        add = new Button("Add");
        add.setBounds(50,355,50,30);
        add.addActionListener(this);
        
        // Modify Button
        modify = new Button("Modify");
        modify.addActionListener(this);
        modify.setBounds(300,355,50,30);
      
        // Delivery Table GUI
        view = new Button("View On-going Delivery");
        view.setBounds(110,355,180,30);
        view.addActionListener(this);
        deliveryTable = new JTable();
        deliveryTable.setBounds(50,400,300,90);
        // Staff ComboBox Data
        updateComboBox();

        // Back Button
        back = new Button("Back");
        back.setBounds(30,500,50,30);
        back.addActionListener(this);
       
        // Adding component to JFrame
        deliverMngPage.add(modify);
        deliverMngPage.add(view);
        deliverMngPage.add(add);
        deliverMngPage.add(back);
        deliverMngPage.add(staffBox);
        deliverMngPage.add(orderBox);
        deliverMngPage.add(deliveryLabel);
        deliverMngPage.add(staffLabel);
        deliverMngPage.add(title);    
    }
    
    private void tableComponents() {
        tableModel = new DefaultTableModel(data,tableHeader);
        if(deliveryTable!=null && scp!=null) {
            deliverMngPage.remove(scp);
        }
        deliveryTable = new JTable(tableModel);
        deliveryTable.setBounds(50,230,700,120);
        deliveryTable.getTableHeader().setBounds(50,210,700,20); 
        scp = new JScrollPane(deliveryTable);
        scp.setBounds(50, 400, 300, 90);
        deliverMngPage.add(scp);  
    }

    public JFrame getJFrame() {
        return deliverMngPage;
    }
    
    public final void updateComboBox() {
        String[] datasize;
        staffModel.removeAllElements();
        // Staff ComboBox
        if (DataIO.allStaff != null) {
            List<String> dataList = new ArrayList<>();
            for (Staff s : DataIO.allStaff) {
                boolean isStaffUsed = false;
                for (Delivery d : DataIO.allDelivery) {
                    if (d.getStaffid().getId().equals(s.getId()) && !d.getStatus()) {
                        isStaffUsed = true;
                        break;
                    }
                }
                if (!isStaffUsed) {
                    dataList.add(s.getId());
                }
            }
            datasize = dataList.toArray(new String[dataList.size()]);
            for (String item : datasize) {
                staffModel.addElement(item);
            }
        }
        // Order ComboBox
        orderModel.removeAllElements();
        if (DataIO.allOrder!=null){
            List<String> dataList2 = new ArrayList<>();
            for(Order o : DataIO.allOrder) {
                boolean isOrderUsed = false;
                for(Delivery d : DataIO.allDelivery) {
                    if(d.getOrderid().getOrderID().equals(o.getOrderID())) {
                        isOrderUsed = true;
                        break;
                    }
                }
                if(!isOrderUsed && o.getOrder_status().equals("Complete")) {
                    dataList2.add(o.getOrderID());
                }
            }
            datasize = dataList2.toArray(new String[dataList2.size()]);
            for(String item : datasize) {
                orderModel.addElement(item);
            }
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent evt) {
        try {
            if(evt.getSource()==add) {
                int LastNum = 0;
                int deliveryLastIndex = DataIO.allDelivery.size() - 1;
                if (deliveryLastIndex >= 0) {
                    String lastDeliveryId = DataIO.allDelivery.get(deliveryLastIndex).getId();
                    LastNum = Integer.parseInt(lastDeliveryId.substring(4));
                }
                LastNum++;
                String deliveryID = "DRID" + LastNum;
                Boolean status = false;
                String feedback = "NA";
                String orderID =(String) orderBox.getSelectedItem();
                Order check = null;
                for (Order c : DataIO.allOrder) {
                    if(orderID.equals(c.getOrderID())) {
                        check = c;
                        break;
                    }
                }
                String staffID = (String) staffBox.getSelectedItem();
                Staff cek = null;
                for(Staff s : DataIO.allStaff) {
                    if(staffID.equals(s.getId())) {
                        cek = s;
                        break;
                    }
                }
                String nowstatus = "Pending";
                Delivery d = new Delivery(deliveryID,status,feedback,
                        new Order(check.getOrderID(),check.getOrderDate(),check.getTotalPrice(),check.getOrder_status(),
                                check.getOrdered_cart(),check.getPayId()),
                        new Staff(cek.getId(),cek.getRole(),cek.getUsername(),cek.getPassword(),cek.getName(),cek.getContactNum()),
                nowstatus);
                DataIO.allDelivery.add(d);
                DataIO.writeToFile();
                orderBox.removeItem(orderID);
                staffBox.removeItem(staffID);
                JOptionPane.showMessageDialog(null,"Delivery Added!");
            } else if (evt.getSource()==view) {
                
                ArrayList<Delivery> falseDelivery = new ArrayList<>();
                for(Delivery de : DataIO.allDelivery) {
                    if(de.getStatus()==false) {
                        falseDelivery.add(de);
                    }
                }
                int size = falseDelivery.size();
                data = new String[size][4];
                for(int i = 0; i<size; i++) {
                    Delivery d = falseDelivery.get(i);
                    if(d.getStatus()==false) {
                        data[i][0] = d.getId();
                        data[i][1] = d.getStaffid().getId();
                        data[i][2] = d.getOrderid().getOrderID();
                        data[i][3] = ""+d.getStatus();
                    }
                }
                tableHeader = new String[] {"Delivery ID","Staff ID","Order ID", "Status"};
                
            } else if (evt.getSource()==modify) {
                int row = deliveryTable.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit.");
                    return;
                }
                String key = deliveryTable.getValueAt(row, 0).toString();
                Delivery deliveryToEdit = null;
                for (Delivery d : DataIO.allDelivery) {
                    if (d.getId().equals(key)) {
                        deliveryToEdit = d;
                        break;
                    }
                }
                if (deliveryToEdit == null) {
                    JOptionPane.showMessageDialog(null, "Delivery not found.");
                    return;
                }
                String ask = User.getInput("What to change?"
                        + "\n Order"
                        + "\n Staff"
                        + "\n Status");
                if (ask == null) {
                    return; // user canceled the input dialog
                }
                ask = ask.trim().toLowerCase();
                if (null == ask) {
                    JOptionPane.showMessageDialog(null, "Invalid input.");
                } else 
                    switch (ask) {
                    case "order":
                        String newOrder = User.getInput("New Order ID : ");
                        if (newOrder != null) {
                            newOrder = newOrder.trim();
                            if (!newOrder.isEmpty()) {
                                boolean found = false;
                                List<Order> dataList2 = new ArrayList<>();
                                for(Order or : DataIO.allOrder) {
                                    boolean isOrderUsed = false;
                                    for(Delivery d : DataIO.allDelivery) {
                                        if(d.getOrderid().getOrderID().equals(or.getOrderID())) {
                                            isOrderUsed = true;
                                            break;
                                        }
                                    }
                                    if(!isOrderUsed) {
                                        dataList2.add(or);
                                    }
                                }                  
                                for (Order o : dataList2) {
                                    if (newOrder.equals(o.getOrderID())) {
                                        deliveryToEdit.setOrderid(o);
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    JOptionPane.showMessageDialog(null, "Order not found/Assigned!.");
                                }else {
                                    JOptionPane.showMessageDialog(null,"Updated!");
                                }
                            }
                        }
                        DataIO.writeToFile();
                        updateComboBox();
                        break;              
                    case "staff":
                        String newStaff = User.getInput("New Staff ID : ");
                        if(newStaff != null) {
                            newStaff = newStaff.trim();
                            if(!newStaff.isEmpty()) {
                                boolean found = false;
                                List<Staff> dataList = new ArrayList<>();
                                for (Staff s : DataIO.allStaff) {
                                    boolean isStaffUsed = false;
                                    for (Delivery d : DataIO.allDelivery) {
                                        if (d.getStaffid().getId().equals(s.getId())) {
                                            isStaffUsed = true;
                                            break;
                                        }
                                    }
                                    if (!isStaffUsed) {
                                        dataList.add(s);
                                    }
                                }
                                for(Staff sa : dataList) {
                                    if(newStaff.equals(sa.getId())) {
                                        deliveryToEdit.setStaffid(sa);
                                        found = true;
                                        break;
                                    }
                                }                         
                                if (!found) {
                                    JOptionPane.showMessageDialog(null, "Staff not found/Assigned!.");
                                } else {
                                    JOptionPane.showMessageDialog(null,"Updated!");
                                }
                            }
                        }
                        DataIO.writeToFile();
                        updateComboBox();
                        break;
                    case "status":
                        if(deliveryToEdit.getStatus() == true) {
                            deliveryToEdit.setStatus(false);
                        } else if(deliveryToEdit.getStatus()==false) {
                            deliveryToEdit.setStatus(true);
                        }
                        JOptionPane.showMessageDialog(null,"Status is " + deliveryToEdit.getStatus());
                        DataIO.writeToFile();
                        updateComboBox();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid input.");
                        break;
                }
            } else if(evt.getSource()==back) {
                deliverMngPage.setVisible(false);
                OODMSystem.adminPage1.getJFrame().setVisible(true);
            }
            tableComponents();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Number Format Error");
        } catch (Exception ev) {
            JOptionPane.showMessageDialog(null,"Data not found, Try Again!");
        }
        
    }
    
}
