/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utile;

import java.sql.*;
import java.sql.DriverManager;


/**
 *
 * @author Plop
 */
public class DataSource {
      private String url="jdbc:mysql://localhost/storeship";
    private String login="root";
    private String pwd="";
    static DataSource instance;
    private Connection connection=null;
    private DataSource(){
        try
        {
            connection= DriverManager.getConnection(url,login,pwd);
            System.out.println("Database is CONNECTED !");
        }
        catch(Exception e){
            System.out.println("NOT CONNECTED to DB ----- Error:" + e);

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
            if (instance==null)
                return instance=new DataSource();
            return instance;
    }

    public String getUrl() {
        return url;
    }

}
