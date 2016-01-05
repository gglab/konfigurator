/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

 

/**
 
 * @author glabg
 */
@Configuration
@PropertySource("classpath:/db.properties")
public class DBResource {

    @Autowired
    Environment env;

    public String getJdbcDriver() {
        //return env.getProperty("jdbcDriver");
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    public String getJdbcConnectionString() {
        //return env.getProperty("jdbcConnectionString");
        return "jdbc:sqlserver://localhost;databaseName=konfigurator";
        //return "jdbc:hsqldb:file:D:\\Programs\\MSSQL\\MSSQL12.MSSQLSERVER\\MSSQL\\DATA\\konfigurator";
    }

    public String getDbUser() {
        //return env.getProperty("dbUser");
        return "gg";
    }

    public String getDbPassword() {
        //return env.getProperty("dbPassword");
        return "gg";
    }

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("db.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
