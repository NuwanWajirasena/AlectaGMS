/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Chathuranga
 */
public abstract class GUI_Builder {
    private String title="Defalt";
    private GUI_Parent gui_parent;
    //private LayoutManager mngr;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    } 
    public GUI_Parent getParent(){
        return gui_parent;
    }
        
    public void makeMainWindow(){
        gui_parent = buildMainWindow();
        JFrame window = (JFrame) gui_parent;
        
        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        //device.setFullScreenWindow(window);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds(0, 0, screenSize.width,screenSize.height);
        window.setVisible(true);
    }
    
    protected abstract GUI_Parent buildMainWindow();
}
