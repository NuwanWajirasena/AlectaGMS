/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package DataAccess;

import Business.IncomeExpense;
import Business.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MaterialDBHandler {
    
    private ConnectionManager c = ConnectionManager.getInstance();
  
// Enter the material details into the database by passing a material object    
    public boolean enterMaterial(Material mat){
         try{  
        String query = "INSERT INTO alectadb.materials (unitPrice, URL, description, currentQuantity ) \n" +
        "VALUES ("+ mat.getUnitPrice()+","+mat.getURL()+",'" +mat.getDescription()+ "'," + mat.getCurrentQuantity() + ");";
            
      System.out.println(query);
       c.executeUpdate(query);
          
      // when a material is added its expense is sent to the income expense database 
        double expense = mat.getUnitPrice()* mat.getCurrentQuantity();
        String expDescription = "Buy "+ mat.getCurrentQuantity()+" of M-"+mat.getMaterialCode()+" at Rs."+mat.getUnitPrice();
        
             IncomeExpense ex = new IncomeExpense();
             ex.setType("Expense"); 
             ex.setValue(expense);
             ex.setDate(new Timestamp(System.currentTimeMillis()));
             ex.setDescription(expDescription);
             
             new IncomeExpenseDBHandler().addIncome_Expense(ex);
             
             return true;
         }
        catch(Exception ex){
             System.out.println("Exception at enter material");
             return false;
         }
    }
    //UPDATE columns of the material table selecting the record based on the primary key value
   public boolean updateMaterial(String updatedColumn,String updatedValue,int primaryKey){
       String query=null;
    
       try{
           int arg = Integer.parseInt(updatedValue);
           query = "UPDATE alectadb.materials SET `"+updatedColumn+"`=" +arg+ " where `materialCode` = "+primaryKey;
           System.out.println(query);
           
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(updatedValue);
              query = "UPDATE alectadb.materials SET `"+updatedColumn +"`="+ arg+" where `materialCode` = "+primaryKey;
              System.out.println(query);
          }
          catch( NumberFormatException exe ){
              query = "UPDATE alectadb.materials SET `"+updatedColumn +"`='"+ updatedValue+"' where `materialCode` = "+primaryKey;
              System.out.println(query);
          }
      }
       c.executeUpdate(query);
       return true;
   }
   
   public void replaceRecord(Material newRec) {
       try{
             String query = "UPDATE alectadb.materials SET `unitPrice` = " +newRec.getUnitPrice()+ ", `URL` = '" +newRec.getURL()+ "', `description` = '"+newRec.getDescription()+"', `currentQuantity` = "+newRec.getCurrentQuantity()+" where `materialCode` = "+newRec.getMaterialCode();
             c.executeUpdate(query);
       }
        catch(Exception ex){
            System.out.println("Exception : " + ex.toString());
         }
   }
   
   //Increase the currentQuantity of the material.
   public boolean increaseQuantity( String updatedValue, int primaryKey ){
       String query = null;
       Material m =this.getMaterial(primaryKey);
       double arg = Double.parseDouble(updatedValue) + m.getCurrentQuantity();
       query = "UPDATE alectadb.materials SET `currentQuantity`=" +arg+ " where `materialCode` = "+primaryKey;
       c.executeUpdate(query);
       
       // when a material is added its expense is sent to the income expense database 
        double expense = m.getUnitPrice()* Double.parseDouble(updatedValue);
        String expDescription = "Buy "+ Double.parseDouble(updatedValue)+" of M-"+m.getMaterialCode()+" at Rs."+m.getUnitPrice();
        
             IncomeExpense ex = new IncomeExpense();
             ex.setType("Expense"); 
             ex.setValue(expense);
             ex.setDate(new Timestamp(System.currentTimeMillis()));
             ex.setDescription(expDescription);
             
             new IncomeExpenseDBHandler().addIncome_Expense(ex);
       return true;
   }
   
   //Decrease the currentQuantity of the material.
   public boolean decreaseQuantity( String updatedValue, int primaryKey ){
       String query = null;
       Material m =this.getMaterial(primaryKey);
       if( m.getCurrentQuantity() < Double.parseDouble(updatedValue)){
           System.out.println("Not enough quantity");
           return false;
       }
       else{
           double arg = - Double.parseDouble(updatedValue) + m.getCurrentQuantity();
           query = "UPDATE alectadb.materials SET `currentQuantity`=" +arg+ " where `materialCode` = "+primaryKey;
           c.executeUpdate(query);
           return true;
       }
   }
    // method to search the material by entering the column name and the keyword
   public ArrayList<Material> searchMaterial(String field,String argument){
      String query=null;
       try{
          int arg = Integer.parseInt(argument);
          query = "select * from alectadb.materials where `"+field+"` = "+arg;
          System.out.println(query);
      }catch(NumberFormatException ex){
          try{
              double arg = Double.parseDouble(argument);
              query = "select * from alectadb.materials where `"+field +"`="+ arg;
              System.out.println(query);
          }
          catch(NumberFormatException exe){
              query = "select * from alectadb.materials where `"+field +"`='"+argument +"'";
              System.out.println(query);
          }
 
      }
       
      ResultSet rs = c.executeQuery(query);
      ArrayList<Material> list = new ArrayList<Material>();
      Material item=null;
      try{
      while(rs.next()){
          
      item = this.createMaterialObject(rs);
      
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
   public boolean deleteMaterial(int ref){
         try{     
        String query = "DELETE FROM alectadb.materials WHERE `materialCode` ="+ref;
             System.out.println(query);
        c.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at delete material");
             return false;
         }
   }
   //Return the material object based on the employeeID
  public Material getMaterial( int ref ){
    
      ArrayList<Material> item = this.searchMaterial("materialCode", Integer.toString(ref) );
      return item.get(0);
  }
   
   // show all materials in the table
  public ArrayList<Material> getMaterials(){
      
      ArrayList<Material> materialList = new ArrayList<Material>();
      
      try{  
        String query = "select * from alectadb.materials";
        ResultSet rs = c.executeQuery(query);
        while (rs.next()) {
            Material item = this.createMaterialObject(rs);
            if(item != null)
                materialList.add(item);
        }
       
         return materialList;
         }
         catch(Exception ex){
             System.out.println(ex.toString());
             return null;
         }  
    }
  
  // create a material object by passing the result set from a query
  public Material createMaterialObject(ResultSet rs){
        Material newItem = new Material();
        try{
        newItem.setMaterialCode(rs.getInt("materialCode"));
        newItem.setUnitPrice(rs.getDouble("unitPrice"));
        newItem.setURL(rs.getString("URL"));
        newItem.setCurrentQuantity(rs.getDouble("currentQuantity"));
        newItem.setDescription(rs.getString("description"));
      
        return newItem;
        }
        catch(SQLException ex){
            c.displaySQLErrors(ex);
            return null;
        }
  }
    
}