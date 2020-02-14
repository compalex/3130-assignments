package assignment_1;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String customerId;
    private String customerName;
    private double previousBalance;
    private List<String> records;
    private double currentBalance;
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public double getPreviousBalance() {
        return previousBalance;
    }
    
    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }
    
    public List<String> getRecords() {
        return records;
    }
    
    public void setRecordsAndBalance(List<Transaction> transactions) {
        records = new ArrayList<>();
        currentBalance = previousBalance;
        
        for(Transaction transaction : transactions) {
            records.add(transaction.getId() + "\t" + 
                transaction.getType() + "\t" + transaction.getAmount());
            if(transaction.getType() == TransactionType.Order) {
                currentBalance += transaction.getAmount();
            } else {
                currentBalance -= transaction.getAmount();
            }
        }
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
}
