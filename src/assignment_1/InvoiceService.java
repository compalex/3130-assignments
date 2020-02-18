package assignment_1;

import java.util.ArrayList;
import java.util.List;

import assignment_1.model.Customer;
import assignment_1.model.Invoice;

public class InvoiceService {
    
    public static List<Invoice> convertAll(List<Customer> customers) {
        if(customers == null || customers.isEmpty()) {
            return null;
        }
        List<Invoice> invoices = new ArrayList<>();
        
        for(Customer customer : customers) {
            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getId());
            invoice.setCustomerName(customer.getName());
            invoice.setPreviousBalance(customer.getBalance());
            invoice.setRecords(customer.getTransactions());
            invoice.setCurrentBalance(customer.getTransactions());
            invoices.add(invoice);
        }
        return invoices;
    }
    
    public static void println(List<Invoice> invoices) {
        //check for null
        for(Invoice invoice : invoices) {
            System.out.println(String.format(Constants.FORMAT_2, invoice.getCustomerName(),
                    invoice.getCustomerId(), invoice.getPreviousBalance()));
            
            for(String record : invoice.getRecords()) {
                System.out.println(record);
            }
            System.out.println(String.format(Constants.FORMAT_3, invoice.getCurrentBalance()));
            System.out.println(Constants.LINE_SEPARATOR);
            System.out.println();
        }
    }
}
