package Business;

import java.sql.Date;
import java.sql.Time;

/*
 * Keeps attendance of each day and
 * updates the daily attendance table of each employee
 * at the end of each day
 */

public class AttendanceRecord {
    private int empID; //ID of the employee
    private Date date;
    private boolean present; //If the employee is present for the day
    private Time arrival; // Time of arrival
    private Time departure; // Time of departure

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }
}
