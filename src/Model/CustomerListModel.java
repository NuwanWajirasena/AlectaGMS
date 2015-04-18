/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.Customer;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Chathuranga
 */
public class CustomerListModel extends AbstractListModel implements ComboBoxModel {

    private String selectedItem;

      private ArrayList<Customer> customers;

      public CustomerListModel(ArrayList args) {
        this.customers = args;
      }

        @Override
      public String getSelectedItem() {

        return selectedItem;
      }

        @Override
      public void setSelectedItem(Object newValue) {
            for (Customer customer: customers){
                if (newValue.equals(customer.getCustomerName())){
                    selectedItem=customer.getCustomerName();
                    break;
                }
            }
      }

        @Override
      public int getSize() {
        return customers.size();
      }

        @Override
      public String getElementAt(int i) {
        return customers.get(i).getCustomerName();
      }
    
}
