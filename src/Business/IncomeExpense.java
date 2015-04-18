package Business;
import java.sql.Timestamp;
public class IncomeExpense {
    private Timestamp date; // Date of the income-expense record entry   
    private int referenceId; //Reference to the record
    private String type; //Whether this is an INCOME or EXPENSE
    private double value; //Value
    private int responsible; //The employee ID who is responsible for the transaction
    private String customer; //The customer associated in the transaction if any
    private String description; //Description of the record
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public int getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public int getResponsible() {
        return responsible;
    }
    public void setResponsible(int responsible) {
        this.responsible = responsible;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }    
}