/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
