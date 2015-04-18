/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataAccess;

import Business.AttendanceRecord;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class AttendanceDBHandler{
    ConnectionManager c = ConnectionManager.getInstance();
    // Enter the attendance details into the database by passing an AttendanceRecord object    
    public void addAttendance(AttendanceRecord rec){
         try{  
        String query = "INSERT INTO alectadb.attendancedb (`date`,`employeeID`,`arrivaltime`) \n" +
        "VALUES ( CURRENT_DATE,"+rec.getEmpID()+",CURRENT_TIME)";    
        System.out.println(query);
        c.executeUpdate(query);
         }
        catch(Exception ex){
             System.out.println("Exception at addAttendance");
         }             
    }
    
  //Update the attendance !!- departure time is automatically set the time to when this method is called
    public boolean updateAttendance(int employeeID){
    String query=null;
    try{
           query = "UPDATE alectadb.attendancedb SET `departureTime` = CURRENT_TIME WHERE `employeeID` = "+employeeID+" AND `date` = CURRENT_DATE";
           
           System.out.println(query);
           c.executeUpdate(query);
           return true;
    }catch(Exception ex){
           System.out.println(ex.toString()); 
             return false;
        }
    }
//   
   
 // get the attendance of an employee from the attendanceDB based on id number
   public ArrayList<AttendanceRecord> getAttendanceList(int id){
        String query=null;
      
           query = "select * from alectadb.attendancedb where `employeeId`= "+id;
           System.out.println(query);
        
      ResultSet rs = c.executeQuery(query);
      
      ArrayList<AttendanceRecord> list = new ArrayList<AttendanceRecord>();
      try{
      while(rs.next()){         
          AttendanceRecord att = this.createAttendanceRecord(rs);
            
          if(att != null)
                list.add(att);
      }
      }
      catch(SQLException ex){
      c.displaySQLErrors(ex);
      }
      
      return list;
   }

 //delete a record based on the ID and date 
   public boolean deleteAttendanceRecord(int ID,Date date){
         try{     
        String query = "DELETE FROM alectadb.attendancedb WHERE `date` = '"+date.toString() +"' AND `employeeID` = "+ID;
        System.out.println(query);
        c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at deleteAttendanceRecord");
             return false;
         }
   }

//    get the list of attendance records on a given day
  public ArrayList<AttendanceRecord> getAttendanceRecords(Date date){
      
      ArrayList<AttendanceRecord> attendanceList = new ArrayList<AttendanceRecord>();
      
      try{  
        String query = "select * from attendancedb WHERE `date` = '"+date.toString() +"'";
        System.out.println(query);
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
           AttendanceRecord att = this.createAttendanceRecord(rs);
            if(att!= null)
                attendanceList.add(att);
        }
       
         return attendanceList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // create an order object by passing the result set from a query
  public AttendanceRecord createAttendanceRecord(ResultSet rs){
        AttendanceRecord att = new AttendanceRecord();
        try{
        att.setEmpID(rs.getInt("employeeID"));
        att.setDate(rs.getDate("date"));
        att.setArrival(rs.getTime("arrivalTime"));
        att.setDeparture(rs.getTime("departureTime"));
        
        return att;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }    
    
}
