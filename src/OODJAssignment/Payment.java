package OODJAssignment;

import java.time.LocalDate;

public class Payment{
    private String id;
    private String cardNumber;
    private double totalPaid;
    private LocalDate paidDate;
    private String pay_status; // pending, approved

    public Payment(String id, String cardNumber, double totalPaid, LocalDate paidDate, String pay_status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.totalPaid = totalPaid;
        this.paidDate = paidDate;
        this.pay_status = pay_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
    
}
