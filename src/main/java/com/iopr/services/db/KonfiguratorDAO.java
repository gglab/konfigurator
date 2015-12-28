/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.services.db;

import javax.persistence.Entity;

/**
 *
 * @author glabg
 */
public class KonfiguratorDAO extends SpringHibernateHSQLDAO{

    private static KonfiguratorDAO dao = null;
    
    private KonfiguratorDAO(){ }
    
    public static KonfiguratorDAO getInstance(){
        if(dao==null){
            dao = new KonfiguratorDAO();
        }
        return dao;
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
