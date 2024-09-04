package home.finance.entity.dao;

import lombok.Data;

@Data
public class Bill {
    private int id;
    private String billName;
    private String billType;
    private int minimumPaymentAmount;
    private int maximumPaymentAmount;
    private int paymentDate;
    private int totalDue;
    private boolean isAutoPay;
    private boolean isPaid;
}
