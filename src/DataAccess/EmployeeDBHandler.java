
package DataAccess;

import Business.Employee;
import alectagms.database.BasicSalaryDBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User 123
 */
public class EmployeeDBHandler{
    
    private ConnectionManager c = ConnectionManager.getInstance();
    
    // Enter the employee details into the database by passing an employee object    
    public boolean enterEmployee(Employee emp){
        try{  
          //  double sal = new BasicSalaryDBHandler().getBasicSalary(emp.getOccupation());
          //  System.out.println("Basic"+sal);
            String query = "INSERT INTO alectadb.employeedetails (employeeID, firstName, lastName, gender, DOB, contactaddress, contactNumber, NIC, expenses, allowances, joinedDate ) \n" +
        "VALUES ("+"'"+emp.getEmpID() +"','"+emp.getFirstName() + "','" + emp.getLastName()+"','" +emp.getGender()+ "','" + emp.getDOB() + "','" + emp.getAddress()+"','" +emp.getContactNo()+"','"+emp.getNIC()+"','" +emp.getExpenses()+"', '"+emp.getAllowances()+"','" + emp.getJoinedDate()+"');";
        
        System.out.println(query);
            c.executeUpdate(query);
            return true;
         }
        catch(Exception ex){
             System.out.println("Exception at enter employee");
             return false;
         }
    }
    //enter the occupation of the employee
    public boolean enterOccupation( int empID, String occupation ){
        String query = null;
        double sal = new BasicSalaryDBHandler().getBasicSalary(occupation);
        query = "UPDATE alectadb.employeedetails SET occupation = '" + occupation + "', `basic` = '"+ sal +"' where employeeID = " + empID ;
        System.out.println(query);
        c.executeUpdate(query);
        return true;
        
    }
    //UPDATE columns of the employee_details table selecting the record based on the primary key value
   public boolean updateEmployee(String updatedColumn,String updatedValue,int primaryKey){
   String query=null;
       try{
           int arg = Integer.parseInt(updatedValue);
           query = "UPDATE alectadb.employeedetails SET `"+updatedColumn+"`= " +arg+ " where employeeID = "+primaryKey;
           System.out.println(query);
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(updatedValue);
              query = "UPDATE alectadb.employeedetails SET `"+updatedColumn +"`= "+ arg+" where employeeID = "+primaryKey;
              System.out.println("1"+query);
          }
          catch( NumberFormatException exe ){      
                  query = "UPDATE alectadb.employeedetails SET `"+updatedColumn +"` = '"+ updatedValue+"' where employeeID = "+primaryKey;
                  System.out.println("2"+query);
              
          }
           
      }
       c.executeUpdate(query);
       return true;
   }
   
    //Replace tha values of all the fields of a given record
  public void replaceEmployee(Employee newRec){
      String query = null;
      try{
          int primaryKey = newRec.getEmpID();
          query = "UPDATE alectadb.employeedetails SET `firstName`='" +newRec.getFirstName()+ "', `lastName` ='" +newRec.getLastName()+ "', `gender` ='" + newRec.getGender()+ "', `NIC` = '" +newRec.getNIC()+ "',`occupation` = '" + newRec.getOccupation()+ "',`contactAddress` = '" + newRec.getAddress()+ 
                  "',`contactNumber` = '" +newRec.getContactNo()+ "',`allowances` = " +newRec.getAllowances()+",`expenses` = " +newRec.getExpenses()+",`DOB` = '" +newRec.getDOB().toString()+ "',`basic` = " +newRec.getBasic()+ ",`joinedDate`= '" + newRec.getJoinedDate().toString()+ "' where `employeeID` = "+primaryKey;
          System.out.println(query);
          c.executeUpdate(query);
    
    }
    catch(Exception ex){
    System.out.println("Exception at replaceEmployee");
    } 
  }
    // method to search the employee by entering the column name and the keyword
   public ArrayList<Employee> searchEmployee(String field,String argument){
      String query=null;
       try{
          int arg = Integer.parseInt(argument);
          query = "select * from alectadb.employeedetails where "+field+" = "+arg;
          System.out.println(query);
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(argument);
              query = "select * from alectadb.employeedetails where "+field +"="+ arg;
              System.out.println(query);
          }
          catch(NumberFormatException exe){
              query = "select * from alectadb.employeedetails where "+field +"="+argument;
              System.out.println(query);
          }
          
      }
       
      ResultSet rs = c.executeQuery(query);
      ArrayList<Employee> list = new ArrayList<Employee>();
      Employee item=null;
      try{
      while(rs.next()){
          
      item = this.createEmployeeObject(rs);
      
        if(item != null)
            list.add(item);
      }
      }
      catch(SQLException ex){
      c.displaySQLErrors(ex);
      }
      
      return list;
   }
   
   //delete a record based on the primary key value 
   public boolean deleteEmployee(int ref){
         try{     
            String query = "DELETE FROM alectadb.employeedetails WHERE employeeID ="+ref;
            System.out.println(query);
            c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at delete employee");
             return false;
         }
   }
  
   
   // show all employees in the table
  public ArrayList<Employee> getAllEmployees(){
      
      ArrayList<Employee> employeeList = new ArrayList<Employee>();
      
      try{  
        String query = "select * from alectadb.employeedetails";
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Employee item = this.createEmployeeObject(rs);
            if(item != null)
                employeeList.add(item);
        }
       
         return employeeList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // get list of employees whose occuaption is not null  
  public ArrayList<Employee> getEmployees(){
      
      ArrayList<Employee> employeeList = new ArrayList<Employee>();
      
      try{  
        String query = "select * from alectadb.employeedetails where `occupation` IS NOT NULL";
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Employee item = this.createEmployeeObject(rs);
            if(item != null)
                employeeList.add(item);
        }
       
         return employeeList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  //gets employees whose occupations are unassigned
  public ArrayList<Employee> getUnassignedEmployees(){
      
      ArrayList<Employee> employeeList = new ArrayList<Employee>();
      
      try{  
        String query = "select * from alectadb.employeedetails where `occupation` IS NULL";
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Employee item = this.createEmployeeObject(rs);
            if(item != null)
                employeeList.add(item);
        }
       
         return employeeList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  
  // create an employee object by passing the result set from a query
  public Employee createEmployeeObject(ResultSet rs){
        Employee newItem = new Employee();
        try{
          
            newItem.setEmpID(rs.getInt("employeeID"));
            newItem.setOccupation( rs.getString("occupation"));
            newItem.setAddress(rs.getString("Contactaddress"));
            newItem.setAllowance(rs.getDouble("allowances"));
            newItem.setBasic(rs.getDouble("basic"));
            newItem.setContactNo(rs.getString("contactNumber"));
            newItem.setDOB(rs.getDate("DOB"));
            newItem.setExpenses(rs.getDouble("expenses"));
            newItem.setFirstName(rs.getString("firstName"));
            newItem.setLastName(rs.getString("lastName"));
            newItem.setGender(rs.getString("gender"));
            newItem.setNIC(rs.getString("NIC"));
      
        return newItem;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }
  
  //Return the employee object based on the employeeID
  public Employee getEmployee( int ref){
    
      ArrayList<Employee> employee = this.searchEmployee("employeeID", Integer.toString(ref) );
      return employee.get(0);

  }
    
    
}
