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
        Collection<Configurable> resultList = super.readAll(type);
        final String getAllProductsStatement = FUNCTION_GET_ALL_PREFIX + type.getSimpleName() + "()";
        try {
            CallableStatement statement = connection.prepareCall(getAllProductsStatement);
            ResultSet executeQuery = statement.executeQuery();
            while(executeQuery.next()){
                resultList.add(new Products(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getFloat(3)));
            }
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

    @Override
    public Entity read(Class type, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
