/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Business.SalaryConstant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Chathuranga
 */
public class SalaryConstantDBHandler {
    
    ConnectionManager cm = ConnectionManager.getInstance();
    
    //add a new salary constant by the administrator 
    public boolean addSalaryConstant(String constantTitle, String constantValue){
        try{
            String query = "INSERT INTO alectadb.salary_constants (constant_title, constant_value) VALUES ('"+constantTitle+"', '"+constantValue+"')";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at insert salary constant record");
            return false;
        }
    }
    
    //change salary constants by the administrator 
    public boolean editSalaryConstant(String constantTitle, String constantValue){
        try{
            String query = "UPDATE alectadb.salary_constants SET `constant_value` = "+constantValue+" WHERE `constant_title` = '"+constantTitle+"'";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at edit salary constant");
            return false;
        }
    }
    
    //delete a salary constant record by the administrator 
    public boolean deleteSalaryConstant(String constantTitle){
        try{     
            String query = "DELETE FROM alectadb.salary_constants WHERE `constant_title` ='"+constantTitle+"'";
            System.out.println(query);
            cm.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception at delete salary constant");
            return false;
        }
    }
    
    // show all salary constants in the table
    public ArrayList<SalaryConstant> getSalaryConstants(){

        ArrayList<SalaryConstant> salaryConstantList = new ArrayList<SalaryConstant>();

        try{  
            String query = "select * from alectadb.salary_constants";
            ResultSet rs = cm.executeQuery(query);
            while (rs.next()) {
                SalaryConstant salaryConstant = this.createSalaryConstantRecord(rs);
                if(salaryConstant != null) salaryConstantList.add(salaryConstant);
            }
            return salaryConstantList;
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            return null;
        }  
    }
  
    //search one record of salary table based on job title
    public double getSalaryConstant(String constantTitle){
        try{  
            String query = "select `constant_value` from alectadb.salary_constants WHERE `constant_title` = '"+constantTitle+"'";
            ResultSet rs = cm.executeQuery(query);
            rs.next();
            return rs.getDouble("constant_value");
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            return -1;
        }  
    }
    
    // create a salary constant object by passing the result set from a query
    public SalaryConstant createSalaryConstantRecord(ResultSet rs){
        SalaryConstant newItem = new SalaryConstant();
        try{
            newItem.setConstantTitle(rs.getString("constant_title"));
            newItem.setConstantValue(rs.getDouble("constant_value"));
            return newItem;
        }
        catch(SQLException ex){
            cm.displaySQLErrors(ex);
            return null;
        }
    }
    
}
