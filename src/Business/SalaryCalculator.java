/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.SalaryConstant;
import Business.SalaryRecord;
import DataAccess.AttendanceDBHandler;
import DataAccess.SalaryConstantDBHandler;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Chathuranga
 */
/*
 * Salary Calculator
 */

public class SalaryCalculator {
    
   // private SalaryAdministrationSetter admin;
    private double salaryPerHour;
    private Calendar calendar; //Calendar instance to set the date
    private Employee employee;
    private double netSalary;
    private int empID;
    private SalaryConstantDBHandler conHandler;
    private ArrayList<AttendanceRecord> records;
    private AttendanceDBHandler attHandler;
    private double work_hours; //No of normal working hours
    private double overTime1_hours; //No of OT1 hours
    private double overTime2_hours; //No of OT2 hours
    private double sunday_hours; //No of Sunday and Poya day hours
    private int absentDays;
    private int noOfHolidays;
    
    
//    public static SalaryCalculator getInstance() {
//        return SalaryCalculator.SalaryCalculatorHolder.INSTANCE;
//    }
//    
//    private static class SalaryCalculatorHolder {
//
//        private static final SalaryCalculator INSTANCE = new SalaryCalculator();
//    }
    
    public SalaryCalculator() {
        conHandler = new SalaryConstantDBHandler();
        attHandler = new AttendanceDBHandler();
        calendar = Calendar.getInstance();
        netSalary = 0;
    }
    
    public void setMonth(int year, int month) {
        calendar.set(Calendar.YEAR, year); //Set the year and month
        calendar.set(Calendar.MONTH, month);
    }
    
    private void initiallizeHours() {
        work_hours = 0; //Initiallize the hours to zero
        overTime1_hours = 0;
        overTime2_hours = 0;
        sunday_hours = 0;
        absentDays = 0;
        noOfHolidays = 0;
    }
    
    private void calculateHours() { //Calculate the no of hours of the Employee ID
       
        records = attHandler.getAttendanceList(empID);
        
        //***************Access the DB to get difference between arrival and departure time
        
        initiallizeHours();
        
//        SalaryConstant con = conHandler.getSalaryConstants().get(0);
        
        for( int i = 1; i < records.size(); i++ ) {
            
            if(records.get(i-1) != null) {

                double AT = (records.get(i-1).getDeparture().getTime() - records.get(i-1).getArrival().getTime()) / 1000 / 3600.00; //Get the number of hours he has worked for the day i
                    
                calendar.set(Calendar.DAY_OF_MONTH, records.get(i-1).getDate().getDay()); //Sets the particular date in the calendar instance
                
                if( calendar.get(Calendar.DAY_OF_WEEK) != 1 && calendar.get(Calendar.DAY_OF_WEEK) != 7 ) { //Check whether the date is a weekday
                    if( AT >= conHandler.getSalaryConstant("HOURS")) { //Check whether the no of hours he has worked is greater than the maximum working hours                
                        work_hours += conHandler.getSalaryConstant("HOURS"); //Add the no of hours in normal working hours 
                        AT -= conHandler.getSalaryConstant("HOURS"); //Reduce the no of hours to calculate the OTs                
                        if( AT >= conHandler.getSalaryConstant("OT1_HOURS")) { //Checks if the employee has worked both OT1 & OT2                       
                            overTime1_hours += conHandler.getSalaryConstant("OT1_HOURS"); //Add the no of hours for OT1
                            AT -= conHandler.getSalaryConstant("OT1_HOURS"); //Reduce the no of hours he has worked OT1
                            overTime2_hours += AT; //Add the no of hours for OT2
                        }
                            else {
                                overTime1_hours += AT;
                            }
                        }
                        else {
                            work_hours += AT;
                        }
                    }
                    //If it is a Saturday, then the no of normal working hours will be HOURS_SAT
                    else if( calendar.get(Calendar.DAY_OF_WEEK) == 7 ) { //If the date is a Saturday
                        if( AT >= conHandler.getSalaryConstant("HOURS_SAT") ) { //Check whether the no of hours he has worked is greater than the maximum working hours for Saturday                
                            work_hours += conHandler.getSalaryConstant("HOURS_SAT"); //Add the no of hours in normal working hours for Saturday
                            AT -= conHandler.getSalaryConstant("HOURS_SAT"); //Reduce the no of hours to calculate the OTs                
                            if( AT >= conHandler.getSalaryConstant("OT1_HOURS_SAT")) { //Checks if the employee has worked both OT1 & OT2                       
                                overTime1_hours += conHandler.getSalaryConstant("OT1_HOURS_SAT"); //Add the no of hours for OT1
                                AT -= conHandler.getSalaryConstant("OT1_HOURS_SAT"); //Reduce the no of hours he has worked OT1
                                overTime2_hours += AT; //Add the no of hours for OT2
                            }
                            else {
                                overTime1_hours += AT;
                            }
                        }
                        else {
                            work_hours += AT;
                        }
                    }
                    //If it is a Sunday, then the employee is paid the double salary per hour normal HOURS
                    else if( AT >= conHandler.getSalaryConstant("HOURS") ) {
                        work_hours += 2 * conHandler.getSalaryConstant("HOURS");
                        AT -= conHandler.getSalaryConstant("HOURS"); //Reduce the no of hours to calculate the OTs                
                        if( AT >= conHandler.getSalaryConstant("OT1_HOURS")) { //Checks if the employee has worked both OT1 & OT2                       
                            overTime1_hours += conHandler.getSalaryConstant("OT1_HOURS"); //Add the no of hours for OT1
                            AT -= conHandler.getSalaryConstant("OT1_HOURS"); //Reduce the no of hours he has worked OT1
                            overTime2_hours += AT; //Add the no of hours for OT2
                        }
                        else {
                            overTime1_hours += AT;
                        }
                    }
                    else {
                        work_hours += AT;
                    }
                }
                else {
                    //*****If absent increment absentDays count
                    this.absentDays++;
                }
            }
    }
    
