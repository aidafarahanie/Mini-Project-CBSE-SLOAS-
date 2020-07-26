/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@ManagedBean(name="login")
@SessionScoped
public class Login implements Serializable{
    
    private String email;
    private String password;
    private boolean isLogged;
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public Login(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    public String action(){
        isLogged = true;
        
        Connection connect = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "select * from users where email='"+email+"'";
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                userID = rs.getString("userid");
            }
            
            System.out.println(userID);
            System.out.println("Data Inserted");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        if(userID != null && password != null){
            HttpSession hs = util.getSession();
            hs.setAttribute("userID" , userID);
        return "HomePage.xhtml?faces-redirect=true";
        
        }
        
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String getReserve(){
        return "viewReservation.xhtml?faces-redirect=true";
    }
}
