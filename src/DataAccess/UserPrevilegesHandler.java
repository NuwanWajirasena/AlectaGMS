/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataAccess;

import Business.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserPrevilegesHandler {
ConnectionManager c = ConnectionManager.getInstance();

public void insertRecord(int id,String empType,String username,String password){

        String query = "INSERT INTO alectadb.userprivileges (`employeeID`, occupation, username, password) \n" +
                       "VALUES ("+id+",'"+empType+"','"+ username+"','"+password+"')";
        
        String ID = Integer.toString(id);
        // get the details of the relevant employee in the privilege table using employeeID
        Employee emp = new EmployeeDBHandler().searchEmployee("employeeID", ID ).get(0);
        
        String query2 = "UPDATE alectadb.userprivileges SET `fullname` = '"+ emp.getFullName()+"' WHERE `employeeID` ="+id;
        System.out.println(query);
        c.executeUpdate(query);
        c.executeUpdate(query2);       
}

public void deleteRecord(int id){

       String query = "DELETE FROM alectadb.userprivileges where `employeeID` ="+id;
       System.out.println(query);
       c.executeUpdate(query);
}

public void updateRecord(int id,String updatingColumn,String updatedValue){
       
        String query = "UPDATE alectadb.userprivileges  SET `"+updatingColumn+"` = '"+updatedValue +"' where `employeeID` = "+id;
        System.out.println(query);
        c.executeUpdate(query);
}

public String getPassword(String username){
    
        String query = "Select `password` from alectadb.userprivileges where `username` = '"+username+"'";
        System.out.println(query);
        ResultSet rs = c.executeQuery(query);
        
        String password = null;
        try{
        rs.next();
        password = rs.getString("password");
                                                }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
        catch(Exception e){
            return null;
        }
        System.out.println(password);
        return password;
}

public boolean usernameAvailable(String username){
        String query = "SELECT `username` from alectadb.userprivileges where `username` = '"+username+"'";
        System.out.println(query);
        ResultSet rs = c.executeQuery(query);
        
        boolean test = false;
        try{
            if(rs.next())
                test = false;
            else
                test = true;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
        }
        return test;
}

public String getType(String username,String password){
        String query = "SELECT `occupation` from alectadb.userprivileges where `username` = '"+username+"' AND `password` = '"+password+"'";
        ResultSet rs = c.executeQuery(query);
        String type =null;
    try {        
        rs.next();
        type = rs.getString("occupation");
        return type;
        
    } catch (SQLException ex) {
       c.displaySQLErrors(ex);
       return null;
    }
    catch(Exception e){
        return "abcdefghijklmnopqrstuvwxyz0123456789";
        
    }
        
}
}
