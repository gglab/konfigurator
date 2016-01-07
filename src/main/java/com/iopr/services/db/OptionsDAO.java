/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.model.Configurable;
import com.iopr.model.Options;
import static com.iopr.services.db.CRUDDAO.connection;
import static com.iopr.services.db.SpringHibernateHSQLDAO.FUNCTION_GET_ALL_PREFIX;
import static com.iopr.services.db.SpringHibernateHSQLDAO.FUNCTION_GET_ALL_SUFFIX;
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
 * @author marr
 */
public class OptionsDAO extends SpringHibernateHSQLDAO {

    private static OptionsDAO dao = null;
    private static final String GET_ALLOWED_OPTIONS_STATEMENT = "SELECT * FROM Get_AllowedOptions(?, ?)";
    private static final String GET_NOT_ALLOWED_OPTIONS_STATEMENT = "SELECT * FROM Get_NotAllowedOptions(?, ?)";

    private OptionsDAO() {
    }

    public static OptionsDAO getInstance() {
        if (dao == null) {
            dao = new OptionsDAO();
        }
        return dao;
    }

//    @Override
    public boolean create(Class type, Options newOption) {
        final String createProductStatement = PROCEDURE_CREATE_PREFIX + type.getSimpleName() + " " + newOption.getProductID() + "," + newOption.getName() + ","
                + newOption.getPrice() + "," + newOption.isIsDefault() + "," + newOption.getGroupID();
        try {
            CallableStatement statement = connection.prepareCall(createProductStatement);
            statement.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Class type, Options updateOption) {
                final String updateOptionStatement = PROCEDURE_UPDATE_PREFIX + type.getSimpleName() + " " + updateOption.getId() + "," + updateOption.getName() + ","
                + updateOption.getPrice();
        System.out.println(updateOptionStatement);
        try {
            CallableStatement statement = connection.prepareCall(updateOptionStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public Object read(Class type, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Configurable> readAll(Class type) {
        Collection<Configurable> resultList = new ArrayList<Configurable>();
        final String getAllStatement = FUNCTION_GET_ALL_PREFIX + type.getSimpleName() + FUNCTION_GET_ALL_SUFFIX;
        try {
            CallableStatement statement = connection.prepareCall(getAllStatement);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                resultList.add(new Options(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getBoolean(3), executeQuery.getFloat(4), executeQuery.getLong(5),
                        executeQuery.getLong(6)));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    @Override
    public boolean remove(Class type, long id) {
        final String deleteOptionStatement = PROCEDURE_DELETE_PREFIX + type.getSimpleName() + " " + Long.toString(id);
        System.out.println(deleteOptionStatement);
        CallableStatement statement;
        try {
            statement = connection.prepareCall(deleteOptionStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
    public Collection<Options> readOptionsForProduct(long productID) {
        Collection<Options> resultList = new ArrayList<Options>();
        final String getAllStatement = FUNCTION_GET_ALL_PREFIX + Options.class.getSimpleName() + FUNCTION_GET_ALL_SUFFIX;
        try {
            CallableStatement statement = connection.prepareCall(getAllStatement);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                if(executeQuery.getLong(5) == productID)
                resultList.add(new Options(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getBoolean(3), executeQuery.getFloat(4), executeQuery.getLong(5),
                        executeQuery.getLong(6)));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }
    
    public Collection<Configurable> readAllowedOptions(long productId, long optionId){
        Collection<Configurable> resultList = new ArrayList<Configurable>();
        try {
            CallableStatement statement = connection.prepareCall(GET_ALLOWED_OPTIONS_STATEMENT);
            statement.setInt(1, (int) productId);
            statement.setInt(2, (int) optionId);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                resultList.add(new Options(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getBoolean(3), executeQuery.getFloat(4), executeQuery.getLong(5),
                        productId));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    public Collection<Configurable> readNotAllowedOptions(int productId, long optionId) {
        Collection<Configurable> resultList = new ArrayList<Configurable>();
        try {
            CallableStatement statement = connection.prepareCall(GET_NOT_ALLOWED_OPTIONS_STATEMENT);
            statement.setInt(1, (int) productId);
            statement.setInt(2, (int) optionId);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                resultList.add(new Options(executeQuery.getLong(1), executeQuery.getString(2), executeQuery.getBoolean(3), executeQuery.getFloat(4), executeQuery.getLong(5),
                        productId));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }
      

}
