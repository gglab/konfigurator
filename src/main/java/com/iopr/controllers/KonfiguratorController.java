/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Product;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author glabg
 */
@Controller
public class KonfiguratorController {
    
    public static Set<Product> products = new HashSet();
    
    static{
        Product p1 = new Product();
        p1.setId(1234);
        p1.setName("PRODUCT1");
        Product p2 = new Product();
        p2.setId(4321);
        p2.setName("Product2");
        products.add(p1);
        products.add(p2);
    }
    
    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        ModelAndView model = new ModelAndView("index");
        model.addObject("name", name);
        model.addObject("products", products);
        return model;
    }
    
    @RequestMapping("/product")
    public ModelAndView product(@RequestParam(value="id", required=false, defaultValue="default") long id) {
        ModelAndView model = new ModelAndView("product");
        Product product = (Product) products.stream().filter((p)-> p.getId() == id).findAny().get();
        model.addObject(product);
        return model;
    }
    
}
