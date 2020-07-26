/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Acer
 */

@ManagedBean
@ViewScoped
public class BasicView implements Serializable {

    private List<Customer> customer = null;

    @PostConstruct
    public void init() {
        System.out.println("create Customer....");
        customer = new ArrayList<Customer>();
        for(int i = 0; i<10; i++){
        customer.add(new Customer("Nor Hidayah", "table 1a", "8:45PM - 7:45PM", "5/5/2020", "012-3455678", 4, "n.hifayah98@gmail.com"));
        }
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
