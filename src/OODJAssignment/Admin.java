
package OODJAssignment;

import javax.swing.JOptionPane;

public class Admin extends User{
    private String id;
    private String email;

    public Admin(String id,String role, String username, String password, String name, String email) {
        super(username, password, name, role);
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override // Admin Register Method
    public void register() {
        try {
            
            String adminUsername = OODMSystem.regPage1.getUsernameField().getText();
            Admin foundAdmin = DataIO.checkAdminUsername(adminUsername);
            if (foundAdmin != null) {
                JOptionPane.showMessageDialog(null, "Username has been used!");
                throw new Exception();
            }
            String adminPassword = OODMSystem.regPage1.getPasswordField().getText();
            int adminLastNum = 0;
            int adminLastIndex = DataIO.allAdmin.size() - 1;
            if (adminLastIndex >= 0) {
                String lastAdminId = DataIO.allAdmin.get(adminLastIndex).getId();
                adminLastNum = Integer.parseInt(lastAdminId.substring(3));
            }
            adminLastNum++;
            String adminRole = "Admin";
            String adminId = "AID" + adminLastNum;
            String adminName = User.getInput("Input your name!");
            String adminEmail = User.getInput("Input your email!");
            Admin admin = new Admin(adminId, adminRole, adminUsername, adminPassword,
                    adminName, adminEmail);
            DataIO.allAdmin.add(admin);
            DataIO.writeToFile();
            JOptionPane.showMessageDialog(null, "Your Admin ID is: " + adminId);
            JOptionPane.showMessageDialog(null, "Registration Success!");            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration Failed!");
        }
    }


    public static void addUser(String usertype) {
        try {
            Object found = null;
            String username = User.getInput("Input new Username");
            switch (usertype) {
                case "Customer":
                    found = DataIO.checkCustomerUsername(username);
                    break;
                case "Staff":
                    found = DataIO.checkStaffUsername(username);
                    break;
                case "Admin":
                    found = DataIO.checkAdminUsername(username);
                    break;
                default:
                    break;
            }
            if(found!=null){
                JOptionPane.showMessageDialog(null,"Username has been Used!");
                throw new Exception();
            }
            String password = User.getInput("Input password");
            String name = User.getInput("Input your name");
            String id;
            Object user;
            switch (usertype) {
                case "Customer":
                    {
                        String role = "Customer";
                        int customerLastNum = 0;
                        int customerLastIndex = DataIO.allCustomer.size() - 1;
                        if (customerLastIndex >= 0) {
                            String lastCustomerId = DataIO.allCustomer.get(customerLastIndex).getId();
                            customerLastNum = Integer.parseInt(lastCustomerId.substring(3));
                        }
                        customerLastNum++;
                        String customerId = "CID" + customerLastNum;
                        String gender = "";
                        Object[] option = {"Male","Female"};
                        int choice = JOptionPane.showOptionDialog(null, "Choose your Gender!", "Gender",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
                        switch (choice) {
                            case JOptionPane.YES_OPTION:
                                gender = "Male";
                                break;
                            case JOptionPane.NO_OPTION:
                                gender = "Female";
                                break;
                            default:
                                throw new Exception();
                        }       
                        String email = User.getInput("Input your email!");
                        String address = User.getInput("Input your address");
                        int age = Integer.parseInt(User.getInput("Input your age"));
                        String contactNumber = User.getInput("Input your contact number");
                        user = new Customer(customerId, role,  username, password, name,gender,email,  address, age, contactNumber);
                        JOptionPane.showMessageDialog(null, "Your Customer ID is: " + customerId);
                        DataIO.allCustomer.add((Customer) user);
                        break;
                    }
                case "Staff":
                    {
                        String role = "Staff";
                        int LastNum = 0;
                        int LastIndex = DataIO.allStaff.size() - 1;
                        if (LastIndex >= 0) {
                            String lastStaffId = DataIO.allStaff.get(LastIndex).getId();
                            LastNum = Integer.parseInt(lastStaffId.substring(3));
                        }
                        LastNum++;
                        id = "SID" + LastNum;
                        String contactNumber = User.getInput("Input your contact number");
                        JOptionPane.showMessageDialog(null, "Your Staff ID is: " + id);
                        user = new Staff(id, role,  username, password, name,  contactNumber);
                        DataIO.allStaff.add((Staff) user);
                        break;
                    }
                case "Admin":
                    {
                        String role = "Admin";
                        int LastNum = 0;
                        int LastIndex = DataIO.allAdmin.size() - 1;
                        if (LastIndex >= 0) {
                            String lastAdminId = DataIO.allAdmin.get(LastIndex).getId();
                            LastNum = Integer.parseInt(lastAdminId.substring(3));
                        }
                        LastNum++;
                        id = "AID" + LastNum;
                        String email = User.getInput("Input your email!");
                        JOptionPane.showMessageDialog(null, "Your Admin ID is: " + id);
                        user = new Admin(id, role,username,password,name,email);
                        DataIO.allAdmin.add((Admin) user); // Casting object user to admin class (Object became Admin object)
                        break;
                    }
                default:
                    break;
            }
            DataIO.writeToFile();
            JOptionPane.showMessageDialog(null, "Registration Success!");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid Input!");
        }   
    }
    
    public static void addItem() {
        try {
            String name = OODMSystem.catManagePage1.getTextField().getText().trim();
            if (name.isEmpty()) {
                throw new Exception();
            }
            int lastNum = 0;
            int lastItem = DataIO.allItem.size()-1;
            if(lastItem>=0) {
                String lastId = DataIO.allItem.get(lastItem).getId();
                lastNum = Integer.parseInt(lastId.substring(3));
            }
            lastNum++;
            String id = "PID" + lastNum;
            double price = Double.parseDouble(User.getInput("Input item Price"));
            String category = OODMSystem.catManagePage1.getComboBox().getSelectedItem().toString();
            Category check = new Category("","");
            for (Category c : DataIO.allCategory) {
                if(category.equals(c.getId())) {
                    check = c;
                    break;
                }
            }
            
            
            int confirmCode = JOptionPane.showConfirmDialog(null, "Do you want to add this item to "+check.getName() + " category","Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmCode==0) {
                int quantity = Integer.parseInt(User.getInput("Input Quantity of Item"));
                if(quantity<=0) {
                    JOptionPane.showMessageDialog(null,"Value must be above 0!");
                    throw new Exception();
                }
                Item i = new Item(id,name,price,quantity,new Category(check.getId(),check.getName()));
                DataIO.allItem.add(i);
                DataIO.writeToFile(); 
                JOptionPane.showMessageDialog(null, "Item with Name : "+name+" has been registered Succesfully"
                + "\n Item ID is : "+id);
                DataIO.assignItemtoCategory();
            } else {
                JOptionPane.showMessageDialog(null, "Item registration failed");
            }   

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Invalid Input Try Again!");
        }
        
    }
    
    public static void addCategory() {
        try {
            String name = OODMSystem.catManagePage1.getTextField().getText().trim();
            if (name.isEmpty()) {
                throw new Exception();
            }

            for (Category c : DataIO.allCategory) {
                if(name.equals(c.getName())) {
                    JOptionPane.showMessageDialog(null,"Category Exist!");
                    throw new Exception();
                }
            }
            int lastNum = 0;
            int lastCategory = DataIO.allCategory.size()-1;
            if(lastCategory>=0) {
                String lastId = DataIO.allCategory.get(lastCategory).getId();
                lastNum = Integer.parseInt(lastId.substring(3));
            }
            lastNum++;
            String id = "CAT" + lastNum;
            JOptionPane.showMessageDialog(null, "Category with Name : "+name+" has been registered Succesfully"
                    + "\n Category ID is : "+id);
            Category c = new Category(id,name);
            DataIO.allCategory.add(c);
            OODMSystem.catManagePage1.getComboBox().addItem(c.getId());
            DataIO.writeToFile();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Invalid Input Try Again!");
        }    
    }
    
}
