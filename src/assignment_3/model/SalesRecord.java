package assignment_3.model;

public class SalesRecord extends Record {
    private int quantity;

    public SalesRecord(int quantity) {
        super(Record.Type.SALES);
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }
}
