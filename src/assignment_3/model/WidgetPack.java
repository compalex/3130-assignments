package assignment_3.model;

public class WidgetPack {
    private double price;
    private int quantity;
    
    public WidgetPack(Double price, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getSales() {
        return price * quantity;
    }
}
