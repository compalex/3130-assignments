package assignment_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assignment_1.Constants.TransactionType;
import assignment_1.model.Customer;
import assignment_1.model.Transaction;

public class CSVReader {
    
    public static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.MASTER_PATH));
        
        for(String[] record : allData) {
            Customer customer = new Customer();
            customer.setId(record[0]);
            customer.setName(record[1]);
            customer.setBalance(Double.parseDouble(record[2])); 
            customers.add(customer);
        }
        return customers;
    }
    
    public static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.TRANSACTION_PATH));
        
        for(String[] record : allData) {
            Transaction transaction = new Transaction();
            transaction.setCustomerId(record[0]);
            switch(record[1]) {
                case "O":
                    transaction.setType(TransactionType.Order);
                    transaction.setId(record[2]);
                    transaction.setItem(record[3]);
                    transaction.setQuantity(Integer.parseInt(record[4]));
                    transaction.setPrice(Double.parseDouble(record[5]));
                    break;
                case "P":
                    transaction.setType(TransactionType.Payment);
                    transaction.setId(record[2]);
                    transaction.setAmount(Double.parseDouble(record[3]));
                    break;
                default:
                    System.err.println(Constants.ERROR_DATA_MSG);
            }
            transactions.add(transaction);
        }
        return transactions;
    }

    private static List<String[]> getAllData(File file) {
        List<String[]> records = new ArrayList<String[]>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {  
            String line = br.readLine();
            
            while((line = br.readLine()) != null) {
                String[] values = line.split(Constants.SEPARATOR);
                records.add(values);
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_DATA_MSG);
            e.printStackTrace();
        }
        return records;
    }
}
