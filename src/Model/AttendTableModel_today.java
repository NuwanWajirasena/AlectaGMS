/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.AttendanceRecord;
import DataAccess.AttendanceDBHandler;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chathuranga
 */
public class AttendTableModel_today extends AbstractTableModel{
    private String[] colNames = {"Date","Employee Id","Arival time","Depature time"};
    private ArrayList<AttendanceRecord> dat= (new AttendanceDBHandler()).getAttendanceRecords(new Date(System.currentTimeMillis()));

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
        AttendanceRecord rec=dat.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rec.getDate();
            case 1:
                return rec.getEmpID();
            case 2:
                return rec.getArrival();
            case 3:
                return rec.getDeparture();
            default:return "Invalid data";
        }
    }
    @Override
    public String getColumnName(int col){
        return colNames[col];
    }
    
}
