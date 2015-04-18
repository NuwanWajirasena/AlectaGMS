/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.SalaryRecord;
import DataAccess.SalaryDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class PayRollTableModal extends AbstractTableModel{

    private String[] colNames = {"Emp. ID","Emp. Name","Occupation","Net Salary"};
    private ArrayList<SalaryRecord> dat= (new SalaryDBHandler()).getSalaryList();

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
        SalaryRecord rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getEmpID();
            case 1:
                return rec.getEmpName();
            case 2:
                return rec.getOccupation();
            case 3:
                return rec.getNetSalary();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }

}
