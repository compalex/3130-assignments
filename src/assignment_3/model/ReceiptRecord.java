package assignment_3.model;

public class ReceiptRecord extends Record {
    private int quantity;
    private double price;
    
    public ReceiptRecord(int quantity, double price) {
        super(Record.Type.RECEIPT);
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
}
