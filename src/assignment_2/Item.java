package assignment_2;

public class Item {
    private int idNum;
    private double price;
    
    public Item(int idNum, double price) {
        this.idNum = idNum;
        this.price = price;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
