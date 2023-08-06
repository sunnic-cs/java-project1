
package pages;

import OODJAssignment.DataIO;
import OODJAssignment.OODMSystem;
import OODJAssignment.Payment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminPaymentPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent evt) {
        try{
            if(evt.getSource()==view) {
                int size = DataIO.allPayment.size();
                data = new String[size][5];
                for(int i = 0 ; i<size; i ++) {
                    Payment p = DataIO.allPayment.get(i);
                    data[i][0] = p.getId();
                    data[i][1] = p.getCardNumber();
                    data[i][2] = ""+p.getTotalPaid();
                    data[i][3] = ""+p.getPaidDate();
                    data[i][4] = p.getPay_status();
                }
                tableHeader = new String[] {"ID","CardNumber","Amount","Date","Status"};
            } else if(evt.getSource()==search) {
                
                String key = field.getText().trim();
                ArrayList<Payment> filter = new ArrayList<>();
                for(Payment p : DataIO.allPayment) {
                    if(key.equals(p.getId()) || key.equals(p.getCardNumber()) ||  key.equals(p.getPaidDate()) || key.equals(p.getPay_status())) {
                        filter.add(p);
                    } 
                }
                int size = filter.size();
                data = new String[size][5];
                for(int i = 0; i < size; i ++) {
                    Payment p = filter.get(i);
                    data[i][0] = p.getId();
                    data[i][1] = p.getCardNumber();
                    data[i][2] = ""+p.getTotalPaid();
                    data[i][3] = ""+p.getPaidDate();
                    data[i][4] = p.getPay_status();
                }
                tableHeader = new String[] {"ID","CardNumber","Amount","Date","Status"};
            } else if(evt.getSource()==back) {
                admPayPage.setVisible(false);
                OODMSystem.adminPage1.getJFrame().setVisible(true);
            }
            tableComponents();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Unknown Error");
        }
    }
    
    private JFrame admPayPage;
    private JLabel title;
    private JButton view, search,back;
    private JTextField field;
    private JTable recordTable;
    private JScrollPane scp;
    private DefaultTableModel tableModel;
    private String[][] data;
    private String[] tableHeader;
    
    public AdminPaymentPage () {
        admPayPage = new JFrame("Payment Record Page");
        admPayPage.setSize(520,400);
        admPayPage.setLocation(700,400);       
        admPayPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admPayPage.setLocationRelativeTo(null);
        admPayPage.setLayout(null);
        
        title= new JLabel("Payment Record Page");
        title.setBounds(160,50,300,40);
        title.setFont(new Font("Consolas",Font.BOLD,17));
        view = new JButton("View");
        view.setBounds(47,111,70,28);
        view.addActionListener(this);
        search = new JButton("Search");
        search.setBounds(390,111,75,28);
        search.addActionListener(this);
        field = new JTextField(30);
        field.setBounds(130,110,250,30);
        back = new JButton("Back");
        back.setBounds(20,20,70,30);
        back.addActionListener(this);
        admPayPage.add(back);
        admPayPage.add(view);
        admPayPage.add(search);
        admPayPage.add(field);
        admPayPage.add(title);

    }
    
    private void tableComponents() {
        tableModel = new DefaultTableModel(data,tableHeader);
        if(recordTable!=null && scp!=null) {
            admPayPage.remove(scp);
        }
        recordTable = new JTable(tableModel);
        recordTable.setBounds(50,230,700,120);
        recordTable.getTableHeader().setBounds(50,210,700,20); 
        scp = new JScrollPane(recordTable);
        scp.setBounds(43, 160, 430, 190);
        admPayPage.add(scp);  
    }
    
    public JFrame getJFrame() {
        return admPayPage;
    }
}