
package OODJAssignment;

import deliverystaff.deliverystaffmenu;
import guest.guestCustomerPage;
import pages.AdminNavPage;
import pages.AdminOrderPage;
import pages.AdminPaymentPage;
import pages.AdminReportPage;
import pages.CategoryManagePage;
import pages.DeliveryManagePage;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.UserManagementPage;
import registeredcustomer.regicustomermenu;


public class OODMSystem {
    public static LoginPage loginPage1;
    public static RegistrationPage regPage1;
    public static AdminNavPage adminPage1;
    public static UserManagementPage userManagePage1;
    public static Customer onlineCustomer;
    public static Staff onlineStaff;
    public static CategoryManagePage catManagePage1;
    public static DeliveryManagePage deliveryManagePage1;
    public static deliverystaffmenu deliverymenu;
    public static regicustomermenu   regicosutomer;
    public static guestCustomerPage GUESTPAGE;
    public static AdminOrderPage orderManagePage1;
    public static AdminPaymentPage payManagePage1;
    public static AdminReportPage reportManagePage1;
    
    public static void main(String[] args) {
        DataIO.createFile();  
        DataIO.readFromFile();
        DataIO.assignItemtoCategory();
        DataIO.assignDetailOrdertoCart();
  
        // General Page
        loginPage1 = new LoginPage();
        regPage1 = new RegistrationPage(); 
        
        // Admin Page
        adminPage1 = new AdminNavPage();
        userManagePage1 = new UserManagementPage(); 
        catManagePage1 = new CategoryManagePage();
        deliveryManagePage1 = new DeliveryManagePage();
        orderManagePage1 = new AdminOrderPage();
        payManagePage1 = new AdminPaymentPage();
        reportManagePage1 = new AdminReportPage();
        
           
        // Delivery Staff Page
        deliverymenu = new deliverystaffmenu();
        
        // Customer Page
        regicosutomer = new regicustomermenu();
        
        // Guest Page
        GUESTPAGE = new guestCustomerPage();
    } 
    
}
