package assignment_1;


import java.util.List;

import assignment_1.model.Customer;
import assignment_1.model.Invoice;

/**
 * Assignment #1
 * Preparing and printing invoices using data from 2 files
 * @version 1.0
 * @author Aliaksandr Yarmak
 */
public class Demo {
    
    public static void main(String[] args) { 
        //loading the list of Customers with their Transactions
        List<Customer> customers = CustomerService.loadCustomersWithTransactions();
        //preparing the list of Invoices
        List<Invoice> invoices = InvoiceService.convertAll(customers);
        //printing all Invoices
        InvoiceService.println(invoices);
    }
}
