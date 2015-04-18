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
public class DataEntryOperator extends GUI_Builder{
    
    public DataEntryOperator(){
        setTitle("Data Entry Operator");
    }
    @Override
    protected GUI_Parent buildMainWindow() {
        Main_Window dataEntry = new Main_Window();
        dataEntry.removeTab(4);
        dataEntry.removeTab(3);
        dataEntry.removeTab(2);
        dataEntry.removeTab(1);
        dataEntry.changeTabName();
        return dataEntry;
    }

   
    
}
