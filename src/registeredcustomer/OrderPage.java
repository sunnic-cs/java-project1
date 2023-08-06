package registeredcustomer;


import OODJAssignment.Cart;
import OODJAssignment.CartDetails;
import OODJAssignment.Category;
import OODJAssignment.Customer;
import OODJAssignment.DataIO;
import OODJAssignment.Item;
import OODJAssignment.Order;
import OODJAssignment.Payment;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Kaede
 */
public class OrderPage extends javax.swing.JFrame {

    /**
     * Creates new form itemcategory
     */
    public OrderPage() {
        
        initComponents(); 
        productTable();
        cartTable();
        third();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        String[] data = new String[(DataIO.allCategory.size())];
        int i = 0;
        for(Category c : DataIO.allCategory) {
            data[i++] = c.getName() ;
        }
        for(String item : data) {
            model.addElement(item);
        }
      /*  jComboBox1 = new JComboBox<>(); */
        jComboBox1.setModel(model);
        jComboBox1.setVisible(true);
                    
    }
   
    private void productTable(){
        try{
            DefaultTableModel defaultTableModel = new DefaultTableModel() {
                // Override the isCellEditable method to always return false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        
            String s1[]=new String []{"Category", "Item","Qty", "Price(RM)"};
            defaultTableModel.setColumnIdentifiers(s1);
            String[] data = new String[4];
            for(Item i : DataIO.allItem) {
                data[0] = i.getCategory().getName();
                data[1] = i.getName();
                data[2] = ""+i.getQuantity();
                data[3] = ""+i.getPrice(); 
                defaultTableModel.addRow(data);
            }
            jTable1.setModel(defaultTableModel);
            jTable1.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Unknown Error");
        }
        
    }
    
    
    private void cartTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
                // Override the isCellEditable method to always return false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }; // Prevent editing through the table
        String s1[]=new String []{"Item", "Price(RM)","Qty","Total(RM)"};
        defaultTableModel.setColumnIdentifiers(s1);
        Cart c = Customer.getCurrentCart();
        ArrayList<CartDetails> orderList = c.getOrderList();
        for(CartDetails k : orderList) {
            for(Item i : DataIO.allItem) {
                if(k.getItem().equals(i.getName())) {
                    String[] data = new String[4];
                    data[0] = k.getItem();
                    data[1] = Integer.toString((int) i.getPrice());
                    data[2] = Integer.toString((int) k.getQty());
                    data[3] = Double.toString((int) k.getTotal());
                    defaultTableModel.addRow(data);
                }
            }
        }
        cart.setModel(defaultTableModel);
        cart.setVisible(true);
    }
      
    private void third(){
        double sum = 0;
        Cart c = Customer.getCurrentCart();
        if(DataIO.allCartDetails!=null||!DataIO.allCartDetails.isEmpty()) {
            for (CartDetails k : DataIO.allCartDetails) {
                if (k.getCartid().getCart_id().equals(c.getCart_id())) {
                    double to = (double) k.getTotal();
                    sum += to;
                    finalprice.setText(Double.toString(sum));
                }   
            }
        }    
    }
    
