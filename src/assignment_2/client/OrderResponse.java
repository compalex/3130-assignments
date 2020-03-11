package assignment_2.demoClient;

public class OrderResponse {
    private String details;
    private double orderPrice;
    private boolean isProcessed;
    
    public OrderResponse() {
        isProcessed = false;
    }
    
    public String getDetails() {
        if(details == null) {
            details = new String();
        }
        return details;
    }
    
    public double getOrderPrice() {
        return orderPrice;
    }
    
    public boolean isProcessed() {
        return isProcessed;
    }
    
    public void addDetails(String details) {
        this.details = getDetails() + details;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }
}
