package com.iopr.services;

import com.iopr.model.Configurable;
import com.iopr.model.Options;
import com.iopr.services.db.OptionsDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glabg
 */
public class ValidatorService {
    
    private static ValidatorService instance;
    
    private ValidatorService(){}
    
    public static ValidatorService getInstance(){
        if(instance==null){
            return new ValidatorService();
        }
        return instance;
    }
    public Collection<Options> validateOptions(Collection<Options> options, int productId){
        Collection<Configurable> selected = getSelectedOptions(options);
        Collection<Configurable> notAllowed = new ArrayList<Configurable>();
        for (Iterator<Configurable> iterator = selected.iterator(); iterator.hasNext();) {
            Options selectedOption = (Options) iterator.next();
            notAllowed.addAll(OptionsDAO.getInstance().readNotAllowedOptions(productId, selectedOption.getId()));
        }
        System.out.println("Not allowed options: " + notAllowed.size());
        for (Iterator<Options> iterator = options.iterator(); iterator.hasNext();) {
            Options option = iterator.next();
            System.out.println("Validate option: " + option.getName() + " is not allowed: " + notAllowed.contains(option));
            option.setIsEnabled(!notAllowed.contains(option));
            //option.setIsDefault(!(selected.contains(option) && notAllowed.contains(option)));
        }
        return options;
    }
    
    private Collection<Configurable> getSelectedOptions(Collection<Options> options){        
        return options.stream().filter(p -> p.isIsDefault()).collect(Collectors.toList());
    }
}
