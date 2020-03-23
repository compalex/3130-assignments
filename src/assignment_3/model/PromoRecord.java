package assignment_3.model;

public class PromoRecord extends Record {
    private double discountPercentage;
    
    public PromoRecord(double percentage) {
        super(Record.Type.PROMOTION);
        discountPercentage = percentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }   
}
