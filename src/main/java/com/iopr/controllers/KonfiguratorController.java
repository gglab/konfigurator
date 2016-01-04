/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Products;
import com.iopr.services.db.ProductsDAO;
import com.iopr.model.Configurable;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Entity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author glabg
 */
@Controller
public class KonfiguratorController {
    
    
    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        ModelAndView model = new ModelAndView("index");
        model.addObject("name", name);
        model.addObject("products", products);
        return model;
    }
    
    @RequestMapping("/product")
    public ModelAndView product(@RequestParam(value="id", required=true) int id) {
        ModelAndView model;
        Products product = (Products) ProductsDAO.getInstance().read(Products.class, id);
        if(product != null){
            model = new ModelAndView("product");
            model.addObject(product);
        }else{
            model = new ModelAndView();
        }
        return model;
    }
    
}
