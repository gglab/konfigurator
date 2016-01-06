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
//@Entity
//@Table(name = "options")
public class Options implements Configurable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isDefault")
    private boolean isDefault;

    @Column(name = "price")
    private float price;

    private long productID;

    private long groupID;

    public Options(long id, String name, boolean isDefault, float price) {
        this.id = id;
        this.name = name;
        this.isDefault = isDefault;
        this.price = price;
    }

    public Options(long id, String name, boolean isDefault, float price, long productID, long groupID) {
        this.id = id;
        this.name = name;
        this.isDefault = isDefault;
        this.price = price;
        this.productID = productID;
        this.groupID = groupID;
    }

    public Options() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the productID
     */
    public long getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    /**
     * @return the groupID
     */
    public long getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    @Override
    public String toString() {
        return "Options{" + "id=" + id + ", name=" + name + ", isDefault=" + isDefault + ", price=" + price + ", productID=" + productID + ", groupID=" + groupID + '}';
    }

}
