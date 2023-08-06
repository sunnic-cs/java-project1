
package pages;

import OODJAssignment.DataIO;
import OODJAssignment.Delivery;
import OODJAssignment.OODMSystem;
import OODJAssignment.Order;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminOrderPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent evt) {
        try{
            if(deliveryRB.isSelected()) {
                tableHeader = new String[] {"DeliveryID","StaffID","OrderID","Status"};
                if(evt.getSource()==view) {
                    int size = DataIO.allDelivery.size();
                    data= new String[size][4];

                    for(int i = 0; i<size; i++) { 
                        Delivery d = DataIO.allDelivery.get(i);
                        data[i][0] = d.getId();
                        data[i][1] = d.getStaffid().getId();
                        data[i][2] = d.getOrderid().getOrderID();
                        data[i][3] = ""+d.getStatus();          
                    }
                } else if(evt.getSource()==search) {
                    String key = field.getText().trim();
                    ArrayList<Delivery> tempDelivery = new ArrayList<>();
                    for(Delivery d : DataIO.allDelivery) {
                        if(key.equals(d.getId()) || key.equals(d.getOrderid().getOrderID()) || key.equals(d.getStaffid().getId()) || key.equals(d.getStatus().toString())) {
                            tempDelivery.add(d);
                        }
                    }
                    
                    int size = tempDelivery.size();
                    data = new String[size][4];
                    for(int i = 0; i < size ; i++) {
                        Delivery d = tempDelivery.get(i);
                        data[i][0] = d.getId();
                        data[i][1] = d.getStaffid().getId();
                        data[i][2] = d.getOrderid().getOrderID();
                        data[i][3] = ""+d.getStatus(); 
                    }
                }
                deliveryTableComp();
            } else if (orderRB.isSelected()) {
                tableHeader = new String[] {"OrderID","OrderDate","TotalPrice","OrderedCart","Status"};
                if(evt.getSource()==view) {
                    int size = DataIO.allOrder.size();
                    data= new String[size][5];

                    for(int i = 0; i<size; i++) { 
                        Order d = DataIO.allOrder.get(i);
                        data[i][0] = d.getOrderID();
                        data[i][1] = ""+d.getOrderDate();
                        data[i][2] = ""+d.getTotalPrice();
                        data[i][3] = d.getOrdered_cart().getCart_id(); 
                        data[i][4] = d.getOrder_status();
                    }
                } else if(evt.getSource()==search) {
                    String key = field.getText().trim();
                    ArrayList<Order> tempOrder = new ArrayList<>();
                    for(Order d : DataIO.allOrder) {
                        if(key.equals(d.getOrderID()) || key.equals(d.getOrderDate()) || key.equals(d.getOrdered_cart().getCart_id()) || key.equals(d.getOrder_status())) {
                            tempOrder.add(d);
                        }
                    }
                    
                    int size = tempOrder.size();
                    data = new String[size][5];
                    for(int i = 0; i < size ; i++) {
                        Order d = tempOrder.get(i);
                        data[i][0] = d.getOrderID();
                        data[i][1] = ""+d.getOrderDate();
                        data[i][2] = ""+d.getTotalPrice();
                        data[i][3] = d.getOrdered_cart().getCart_id(); 
                        data[i][4] = d.getOrder_status();
                    }
                }
                orderTableComp();
            }
            if (evt.getSource()==back) {
                admOrdPage.setVisible(false);
                OODMSystem.adminPage1.getJFrame().setVisible(true);
            }
        } catch (Exception e) {
            
        }
    }
    
    
    public JFrame getJFrame() {
        return admOrdPage;
    }
    
    private final JFrame admOrdPage;
    private final JLabel title;
    private final JTextField field;
    private JTable orderTable, deliveryTable;
    private JScrollPane scpOrder, scpDelivery;
    private final JButton search;
    private final JButton view,back;
    private final JRadioButton orderRB;
    private final JRadioButton deliveryRB;
    private DefaultTableModel orderModel,deliveryModel;
    private String[] tableHeader = null;
    private String[][] data = null;
    
    public AdminOrderPage () {
        admOrdPage = new JFrame("Order/Delivery Page");
        admOrdPage.setSize(800,600);
        admOrdPage.setLocation(1000,400);       
        admOrdPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admOrdPage.setLocationRelativeTo(null);
        admOrdPage.setLayout(null);
        title= new JLabel("Order/Delivery");
        title.setBounds(310,50,600,40);
        title.setFont(new Font("Consolas",Font.BOLD,23));
        field = new JTextField(30);
        field.setBounds(255,100,300,30);
        search = new JButton("Search");
        search.setBounds(565,100,80,29);
        view = new JButton("View");
        view.setBounds(165,100,80,29);
        view.addActionListener(this);
        search.addActionListener(this);
        back = new JButton("Back");
        back.setBounds(40,40,75,30);
        back.addActionListener(this);
        orderRB = new JRadioButton ("Order");
        deliveryRB = new JRadioButton("Delivery");
        orderRB.setBounds(320,135,80,30);
        deliveryRB.setBounds(400,135,80,30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(deliveryRB);
        bg.add(orderRB);

        orderTableComp();
        deliveryTableComp();
        
        admOrdPage.add(back);
        admOrdPage.add(deliveryRB);
        admOrdPage.add(orderRB);
        admOrdPage.add(view);
        admOrdPage.add(search);
        admOrdPage.add(field);
        admOrdPage.add(title);
        admOrdPage.setVisible(false);
    }
    
    private void orderTableComp() {
        orderModel = new DefaultTableModel(data,tableHeader);
        if(orderTable!=null && scpOrder!=null) {
            admOrdPage.remove(scpOrder);
        }
        orderTable = new JTable(orderModel);
        orderTable.setBounds(50,230,700,120);
        orderTable.getTableHeader().setBounds(50,210,700,20); 
        scpOrder = new JScrollPane(orderTable);
        scpOrder.setBounds(50,200,330,300);
        admOrdPage.add(scpOrder);
    }
    
    private void deliveryTableComp() {
        deliveryModel = new DefaultTableModel(data,tableHeader);
        if(deliveryTable!=null && scpDelivery!=null) {
            admOrdPage.remove(scpDelivery);
        }
        deliveryTable  = new JTable(deliveryModel);
        deliveryTable.setBounds(50,230,700,120);
        deliveryTable.getTableHeader().setBounds(50,210,700,20); 
        scpDelivery = new JScrollPane(deliveryTable);
        scpDelivery.setBounds(400,200,330,300);
        admOrdPage.add(scpDelivery);
    }
    
}