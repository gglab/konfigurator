/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Configurable;
import com.iopr.model.Groups;
import com.iopr.model.Options;
import com.iopr.model.Products;
import com.iopr.model.Rules;
import com.iopr.services.db.GroupsDAO;
import com.iopr.services.db.OptionsDAO;
import com.iopr.services.db.ProductsDAO;
import com.iopr.services.db.RulesDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        Products newProduct = new Products();
        ret.addObject("products", products);
        ret.addObject("newProduct", newProduct);
        return ret;
    }

    @RequestMapping(value = "adminProduct/create", method = RequestMethod.POST)
    public String addProduct(Products newProduct) {
        ProductsDAO.getInstance().create(Products.class, newProduct);
        return "redirect:/adminProduct";
    }

    @RequestMapping(value = "adminProduct/remove", method = RequestMethod.POST)
    public String deleteProduct(Products delProduct) {
        ProductsDAO.getInstance().remove(Products.class, delProduct.getId());
        return "redirect:/adminProduct";
    }

    @RequestMapping(value = "adminProduct/update", method = RequestMethod.POST)
    public String updateProduct(Products updatedProduct) {
        ProductsDAO.getInstance().update(Products.class, updatedProduct);
        return "redirect:/adminProduct";
    }

    @RequestMapping("/adminOption")
    public ModelAndView adminOption() {
        ModelAndView ret = new ModelAndView();
        List<Configurable> options = (List<Configurable>) OptionsDAO.getInstance().readAll(Options.class);
        ret.addObject("options", options);
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        ret.addObject("products", products);
        List<Configurable> groups = (List<Configurable>) GroupsDAO.getInstance().readAll(Groups.class);
        ret.addObject("groups", groups);
        Options newOption = new Options();
        ret.addObject("newOption", newOption);
        return ret;
    }

    @RequestMapping(value = "adminOption/create", method = RequestMethod.POST)
    public String addOption(Options newOption) {
        System.out.println(newOption.toString());
        OptionsDAO.getInstance().create(Options.class, newOption);
        return "redirect:/adminOption";
    }

    @RequestMapping(value = "adminOption/remove", method = RequestMethod.POST)
    public String deleteOption(Options delOption) {
        OptionsDAO.getInstance().remove(Options.class, delOption.getId());
        return "redirect:/adminOption";
    }

    @RequestMapping(value = "adminOption/update", method = RequestMethod.POST)
    public String updateOption(Options updatedOptions) {
        System.out.println(updatedOptions.toString());
        OptionsDAO.getInstance().update(Options.class, updatedOptions);
        return "redirect:/adminOption";
    }

    @RequestMapping("/adminGroup")
    public ModelAndView adminGroup() {
        ModelAndView ret = new ModelAndView();
        List<Configurable> groups = (List<Configurable>) GroupsDAO.getInstance().readAll(Groups.class);
        ret.addObject("groups", groups);
        Groups newGroup = new Groups();
        ret.addObject("newGroup", newGroup);

        return ret;
    }

    @RequestMapping(value = "adminGroup/update", method = RequestMethod.POST)
    public String updateGroup(Groups updatedGroup) {
        GroupsDAO.getInstance().update(Groups.class, updatedGroup);
        return "redirect:/adminGroup";
    }

    @RequestMapping(value = "adminGroup/create", method = RequestMethod.POST)
    public String addGroup(Groups newGroup) {
        GroupsDAO.getInstance().create(Groups.class, newGroup);
        return "redirect:/adminGroup";
    }

    @RequestMapping("/adminRule")
    public ModelAndView adminRule(@RequestParam(value = "productID", required = false) Long productID) {
        ModelAndView ret = new ModelAndView();
        List<Configurable> rules = (List<Configurable>) RulesDAO.getInstance().readAll(Rules.class);
        Rules newRule = new Rules();
        List<Options> options = new ArrayList<>();
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        Products productForRule = new Products();

        if (productID != null) {
            options = (List<Options>) OptionsDAO.getInstance().readOptionsForProduct(productID);
            productForRule.setId(productID);
            
        }
        ret.addObject("options", options);
        ret.addObject("products", products);
        ret.addObject("rules", rules);
        ret.addObject("newRule", newRule);
        ret.addObject("productForRule", productForRule);
        return ret;
    }

    @RequestMapping(value = "adminRule/remove", method = RequestMethod.POST)
    public String deleteRule(Rules delRule) {
        System.out.println(delRule.toString());
        RulesDAO.getInstance().remove(Rules.class, delRule.getId());
        return "redirect:/adminRule";
    }

    @RequestMapping(value = "adminRule/selectProductForRule", method = RequestMethod.POST)
    public String selectProductForRule(Products productForRule) {
//        OptionsDAO.getInstance().readOptionsForProduct(productForRule.getId());
        return "redirect:/adminRule?productID="+Long.toString(productForRule.getId());
    }

    @RequestMapping(value = "adminRule/create", method = RequestMethod.POST)
    public String addRule(Rules newRule) {
        RulesDAO.getInstance().create(Rules.class, newRule);
        return "redirect:/adminRule";
    }
}
