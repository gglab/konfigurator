/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.model.Product;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 *
 * @author glabg
 */
public abstract class SpringHibernateHSQLDAO implements CRUDDAO{
    
    public static final String PROCEDURE_GET_ALL_PREFIX = "GetAll_";
   // public static final String 
    
    @Override
    public Collection <Entity> readAll(Class type){
        Collection <Entity> result = new HashSet<Entity> ();
        
        String statementString = "SELECT * FROM dbo." + PROCEDURE_GET_ALL_PREFIX + type.getSimpleName() + "()";
        //String statementString = "select * from products where isActive = 1";
        System.out.println("GG: " + statementString);
        ResultSet procResult;
        try {
            Statement statement = connection.createStatement();
            procResult = statement.executeQuery(statementString);
            while(procResult.next()){
                result.add((Entity) new Product(procResult.getLong(1), procResult.getString(2), procResult.getFloat(3)));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SpringHibernateHSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
