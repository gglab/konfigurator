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
 * @author glabg
 */
@Entity
@Table(name = "productToOptionLink")
public class ProductToOptionLink implements Configurable{
    
    @Id
    @Column(name = "product2option")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "productID")
    private long productID;
    
    @Column(name = "optionID")
    private long optionID;
    
    @Column(name = "groupID")
    private long groupID;

    public ProductToOptionLink(long id, long productID, long optionID, long groupID) {
        this.id = id;
        this.productID = productID;
        this.optionID = optionID;
        this.groupID = groupID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getOptionID() {
        return optionID;
    }

    public void setOptionID(long optionID) {
        this.optionID = optionID;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }
    
    
    
}
