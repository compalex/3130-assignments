package assignment_2;

import assignment_2.Constants.ItemType;

public class Item {
    private ItemType type;
    private double price;
    
    public Item(ItemType type, double price) {
        this.setType(type);
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
