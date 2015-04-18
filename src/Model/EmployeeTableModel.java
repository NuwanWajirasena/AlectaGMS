/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Employee;
import DataAccess.EmployeeDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class EmployeeTableModel extends AbstractTableModel{

   private String[] colNames = {"Employee ID","First Name","Last Name","Gender","DOB","Address","Contact No.","NIC No."};
    private ArrayList<Employee> dat= (new EmployeeDBHandler()).getAllEmployees();

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
        Employee rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getEmpID();
            case 1:
                return rec.getFirstName();
            case 2:
                return rec.getLastName();
            case 3:
                return rec.getGender();
            case 4:
                return rec.getDOB();
            case 5:
                return rec.getAddress();
            case 6:
                return rec.getContactNo();
            case 7:
                return rec.getNIC();
            default:return "Invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }
    
}
