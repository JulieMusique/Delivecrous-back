/**
 * 
 */
package com.imt.framework.web.delivecrous;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Ingredient;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.ressources.DishResource;

import org.junit.jupiter.api.Test;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 11 dec. 2023
 */
public class DishTest {
	
    private static Dish dish;

    @BeforeAll
    public static void setUp() {
        dish = new Dish();
    }

    @Test
    public void testGetDishTitle() {
        dish.setTitle("Burger");
        assertEquals("Burger", dish.getTitle());
    }

    @Test
    public void testGetDishDescription() {
        dish.setDescription("Plat constitue de pain et steaks");
        assertEquals("Plat constitue de pain et steaks", dish.getDescription());
    }

    @Test
    public void testGetDishPrice() {
        dish.setPrice(15);
        assertEquals(15, dish.getPrice());
    }

    @Test
    public void testGetDishImagePath() {
        dish.setImagePath("https://www.thecookierookie.com/wp-content/uploads/2023/04/featured-stovetop-burgers-recipe.jpg");
        assertEquals("https://www.thecookierookie.com/wp-content/uploads/2023/04/featured-stovetop-burgers-recipe.jpg", dish.getImagePath());
    }
    
    @Test
    public void testGetIngredientList() {
    	List<Ingredient> ingredients = new ArrayList<>();
    	
    	Ingredient ingredient1 = new Ingredient();
    	ingredient1.setName("Pain");
    	ingredient1.setCalorie(100);
    	ingredient1.setCategories(Set.of("FAT"));
    	Set<String> allergens = new HashSet<>(Set.of("WHEAT"));
    	allergens.add("SESAME");
    	ingredient1.setAllergenList(allergens);
    	ingredients.add(ingredient1);
    	
    	Ingredient ingredient2 = new Ingredient();
    	ingredient2.setName("STEAK");
    	ingredient2.setCalorie(200);
    	ingredient2.setCategories(Set.of("MEAT"));
    	ingredients.add(ingredient2);
    	
        dish.setIngredientList(ingredients);
        assertEquals(ingredients, dish.getIngredientList());
    }
}