    private void placeOrder(){
        Cart c = Customer.getCurrentCart(); // current cart of customer
        double sum = 0;
        for(CartDetails k : c.getOrderList()) {
            sum = sum + k.getTotal();
        } // calculate sum of the cart total price
        
        // Order Object
        int lastNum = 0;
        int lastOrder = DataIO.allOrder.size()-1;
        
        if(lastOrder>=0) {
            String lastId = DataIO.allOrder.get(lastOrder).getOrderID();
            lastNum = Integer.parseInt(lastId.substring(4));
        }
        lastNum++;
        String order_status = "Pending";
        String id = "ORID"+lastNum;
        LocalDate today = LocalDate.now();
        double total1 = sum;
        c.setCart_status(true);

        // Generate Payment Object
        int payNumber = 0;
        int lastPay = DataIO.allPayment.size()-1;
        if(lastPay>=0) {
            String lastId = DataIO.allPayment.get(lastPay).getId();
            payNumber = Integer.parseInt(lastId.substring(4));
        }
        payNumber++;
        String pay_Id = "PAID" + payNumber;
        String cardNumber = "12345";
        double totalPaid = 0;
        LocalDate payDate = LocalDate.now();
        String pay_Status = "Pending";
        
        // Assigning values
        Payment p = new Payment(pay_Id,cardNumber,totalPaid,payDate,pay_Status);
        
        Order o = new Order(id,today,total1,order_status,
                new Cart(c.getCart_id(),c.isCart_status(),c.getOwner()), p);
        
        
        JOptionPane.showMessageDialog(null,"Order has been Placed Succesfully! \nProceed to Payment");
        DataIO.allPayment.add(p);
        DataIO.allOrder.add(o);  
        
        for(Item i : DataIO.allItem) {
            for(CartDetails cd : c.getOrderList()) {
                if(i.getName().equals(cd.getItem())) {
                    //i qty is current stock in system
                    //cd qty is num of item placed into order
                    int stock = i.getQuantity()-cd.getQty();
                    i.setQuantity(stock);
                }
            }  
        }
        
        DataIO.writeToFile(); 
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        back = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        total = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        qtyy = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        ADDCART = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        TOTALL = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        cart = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        qty2 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        TOTAL2 = new javax.swing.JTextField();
        DELETEitem = new javax.swing.JButton();
        MODIFYitem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        order = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        finalprice = new javax.swing.JTextField();
        VIEWALL = new javax.swing.JButton();
        searchTxtF = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText(" SEARCH & ORDER PAGE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );

        jPanel3.setBackground(new java.awt.Color(255, 0, 153));

        back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        back.setText("<<BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        exit.setText("EXIT>>");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        total.setBackground(new java.awt.Color(255, 153, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Please select Item category to narrow down");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        qtyy.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        qtyy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtyyStateChanged(evt);
            }
        });
        qtyy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qtyyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qtyyMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                qtyyMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("QTY :");

        ADDCART.setText("Add to cart");
        ADDCART.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDCARTActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("TOTAL (RM) :");

        TOTALL.setEditable(false);
        TOTALL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TOTALLMouseEntered(evt);
            }
        });
        TOTALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOTALLActionPerformed(evt);
            }
        });

        cart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartMouseClicked(evt);
            }
        });
        cart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cartPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(cart);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("↓↓↓");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("QTY :");

        qty2.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        qty2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qty2StateChanged(evt);
            }
        });
        qty2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qty2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qty2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                qty2MousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("TOTAL (RM) :");

        TOTAL2.setEditable(false);
        TOTAL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TOTAL2MouseEntered(evt);
            }
        });
        TOTAL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOTAL2ActionPerformed(evt);
            }
        });

        DELETEitem.setText("DELETE ITEM");
        DELETEitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEitemActionPerformed(evt);
            }
        });

        MODIFYitem.setText("MODIFY");
        MODIFYitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODIFYitemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("current your cart");

        order.setText("PLACE ORDER");
        order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("CART TOTAL AMOUNT (RM) :");

        finalprice.setEditable(false);
        finalprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalpriceActionPerformed(evt);
            }
        });

        VIEWALL.setText("VIEW ALL");
        VIEWALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEWALLActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout totalLayout = new javax.swing.GroupLayout(total);
        total.setLayout(totalLayout);
        totalLayout.setHorizontalGroup(
            totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalLayout.createSequentialGroup()
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(VIEWALL))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qty2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TOTAL2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(finalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(totalLayout.createSequentialGroup()
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(totalLayout.createSequentialGroup()
                                .addGap(0, 78, Short.MAX_VALUE)
                                .addComponent(ADDCART))
                            .addComponent(TOTALL)
                            .addComponent(qtyy)
                            .addComponent(searchTxtF)))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(DELETEitem)
                        .addGap(89, 89, 89)
                        .addComponent(MODIFYitem))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addGroup(totalLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(48, 48, 48)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        totalLayout.setVerticalGroup(
            totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalLayout.createSequentialGroup()
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(totalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qtyy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TOTALL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(ADDCART)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VIEWALL))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(TOTAL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DELETEitem)
                    .addComponent(MODIFYitem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finalprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(26, 26, 26)
                .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit)
                    .addComponent(back))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        systemExit();
        setVisible(false);
        regicustomermenu Info = new regicustomermenu();
        Info.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel  defaultTableModel = new DefaultTableModel();        
        String s1[]=new String []{"Category", "Item","Qty", "Price(RM)"};
        defaultTableModel.setColumnIdentifiers(s1);
        for(Item i : DataIO.allItem) {
            if(jComboBox1.getSelectedItem().equals(i.getCategory().getName())) {
                String[] data = new String[4];
                data[0] = i.getCategory().getName();
                data[1] = i.getName();
                data[2] = ""+i.getQuantity();
                data[3] = ""+i.getPrice(); 
                defaultTableModel.addRow(data);
            }
        }
        jTable1.setModel(defaultTableModel);
        jTable1.setVisible(true);


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void ADDCARTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDCARTActionPerformed
        try { 
            Cart c = Customer.getCurrentCart(); // to get current unpaid cart
            TableModel model1 = jTable1.getModel();
            
            int index = jTable1.getSelectedRow();
            int Q = (int) qtyy.getValue();
            if(Q<1) {
                JOptionPane.showMessageDialog(null,"At least 1 Qty required!");
                return;
            }
         
            String item1 = (model1.getValueAt(index,1).toString()); 
            int size = c.getOrderList().size();

            for(int x = 0; x<size; x++) {
                if(c.getOrderList().get(x).getItem().equals(item1)) {
                    JOptionPane.showMessageDialog(null,"You already added this item!!");
                    return;
                }
            }
            int currQty = Integer.parseInt(model1.getValueAt(index,2).toString());
            double one = Double.parseDouble(model1.getValueAt(index,3).toString());
            double sum =  (int) (Q*one);

            String item = item1;
            int qty = Q;
            double s = sum;
            
            if(Q>currQty) {
                JOptionPane.showMessageDialog(null,"Not enough Quantity available!");
                return;
            }
            
            CartDetails l = new CartDetails(item,qty,s,new Cart(c.getCart_id(),c.isCart_status(),c.getOwner()));
            DataIO.allCartDetails.add(l);
            c.addToCart(l);
            JOptionPane.showMessageDialog(null,"Succeed adding to cart!");
            cartTable();
            third();
            DataIO.writeToFile();
    
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Input Try Again!");    
        }    
    }//GEN-LAST:event_ADDCARTActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            qtyy.setValue(1);
            TableModel model1 = jTable1.getModel();
            int index = jTable1.getSelectedRow();
            int quantity = Integer.parseInt(qtyy.getValue().toString());
            double price = Double.parseDouble(model1.getValueAt(index,3).toString());
            double totalPrice = quantity*price;
            TOTALL.setText(Double.toString(totalPrice));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void TOTALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOTALLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTALLActionPerformed

    private void TOTALLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TOTALLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTALLMouseEntered

    private void qtyyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyyMouseEntered

    }//GEN-LAST:event_qtyyMouseEntered

    private void qtyyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyyMouseClicked

    }//GEN-LAST:event_qtyyMouseClicked

    private void qtyyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyyMousePressed

    }//GEN-LAST:event_qtyyMousePressed

    private void qtyyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtyyStateChanged
        

        TableModel model1 = jTable1.getModel();
        int index = jTable1.getSelectedRow();
        
        int Q = (int) qtyy.getValue(); // get qty wanted
        double price = Double.parseDouble(model1.getValueAt(index,3).toString());
        double sum =  (double) (Q*price);
        TOTALL.setText(Double.toString(sum));

    }//GEN-LAST:event_qtyyStateChanged

    private void qty2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qty2StateChanged
        TableModel model1 = cart.getModel();
        int index = cart.getSelectedRow();
        if(index<0) {
            JOptionPane.showMessageDialog(null,"Select a specific row!");
            return;
        }
        int Q = (int) qty2.getValue();

        double one = Double.parseDouble(model1.getValueAt(index,1).toString());
        double sum =  (double) (Q*one);
        TOTAL2.setText(Double.toString(sum));

    }//GEN-LAST:event_qty2StateChanged

    private void qty2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qty2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_qty2MouseClicked

    private void qty2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qty2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_qty2MouseEntered

    private void qty2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qty2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_qty2MousePressed

    private void TOTAL2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TOTAL2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTAL2MouseEntered

    private void TOTAL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOTAL2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TOTAL2ActionPerformed

    private void DELETEitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEitemActionPerformed
        
        TableModel model1 = cart.getModel();
        int index = cart.getSelectedRow();
        if(index<0) {
            JOptionPane.showMessageDialog(null,"Select a specific row!");
            return;
        }
        String item2 = (model1.getValueAt(index,0).toString());
        Cart c = Customer.getCurrentCart();
        for(CartDetails k : c.getOrderList()) {
            if (k.getItem().equals(item2)) {
                DataIO.allCartDetails.remove(k);
                c.removeFromCart(k);
                DataIO.writeToFile();
                JOptionPane.showMessageDialog(null,"Succeed deleting!");
                //cartTable();
                third();
                break;
            }  
        }    
        cartTable();
    }//GEN-LAST:event_DELETEitemActionPerformed

    private void MODIFYitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODIFYitemActionPerformed
        
        TableModel model2 = jTable1.getModel();
        TableModel model1 = cart.getModel();
        Cart c = Customer.getCurrentCart();
        int index = cart.getSelectedRow();
        if(index<0) {
            JOptionPane.showMessageDialog(null,"Select a specific row!");
            return;
        }
        int Q2 = (int) qty2.getValue();
        if(Q2<=0) {
            JOptionPane.showMessageDialog(null,"Input at least 1 Qty!");
            return;
        }
        int currQty = Integer.parseInt(model2.getValueAt(index,2).toString());
        String item2 = (model1.getValueAt(index,0).toString());
        for(CartDetails k : c.getOrderList()) {
            if (k.getItem().equals(item2)) {
                double one = Integer.parseInt(model1.getValueAt(index,1).toString());
                double sum2 =  Q2*one;
                TOTAL2.setText(Double.toString(sum2));
                int qty = Q2;
                if(Q2>currQty) {
                    JOptionPane.showMessageDialog(null,"Not enough Quantity available!");
                    return;
                }
                double totalChanged = sum2;
                k.setQty(qty);
                k.setTotal(totalChanged);
                c.removeFromCart(k);
                c.addToCart(k);
                DataIO.writeToFile();   
                JOptionPane.showMessageDialog(null,"Succeed modifying qty!");
                cartTable();
                third();
                break;
            }  
        }
    }//GEN-LAST:event_MODIFYitemActionPerformed

    private void cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartMouseClicked

    }//GEN-LAST:event_cartMouseClicked

    private void orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderActionPerformed

        if(finalprice.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"No item in cart found!");
            return;
        }
        
        placeOrder();
        systemExit();
        setVisible(false);
        PayOrder Info = new PayOrder(); 
        Info.once();
        Info.setVisible(true); 
    }//GEN-LAST:event_orderActionPerformed

    private void VIEWALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIEWALLActionPerformed
 
        productTable();
    }//GEN-LAST:event_VIEWALLActionPerformed

    private void finalpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finalpriceActionPerformed

    private void cartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cartPropertyChange
        third();
    }//GEN-LAST:event_cartPropertyChange

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
        if(jTable1!=null) {
            String key = searchTxtF.getText().trim();
            DefaultTableModel defaultTableModel = new DefaultTableModel() {
                // Override the isCellEditable method to always return false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        
            String s1[]=new String []{"Category", "Item","Qty", "Price(RM)"};
            defaultTableModel.setColumnIdentifiers(s1);
            String[] data = new String[4];
            for(Item i : DataIO.allItem) {
                if(i.getName().equals(key) || i.getCategory().getName().equals(key)) {
                    data[0] = i.getCategory().getName();
                    data[1] = i.getName();
                    data[2] = ""+i.getQuantity();
                    data[3] = ""+i.getPrice(); 
                    defaultTableModel.addRow(data);
                }
            }
            jTable1.setModel(defaultTableModel);
            jTable1.setVisible(true);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDCART;
    private javax.swing.JButton DELETEitem;
    private javax.swing.JButton MODIFYitem;
    private javax.swing.JTextField TOTAL2;
    private javax.swing.JTextField TOTALL;
    private javax.swing.JButton VIEWALL;
    private javax.swing.JButton back;
    private javax.swing.JTable cart;
    private javax.swing.JButton exit;
    private javax.swing.JTextField finalprice;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton order;
    private javax.swing.JSpinner qty2;
    private javax.swing.JSpinner qtyy;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTxtF;
    private javax.swing.JPanel total;
    // End of variables declaration//GEN-END:variables
    private void systemExit(){
        WindowEvent winCloseing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    }
}
