package assignment_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    public static List<Customer> loadCustomers() {
        List<Customer> customers = CSVReader.getCustomers();     
        checkForDuplicates(customers);
        List<Transaction> transactions = CSVReader.getTransactions(customers);
        customers = handleTransactions(customers, transactions);
        return customers;
    }
    
    private static void checkForDuplicates(List<Customer> customers) {
        if(customers == null || customers.isEmpty()) {
            return;
        }
        List<String> customerIDs = new ArrayList<>();
        
        for(Customer customer : customers) {
            if(customerIDs.contains(customer.getId())) {
                System.err.println(Constants.ERROR_CUSTOMER_ID_DUPLICATION_MSG);
            }
            customerIDs.add(customer.getId());
        }
    }

    private static List<Customer> handleTransactions(List<Customer> customers, List<Transaction> transactions) {
        if(customers == null || customers.isEmpty() || transactions == null) {
            return customers;
        }
        Map<String, Customer> customerMap = new HashMap<>();
        
        for(Customer customer : customers) {
            customerMap.put(customer.getId(), customer);
        }
        
        for(Transaction transaction : transactions) {
            if(customerMap.containsValue(transaction.getCustomer())) {
                customerMap.get(transaction.getCustomer().getId()).getTransactions().add(transaction);
            } else {
                System.err.println(Constants.ERROR_NO_CUSTOMER_MSG);
            }
        }
        return customers;
    }
}
