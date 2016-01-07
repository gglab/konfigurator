/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marr
 */
@Entity
@Table(name = "Rules")
public class Rules implements Configurable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "productID")
    private long productID;
    
    @Column(name = "option1ID")
    private long option1ID;
    
    @Column(name = "option2ID")
    private long option2ID;

    public Rules(long id, long productID, long option1ID, long option2ID){
        this.id = id;
        this.productID = productID;
        this.option1ID = option1ID;
        this.option2ID = option2ID;
        
    }

    public Rules() {
        
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the productID
     */
    public long getProductID() {
        return productID;
    }

    /**
     * @return the option1ID
     */
    public long getOption1ID() {
        return option1ID;
    }

    /**
     * @return the option2ID
     */
    public long getOption2ID() {
        return option2ID;
    }

    @Override
    public String toString() {
        return "Rules{" + "id=" + id + ", productID=" + productID + ", option1ID=" + option1ID + ", option2ID=" + option2ID + '}';
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    /**
     * @param option1ID the option1ID to set
     */
    public void setOption1ID(long option1ID) {
        this.option1ID = option1ID;
    }

    /**
     * @param option2ID the option2ID to set
     */
    public void setOption2ID(long option2ID) {
        this.option2ID = option2ID;
    }

    
}
