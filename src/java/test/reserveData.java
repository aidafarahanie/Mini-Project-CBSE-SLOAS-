/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Acer
 */
@RequestScoped
public class reserveData {
    @ManagedProperty(value="#{login}")
    private Login login;
    
    private static String userID;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getUserID() {
        return login.getUserID();
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    
    public static ArrayList<viewReservation> getReservation() {
        Connection connect = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "SELECT users.email, reservation.reservationid, reservation.tableno, reservation.pax, reservation.times, reservation.dates\n" +
            "FROM users\n" +
            "INNER JOIN reservation ON users.USERID=reservation.USERID";
            ArrayList<viewReservation> al = new ArrayList<viewReservation>();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            boolean found = false;
            
            while(rs.next()){
                viewReservation e = new viewReservation();
                e.setEmail(rs.getString("email"));
                e.setReservationID(rs.getString("reservationid"));
                e.setPax(rs.getString("pax"));
                e.setDates(rs.getString("dates"));
                e.setTimes(rs.getString("times"));
                e.setTableNo(rs.getString("tableNo"));
                al.add(e);
                found = true;
            }
            
            rs.close();
            
            if(found){
                return al;
            }else{
                return null;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return(null);
        }
 
    }
            
}
