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
        options = setAllAllowed(options);
        Collection<Configurable> selected = getSelectedOptions(options);
        if(selected.size()==0)
            return options;
        
        Collection<Configurable> notAllowed = new ArrayList<Configurable>();
        for (Iterator<Configurable> iterator = selected.iterator(); iterator.hasNext();) {
            Options selectedOption = (Options) iterator.next();
            notAllowed.addAll(OptionsDAO.getInstance().readNotAllowedOptions(productId, selectedOption.getId()));
            //System.out.println("Not allowed options for " + selectedOption.getName() + ": " + notAllowed.size());
        }
        if(notAllowed.size()==0)
            return options;
        
        for (Iterator<Options> iterator = options.iterator(); iterator.hasNext();) {
            Options option = iterator.next();
            boolean isNotAllowed = notAllowed.stream().anyMatch(p -> p.getId() == option.getId());
            option.setIsEnabled(!isNotAllowed);
            //System.out.println("Validate option: " + option.getName() + " is not allowed: " + isNotAllowed + " set it enabled: " + option.isIsEnabled());
        }
        return options;
    }
    
    private Collection<Configurable> getSelectedOptions(Collection<Options> options){        
        return options.stream().filter(p -> p.isIsDefault()).collect(Collectors.toList());
    }

    private Collection<Options> setAllAllowed(Collection<Options> options) {
        for (Options option : options) {
            option.setIsEnabled(true);
        }
        return options;
    }
}
