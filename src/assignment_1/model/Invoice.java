package assignment_1.model;

import java.util.ArrayList;
import java.util.List;

import assignment_1.Constants;
import assignment_1.Constants.TransactionType;

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
    
    public void setRecords(List<Transaction> transactions) {
        records = new ArrayList<>();
        
        for(Transaction transaction : transactions) {
            records.add(String.format(Constants.FORMAT_1, transaction.getId(),
                    transaction.getType(), transaction.getAmount()));
        }
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    public void setCurrentBalance(List<Transaction> transactions) {
        currentBalance = previousBalance;
        
        for(Transaction transaction : transactions) {
            if(transaction.getType() == TransactionType.Order) {
                currentBalance += transaction.getAmount();
            } else {
                currentBalance -= transaction.getAmount();
            }
        }
    }
}
