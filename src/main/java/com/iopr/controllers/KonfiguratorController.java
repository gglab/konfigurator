/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iopr.controllers;

import com.iopr.model.Products;
import com.iopr.services.db.ProductsDAO;
import com.iopr.model.Configurable;
import com.iopr.model.Options;
import com.iopr.services.ValidatorService;
import com.iopr.services.db.OptionsDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

    private Collection<Options> options;
    private long productID;

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<Configurable> products = (List<Configurable>) ProductsDAO.getInstance().readAll(Products.class);
        ModelAndView model = new ModelAndView("index");
        model.addObject("name", name);
        model.addObject("products", products);
        return model;
    }

    @RequestMapping("/product")
    public ModelAndView product(    @RequestParam(value = "id", required = true) int id,
                                    @RequestParam(value = "optionId", required = false) String optionId) {
        ModelAndView model = new ModelAndView();
        Products product = (Products) ProductsDAO.getInstance().read(Products.class, id);
        if (product != null) {
            model.addObject("productName", product.getName());
            model.addObject("productId", product.getId());
        }
        if(options == null||productID!=id ){
            options = OptionsDAO.getInstance().readOptionsForProduct(product.getId());
            productID = product.getId();
        }
        if (optionId != null) {
            Options selected = (Options) options.stream().filter(p -> p.getId() == Long.parseLong(optionId)).findAny().get();
            selected.setIsDefault(!selected.isIsDefault());
        }
        options = (List<Options>) ValidatorService.getInstance().validateOptions(options, (int) product.getId());
        model.addObject("options", options);
        model.addObject("productStandardPrice", getPrice(product));       
        return model;
    }

    public float getPrice(Products product) {
        float standardPrice = product.getStandardPrice();
        for (Iterator<Options> iterator = options.iterator(); iterator.hasNext();) {
            Options next = (Options) iterator.next();
            if (next.isIsDefault()) {
                standardPrice += next.getPrice();
            }
        }
        return standardPrice;
    }
}
