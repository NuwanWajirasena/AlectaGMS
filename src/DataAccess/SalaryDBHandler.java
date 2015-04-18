/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

/**
 *
 * @author Chathuranga
 */
import Business.SalaryRecord;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDBHandler {
    
    private ConnectionManager con = ConnectionManager.getInstance();
    
    //Enters a salary record into the table
    public void enterSalary(SalaryRecord sal){
         try{  
            String query = "INSERT INTO alectadb.salarydb (employeeID, employeeName, occupation, netSalary) \n" +
            "VALUES ("+sal.getEmpID()+",'"+sal.getEmpName()+"','"+sal.getOccupation()+"',"+sal.getNetSalary()+");";

            System.out.println(query);
            con.executeUpdate(query);
         }
         catch(Exception ex){
             
         }
    }
    
    //This retuns the list of salary of the existing employees
    public ArrayList<SalaryRecord> getSalaryList(){
        String query=null;
      
        query = "select * from alectadb.salarydb;";
        
        ResultSet rs = con.executeQuery(query);
      
        ArrayList<SalaryRecord> list = new ArrayList<SalaryRecord>();
        
        try{
            while(rs.next()){         
                SalaryRecord s = this.createSalaryRecord(rs);

                if(s != null)
                    list.add(s);
            }
        }
        catch(SQLException ex){
            con.displaySQLErrors(ex);
        }
      
        return list;
   }   
    
   //delete a record based on the employee ID
   public boolean deleteAttendanceRecord(int ID){
         try{     
        String query = "DELETE FROM alectadb.SalaryDB WHERE `employeeID` = "+ ID;
        System.out.println(query);
        con.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at deleteRecord");
             return false;
         }
   }
   
  // create a salary record object by passing the result set from a query
  public SalaryRecord createSalaryRecord(ResultSet rs){
        SalaryRecord s = new SalaryRecord();
        try{
            s.setEmpID(rs.getInt("employeeID"));
            s.setEmpName(rs.getString("employeeName"));
            s.setOccupation(rs.getString("occupation"));
            s.setNetSalary(rs.getDouble("netSalary"));

            return s;
        }
        catch(SQLException ex){
            con.displaySQLErrors(ex);
            return null;
        }
  }    
    
}