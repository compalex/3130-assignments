package assignment_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment_1.model.Customer;
import assignment_1.model.Transaction;

public class CustomerService {
    
    public static List<Customer> loadCustomersWithTransactions() {
        List<Customer> customers = CSVReader.getCustomers();     
        customers = removeDuplicates(customers);
        List<Transaction> transactions = CSVReader.getTransactions();
        customers = addTransactions(customers, transactions);
        return customers;
    }
    
    private static List<Customer> removeDuplicates(List<Customer> customers) {
        if(customers == null || customers.isEmpty()) {
            return customers;
        }
        Map<String, Customer> customerMap = new HashMap<>();
        
        //duplicates will be deleted by putting them into Map
        for(Customer customer : customers) {
            if(customerMap.containsKey(customer.getId())) {
                System.err.println(Constants.ERROR_CUSTOMER_ID_DUPLICATION_MSG);
            }
            customerMap.put(customer.getId(), customer);
        }
        return new ArrayList<>(customerMap.values());
    }

    private static List<Customer> addTransactions(List<Customer> customers, 
            List<Transaction> transactions) {
        if(customers == null || customers.isEmpty() || transactions == null) {
            return customers;
        }
        Map<String, Customer> customerMap = new HashMap<>();
        
        for(Customer customer : customers) {
            customerMap.put(customer.getId(), customer);
        }
        
        for(Transaction transaction : transactions) {
            if(customerMap.containsKey(transaction.getCustomerId())) {
                customerMap.get(transaction.getCustomerId()).getTransactions().add(transaction);
            } else {
                System.err.println(Constants.ERROR_NO_CUSTOMER_MSG);
            }
        }
        return new ArrayList<>(customerMap.values());
    }
}
