package assignment_3.widgetPocessor;

import java.util.Map;
import assignment_3.model.Invoice;

public interface IWidgetProcessor {
    Invoice processSales(int quantity);
    boolean processReceipt(int quantity, double price);
    boolean processPromotion(double percentage, int num);
    Map<Double, Integer> getStockRemainder();
}
