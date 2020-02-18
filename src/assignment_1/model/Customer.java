package assignment_1.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private double balance;
    private List<Transaction> transactions;
    
    public String getId() {
        return id;
    }
    
    public void setId(String customerNum) {
        this.id = customerNum;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public List<Transaction> getTransactions() {
        if(transactions == null) {
            transactions = new ArrayList<Transaction>();
        }
        return transactions;
    }
    
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
