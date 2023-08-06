
package pages;

import OODJAssignment.Admin;
import OODJAssignment.Category;
import OODJAssignment.DataIO;
import OODJAssignment.Item;
import OODJAssignment.OODMSystem;
import OODJAssignment.User;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CategoryManagePage implements ActionListener, ItemListener {
    @Override
    public void actionPerformed(ActionEvent evt) {
        try { 
        Boolean selected = false;
        String key = txtfield.getText().trim(); // trim() to remove leading and trailing spaces 
        if(evt.getSource()==add) {
            if(categoryRB.isSelected()) {
                Admin.addCategory();
            } else if (itemRB.isSelected()) {
                Admin.addItem();
            }
        } else if (evt.getSource()==search) {
            selected = false;
            if(selected==false) {
                scpList.setVisible(true);
            }
            if(categoryRB.isSelected()) {
                itemLModel.clear();
                Category c  = new Category("","");
                for(Category ca : DataIO.allCategory) {
                    if(categoryComb.getSelectedItem().equals(ca.getId())) {
                        c = ca;
                        break;
                    }
                }
                tableHeader = new String[] {
                    "CAT ID",
                    "Name"
                };
                data = new String[1][2];
                data[0][0] = c.getId();
                data[0][1] = c.getName();
                
                int size = c.getItemList().size();
                for(int i = 0; i<size;i++) {
                    itemLModel.addElement(""+c.getItemList().get(i).getId()+" : "+c.getItemList().get(i).getName()+" : " + c.getItemList().get(i).getQuantity()+" Pcs : RM"+c.getItemList().get(i).getPrice());   
                }
                //tableComponents();

            } else if (itemRB.isSelected()) {
                Item item = null;
                for(Item i : DataIO.allItem) {
                    if(key.equals(i.getId())) {
                        item = i;
                        break;
                    }
                }
                if (item==null) {
                    JOptionPane.showMessageDialog(null, "Item not found!");
                    throw new Exception();
                } else {
                    tableHeader = new String[] {
                        "ItemID",
                        "Name",
                        "Price",
                        "Quantity",
                        "CAT ID"
                    };
                    data = new String[1][5];
                    data[0][0] = item.getId();
                    data[0][1] = item.getName();  
                    data[0][2] = ""+item.getPrice();
                    data[0][3] = ""+item.getQuantity();
                    data[0][4] = item.getCategory().getId();
                }   
            }
            tableComponents();
        } else if (evt.getSource()==modify) {
            if(categoryRB.isSelected()) {
                Category c = new Category("","");
                for(Category ca : DataIO.allCategory) {
                    if(categoryComb.getSelectedItem().equals(ca.getId())) {
                        c = ca;
                        break;
                    }
                }

                int input = Integer.parseInt(JOptionPane.showInputDialog("What would like to change?"
                            + "\n1.Name"));
                switch (input) {
                    case 1:
                        String newName = JOptionPane.showInputDialog("Current Name is "+ c.getName()+ "\nInput new Name");
                        if(!newName.isEmpty()) {
                            c.setName(newName);
                        }
                        JOptionPane.showMessageDialog(null, "Current Name is "+c.getName());
                        break;
                    default:
                        throw new Exception();
                }
                DataIO.writeToFile();

            } else if (itemRB.isSelected()) {
                Item item = null;
                for(Item i : DataIO.allItem) {
                    if(key.equals(i.getId())) {
                        item = i;
                        break;
                    }
                }
                if (item==null) {
                    JOptionPane.showMessageDialog(null, "Item not found!");
                    throw new Exception();
                } else {
                    int input = Integer.parseInt(JOptionPane.showInputDialog("What would like to change?"
                            + "\n1.Name"
                            + "\n2.Price"
                            + "\n3.CategoryID"
                            + "\n4.Quantity"));
                    switch (input) {
                        case 1:
                            String newName = JOptionPane.showInputDialog("Current Name is "+ item.getName()+ "\nInput new Name");
                            if(!newName.isEmpty()) {
                                item.setName(newName);
                            }
                            break;
                        case 2:
                            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Current Price is "+ item.getPrice()+ "\nInput new Price"));
                            if(newPrice>=0) {
                                item.setPrice(newPrice);
                            }
                            JOptionPane.showMessageDialog(null, "Current Price is "+item.getName());
                            break;
                        case 3:
                            String newCATID = JOptionPane.showInputDialog("Current Category ID is "+ item.getCategory().getId()+ "\nInput new Category ID");
                            if(!newCATID.isEmpty()) {
                                Category flag = null;
                                for(Category c: DataIO.allCategory) {
                                    if(newCATID.equals(c.getId())){
                                        flag = c; 
                                        item.getCategory().setId(newCATID);
                                        break;
                                    }
                                }
                                if (flag==null) {
                                    JOptionPane.showMessageDialog(null, "Category ID not found!");
                                    throw new Exception();
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Current CategoryID is "+item.getCategory().getId());
                            break;
                        case 4:
                            int newQty = Integer.parseInt(User.getInput("How many qty to add?"));
                            if(newQty<1) {
                                JOptionPane.showMessageDialog(null,"Please input more than 0 value!");
                                return;
                            }
                            newQty = newQty + item.getQuantity();
                            item.setQuantity(newQty);
                            break;
                        default:
                            throw new Exception();
                    }
                    DataIO.writeToFile();
                }
            }
        } else if (evt.getSource()==delete) {
            if(categoryRB.isSelected()) {
                Category c = null;
                for(Category ca : DataIO.allCategory) {
                    if(key.equals(ca.getId())) {
                        c = ca;
                        break;
                    }
                }
                if (c==null) {
                    JOptionPane.showMessageDialog(null,"Category not found!");
                } else {
                    if(!c.getItemList().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There are items within the category!");
                        throw new Exception();
                    } else {
                        JOptionPane.showMessageDialog(null, "Category has been deleted");
                        categCombModel.removeElement(c);
                        DataIO.allCategory.remove(c);
                        DataIO.writeToFile();
                        categoryComb.setModel(categCombModel);
                    }
                }
            } else if (itemRB.isSelected()) {
                Item item = null;
                for(Item i : DataIO.allItem) {
                    if(key.equals(i.getId())) {
                        item = i;
                        break;
                    }
                }
                if (item==null) {
                    JOptionPane.showMessageDialog(null,"Item not found!");
                } else {
                    JOptionPane.showMessageDialog(null, "Item has been deleted");
                    DataIO.allItem.remove(item);
                    DataIO.assignItemtoCategory();
                    DataIO.writeToFile();
                }
            }
        } else if (evt.getSource()==backBtn) {
            catMngPage.setVisible(false);
            OODMSystem.adminPage1.getJFrame().setVisible(true);
        } else if(evt.getSource()==view) {
            selected = true;
            if(categoryRB.isSelected()) {
                int size = DataIO.allCategory.size();
                data = new String[size][2];
                for(int i =0; i<size; i++) {
                    Category c = DataIO.allCategory.get(i);
                    data[i][0] = c.getId();
                    data[i][1] = c.getName();
                }
                tableHeader = new String[] {"CAT ID", "Category Name"};
            } else if (itemRB.isSelected()) { 
                int size = DataIO.allItem.size();
                data = new String[size][5];
                for(int i =0; i<size; i++) {
                    Item c = DataIO.allItem.get(i);
                    data[i][0] = c.getId();
                    data[i][1] = c.getName();
                    data[i][2] = ""+c.getPrice();
                    data[i][3] = ""+c.getQuantity();
                    data[i][4] = c.getCategory().getId();
                }
                tableHeader = new String[] {"Item ID", "Name", "Price","Quantity", "CAT ID"};
            }   
            tableComponents();
            if(selected==true) {
                scpList.setVisible(false);
            }
        }
        txtfield.setText(" ");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input, Try again!");
        }         
    }
    
    private void tableComponents() {
        tableModel = new DefaultTableModel(data,tableHeader);
        if(viewTable!=null) {
            catMngPage.remove(scp);
        }
        viewTable = new JTable(tableModel);
        viewTable.setBounds(50,230,700,120);
        viewTable.getTableHeader().setBounds(50,210,700,20); 
        scp = new JScrollPane(viewTable);
        scp.setBounds(135,170,240,120);
        catMngPage.add(scp);  
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent ev) {
        String msg = "";
        Category temp = null;
        if (ev.getStateChange()==ItemEvent.SELECTED) {
            for(Category c : DataIO.allCategory) {
                if(categoryComb.getSelectedItem().equals(c.getId())) {
                    temp = c;
                    break;
                }
            }
            if(temp!=null) {
                msg = "Category Name : " + temp.getName();
            }
            categoryName.setText(msg);
        }    
    }
    
    private JFrame catMngPage;
    private final Button add,search,delete,modify,backBtn,view; 
    private JLabel title,categoryName;
    private final JRadioButton itemRB,categoryRB;
    private JTable viewTable;
    private JScrollPane scp,scpList;
    private JComboBox<String> categoryComb;
    private DefaultComboBoxModel<String> categCombModel;
    private JList<String> itemL;
    private DefaultListModel<String> itemLModel;
    private JTextField txtfield = new JTextField(30);
    private DefaultTableModel tableModel = new DefaultTableModel(); // new DefaultTableModel(data,tableHeader);
    private String[] tableHeader = null;
    private String[][] data = null;
 
    public CategoryManagePage () {
        catMngPage = new JFrame("Category/Item Management Page");
        catMngPage.setSize(400,600);
        catMngPage.setLocation(700,400);       
        catMngPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catMngPage.setLocationRelativeTo(null);
        catMngPage.setLayout(null);
        
        title = new JLabel("Category and Item Management");
        title.setBounds(70,50,300,40);
        title.setFont(new Font("Consolas",Font.BOLD,17));
        catMngPage.add(title);
        categoryName = new JLabel("Pick Category!");
        categoryName.setBounds(221,40,300,120);
        catMngPage.add(categoryName);
        
        txtfield = new JTextField();
        txtfield.setBounds(50,110,160,30);
        catMngPage.add(txtfield);
        view = new Button("View");
        add = new Button("Add");
        search = new Button("Search");
        delete = new Button("Delete");
        modify = new Button("Modify");
        view.setBounds(135,430,70,40);
        add.setBounds(50,150,70,40);
        search.setBounds(50,220,70,40);
        delete.setBounds(50,290,70,40);
        modify.setBounds(50,360,70,40);
        backBtn = new Button("Back");
        backBtn.setBounds(50,430,70,40);
        itemRB = new JRadioButton("Item");
        itemRB.setBounds(190,480,80,40);
        categoryRB = new JRadioButton("Category");
        categoryRB.setBounds(110,480,80,40);
        ButtonGroup bgType = new ButtonGroup();
        bgType.add(itemRB);
        bgType.add(categoryRB);
        
        view.addActionListener(this);
        add.addActionListener(this);
        search.addActionListener(this);
        delete.addActionListener(this);
        modify.addActionListener(this);
        backBtn.addActionListener(this);
        
        catMngPage.add(view);
        catMngPage.add(itemRB);
        catMngPage.add(categoryRB);
        catMngPage.add(add);
        catMngPage.add(search);
        catMngPage.add(delete);
        catMngPage.add(modify);
        catMngPage.add(backBtn);  
        
        categCombModel = new DefaultComboBoxModel<>();
        
        String[] datasize = null;
        if (DataIO.allCategory!=null) {
            datasize = new String[(DataIO.allCategory.size())];
            
            int i = 0;
            for(Category c : DataIO.allCategory) {
                datasize[i++] = c.getId() ;        
            }

            for(String item : datasize) {        
                categCombModel.addElement(item);
            }    
        }
   
        categoryComb = new JComboBox<>();
        categoryComb.setBounds(220,110,100,30);
        categoryComb.setModel(categCombModel);      
        catMngPage.add(categoryComb);
        categoryComb.addItemListener(this);
        
        itemLModel = new DefaultListModel<>();
        itemL = new JList<>(itemLModel);
        itemL.setBounds(135,300,240,120);
        scpList = new JScrollPane(itemL);
        scpList.setBounds(135,300,240,120);
        catMngPage.add(scpList);

    }
 
    public JFrame getJFrame() {
        return catMngPage;
    }
    
    public JTextField getTextField() {
        return txtfield;
    }
    
    public  JComboBox getComboBox() {
        return categoryComb;
    }
}
