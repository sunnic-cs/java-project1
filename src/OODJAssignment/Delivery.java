package OODJAssignment;

public class Delivery {
    private String id;
    private Boolean status;
    private String feedback;
    private Order Orderid;
    private Staff Staffid;
    private String nowstatus;

    public Delivery(String id, Boolean status, String feedback, Order Orderid, Staff Staffid,String nowstatus) {
        this.id = id;
        this.status = status;
        this.feedback = feedback;
        this.Orderid = Orderid;
        this.Staffid = Staffid;
        this.nowstatus = nowstatus;
    }
    

    public String getNowstatus() {
        return nowstatus;
    }

    public void setNowstatus(String nowstatus) {
        this.nowstatus = nowstatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Order getOrderid() {
        return Orderid;
    }

    public void setOrderid(Order Orderid) {
        this.Orderid = Orderid;
    }

    public Staff getStaffid() {
        return Staffid;
    }

    public void setStaffid(Staff Staffid) {
        this.Staffid = Staffid;
    }

    
}
