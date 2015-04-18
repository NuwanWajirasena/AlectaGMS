/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.IncomeExpense;
import DataAccess.IncomeExpenseDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class DoubleEntryTableModel extends AbstractTableModel{
    private String[] colNames = {"Ref. ID","Timestamp","Type","Value","Customer","Resposible Employee","Description"};
    private ArrayList<IncomeExpense> dat= (new IncomeExpenseDBHandler()).getIncome_Expenses();

     @Override
    public int getRowCount() {
        return dat.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IncomeExpense rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getReferenceId();
            case 1:
                return rec.getDate();
            case 2:
                return rec.getType();
            case 3:
                return rec.getValue();
            case 4:
                return rec.getCustomer();
            case 5:
                return rec.getResponsible();
            case 6:
                return rec.getDescription();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }

    
}
