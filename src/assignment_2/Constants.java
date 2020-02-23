package assignment_2;

public class Constants {
    public enum City {
        NewYork,
        LosAngeles,
        Miami,
        Houston,
        Chicago
    }
    
    public enum CardType {
        Shipment,
        Order
    }
    
    public enum ItemType {
        Laptop,
        Printer,
        Table
    }

    public static final String SEPARATOR = ",";
    public static final String ITEMCARDS_PATH = "itemCards.csv";
    public static final String PRICECARD_PATH = "priceCard.csv";
    public static final String ERROR_DATA_MSG = "Wrong data";
    public static final int ITEM_NUMBER = 3;
    public static final String LINE_SEPARATOR = "__________________________________________";
}
