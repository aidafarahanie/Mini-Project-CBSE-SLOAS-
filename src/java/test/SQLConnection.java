/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
/**
 *
 * @author Acer
 */
public class SQLConnection {
    private Connection connect= null;
    private static SQLConnection sqlconn = null;
    private Statement st = null;
    
    public  static SQLConnection getInstance(){
        if(sqlconn == null){
            sqlconn = new SQLConnection();
        }
        
        return sqlconn;
    }
    
     public Connection getSQLconnection(){
        String dbName = "test";
        String userName = "root";
        String password = "1234";
        String url = "jdbc:derby://localhost:1527/";
        String driver = "com.mysql.jdbc.Driver";
        
        try{
            Class.forName(driver);
            connect = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected");

        }
        
        catch(Exception ex){
            ex.printStackTrace();
        } 
        return connect;
    }
     
     SQLConnection(){
     
     }
     
}
