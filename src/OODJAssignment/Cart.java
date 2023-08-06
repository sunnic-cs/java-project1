package OODJAssignment;

import java.util.ArrayList;


public class Cart {
    private String cart_id;
    private boolean cart_status;
    private Customer owner;
    private ArrayList<CartDetails> orderList = new ArrayList<>();

    public Cart(String cart_id, boolean cart_status, Customer owner) {
        this.cart_id = cart_id;
        this.cart_status = cart_status;
        this.owner = owner;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
    
    public void addToCart(CartDetails o) {
        orderList.add(o);
    }
    
    public void removeFromCart(CartDetails o) {
        orderList.remove(o);
    }

    public boolean isCart_status() {
        return cart_status;
    }

    public void setCart_status(boolean cart_status) {
        this.cart_status = cart_status;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public ArrayList<CartDetails> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<CartDetails> orderList) {
        this.orderList = orderList;
    }
}
