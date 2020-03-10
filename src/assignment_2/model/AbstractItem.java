package assignment_2.model;

public class AbstractItem {
    
    public enum Product {
        Laptop,
        Printer,
        Table
    }
    
    protected Product product;
    protected double price;
    
    public AbstractItem(Product product, double price) {
        this.setProduct(product);
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
