package OODJAssignment;
public class CartDetails {
    private String item;
    private int qty;
    private double total;
    private Cart cartid; 

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cart getCartid() {
        return cartid;
    }

    public void setCartid(Cart cartid) {
        this.cartid = cartid;
    }

    public CartDetails(String item, int qty, double total, Cart cartid) {
        this.item = item;
        this.qty = qty;
        this.total = total;
        this.cartid = cartid;
    }
      
}
