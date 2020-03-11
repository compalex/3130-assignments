package assignment_2;

public class Constants {
    
    public enum City {
        NewYork,
        LosAngeles,
        Miami,
        Houston,
        Chicago
    }
    public static final String SEPARATOR = ",";
    public static final String ITEMCARDS_PATH = "itemCards.csv";
    public static final String PRICECARD_PATH = "priceCard.csv";
    public static final String ERROR_DATA_MSG = "Wrong data";
    public static final String LINE_SEPARATOR = "_____________________________________________________\n";
    public static final String ORDER_UNFILLED_MSG = "Order Unfilled!";
    public static final String ORDER_PRICE = "Price of Order: %.2f$\n";
    public static final String SHIPMENT_ERROR_MSG = "Shipment cancelled";
    public static final String CHANGE_FORMAT = "%d of item %s shipped from %s to %s at a 10%% surcharge\n";
    public static final String PROD_FORMAT = " %s:%d";
    public static final String PRINT_CARD = "Processing the card: ";
    public static final String PRINT_WAREHOUSE = "Warehouse at ";
}
