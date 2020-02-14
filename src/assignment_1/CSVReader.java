package assignment_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<Transaction> getTransactions(List<Customer> customers) {
        List<Transaction> transactions = new ArrayList<>();
        List<String[]> allData = getAllData(new File(Constants.TRANSACTION_PATH));
        
        for(String[] record : allData) {
            Transaction transaction = new Transaction();
            
            for(Customer customer : customers) {
                if(customer.getId().equals(record[0])) {
                    transaction.setCustomer(customer);
                }
            }

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
        BufferedReader br = null;
        List<String[]> records = new ArrayList<String[]>(); 
        String line;
        try {
            br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
