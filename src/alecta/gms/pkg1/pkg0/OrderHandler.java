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
public class OrderHandler extends GUI_Builder {
    
    public OrderHandler(){
        setTitle("Order Handler");
    }
    @Override
    protected GUI_Parent buildMainWindow() {
        Main_Window order = new Main_Window();
        order.removeTab(5);
        order.removeTab(3);
        order.removeTab(2);
        order.removeTab(1);
        order.changeTabName();
        return order;
    }

    
    
}
