
package OODJAssignment;

public class Item {
    private String name;
    private String id;
    private double price;
    private int quantity;
    private Category category; // to take categoryID
    
    public Item(String id,String name, double price,int quantity, Category category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
