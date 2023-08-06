package OODJAssignment;

import java.time.LocalDate;

public class Order {
    private String orderID;
    private LocalDate orderDate;
    private double TotalPrice;
    private String order_status; // status = pending, complete
    private Cart ordered_cart;
    private Payment payId;

    public Order(String orderID, LocalDate orderDate, double TotalPrice, String order_status, Cart ordered_cart, Payment payId) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.TotalPrice = TotalPrice;
        this.order_status = order_status;
        this.ordered_cart = ordered_cart;
        this.payId = payId;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Cart getOrdered_cart() {
        return ordered_cart;
    }

    public void setOrdered_cart(Cart ordered_cart) {
        this.ordered_cart = ordered_cart;
    }

    public Payment getPayId() {
        return payId;
    }

    public void setPayId(Payment payId) {
        this.payId = payId;
    }
  
}
