/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.model.Configurable;
import com.iopr.model.Products;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

/**
 *
 * @author glabg
 */
public abstract class SpringHibernateHSQLDAO implements CRUDDAO{
    
    public static final String PROCEDURE_GET_ALL_PREFIX = "GetAll_";
   // public static final String 
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Override
    public Collection<Configurable> readAll(Class type){    
        
        String statementString = "SELECT * FROM dbo." + PROCEDURE_GET_ALL_PREFIX + type.getSimpleName() + "()";
        
//        EntityManager em = emf.createEntityManager();
//        List<Configurable> resultList = em.createQuery(statementString).getResultList();
        List<Configurable> resultList = new ArrayList<Configurable>();
//        //String statementString = "select * from products where isActive = 1";
        System.out.println("GG: " + statementString);
        ResultSet procResult;
        try {
            Statement statement = connection.createStatement();
            procResult = statement.executeQuery(statementString);
            while(procResult.next()){
                resultList.add((Configurable) new Products(procResult.getLong(1), procResult.getString(2), procResult.getFloat(3)));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SpringHibernateHSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultList;
    }
    
    
}
