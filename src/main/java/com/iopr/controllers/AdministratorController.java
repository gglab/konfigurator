/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Configurable;
import com.iopr.model.Options;
import com.iopr.model.Products;
import com.iopr.services.db.GroupsDAO;
import com.iopr.services.db.OptionsDAO;
import com.iopr.services.db.ProductsDAO;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author marr
 */
@Controller
public class AdministratorController {

    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView ret = new ModelAndView();

        return ret;
    }

    @RequestMapping("/adminProduct")
    public ModelAndView adminProduct() {
        ModelAndView ret = new ModelAndView();
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        ret.addObject("products", products);
        return ret;
    }
    
    @RequestMapping(value = "adminProduct", method=RequestMethod.POST)
    public void addProduct(){
        System.out.println("Linia");
    }

    @RequestMapping("/adminOption")
    public ModelAndView adminOption() {
        ModelAndView ret = new ModelAndView();
        List<Configurable> options = (List<Configurable>) OptionsDAO.getInstance().readAll(Options.class);
        ret.addObject("options", options);
        return ret;
    }

    @RequestMapping("/adminGroup")
    public ModelAndView adminGroup() {
        ModelAndView ret = new ModelAndView();
        List<Configurable> groups = (List<Configurable>) GroupsDAO.getInstance().readAll(Options.class);
        ret.addObject("groups", groups);

        return ret;
    }

    @RequestMapping("/adminRule")
    public ModelAndView adminRule() {
        ModelAndView ret = new ModelAndView();

        return ret;
    }
}
