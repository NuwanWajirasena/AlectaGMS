/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import GUI.Configuration_Manager;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Chathuranga
 */
//Singleton class
public class ConnectionManager {
    private String ip_address;
    private String port;
    private String database;
    private String username;
    private String password;
    
    String url;
    
    protected Connection connection;
    
    private ConnectionManager() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
            ip_address=prop.getProperty("ip_address");
            port=prop.getProperty("port");
            database=prop.getProperty("database");
            username=prop.getProperty("dbuser");
            password=prop.getProperty("dbpassword");
            url = "jdbc:mysql://"+ip_address+":"+port+"/"+database;
            /*try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            try {
                 //String url ="jdbc:mysql://localhost:3306/alectadb";
                connection = (Connection)DriverManager.getConnection(url, username, password);
                
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Sorry! Connection has lost", "Connection", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("SQLException : "+ex.getMessage());
                System.out.println("SQLState : "+ex.getSQLState());
                System.out.println("VendorError: "+ex.getErrorCode()); 
                new Configuration_Manager().setVisible(true);
            } catch (Exception e){
                new Configuration_Manager().setVisible(true);
            }
        } catch (IOException ex) {
            Configuration_Manager win = new Configuration_Manager();
            win.setVisible(true);
        }
        
    }
    
    public static ConnectionManager getInstance() {
        return ConnectionManagerHolder.INSTANCE;
    }
    
    private static class ConnectionManagerHolder {

        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }
    
    public  void displaySQLErrors(SQLException ex){
        System.out.println("SQLException : "+ex.getMessage());
        System.out.println("SQLState : "+ex.getSQLState());
        System.out.println("VendorError: "+ex.getErrorCode());    
    }
    
     
    // execute the query written as a string for INSERT,DELETE and UPDATE operations;
    public void executeUpdate(String query){
        try{
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        st.close();
        //connection.close();
        }
        catch(SQLException ex){
            displaySQLErrors(ex);
            System.out.println("Customer Already Exists");
        }      
    }
    
    //execute the query written as a string for SELECT operations;
    public ResultSet executeQuery(String query){
        
        try{
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
       return rs;
        }
        
        catch(SQLException ex){
            displaySQLErrors(ex);
            System.out.println("Exception at executeQuery");
            return null;
        }
        catch(Exception e){
            return null;
        }
    }
    
    

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatabase() {
        return database;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }
    
}
