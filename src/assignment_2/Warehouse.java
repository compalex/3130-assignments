package assignment_2;

public class Warehouse {
    private Constants.City city;
    private Item items[];
    
    public Warehouse(Constants.City city) {
        this.city = city;
        
        for(int i = 1; i <= Constants.ITEM_QUANTITY; i++) {
            items[i] = new Item(i);
        }
    }
}
