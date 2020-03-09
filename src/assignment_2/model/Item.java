package assignment_2.model;

public class Item {
    
    public enum Type {
        Laptop,
        Printer,
        Table
    }
    
    private Type type;
    private double price;
    
    public Item(Type type, double price) {
        this.setType(type);
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
