package OODJAssignment;

import javax.swing.JOptionPane;

public abstract class User {
    protected String username;
    protected String password;
    protected String name;
    protected String role;
    
    protected abstract void register(); // Abstract Method

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    public static String getInput(String msg) { // If user cancelled the input prompt, will return exception
        String input = JOptionPane.showInputDialog(msg);
        if(input==null) {
            throw new RuntimeException("Please Try Again!");
        }
        return input;   
    }
}
