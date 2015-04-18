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
public class Administrator extends  GUI_Builder{
    
    public Administrator(){
        setTitle("Administrator");
    }

    @Override
    protected GUI_Parent buildMainWindow() {
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         * 
         */

       
         
        return new Main_Window();
        
    }
}
