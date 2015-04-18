
package DataAccess;

import Business.GarmentItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User 123
 */
public class GarmentItemDBHandler {
    
    private ConnectionManager c = ConnectionManager.getInstance();
// Enter the garment item details into the database by passing a garment item object    
    public boolean enterGarmentItem(GarmentItem item){
         try{  
        String query = "INSERT INTO alectadb.garment_items (garmentItemCode, price, cost, URL, quantity, inStockQuantity) \n" +
        "VALUES ("+item.getGarmentItemCode()+","+item.getPrice()+","+item.getCost()+",'"+item.getURL()+"', "+item.getQuantity()+", "+item.getInStockQuantity()+");";
        
        System.out.println(query);
            c.executeUpdate(query);
            return true;
         }
        catch(Exception ex){
             System.out.println("Exception at entergarmentitem");
             return false;
         }
    }
    //UPDATE columns of the garment_items table selecting the record based on the primary key value
   public boolean updateGarmentItem(String updatedColumn,String updatedValue,int primaryKey){
   String query=null;
       try{
           int arg = Integer.parseInt(updatedValue);
           query = "UPDATE alectadb.garment_items SET `"+updatedColumn+"`=" +arg+ " where `garmentItemCode` = "+primaryKey;
           System.out.println(query);
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(updatedValue);
              query = "UPDATE alectadb.garment_items SET `"+updatedColumn +"`="+ arg+" where `garmentItemCode` = "+primaryKey;
              System.out.println(query);
          }
          catch(NumberFormatException exe){
              query = "UPDATE alectadb.garmentItemCode SET `"+updatedColumn +"`='"+ updatedValue+"' where `garmentItemCode` = "+primaryKey;
              System.out.println(query);
          }
      }
       c.executeUpdate(query);
       return true;
   }
   
   //Replace tha values of all the fields of a given record
  public void replaceGarmentItem(GarmentItem newRec){
    String query = null;
      try{
          int primaryKey = newRec.getGarmentItemCode();
          query = "UPDATE alectadb.garment_items SET `price`=" +newRec.getPrice()+ ", `cost` =" +newRec.getCost()+ ", `URL` ='" + newRec.getURL()+"', `quantity` = "+newRec.getQuantity()+", `inStockQuantity` = "+newRec.getInStockQuantity()+" where `garmentItemCode` = "+primaryKey;
          System.out.println(query);
          c.executeUpdate(query);
    }
    catch(Exception ex){
    System.out.println("Exception at replaceGarmentItem");
    } 
 }
  
  //Increase the Quantity of the garment
   public boolean increaseQuantity( String updatedValue, int primaryKey ){
       String query = null;
       GarmentItem g =this.getGarment_Item(primaryKey);
       int arg = Integer.parseInt(updatedValue) + g.getQuantity();
       query = "UPDATE alectadb.garment_items SET `quantity`=" +arg+ " where `garmentItemCode` = "+primaryKey;
       c.executeUpdate(query);
       this.increaseInStockQuantity(updatedValue, primaryKey);
       return true;
   }
   
   //Decrease the Quantity of the garment
   public boolean decreaseQuantity( String updatedValue, int primaryKey ){
       String query = null;
       GarmentItem g = this.getGarment_Item(primaryKey);
       if( g.getInStockQuantity() < Integer.parseInt(updatedValue)){
           System.out.println("Not enough quantity");
           return false;
       }
       else{
           int arg = - Integer.parseInt(updatedValue) + g.getInStockQuantity();
           query = "UPDATE alectadb.garment_items SET `quantity`=" +arg+ " where `garmentItemCode` = "+primaryKey;
           c.executeUpdate(query);
           return true;
       }
   }
   
  //Increase the inStockQuantity of the garment
   public boolean increaseInStockQuantity( String updatedValue, int primaryKey ){
       String query = null;
       GarmentItem g =this.getGarment_Item(primaryKey);
       int arg = Integer.parseInt(updatedValue) + g.getInStockQuantity();
       query = "UPDATE alectadb.garment_items SET `inStockQuantity`=" +arg+ " where `garmentItemCode` = "+primaryKey;
       c.executeUpdate(query);
       return true;
   }
   
   //Decrease the inStockQuantity of the garment
   public boolean decreaseInStockQuantity( String updatedValue, int primaryKey ){
       String query = null;
       GarmentItem g = this.getGarment_Item(primaryKey);
       if( g.getInStockQuantity() < Integer.parseInt(updatedValue)){
           System.out.println("Not enough quantity");
           return false;
       }
       else{
           int arg = - Integer.parseInt(updatedValue) + g.getInStockQuantity();
           query = "UPDATE alectadb.garment_items SET `inStockQuantity`=" +arg+ " where `garmentItemCode` = "+primaryKey;
           c.executeUpdate(query);
           return true;
       }
   }
  
    // method to search the GarmentItem by entering the column name and the keyword
   public ArrayList<GarmentItem> searchGarmentItem(String field,String argument){
      String query=null;
       try{
          int arg = Integer.parseInt(argument);
          query = "select * from alectadb.garment_items where `"+field+"` = "+arg;
          System.out.println(query);
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(argument);
              query = "select * from alectadb.garment_items where `"+field +"`= "+ arg;
              System.out.println(query);
          }
          catch(NumberFormatException exe){
              query = "select * from alectadb.garment_items where `"+field +"`='"+argument +"'";
              System.out.println(query);
          }
      }
       
      ResultSet rs = c.executeQuery(query);
      ArrayList<GarmentItem> list = new ArrayList<GarmentItem>();
      GarmentItem item=null;
      try{
      while(rs.next()){
          
      item = this.createGarmentItemObject(rs);
      
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
   public boolean deleteGarmentItem(int ref){
         try{     
        String query = "DELETE FROM alectadb.garment_items WHERE `garmentItemCode` ="+ref;
             System.out.println(query);
        c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at delete garment item");
             return false;
         }
   }
   //Return the garment item object based on the employeeID
  public GarmentItem getGarment_Item( int ref){
    
      ArrayList<GarmentItem> item = this.searchGarmentItem("garmentItemCode", Integer.toString(ref) );
      return item.get(0);

  }
  
   
   // show all garment items in the table
  public ArrayList<GarmentItem> getGarmentItems(){
      
      ArrayList<GarmentItem> garmentItemList = new ArrayList<GarmentItem>();
      
      try{  
        String query = "select * from alectadb.garment_items";
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            GarmentItem item = this.createGarmentItemObject(rs);
            if(item != null)
                garmentItemList.add(item);
        }
       
         return garmentItemList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // create a garment item object by passing the result set from a query
  public GarmentItem createGarmentItemObject(ResultSet rs){
        GarmentItem newItem = new GarmentItem();
        try{
        newItem.setGarmentItemCode(rs.getInt("garmentItemCode"));
        newItem.setPrice(rs.getDouble("price"));
        newItem.setCost(rs.getDouble("cost"));
        newItem.setURL(rs.getString("URL"));
        newItem.setQuantity(rs.getInt("quantity"));
        newItem.setInStockQuantity(rs.getInt("inStockQuantity"));
      
        return newItem;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }
}

