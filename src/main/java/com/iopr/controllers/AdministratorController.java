/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

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

        return ret;
    }
    
    @RequestMapping(value = "adminProduct", method=RequestMethod.POST)
    public void addProduct(){
        System.out.println("Linia");
    }

    @RequestMapping("/adminOption")
    public ModelAndView adminOption() {
        ModelAndView ret = new ModelAndView();

        return ret;
    }

    @RequestMapping("/adminGroup")
    public ModelAndView adminGroup() {
        ModelAndView ret = new ModelAndView();

        return ret;
    }

    @RequestMapping("/adminRule")
    public ModelAndView adminRule() {
        ModelAndView ret = new ModelAndView();

        return ret;
    }
}
