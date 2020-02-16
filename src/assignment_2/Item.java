package assignment_2;

public class Item {
    private Constants.ItemType type;
    private Constants.City city;
    private int serialNum;
    private int amount;
    private double price;
    
    public Item(int serialNum) {
        this.serialNum = serialNum;
    }
}
