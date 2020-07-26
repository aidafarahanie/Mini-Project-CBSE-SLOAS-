/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Acer
 */
@ManagedBean(name="reserve")
@RequestScoped
public class reserve {
    @ManagedProperty(value="#{login}")
    private Login login;
    
    private String reservationID;
    private String[] times;
    private Date dates;
    private String tableNo;
    private String pax;

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    
    
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }


    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }
    
    
    
    private String LogginuserID;

    public String getLogginuserID() {
        return login.getUserID();
    }

    public void setLogginuserID(String LogginuserID) {
        this.LogginuserID = LogginuserID;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
    public String bookReserve(){
        Connection connect = null;
        PreparedStatement ps;
        
        Random rand  = new Random();
        
        int random = rand.nextInt(1000); 
        
        reservationID = Integer.toString(random);
        int random2 = rand.nextInt(30);
        
        int paxs = Integer.parseInt(pax);
        
        if(paxs < 5){
            tableNo = random2+"A";
        }else{
            tableNo = random2+"B";
        }
        
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String datess = format.format(dates);
            System.out.println(datess);

            SQLConnection connection = new SQLConnection();
            connect = connection.getSQLconnection();
            String sql = "insert into reservation(reservationid, userid, tableno, pax, times, dates) values('"+reservationID+"', '"+getLogginuserID()+"','"+tableNo+"','"+pax+"','"+Arrays.toString(times)+"','"+datess+"')";
            ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Data Inserted");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return "HomePage.xhtml?faces-redirect=true";
    }
}
