package OODJAssignment;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Customer extends User {
    private String id;
    private String gender;
    private String email;
    private String address;
    private int age;
    private String contactNumber;
    private ArrayList<Cart> myCart = new ArrayList<>();

    public Customer(String id, String role, String username, String password, String name, String gender, String email, String address, int age, String contactNumber) {
        super(username, password, name, role);
        this.id = id;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ArrayList<Cart> getMyCart() {
        return myCart;
    }

    public void setMyCart(ArrayList<Cart> myCart) {
        this.myCart = myCart;
    }


    
    @Override // Customer Register Method
    public void register() {
        try {
            String cusUsername = OODMSystem.regPage1.getUsernameField().getText();
            Customer foundCustomer = DataIO.checkCustomerUsername(cusUsername);
            if (foundCustomer != null) {
                JOptionPane.showMessageDialog(null, "Username has been used!");
                throw new Exception();
            }
            String cusPassword = OODMSystem.regPage1.getPasswordField().getText();
            String customerName = User.getInput("Input your name!");
            String customerGender = "";
            Object[] customerGenderOption = {"Male", "Female"};
            int customerGenderChoice = JOptionPane.showOptionDialog(
                    null,
                    "Gender?",
                    "Gender",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    customerGenderOption,
                    customerGenderOption[0]
            );
            switch (customerGenderChoice) {
                case JOptionPane.YES_OPTION:
                    customerGender = "Male";
                    break;
                case JOptionPane.NO_OPTION:
                    customerGender = "Female";
                    break;
                default:
                    throw new Exception();
            }
            String customerRole = "Customer";
            String customerEmail = User.getInput("Input your email!");
            String customerAddress = User.getInput("Input your address!");
            String Age = User.getInput("Input your age");
            int customerAge = Integer.parseInt(Age);
            String customerContactNumber = User.getInput("Input your contact Number");
            int customerLastNum = 0;
            int customerLastIndex = DataIO.allCustomer.size() - 1;
            if (customerLastIndex >= 0) {
                String lastCustomerId = DataIO.allCustomer.get(customerLastIndex).getId();
                customerLastNum = Integer.parseInt(lastCustomerId.substring(3));
            }
            customerLastNum++;
            String customerId = "CID" + customerLastNum;
            JOptionPane.showMessageDialog(null, "Your Customer ID is: " + customerId);
            Customer customer = new Customer(customerId, customerRole, cusUsername,
                    cusPassword, customerName, customerGender, customerEmail,
                    customerAddress, customerAge, customerContactNumber);
            DataIO.allCustomer.add(customer);
            DataIO.writeToFile();
            JOptionPane.showMessageDialog(null, "Registration Success!");
        } catch(NumberFormatException ne) {
            JOptionPane.showMessageDialog(null, "Input age in Number format!");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration Failed!");
        }
    }
    
    
    public static ArrayList<Cart> getPendingOrderedCarts() { // Cart not paid but order placed
        ArrayList<Cart> pendingOrderedCarts = new ArrayList<>();
        for (Order or : DataIO.allOrder) {
            for(Cart ca : DataIO.allCart) {
                if (or.getOrder_status().equals("Pending") && ca.getCart_id().equals(or.getOrdered_cart().getCart_id())) {
                    Cart orderedCart = ca;
                    if (orderedCart.isCart_status()) {
                        pendingOrderedCarts.add(orderedCart);
                    }
                }
            }
        }
        return pendingOrderedCarts;
    }
    
    public static Order getPaidOrder() { // order paid and can be used for delivery
        Order ord = null;
        int lastOrder = DataIO.allOrder.size()-1;      
        for(int x = lastOrder; x>0; x--) {
            Order o = DataIO.allOrder.get(x);
            if(o.getOrdered_cart().getOwner().getId().equals(OODMSystem.onlineCustomer.getId())) {
                if(o.getOrder_status().equals("Complete")&&o.getPayId().getPay_status().equals("Approved")) {
                    ord = o;
                    break;
                }
            }
        }
        return ord;
    }
    
    public static Cart getCurrentCart() { // create new cart is current cart already placed to order
        Cart ct = null;
        if (OODMSystem.onlineCustomer.getMyCart()==null || OODMSystem.onlineCustomer.getMyCart().isEmpty()) {
            int lastNum = 0;
            int lastCart = DataIO.allCart.size()-1;
            if(lastCart>=0) {
                String lastId = DataIO.allCart.get(lastCart).getCart_id();
                lastNum = Integer.parseInt(lastId.substring(4));
            }
            lastNum++;

            String cartid = "CRID"+lastNum;
            boolean cart_status = false;
            Customer owner = OODMSystem.onlineCustomer;
            ct = new Cart(cartid,cart_status,owner);
            OODMSystem.onlineCustomer.getMyCart().add(ct);
            DataIO.allCart.add(ct);
            DataIO.writeToFile();
        } else {
            boolean hasCart = false;
            for (Cart c : OODMSystem.onlineCustomer.getMyCart()) {
                if(c.isCart_status()==false) {
                    ct = c;
                    hasCart = true;
                    break;
                }
            }
            
            if(hasCart==false) {
                int lastNum = 0;
                int lastCart = DataIO.allCart.size()-1;
                if(lastCart>=0) {
                    String lastId = DataIO.allCart.get(lastCart).getCart_id();
                    lastNum = Integer.parseInt(lastId.substring(4));
                }
                lastNum++;
                String cartid = "CRID"+lastNum;
                boolean cart_status = false;
                Customer owner = OODMSystem.onlineCustomer;
                ct = new Cart(cartid,cart_status,owner);
                OODMSystem.onlineCustomer.getMyCart().add(ct);
                DataIO.allCart.add(ct);
                DataIO.writeToFile();
            }
        }
        return ct;
    }
}
