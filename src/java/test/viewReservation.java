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
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@ManagedBean(name="vr")
@RequestScoped
public class viewReservation {
    
    @ManagedProperty(value="#{login}")
    private Login login;
    private String pax;
    private String times;
    private String dates;
    private String userID = "50";
    private String tableNo;
    public String reservationID;
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
    

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getUserID() {
        return login.getUserID();
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    
    public ArrayList<viewReservation> getMessages(){
        return reserveData.getReservation();
    }
    
    @PostConstruct
    public void init(){
        Connection connect = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "select * from reservation where userid='"+userID+"'";
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                pax = rs.getString("pax");
                dates = rs.getString("dates");
                times = rs.getString("times");
                tableNo = rs.getString("tableNo");
                
            }
            
            System.out.println(userID);
            System.out.println("Data Inserted");
            
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }
    
    public String deleteReservation(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String reserveid= params.get("action");
        System.out.println(reserveid);
        Connection connect = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "delete from reservation where reservationid=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, reserveid);
            ps.executeUpdate();
            System.out.println("Data deleted");
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "/viewReservation.xhtml?faces-redirect=true";
    }
    
}
