package OODJAssignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class DataIO {
    public static ArrayList<Cart> allCart = new ArrayList<>();
    public static ArrayList<Customer> allCustomer = new ArrayList<>();
    public static ArrayList<Admin> allAdmin = new ArrayList<>();
    // public static ArrayList<User> allUsers = new ArrayList<>();
    public static ArrayList<Staff> allStaff = new ArrayList<>();
    public static ArrayList<Category> allCategory = new ArrayList<>();
    public static ArrayList<Item> allItem = new ArrayList<>();
    public static ArrayList<CartDetails> allCartDetails = new ArrayList<>();
    public static ArrayList<Order>allOrder = new ArrayList<>();
    public static ArrayList<Delivery>allDelivery = new ArrayList<>();
    public static ArrayList<Payment>allPayment = new ArrayList<>();
    
    public static void readFromFile(){
        try {
            allCustomer.clear();
            Scanner read1,read2,read3,read4,read5,read6,read7,read8,read9,read10;
            read1 = new Scanner(new File("customer.txt"));
            while(read1.hasNext()) {
                String id = read1.nextLine();
                String role = read1.nextLine();
                String username = read1.nextLine();
                String user_pwd = read1.nextLine();
                String name = read1.nextLine();    
                String gender = read1.nextLine();
                String email = read1.nextLine();
                String address = read1.nextLine();
                int age = Integer.parseInt(read1.nextLine());
                String contactNumber = read1.nextLine();
                read1.nextLine();
                Customer c = new Customer(id, role, username, user_pwd, name,gender,email, address, age, contactNumber);
                allCustomer.add(c);
            }
            allStaff.clear();
            read2 = new Scanner(new File("staff.txt"));
            while(read2.hasNext()) {
                String staff_id = read2.nextLine();
                String role = read2.nextLine();
                String username = read2.nextLine();
                String userpwd = read2.nextLine();
                String name = read2.nextLine();
                String contactNumber = read2.nextLine();
                read2.nextLine();
                Staff s = new Staff(staff_id, role, username, userpwd, name,  contactNumber);
                allStaff.add(s);
            }
            allAdmin.clear();
            read3 = new Scanner(new File("admin.txt"));
            while(read3.hasNext()) {
                String admin_id = read3.nextLine();
                String role = read3.nextLine();
                String username = read3.nextLine();
                String userpwd = read3.nextLine();
                String name = read3.nextLine();              
                String admin_email = read3.nextLine();
                read3.nextLine();
                Admin a = new Admin(admin_id, role, username, userpwd, name, admin_email);
                allAdmin.add(a);
            }
            allCategory.clear();
            read4 = new Scanner(new File("category.txt"));
            while(read4.hasNext()) {
                String id = read4.nextLine();
                String name = read4.nextLine();
                read4.nextLine();
                Category c = new Category(id, name);
                allCategory.add(c);
            }           
            allItem.clear();
            read5 = new Scanner(new File("item.txt"));
            while(read5.hasNext()) {
                String id = read5.nextLine();
                String name = read5.nextLine();
                double price = Double.parseDouble(read5.nextLine());
                int quantity = Integer.parseInt(read5.nextLine());
                Category categoryID = readCategory(read5.nextLine());
                read5.nextLine();
                Item i = new Item (id,name,price,quantity,categoryID);
                allItem.add(i);
            }
            
            allCart.clear();
            read8 = new Scanner(new File("cart.txt"));
            while(read8.hasNext()) {
                String id = read8.nextLine();
                Boolean status = Boolean.valueOf(read8.nextLine());
                Customer owner = readOwner(read8.nextLine());
                read8.nextLine();
                Cart c = new Cart(id,status,owner);
                allCart.add(c);
                owner.getMyCart().add(c);
            }
            allCartDetails.clear();
            read6 = new Scanner(new File("cartDetails.txt"));
            while(read6.hasNext()) {
                String item = read6.nextLine();
                int qty = Integer.parseInt(read6.nextLine());
                double total = Double.parseDouble(read6.nextLine());
                Cart cartid = readCart(read6.nextLine());
                read6.nextLine();
                CartDetails k = new CartDetails (item,qty,total,cartid);
                allCartDetails.add(k);            
            }
            allPayment.clear();
            read10 = new Scanner(new File("payment.txt"));
            while(read10.hasNext()) {
                String id = read10.nextLine();
                String cardNum = read10.nextLine();
                double total = Double.parseDouble(read10.nextLine());
                LocalDate date = LocalDate.parse(read10.nextLine());
                String status = read10.nextLine();
                read10.nextLine();
                Payment o = new Payment (id,cardNum,total,date,status);
                allPayment.add(o);
            }
            
            allOrder.clear();
            read7 = new Scanner(new File("order.txt"));
            while(read7.hasNext()) {
                String orderID = read7.nextLine();
                LocalDate date = LocalDate.parse(read7.nextLine());
                double total = Double.parseDouble(read7.nextLine());
                String status = read7.nextLine();
                Cart cartid = readCart(read7.nextLine());
                Payment payid = readPayment(read7.nextLine());
                read7.nextLine();
                Order o = new Order (orderID,date,total,status,cartid,payid);
                allOrder.add(o);
            } 
            allDelivery.clear();
            read9 = new Scanner(new File("delivery.txt"));
            while(read9.hasNext()) {
                String deliveryID = read9.nextLine();
                Boolean status = Boolean.valueOf(read9.nextLine());
                String feedback = read9.nextLine();
                Order or= readOrder(read9.nextLine());
                Staff st= readStaff(read9.nextLine());
                String Nowstatus = read9.nextLine();
                read9.nextLine();
                Delivery d = new Delivery(deliveryID,status,feedback,or,st,Nowstatus);
                allDelivery.add(d);
            }         
        } catch(FileNotFoundException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error when reading File...");
        }       
    }
    
    public static Order readOrder(String id) {
        Order found = null;
        for(Order c : allOrder) {
            if(id.equals(c.getOrderID())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static Staff readStaff(String id) {
        Staff found = null;
        for(Staff c : allStaff) {
            if(id.equals(c.getId())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static void assignItemtoCategory() {
        for(Category c:allCategory) {
            ArrayList<Item> itemList = new ArrayList<>();
            for(Item i : allItem) {
                if(i.getCategory().getId().equals(c.getId())) {
                  itemList.add(i);  
                }
            }
            c.setItemList(itemList);
            
        }
    }   
    
    public static void assignDetailOrdertoCart() {
        for(Cart c : allCart) {
            ArrayList<CartDetails> orderList = new ArrayList<>();
            for(CartDetails k : allCartDetails) {
                if(c.getCart_id().equals(k.getCartid().getCart_id())) {
                    orderList.add(k);
                }
            }
            c.setOrderList(orderList);
        }
    }
    
    public static Category readCategory(String id) {
        Category found = null;
        for(Category c : allCategory) {
            if(id.equals(c.getId())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static Customer readOwner(String id) {
        Customer found = null;
        for(Customer c : allCustomer) {
            if(id.equals(c.getId())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static Cart readCart(String id) {
        Cart found = null;
        for(Cart c : allCart) {
            if(id.equals(c.getCart_id())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static Payment readPayment(String id) {
        Payment found = null;
        for(Payment c : allPayment) {
            if(id.equals(c.getId())) {
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static void writeToFile() {
        try {
            PrintWriter print1,print2,print3,print4,print5,print6,print7,print8,print9,print10;
            print1 = new PrintWriter("customer.txt");
            for (Customer c : allCustomer) {
                print1.println(c.getId());
                print1.println(c.getRole());
                print1.println(c.getUsername());
                print1.println(c.getPassword());
                print1.println(c.getName());  
                print1.println(c.getGender());
                print1.println(c.getEmail());
                print1.println(c.getAddress());
                print1.println(c.getAge());
                print1.println(c.getContactNumber());
                print1.println();
            }
            print1.close();
            
            print2 = new PrintWriter("staff.txt");
            for (Staff s : allStaff) {
                print2.println(s.getId());
                print2.println(s.getRole());
                print2.println(s.getUsername());
                print2.println(s.getPassword());
                print2.println(s.getName());
                print2.println(s.getContactNum());
                print2.println();   
            }
            print2.close();
            
            print3 = new PrintWriter("admin.txt");
            for (Admin a : allAdmin) {
                print3.println(a.getId());
                print3.println(a.getRole());
                print3.println(a.getUsername());
                print3.println(a.getPassword());
                print3.println(a.getName());
                print3.println(a.getEmail());
                print3.println();
            }
            print3.close();
            
            print4 = new PrintWriter("category.txt");
            for(Category c : allCategory) {
                print4.println(c.getId());
                print4.println(c.getName());
                print4.println();
            }
            print4.close();
            
            print5 = new PrintWriter("item.txt");
            for(Item i : allItem) {
                print5.println(i.getId());
                print5.println(i.getName());
                print5.println(i.getPrice());
                print5.println(i.getQuantity());
                print5.println(i.getCategory().getId());
                print5.println();
            }
            print5.close();
            
            print7 = new PrintWriter("order.txt");
            for(Order k : allOrder) {
                print7.println(k.getOrderID());
                print7.println(k.getOrderDate());
                print7.println(k.getTotalPrice());
                print7.println(k.getOrder_status());
                print7.println(k.getOrdered_cart().getCart_id());
                print7.println(k.getPayId().getId());
                print7.println();
            }
            print7.close();
            
            print6 = new PrintWriter("cartDetails.txt");
            for(CartDetails k : allCartDetails) {
                print6.println(k.getItem());
                print6.println(k.getQty());
                print6.println(k.getTotal());
                print6.println(k.getCartid().getCart_id());
                print6.println();
                
            }
            print6.close();
            
            print8 = new PrintWriter("cart.txt");
            for(Cart c : allCart) {
                print8.println(c.getCart_id());
                print8.println(c.isCart_status());
                print8.println(c.getOwner().getId());
                print8.println();
            }
            print8.close();
            
            print9 = new PrintWriter("delivery.txt");
            for(Delivery d : allDelivery) {
                print9.println(d.getId());
                print9.println(d.getStatus());
                print9.println(d.getFeedback());
                print9.println(d.getOrderid().getOrderID());
                print9.println(d.getStaffid().getId());
                print9.println(d.getNowstatus());
                print9.println();
            }
            print9.close();
            
            print10 = new PrintWriter("payment.txt");
            for(Payment p : allPayment) {
                print10.println(p.getId());
                print10.println(p.getCardNumber());
                print10.println(p.getTotalPaid());
                print10.println(p.getPaidDate());
                print10.println(p.getPay_status());
                print10.println();
            }
            print10.close();
        }catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error when writing File...");
        }
    }
   
    public static Customer checkCustomerUsername(String username) {
        Customer found = null;
        
        for(Customer c : allCustomer) {
            if (username.equals(c.getUsername())) {
                found = c;
                break;
            }
        }   
        return found;
    }
    
    public static Staff checkStaffUsername(String username) {
        Staff found = null;
        for(Staff s : allStaff) {
            if(username.equals(s.getUsername())) {
               found = s;
               break;
            }  
        }
        return found;
    }
    
    public static Admin checkAdminUsername(String username) {
        Admin found = null;
        for(Admin a : allAdmin) {
            if(username.equals(a.getUsername())) {
               found = a;
               break;
            }  
        }
        return found;
    }
    
    public static void createFile(){
        try {
            File fp1 = new File("customer.txt");
            fp1.createNewFile();
            File fp2 = new File("staff.txt");
            fp2.createNewFile();
            File fp3 = new File("admin.txt");
            fp3.createNewFile();
            File fp4 = new File("category.txt");
            fp4.createNewFile();
            File fp5 = new File("item.txt");
            fp5.createNewFile();
            File fp6 = new File("cartDetails.txt");
            fp6.createNewFile();
            File fp7 = new File("order.txt");
            fp7.createNewFile();
            File fp8 = new File("cart.txt");
            fp8.createNewFile();
            File fp9 = new File("delivery.txt");
            fp9.createNewFile();
            File fp10 = new File("payment.txt");
            fp10.createNewFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Creating File");
        }
    }
}
