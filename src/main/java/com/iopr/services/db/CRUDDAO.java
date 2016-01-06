/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import java.sql.Connection;
import java.util.Collection;
import javax.persistence.Entity;

/**
 *
 * @author glabg
 */
public interface CRUDDAO {
    
    public Connection connection = new DBConnection().getConnection();
    
    public boolean create(Class type, Entity object);
    public boolean update(Class type, Entity object);
    public Object read(Class type, int id);
    public <Entity>Collection readAll(Class type);
    public boolean delete(Class type, Entity object);
}
