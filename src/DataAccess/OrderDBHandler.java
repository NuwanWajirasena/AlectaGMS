package DataAccess;


import Business.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDBHandler{
ConnectionManager c = ConnectionManager.getInstance();

// Enter the order details into the database by passing an order object    
    public boolean addOrder(Order ord){
         try{  
        String query = "INSERT INTO alectadb.`order` (`garmentItemCode`,deadline, customer, quantity, status) \n" +
        "VALUES ("+ord.getGamentItemCode()+",'"+ord.getDeadLine().toString()+"','"+ord.getCustomer()+"',"+ord.getQuantity()+",'"+ord.getStatus()+"');";
        
        System.out.println(query);
            c.executeUpdate(query);
            return true;
         }
        catch(Exception ex){
             System.out.println("Exception at enterorder");
             return false;
         }
    }
    
    //Relace the order object with the new order object
    public void replaceRecord(Order newRec){
        try{
             String query = "UPDATE alectadb.order SET  `garmentItemCode` = " +newRec.getGamentItemCode()+ ", `customer` = '"+newRec.getCustomer()+"', `quantity` = "+newRec.getQuantity()+", `deadline` = '"+newRec.getDeadLine().toString()+"', `status` = '"+newRec.getStatus()+"' where `orderReference` = " +newRec.getOrderReference();
             System.out.println(query);
             c.executeUpdate(query);             
        }
        catch(Exception ex){
             System.out.println("Exception at replaceIncomeExpense");
         }             
    }
    
    //UPDATE columns of the order table selecting the record based on the primary key value
   public boolean updateOrder(String updatedColumn,String updatedValue,int primaryKey){
   String query=null;
       try{
           int arg = Integer.parseInt(updatedValue);
           query = "UPDATE alectadb.`order` SET `"+updatedColumn+"`=" +arg+ " where `orderReference` = "+primaryKey;
           System.out.println(query);
      }catch(NumberFormatException ex){
           query = "UPDATE alectadb.`order` SET `"+updatedColumn +"`= '"+ updatedValue+"' where `orderReference` = "+primaryKey;
           System.out.println(query);
      }
       c.executeUpdate(query);
       return true;
   }
   
   
    // method to search the order by entering the column name and the keyword
   public ArrayList<Order> searchOrder(String field,String argument){
      String query=null;
       try{
          int arg = Integer.parseInt(argument);
          query = "select * from alectadb.`order` where `"+field+"` = "+arg;
           System.out.println(query);
      }catch(NumberFormatException ex){
          query = "select * from alectadb.`order` where `"+field+"` = '"+argument+"'";
           System.out.println(query);
      }
       
      ResultSet rs = c.executeQuery(query);
      ArrayList<Order> list = new ArrayList<Order>();
      Order ord=null;
      try{
      while(rs.next()){
          
      ord = this.createOrderObject(rs);
      
        if(ord != null)
            list.add(ord);
      }
      }
      catch(SQLException ex){
      c.displaySQLErrors(ex);
      }
      
      return list;
   }
   
   //search an order based 0n the orderReference
   public Order searchOrderByRef(int ref){
       String id = Integer.toString(ref);
       ArrayList<Order> list = this.searchOrder("OrderReference",id);
       return list.get(0);
       
   }
   //delete a record based on the primary key value 
   public boolean deleteOrder(int ref){
         try{     
        String query = "DELETE FROM alectadb.`order` WHERE `orderReference` ="+ref;
             System.out.println(query);
        c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at enterorder");
             return false;
         }
   }
   
   // show all orders in the table
  public ArrayList<Order> getOrders(){
      
      ArrayList<Order> orderList = new ArrayList<Order>();
      
      try{  
        String query = "select * from alectadb.`order`";
       // Statement st = connection.createStatement();
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Order ord = this.createOrderObject(rs);
            if(ord != null)
                orderList.add(ord);
        }
       
         return orderList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // create an order object by passing the result set from a query
  public Order createOrderObject(ResultSet rs){
        Order newOrder = new Order();
        try{
        newOrder.setGamentItemCode(rs.getInt("garmentItemCode"));
        newOrder.setCustomer(rs.getString("customer"));
        newOrder.setQuantity(rs.getInt("quantity"));
        newOrder.setStatus(rs.getString("status"));
        newOrder.setDeadLine(rs.getDate("deadline"));
        newOrder.setTimestamp(rs.getTimestamp("timeOfEntry"));
        newOrder.setOrderReference(rs.getInt("orderReference"));
        
        return newOrder;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }
}
