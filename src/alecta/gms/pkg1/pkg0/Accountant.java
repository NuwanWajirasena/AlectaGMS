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
public class Accountant extends GUI_Builder{
    public Accountant(){
        setTitle("Accountant");
    }

    @Override
    protected GUI_Parent buildMainWindow() {
        Main_Window acc_window=new Main_Window();
        acc_window.removeTab(5);
        acc_window.removeTab(4);
        acc_window.removeTab(3);
        acc_window.removeTab(1);
        acc_window.changeTabName();
        return acc_window;
    }

  
}
