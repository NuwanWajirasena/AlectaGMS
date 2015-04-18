/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author DELL PC
 */

import java.sql.Date;
import java.sql.Timestamp;
public class Order {
    
    private int orderReference;
    private int gamentItemCode;
    private String customer;
    private int quantity;
    private Date deadLine;
    private Timestamp timestamp;
    private String status;
    
    public String getCustomer() {
        return customer;
    }
    public Date getDeadLine() {
        return deadLine;
    }
    public int getGamentItemCode() {
        return gamentItemCode;
    }
    public int getOrderReference() {
        return orderReference;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getStatus() {
        return status;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
    public void setGamentItemCode(int gamentItemCode) {
        this.gamentItemCode = gamentItemCode;
    }
    public void setOrderReference(int orderReference) {
        this.orderReference = orderReference;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}