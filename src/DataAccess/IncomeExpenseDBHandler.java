package DataAccess;
import Business.IncomeExpense;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class IncomeExpenseDBHandler {
    
    private ConnectionManager con = ConnectionManager.getInstance();
    
    // Enter the income or expense details into the database by passing an Income_Expence object    
    public void addIncome_Expense(IncomeExpense record){
         try{  
            String query = "INSERT INTO alectadb.Income_ExpenseDB (`type`, `value`, `responsible`, `customer`, `description`) \n" +
            "VALUES ( '" + record.getType() + "', " + record.getValue() + ", " + record.getResponsible() + ", '" + record.getCustomer() + "', '" + record.getDescription() + "' )";    
            System.out.println(query);
            con.executeUpdate(query);
         }
        catch(Exception ex){
             
         }             
    }
    //Update the given column with the given value for the given reference no
    public boolean updateRecord(int reference, String updateCol, String updateVal){
        String query=null;
        try{
               query = "UPDATE alectadb.Income_ExpenseDB SET `" +updateCol+ "` = "+ Integer.parseInt(updateVal)+" WHERE `referenceId` = " + reference;          
               System.out.println(query);
               con.executeUpdate(query);
               return true;
            }
            catch(Exception e1) {
                try {
                    query = "UPDATE alectadb.Income_ExpenseDB SET `" +updateCol+ "` = "+ Double.parseDouble(updateVal) +" WHERE `referenceId` = " + reference;          
                    System.out.println(query);
                    con.executeUpdate(query);
                    return true;
                }catch(Exception e2) {
                    try {
                        query = "UPDATE alectadb.Income_ExpenseDB SET `" +updateCol+ "` = '"+ updateVal +"' WHERE `referenceId` = " + reference;          
                        System.out.println(query);
                        con.executeUpdate(query);
                        return true;
                    }catch(Exception e3){
                        System.out.println(e3.toString()); 
                        return false;
                    }
                }
            }
    }
    
    public void replaceIncomeExpense(IncomeExpense newRec){
        try{
             String query = "UPDATE alectadb.Income_ExpenseDB SET `type` = '" +newRec.getType()+ "', `value` = " +newRec.getValue()+ ", `description` = '"+newRec.getDescription()+"', `responsible` = "+newRec.getResponsible()+", `customer` = '"+newRec.getCustomer()+"' where `referenceID` = "+newRec.getReferenceId();
             con.executeUpdate(query);             
        }
        catch(Exception ex){
             System.out.println("Exception at replaceIncomeExpense");
         }             
    }
    
   // get the record of a income or expense record reference number
   public IncomeExpense getIncome_ExpenseRecord(int ref){
        
       String query=null;
      
       query = "select * from alectadb.Income_ExpenseDB where `referenceId`= " + ref;
       System.out.println(query);
       String ID = Integer.toString(ref);
      
      ArrayList<IncomeExpense> list = this.getIncome_ExpenseList("referenceID",ID);
      
      
      return list.get(0);
   }
   
   // get the record of a income or expense record reference number
   public ArrayList<IncomeExpense> getIncome_Expenses(){
        
       String query=null;
      
       query = "select * from alectadb.Income_ExpenseDB;";
       System.out.println(query);
        
      ResultSet rs = con.executeQuery(query);
      
      ArrayList<IncomeExpense> list = new ArrayList<IncomeExpense>();
      
      try{
        while(rs.next()){         
             IncomeExpense rec = this.createIncomeExpenseRecord(rs);
            
          if(rec != null)
                list.add(rec);
      }
      }
      catch(SQLException ex){
        con.displaySQLErrors(ex);
      }
      return list;
   }
   
   // get the record of a income or expense record for a particular query
   public ArrayList<IncomeExpense> getIncome_ExpenseList(String column, String val){
        
       String query=null;
       if(column.equals(("value")) || column.equals("responsible")) {
        query = "select * from alectadb.Income_ExpenseDB where `" + column + "`= " + val;
        System.out.println(query);
       }
       else {
            query = "select * from alectadb.Income_ExpenseDB where `" + column + "` = '" + val +"';";
            System.out.println(query);
       }
       ResultSet rs = con.executeQuery(query);
       ArrayList<IncomeExpense> list = new ArrayList<IncomeExpense>();
      
      try{
        while(rs.next()){         
             IncomeExpense rec = this.createIncomeExpenseRecord(rs);
            
          if(rec != null)
                list.add(rec);
      }
      }
      catch(SQLException ex){
        con.displaySQLErrors(ex);
      }
      return list;
   }
   
   //delete a record based on the referenceID
   public boolean deleteRecord(int ID){
         try{     
        String query = "DELETE FROM alectadb.Income_ExpenseDB WHERE `referenceID` = "+ ID;
        System.out.println(query);
        con.executeUpdate(query);
         return true;
         }
         catch(Exception ex){
             System.out.println("Exception at deleteRecord");
             return false;
         }
   }
   
   // create an income_expense object by passing the result set from a query
   public IncomeExpense createIncomeExpenseRecord(ResultSet rs){
        IncomeExpense rec = new IncomeExpense();
        try{
            rec.setDate(rs.getTimestamp("date"));
            rec.setReferenceId(rs.getInt("referenceId"));
            rec.setType(rs.getString("type"));
            rec.setValue(rs.getDouble("value"));
            rec.setResponsible(rs.getInt("responsible"));
            rec.setCustomer(rs.getString("customer"));
            rec.setDescription(rs.getString("description"));        
        return rec;
        }
        catch(SQLException ex){
            con.displaySQLErrors(ex);
            return null;
        }
   }
   
   //Get the total income
   public double getTotalIncome() {
       ArrayList<IncomeExpense> rec = this.getIncome_ExpenseList("type", "Income");
       double total = 0;
       for(int i=0; i<rec.size(); i++) {
           total += rec.get(i).getValue();
       }
       return total;
   }
   
   //Get the total expense
   public double getTotalExpense() {
       ArrayList<IncomeExpense> rec = this.getIncome_ExpenseList("type", "Expense");
       double total = 0;
       for(int i=0; i<rec.size(); i++) {
           total += rec.get(i).getValue();
       }
       return total;
   } 
}