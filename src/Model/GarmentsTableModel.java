/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.GarmentItem;
import DataAccess.GarmentItemDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class GarmentsTableModel extends AbstractTableModel{
    private String[] colNames = {"Garment item code","Price","Cost","Current Qty","In-Stock Qty"};
    private ArrayList<GarmentItem> dat= (new GarmentItemDBHandler()).getGarmentItems();

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
        GarmentItem rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getGarmentItemCode();
            case 1:
                return rec.getPrice();
            case 2:
                return rec.getCost();
            case 3:
                return rec.getQuantity();
            case 4:
                return rec.getInStockQuantity();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }

}
