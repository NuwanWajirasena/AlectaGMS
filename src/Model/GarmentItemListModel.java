/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.GarmentItem;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Chathuranga
 */
public class GarmentItemListModel extends AbstractListModel implements ComboBoxModel {
    private String selectedItem;

      private ArrayList<GarmentItem> garmentItem;

      public GarmentItemListModel(ArrayList args) {
        this.garmentItem = args;
      }

        @Override
      public String getSelectedItem() {

        return selectedItem;
      }

        @Override
      public void setSelectedItem(Object newValue) {
            for (GarmentItem item: garmentItem){
                if (Integer.parseInt(newValue.toString())==item.getGarmentItemCode()){
                    selectedItem=item.getGarmentItemCode()+"";
                    break;
                }
            }
      }

        @Override
      public int getSize() {
        return garmentItem.size();
      }

        @Override
      public String getElementAt(int i) {
        return garmentItem.get(i).getGarmentItemCode()+"";
      }
    
}
