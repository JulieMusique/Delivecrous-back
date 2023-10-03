package com.imt.framework.web.delivecrous.entities;

import java.util.*;

public enum Category {
    VEGETARIAN("VEGETARIAN"),
    MEAT("MEAT"),
    FISH("FISH"),
    HEALTHY("HEALTHY"),
    FAT("FAT");
	
	private String name;
	
	Category(String name){
		this.name = name;
	}

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static boolean contain(String value){
        for(int i=0; i<Category.values().length; i++){
            if(Objects.equals(value, Category.values()[i].name()))
                return true;
        }
        return false;
    }

    public static void testCategories(Set<String> categories) throws Exception {
        for (String category : categories) {
            if (!Category.contain(category))
                throw new Exception(category + " inconnu");
        }
    }

    public static void initDishCategories(Dish dish) {
        Set<String> categories = new HashSet<>();
        for(int i=0; i<dish.getIngredientList().size(); i++){
            //categories = dish.getCategories();
            categories.addAll(dish.getIngredientList().get(i).getCategories());
        }
        if(categories.contains("VEGETARIAN")){
            if(categories.contains("MEAT") || categories.contains("FISH"))
                categories.remove("VEGETARIAN");
        }

        if(categories.contains("HEALTHY")) {
            if (categories.contains("FAT"))
                categories.remove("HEALTHY");
        }

        dish.setCategories(categories);
    }

}
