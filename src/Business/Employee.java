package Business;

import java.sql.Date;

/*
 * Employee Class
 */

public class Employee {
    private int empID;
    private String firstName;
    private String lastName;
    private Date joinedDate;
    private String gender;
    private Date DOB;
    private String address;
    private String contactNo;
    private String NIC;
    private String occupation;
    private double basic;
    private double expenses;
    private double allowances;

    public double getBasic() {
        return basic;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowance(double allowances) {
        this.allowances = allowances;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String type) {
        this.occupation = type;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNIC() {
        return NIC;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public int getEmpID() {
        return empID;
    }
    
    public void setEmpID(int empID) {
        this.empID = empID;
    }
    
    public String getFullName(){
        return (firstName+" "+lastName);
    }    
}
