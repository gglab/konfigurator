/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Products;
import com.iopr.services.db.ProductsDAO;
import com.iopr.model.Configurable;
import com.iopr.model.Groups;
import com.iopr.model.Options;
import com.iopr.model.ProductToOptionLink;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author glabg
 */
@Controller
public class KonfiguratorController {

    private List<Configurable> options = new ArrayList<Configurable>();
    private List<Configurable> groups = new ArrayList<Configurable>();
    private List<Configurable> links = new ArrayList<Configurable>();

    private void init() {

        Options o1 = new Options(1, "option1", true, 8);
        Options o2 = new Options(2, "option2", false, 15);
        Options o3 = new Options(3, "option3", false, 5);

        Options o4 = new Options(4, "option4", true, 80);
        Options o5 = new Options(5, "option5", false, 12);
        options.add(o1);
        options.add(o5);
        options.add(o2);
        options.add(o3);
        options.add(o4);

        Groups g1 = new Groups(1, "grupa1");
        Groups g2 = new Groups(2, "grupa2");
        groups.add(g1);
        groups.add(g2);

        ProductToOptionLink l1 = new ProductToOptionLink(1, 1, 1, 1);
        ProductToOptionLink l2 = new ProductToOptionLink(2, 1, 2, 1);
        ProductToOptionLink l3 = new ProductToOptionLink(3, 1, 3, 1);
        ProductToOptionLink l4 = new ProductToOptionLink(4, 1, 4, 2);
        ProductToOptionLink l5 = new ProductToOptionLink(5, 1, 5, 2);
        links.add(l1);
        links.add(l2);
        links.add(l3);
        links.add(l4);
        links.add(l5);
    }

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        ModelAndView model = new ModelAndView("index");
        model.addObject("name", name);
        model.addObject("products", products);
        return model;
    }

    @RequestMapping("/product")
    public ModelAndView product(@RequestParam(value = "id", required = true) int id) {
        init();
        ModelAndView model = new ModelAndView();
        Products product = (Products) ProductsDAO.getInstance().read(Products.class, id);
        if (product != null) {
            model.addObject("productName", product.getName());
            model.addObject("productId", product.getId());
            //model.addObject("productStandardPrice", product.getStandardPrice());
        }
        if (options != null) {
            model.addObject("options", options);
            model.addObject("productStandardPrice", getPrice(options, product));
        }

        return model;
    }

    public float getPrice(Collection<Configurable> options, Products product) {
        float standardPrice = product.getStandardPrice();
        for (Iterator<Configurable> iterator = options.iterator(); iterator.hasNext();) {
            Options next = (Options) iterator.next();
            if (next.isIsDefault()) {
                standardPrice += next.getPrice();
            }
        }
        return standardPrice;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void processForm(@RequestParam("json") String json) {
        System.out.println("TESTGGGG!");
        System.out.println(json);
    }

}
