package assignment_3.widgetProcessor;

import java.util.List;
import assignment_3.model.Invoice;
import assignment_3.model.PromoRecord;
import assignment_3.model.ReceiptRecord;
import assignment_3.model.SalesRecord;
import assignment_3.model.WidgetPack;

public interface IWidgetProcessor {
    Invoice processSales(SalesRecord record);
    boolean processReceipt(ReceiptRecord record);
    boolean processPromotion(PromoRecord record);
    List<WidgetPack> getStockRemainder();
}
