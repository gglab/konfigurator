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
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

/**
 *
 * @author glabg
 */

public abstract class SpringHibernateHSQLDAO implements CRUDDAO{
    
    public static final String FUNCTION_GET_ALL_PREFIX = "SELECT * FROM GetAll_";
    public static final String FUNCTION_GET_ALL_SUFFIX = "()";
    public static final String FUNCTION_GET_BY_ID_PREFIX = "SELECT * FROM GetByID_";
    public static final String FUNCTION_GET_BY_ID_SUFFIX = "(?)";
   // public static final String    
    
}
