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
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 *
 * @author glabg
 */
public class ProductsDAO extends SpringHibernateHSQLDAO{

    private static ProductsDAO dao = null;
    
    private ProductsDAO(){ }
    
    public static ProductsDAO getInstance(){
        if(dao==null){
            dao = new ProductsDAO();
        }
        return dao;
    }
    
    @Override
    public Collection<Configurable> readAll(Class type){
        Collection<Configurable> resultList = new ArrayList<Configurable>();
        final String getAllProductsStatement = FUNCTION_GET_ALL_PREFIX + type.getSimpleName() + FUNCTION_GET_ALL_SUFFIX;
        try {
            CallableStatement statement = connection.prepareCall(getAllProductsStatement);
            ResultSet executeQuery = statement.executeQuery();
            while(executeQuery.next()){
                resultList.add(new Products(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getFloat(3)));
            }
                statement.close();
                executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }
    
    @Override
    public boolean create(Class type, Entity object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Class type, Entity object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param type
     * @param id
     * @return
     */
    @Override
    public Object read(Class type, int id) {
        final String getProductsByIdStatement = FUNCTION_GET_BY_ID_PREFIX + type.getSimpleName() + FUNCTION_GET_BY_ID_SUFFIX;
        try {
            CallableStatement statement = connection.prepareCall(getProductsByIdStatement);
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if(executeQuery.next())
                return new Products(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getFloat(3));
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}