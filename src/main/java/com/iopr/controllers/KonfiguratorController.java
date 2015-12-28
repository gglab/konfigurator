/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Product;
import com.iopr.services.db.KonfiguratorDAO;
import java.util.HashSet;
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
        HashSet<Entity> products = (HashSet<Entity>) KonfiguratorDAO.getInstance().readAll(Product.class);
        ModelAndView model = new ModelAndView("index");
        model.addObject("name", name);
        model.addObject("products", products);
        return model;
    }
    
    @RequestMapping("/product")
    public ModelAndView product(@RequestParam(value="id", required=false, defaultValue="default") long id) {
//        HashSet<Entity> products = (HashSet<Entity>) KonfiguratorDAO.getInstance().readAll(Product.class);
        ModelAndView model = new ModelAndView("product");
//        Product product = (Product) products.stream().filter((p)-> p.getId() == id).findAny().get();
//        model.addObject(product);
        return model;
    }
    
}
