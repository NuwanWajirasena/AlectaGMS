/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataAccess;

import Business.Customer;
import Business.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDBHandler{
ConnectionManager c = ConnectionManager.getInstance();

// Enter the order details into the database by passing an order object    
    public void addCustomer(Customer customer){
         try{  
        String query = "INSERT INTO alectadb.customerdb (`customerName`, `contactNumber`, address) \n" +
        "VALUES ('"+customer.getCustomerName()+"','"+customer.getContactNo()+"','"+customer.getAddress()+"')";    
        System.out.println(query);
            c.executeUpdate(query);
         }
        catch(Exception ex){
             System.out.println("Exception at addCustomer");
         }
         
       //ArrayList<Order> listOfOrders = new OrderDBHandler().searchOrder("customer",customer.getCustomerName());
         
    }
    
     //UPDATE columns of the customer_details table selecting the record based on the primary key value
   public boolean updateCustomer(String updatedColumn,String updatedValue,String primaryKey){
       String query=null;
       try{
           query = "UPDATE alectadb.customerdb SET `"+updatedColumn+"` = '"+updatedValue+"' where customerName = '"+primaryKey+"'";
           System.out.println(query);
      }catch(Exception ex){
           System.out.println(ex.toString());
      }
       c.executeUpdate(query);
       return true;
   }
   
   //UPDATE a customer object
   public boolean replaceCustomer(Customer customer){
       String query = null;
       try{
           query = "UPDATE alectadb.customerdb SET `contactNumber` = '"+customer.getContactNo()+"', `address` = '"+customer.getAddress()+"' WHERE `customerName` ='"+customer.getCustomerName()+"'";
           System.out.println(query);
       }
       catch(Exception ex){
           System.out.println(ex.toString());
       }
       c.executeUpdate(query);
       return true;
   }
    //Return the customer object based on the ustomer name
  public Customer getCustomer( String ref){
    
      ArrayList<Customer> customer = this.searchCustomer("customerName", ref );
      return customer.get(0);

  }
    // method to search the order by entering the column name and the keyword
   // list of orders need to be added
   public ArrayList<Customer> searchCustomer(String field,String argument){
        String query=null;
       try{
          query = "select * from alectadb.customerdb where `"+field+"` = '"+argument+"'";
           System.out.println(query);
      }catch(Exception ex){
           System.out.println(ex.toString());
      }
       
      ResultSet rs = c.executeQuery(query);
      ArrayList<Customer> list = new ArrayList<Customer>();
      try{
      while(rs.next()){         
      Customer c = this.createCustomerObject(rs);
     
        if(c != null)
            list.add(c);
      }
      }
      catch(SQLException ex){
      c.displaySQLErrors(ex);
      }
      
      return list;
   }
   
   //delete a record based on the primary key value 
   public boolean deleteCustomer(String name){
         try{     
        String query = "DELETE FROM alectadb.customerdb WHERE `customerName` = '"+name+"'";
        System.out.println(query);
        c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at deleteCustomer");
             return false;
         }
   }
  
   
   // show all customers in the table
  public ArrayList<Customer> getCustomers(){
      
      ArrayList<Customer> orderList = new ArrayList<Customer>();
      
      try{  
        String query = "select * from alectadb.customerdb";
       // Statement st = connection.createStatement();
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Customer cs = this.createCustomerObject(rs);
            if(cs != null)
                orderList.add(cs);
        }
       
         return orderList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // create an order object by passing the result set from a query
  public Customer createCustomerObject(ResultSet rs){
        Customer newCustomer = new Customer();
        try{
        newCustomer.setCustomerName(rs.getString("customername"));
        newCustomer.setContactNo(rs.getString("contactNumber"));
        newCustomer.setAddress(rs.getString("address"));
        
        return newCustomer;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }    
}
