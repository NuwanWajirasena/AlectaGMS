/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Order;
import DataAccess.OrderDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class OrderTableModel extends AbstractTableModel{
    
    private String[] colNames = {"Order Ref.","Garm. Item code","Customer","Qty","Due Date","Status"};
    private ArrayList<Order> dat= (new OrderDBHandler()).getOrders();

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
        Order rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getOrderReference();
            case 1:
                return rec.getGamentItemCode();
            case 2:
                return rec.getCustomer();
            case 3:
                return rec.getQuantity();
            case 4:
                return rec.getDeadLine();
            case 5:
                return rec.getStatus();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }

}
