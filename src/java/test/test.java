/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Acer
 */
@ManagedBean(name="test")
public class test {
    public String fullName;
    public String email;
    public String password;
    public String confPassword;
    public String type;
    public String phoneNumber;
    public String userID;
    int min = 50;
    int max = 1000;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    public String goTo(){
        return "HomePage.xhtml?faces-redirect=true";
    }
    
    public String goReserve(){
        return "reservePage.xhtml?faces-redirect=true";
    }
    
    public String goRegister(){

        Connection connect = null;
        PreparedStatement ps;
        
        Random rand  = new Random();
        
        int random = rand.nextInt(10000); 
        System.out.println(random);
        
        userID = Integer.toString(random);
        
        try {
            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "insert into users(userid, email, password, type) values('"+userID+"', '"+email+"','"+password+"','"+type+"')";
            ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Data Inserted");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "/index.xhtml?faces-redirect=true";
    }
}
