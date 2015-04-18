/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alecta.gms.pkg1.pkg0;
import GUI.Configuration_Manager;
import GUI.GUI_Builder;
import GUI.Login_GUI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Chathuranga
 */
public class AlectaGMS {

    /**
     * @param args the command line arguments
     */
    //protected static Login_GUI login;
    private static GUI_Builder user;
    public static void setUser(GUI_Builder builder){
        user=builder;
    }
    public static GUI_Builder getUser(){
        return user;
    } 
    public static void main(String[] args) {
        
        
        Properties prop = new Properties();
        Properties prop1= new Properties();
         try {
            prop1.load(new FileInputStream("theme.properties"));
             try {
                    //UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
                    String lookAndFeel = "com.jtattoo.plaf.";
                    lookAndFeel=lookAndFeel+prop1.getProperty("theme");
                    UIManager.setLookAndFeel(lookAndFeel);//new HiFiLookAndFeel());
              } 
              catch (Exception e) 
              {
                    e.printStackTrace();
              }
          } catch (IOException ex) {
           
            try {
                    //UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
                    prop1.setProperty("theme", "hifi.HiFiLookAndFeel");
                    prop1.store(new FileOutputStream("theme.properties"), null);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");//new HiFiLookAndFeel());
              } 
              catch (Exception ei) 
              {
                    ex.printStackTrace();
              }
          }
            
            
            
        try {
             prop.load(new FileInputStream("config.properties"));
             
        Login_GUI login = new Login_GUI();
        login.setVisible(true);
        login.setLocation(450, 250);
         }
        catch (IOException e){
           
            
             Configuration_Manager configGUI = new Configuration_Manager();
             configGUI.setLocation(450, 150);
             configGUI.setVisible(true);
        }
       
           
          /* try {
                       //set the properties value
                       prop.setProperty("database", "localhost");
                       prop.setProperty("dbuser", "mkyong");
                       prop.setProperty("dbpassword", "password");
    
                       //save properties to project root folder
                       prop.store(new FileOutputStream("config.properties"), null);
    
               } catch (IOException ex) {
                       ex.printStackTrace();
           }
           // TODO code application logic here
           /* try {
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
           java.awt.EventQueue.invokeLater(new Runnable() {

               @Override
               public void run() {
                   new Login_GUI().setVisible(true);
               }
           });*/
      
        }
        

}
