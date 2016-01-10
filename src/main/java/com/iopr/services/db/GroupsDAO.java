/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.model.Configurable;
import com.iopr.model.Groups;
import com.iopr.model.Options;
import com.iopr.model.Products;
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
public class GroupsDAO extends SpringHibernateHSQLDAO {

    private static GroupsDAO dao = null;

    private GroupsDAO() {
    }

    public static GroupsDAO getInstance() {
        if (dao == null) {
            dao = new GroupsDAO();
        }
        return dao;
    }

//    @Override
    public boolean create(Class type, Groups newGroup) {
        final String createGroupStatement = PROCEDURE_CREATE_PREFIX + type.getSimpleName() + " " + newGroup.getName();
        try {
            CallableStatement statement = connection.prepareCall(createGroupStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
        return true;
    }

    public boolean update(Class type, Groups updatedGroup) {
        final String updateGroupStatement = PROCEDURE_UPDATE_PREFIX + type.getSimpleName() + " " + updatedGroup.getId() + ",'" + updatedGroup.getName()+"'";
        System.out.println(updateGroupStatement);
        try {
            CallableStatement statement = connection.prepareCall(updateGroupStatement);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
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
                resultList.add(new Groups(executeQuery.getLong(1), executeQuery.getString(2)));
            }
            statement.close();
            executeQuery.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
        return resultList;
    }

    @Override
    public boolean remove(Class type, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
