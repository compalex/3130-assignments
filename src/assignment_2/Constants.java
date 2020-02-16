package assignment_2;

public class Constants {
    public enum City {
        NewYork,
        LosAngeles,
        Miami,
        Houston,
        Chicago;
    }
    
    public enum ItemType {
        Shipment,
        Order
    }
    public static final String SEPARATOR = ",";
    public static final String ITEMCARDS_PATH = "itemCards.csv";
    public static final String PRICECARD_PATH = "priceCard.csv";
    public static final String ERROR_DATA_MSG = "Wrong data";
    public static final int ITEM_QUANTITY = 3;
}