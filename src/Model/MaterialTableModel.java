/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Material;
import DataAccess.MaterialDBHandler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class MaterialTableModel extends AbstractTableModel{
    private String[] colNames = {"Material code","Image","Description","Unit price","Available Qty"};
    private ArrayList<Material> dat= (new MaterialDBHandler()).getMaterials();

    
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
        Material rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getMaterialCode();
            case 1:
                return rec.getURL();
            case 2:
                return rec.getDescription();
            case 3:
                return rec.getUnitPrice();
            case 4:
                return rec.getCurrentQuantity();
            default:return "invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }
}
