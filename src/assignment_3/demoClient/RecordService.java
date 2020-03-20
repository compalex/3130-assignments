package assignment_3.demoClient;

import java.util.List;
import java.util.Map;
import assignment_3.CSVReader;
import assignment_3.Constants;
import assignment_3.model.Invoice;
import assignment_3.model.Record;
import assignment_3.widgetPocessor.IWidgetProcessor;

public class RecordService {
    private IWidgetProcessor processor;
    
    public RecordService(IWidgetProcessor processor) {
        this.processor = processor;
    }
    
    public List<Record> getRecords() {
        List<Record> records = CSVReader.getRecords();
        if(records == null || records.isEmpty()) {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
        return records;
    }

    public void processRecord(Record record) {
        switch(record.getType()) {
            case Receipt:
                processReceiptRecord(record);
                break;
            case Sales:
                processSalesRecord(record);
                break;
            case Promotion:
                processPromotion(record);
                break;
            default:
                System.err.println(Constants.ERROR_DATA_MSG);
        }
        System.out.println();
    }

    private void processReceiptRecord(Record record) {
        int quantity = record.getQuantity();
        double price = record.getPrice();
        boolean isSuccess = processor.processReceipt(quantity, price);
        if(isSuccess) {
            System.out.printf(Constants.PRINT_RECEIPT, quantity, price);
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
    }

    private void processSalesRecord(Record record) {
        int quantity = record.getQuantity();
        Invoice invoice = processor.processSales(quantity);
        if(invoice != null) {
            System.out.print(invoice.toString());
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }   
    }
    
    private void processPromotion(Record record) {
        double percentage = record.getDiscountPercentage();
        int num = Constants.DISCOUNTED_CUSTOMERS_NUM;
        boolean isSuccess = processor.processPromotion(percentage, num);
        if(isSuccess) {
            System.out.printf(Constants.PRINT_PROMOTION, num, percentage);
        } else {
            System.err.println(Constants.ERROR_DATA_MSG);
        }
    }
    
    public void printRemainingWidgets() {
        Map<Double, Integer> amountByPriceMap = processor.getStockRemainder();
        if(!amountByPriceMap.isEmpty()) {
            int totalNum = 0;
            
            for(Double price : amountByPriceMap.keySet()) {
                totalNum += amountByPriceMap.get(price);
            }
            System.out.printf(Constants.PRINT_REMAIN_HEADER, totalNum);
            
            for(Double price : amountByPriceMap.keySet()) {
                System.out.printf(Constants.PRINT_REMAIN_LINE, amountByPriceMap.get(price), price);
            }
        }
    }
}
