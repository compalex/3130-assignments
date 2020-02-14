package assignment_1;

public class Transaction {
    private Customer customer;
    private TransactionType type;
    private String id;
    private String item;
    private int quantity;
    private double price;
    private double amount;
    
    public double getAmount() {
        if(type == TransactionType.Order) {
            amount = quantity * price;
        }
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public void setType(TransactionType type) {
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    } 
}
