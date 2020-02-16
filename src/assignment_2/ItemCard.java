package assignment_2;

public class ItemCard {
    private Constants.ItemType type;
    private Constants.City city;
    private int[] amounts;
    
    public Constants.ItemType getType() {
        return type;
    }
    
    public void setType(Constants.ItemType type) {
        this.type = type;
    }
    
    public Constants.City getCity() {
        return city;
    }
    
    public void setCity(String stringCity) {
        switch(stringCity) {
            case "New York":
                city = Constants.City.NewYork;
                break;
            case "Los Angeles":
                city = Constants.City.LosAngeles;
                break;
            case "Miami":
                city = Constants.City.Miami;
                break;
            case "Houston":
                city = Constants.City.Houston;
                break;
            case "Chicago":
                city = Constants.City.Chicago;
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);    
        }
    }
    
    public int[] getAmounts() {
        return amounts;
    }
    
    public void setAmounts(int amt1, int amt2, int amt3) {
        amounts =  new int[] {amt1, amt2, amt3};
    }
}
