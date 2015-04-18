/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Employee;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Chathuranga
 */
public class EmployeeListModel extends AbstractListModel implements ComboBoxModel {
    private String selectedItem;

      private ArrayList<Employee> employee;

      public EmployeeListModel(ArrayList args) {
        this.employee = args;
      }

        @Override
      public String getSelectedItem() {

        return selectedItem;
      }

        @Override
      public void setSelectedItem(Object newValue) {
            for (Employee emp: this.employee){
                if (Integer.parseInt(newValue.toString())==(emp.getEmpID())){
                    selectedItem=emp.getEmpID()+"";
                    break;
                }
            }
      }

        @Override
      public int getSize() {
        return employee.size();
      }

        @Override
      public String getElementAt(int i) {
        return employee.get(i).getEmpID()+"";
      }
    
    
}
