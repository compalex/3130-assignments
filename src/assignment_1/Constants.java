package assignment_1;

public class Constants {
    public enum TransactionType {
        Order,
        Payment
    }
    public static final String MASTER_PATH = "master.csv";
    public static final String TRANSACTION_PATH = "transaction.csv";
    public static final String ERROR_DATA_MSG = "Wrong data";
    public static final String ERROR_NO_CUSTOMER_MSG = "Transaction hasn't existing customer id";
    public static final String ERROR_CUSTOMER_ID_DUPLICATION_MSG = "2 customers have the same id";
    public static final String LINE_SEPARATOR = "_______________________________________________";
    public static final String SEPARATOR = ",";
    public static final String FORMAT_1 = "%s\t%s\t\t%f";
    public static final String FORMAT_2 = "%s\t%s\n\t\tPREVIOUS BALANCE:\t%f";
    public static final String FORMAT_3 = "\t\tBALANCE DUE:\t\t%f";
}
