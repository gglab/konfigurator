/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

/**
 *
 * @author glabg
 */

public abstract class SpringHibernateHSQLDAO implements CRUDDAO{
    
    public static final String FUNCTION_GET_ALL_PREFIX = "SELECT * FROM GetAll_";
    public static final String FUNCTION_GET_ALL_SUFFIX = "()";
    public static final String FUNCTION_GET_BY_ID_PREFIX = "SELECT * FROM GetByID_";
    public static final String FUNCTION_GET_BY_ID_SUFFIX = "(?)";
    public static final String PROCEDURE_CREATE_PREFIX = "exec Create_";
    public static final String PROCEDURE_DELETE_PREFIX = "exec Remove_";
    public static final String PROCEDURE_UPDATE_PREFIX = "exec Update_";
    
   // public static final String    
    
}
