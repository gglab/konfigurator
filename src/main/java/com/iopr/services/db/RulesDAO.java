/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.model.Configurable;
import com.iopr.model.Rules;
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
public class RulesDAO extends SpringHibernateHSQLDAO {

    private static RulesDAO dao = null;

    private RulesDAO() {
    }

    public static RulesDAO getInstance() {
        if (dao == null) {
            dao = new RulesDAO();
        }
        return dao;
    }

//    @Override
    public boolean create(Class type, Rules newRule) {
        final String createRuleStatement = PROCEDURE_CREATE_PREFIX + type.getSimpleName() + " " +  newRule.getProductID() +", " + newRule.getOption1ID()
                                                                                + ", " + newRule.getOption2ID();
        System.out.println(createRuleStatement);
        try {
            CallableStatement statement = connection.prepareCall(createRuleStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RulesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean update(Class type, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                resultList.add(new Rules(executeQuery.getLong(1), executeQuery.getLong(2), executeQuery.getLong(3), executeQuery.getLong(4)));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(RulesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    @Override
    public boolean remove(Class type, long id) {
        final String deleteRuleStatement = PROCEDURE_DELETE_PREFIX + type.getSimpleName() + " " + Long.toString(id);
        System.out.println(deleteRuleStatement);
        CallableStatement statement;
        try {
            statement = connection.prepareCall(deleteRuleStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RulesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
