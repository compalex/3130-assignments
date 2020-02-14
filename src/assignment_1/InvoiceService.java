package assignment_1;

import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    public static List<Invoice> convertAll(List<Customer> customers) {
        List<Invoice> invoices = new ArrayList<>();
        
        for(Customer customer : customers) {
            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getId());
            invoice.setCustomerName(customer.getName());
            invoice.setPreviousBalance(customer.getBalance());
            invoice.setRecordsAndBalance(customer.getTransactions());
            invoices.add(invoice);
        }
        return invoices;
    }
    
    public static void println(List<Invoice> invoices) {
        for(Invoice invoice : invoices) {
            System.out.println(invoice.getCustomerName() + "\t" + invoice.getCustomerId()
                + "\n\t\tPREVIOUS BALANCE:\t" + invoice.getPreviousBalance());
            
            for(String record : invoice.getRecords()) {
                System.out.println(record);
            }
            System.out.println("\t\tBALANCE DUE:\t" + invoice.getCurrentBalance());
            System.out.println(Constants.LINE_SEPARATOR);
        }
    }
}
