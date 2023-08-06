package OODJAssignment;

import javax.swing.JOptionPane;

public class Staff extends User{
    private String id;
    private String contactNum;

    public Staff(String id, String role, String username, String password, String name, String contactNum) {
        super(username, password, name, role);
        this.id = id;
        this.contactNum = contactNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
    
    
    @Override // Staff Register Method
    public void register() {
        try {
            
            String staffUsername = OODMSystem.regPage1.getUsernameField().getText();
            
            Staff foundStaff = DataIO.checkStaffUsername(staffUsername);
            if (foundStaff != null) {
                JOptionPane.showMessageDialog(null, "Username has been used!");
                throw new Exception();
            }
            String staffPassword = OODMSystem.regPage1.getPasswordField().getText();
            String staffName = User.getInput("Input your name!");
            String staffContactNumber = User.getInput("Input your Contact Number!");
            int staffLastNum = 0;
            int staffLastIndex = DataIO.allStaff.size() - 1;
            if (staffLastIndex >= 0) {
                String lastStaffId = DataIO.allStaff.get(staffLastIndex).getId();
                staffLastNum = Integer.parseInt(lastStaffId.substring(3));
            }
            staffLastNum++;
            String staffRole = "Staff";
            String staffId = "SID" + staffLastNum;
            Staff staff = new Staff(staffId, staffRole, staffUsername, staffPassword,
            staffName, staffContactNumber);
            DataIO.allStaff.add(staff);
            DataIO.writeToFile();
            JOptionPane.showMessageDialog(null, "Your Staff ID is: " + staffId);
            JOptionPane.showMessageDialog(null, "Registration Success!");
        } catch(NumberFormatException ne) {
            JOptionPane.showMessageDialog(null, "Input age in Number format!");
        }   catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration Failed!");
        }
    }

}
