package assignment_3.model;

public abstract class Record {

    public enum Type {
        SALES,
        RECEIPT,
        PROMOTION
    }
    protected Type type;
    
    public Record(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