    private void calculateSalary() {   
        double workHours = (calendar.getActualMaximum(Calendar.DATE) - this.getSaturdays() - this.getAbsentDays()) * conHandler.getSalaryConstant("HOURS") + getSaturdays() * conHandler.getSalaryConstant("HOURS_SAT") - this.getNoOfHolidays();
        
        salaryPerHour = employee.getBasic() / workHours;
        
        double salary = this.getWork_hours() * salaryPerHour + this.getOverTime1_hours() * conHandler.getSalaryConstant("SalaryOT1") + this.getOverTime2_hours() * conHandler.getSalaryConstant("SalaryOT2");
        
        salary += employee.getAllowances();
        
        if(this.getAbsentDays() <= 1) {
            salary += conHandler.getSalaryConstant("attendanceAllowance");
        }
        
        netSalary = salary - employee.getExpenses();
    }
    
    public double getSalary(Employee e) {
        employee = e;
        this.calculateHours();
        this.calculateSalary();
        return netSalary;
    }
    
    public SalaryRecord getSalaryRecord(Employee e) {
        SalaryRecord sal = new SalaryRecord();
        this.empID = e.getEmpID();
        sal.setEmpID(e.getEmpID());
        sal.setEmpName(e.getFullName());
        sal.setOccupation(e.getOccupation());
        sal.setNetSalary(this.getSalary(e));
        return sal;
    }
    
    private double getSaturdays() { //Get the no of saturdays in the month
        double saturdays = 0;
        Calendar sat = Calendar.getInstance(); //Calendar instance to count the no of Saturdays
        sat.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1); //Set the year and month
        for( int i=2; i <= records.size(); i++ ) { //Iterate through the month
            if( sat.get(Calendar.DAY_OF_WEEK) == 7 && !this.isHoliday(i-1)) { //If it is a Saturday count
                saturdays++;
            }
            sat.set(Calendar.DAY_OF_MONTH, i);
        }
        return saturdays;
    }
    
    public int getAbsentDays() {
        return absentDays;
    }

    public double getWork_hours() {
        return work_hours;
    }

    public double getOverTime1_hours() {
        return overTime1_hours;
    }

    public double getOverTime2_hours() {
        return overTime2_hours;
    }

    public double getSunday_hours() {
        return sunday_hours;
    }

    public int getNoOfHolidays() {
        noOfHolidays = 0;
        Calendar hol = Calendar.getInstance();
        for(int i=1; i<=records.size(); i++) { //Gets the number of holidays except for Saturdays
            hol.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i);
            if(hol.get(Calendar.DAY_OF_WEEK) != 7 && isHoliday(i)) {
                noOfHolidays++;
            }
        }
        return noOfHolidays;
    }

    public boolean isHoliday(int i) {
        if(records.get(i-1) == null) {
            return false;
        }
        else {
            return true;
        }
    }
}