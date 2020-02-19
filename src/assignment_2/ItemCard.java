package assignment_2;

import java.util.List;

public class ItemCard {
    private Constants.CardType type;
    private Constants.City city;
    private List<Integer> amounts;
    
    public Constants.CardType getType() {
        return type;
    }
    
    public void setType(Constants.CardType type) {
        this.type = type;
    }
    
    public Constants.City getCity() {
        return city;
    }
    
    public void setCity(Constants.City city) {
        this.city = city;
    }
    
    public List<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }
}
