/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Customer;
import DataAccess.CustomerDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class CustomerTableModel extends AbstractTableModel{
     private String[] colNames = {"Customer name","Contact No.","Address"};
    private ArrayList<Customer> dat= (new CustomerDBHandler()).getCustomers();

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
        Customer rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getCustomerName();
            case 1:
                return rec.getContactNo();
            case 2:
                return rec.getAddress();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }
}
