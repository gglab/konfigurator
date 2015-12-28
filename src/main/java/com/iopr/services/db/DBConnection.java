/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import com.iopr.DBResource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;


/**
 *
 * @author glabg
 */
public class DBConnection {

    Connection connection = null;
    
    private static Logger logger = Logger.getLogger(DBConnection.class.getName());
    
    public DBConnection() {
        
        DBResource dbr = new DBResource();
        try {
            Class.forName(dbr.getJdbcDriver());
        } catch (Exception e) {
            logger.error("failed to load JDBC driver", e);
            return;
        }
        try {
            connection = DriverManager.getConnection(dbr.getJdbcConnectionString(),
                    dbr.getDbUser(),
                    dbr.getDbPassword());
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
