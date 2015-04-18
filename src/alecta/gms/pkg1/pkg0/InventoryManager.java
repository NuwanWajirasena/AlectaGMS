/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alecta.gms.pkg1.pkg0;

import GUI.GUI_Builder;
import GUI.GUI_Parent;
import GUI.Main_Window;

/**
 *
 * @author Chathuranga
 */
public class InventoryManager extends GUI_Builder{

    public InventoryManager(){
        setTitle("Inventory Manager");
    }
    @Override
    protected GUI_Parent buildMainWindow() {
        Main_Window inventory = new Main_Window();
        inventory.removeTab(5);
        inventory.removeTab(4);
        inventory.removeTab(2);
        inventory.removeTab(1);
        inventory.changeTabName();
        return inventory;
    }

    
}
