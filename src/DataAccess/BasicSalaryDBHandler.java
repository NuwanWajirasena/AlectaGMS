package alectagms.database;

import DataAccess.ConnectionManager;
import alectagms.BasicSalary;
import java.sql.*;
import java.util.ArrayList;

public class BasicSalaryDBHandler {
    
    ConnectionManager cm = ConnectionManager.getInstance();
    
    //add a basic salary record by the administrator 
    public boolean addBasicSalary(BasicSalary basicSalary){
        try{     
            String query = "INSERT INTO alectadb.basic_salaries (job_title, basic_salary) VALUES ('"+basicSalary.getJobTitle()+"', '"+basicSalary.getBasicSalary()+"')";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at insert basic salary record");
            return false;
        }
    }
    
    //change the basic salary of a particular job title by the administrator 
    public boolean editBasicSalary(BasicSalary basicSalary){
        try{
            String query = "UPDATE alectadb.basic_salaries SET basic_salary = "+basicSalary.getBasicSalary()+" WHERE job_title = '"+basicSalary.getJobTitle()+"'";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at edit basic salary record");
            return false;
        }
    }
    
    //delete a basic salary record by the administrator 
    public boolean deleteBasicSalary(String jobTitle){
        try{     
            String query = "DELETE FROM alectadb.basic_salaries WHERE job_title ='"+jobTitle+"'";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at delete basic salary record");
            return false;
        }
    }
    
    // show all basic salary records in the table
    public ArrayList<BasicSalary> getBasicSalaries(){

        ArrayList<BasicSalary> basicSalaryList = new ArrayList<BasicSalary>();

        try{  
            String query = "select * from alectadb.basic_salaries";
            ResultSet rs = cm.executeQuery(query);
            while (rs.next()) {
                BasicSalary basicSalary = this.createBasicSalaryRecord(rs);
                if(basicSalary != null) basicSalaryList.add(basicSalary);
            }
            return basicSalaryList;
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            return null;
        }  
    }
  
    //search one record of salary table based on job title
    public double getBasicSalary(String jobTitle){
        try{  
            String query = "select `basic_salary` from alectadb.basic_salaries WHERE `job_title` = '"+jobTitle+"'";
            ResultSet rs = cm.executeQuery(query);
            rs.next();
            return rs.getDouble("basic_salary");
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            return -1;
        }  
    }
    
    // create a basic salary object by passing the result set from a query
    public BasicSalary createBasicSalaryRecord(ResultSet rs){
        BasicSalary newItem = new BasicSalary();
        try{
            newItem.setJobTitle(rs.getString("job_title"));
            newItem.setBasicSalary(rs.getInt("basic_salary"));
            return newItem;
        }
        catch(SQLException ex){
            cm.displaySQLErrors(ex);
            return null;
        }
    }
}