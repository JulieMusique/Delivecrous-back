package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.*;

public enum Allergen {
    EGG,
    MILK,
    MUSTARD,
    PEANUT, //Arachide
    MOLLUSC,
    CRUSTACEAN,
    SESAME,
    SOY, //Soja
    SULPHITES,
    NUT,  //Noix
    WHEAT;  //Blé

    public static boolean contain(String value){
        for(int i=0; i<Allergen.values().length; i++){
            if(Objects.equals(value, Allergen.values()[i].name()))
                return true;
        }
        return false;
    }

    public static void testAllergens(Set<String> allergens) throws Exception {
        for (String allergen : allergens) {
                if (!Allergen.contain(allergen))
                    throw new Exception(allergen + " inconnu");
        }
    }

    public static void initDishAllergens(Dish dish) {
        Set<String> allergens = new HashSet<>();
        for(int i=0; i<dish.getIngredientList().size(); i++){
            allergens = dish.getAllergenList();
            allergens.addAll(dish.getIngredientList().get(i).getAllergenList());
        }
        dish.setAllergenList(allergens);
    }
}
