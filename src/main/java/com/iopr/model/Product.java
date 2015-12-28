/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author glabg
 */

@Entity
public class Product {

    public Product(long id, String name, float standardPrice) {
        this.id = id;
        this.name = name;
        this.standardPrice = standardPrice;
    }

    private long id;
    private String name;
    private float standardPrice;
    
    public float getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(float standardPrice) {
        this.standardPrice = standardPrice;
    }
    
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

}
