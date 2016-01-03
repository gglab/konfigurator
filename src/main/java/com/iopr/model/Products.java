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
@Table(name = "Products")
public class Products implements Configurable{

    public Products(long id, String name, float standardPrice) {
        this.id = id;
        this.name = name;
        this.standardPrice = standardPrice;
    }
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "standardPrice")
    private float standardPrice;
    
    public float getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(float standardPrice) {
        this.standardPrice = standardPrice;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

}
