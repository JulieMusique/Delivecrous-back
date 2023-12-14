package com.imt.framework.web.delivecrous;

import org.junit.jupiter.api.Test;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Favoris;
import com.imt.framework.web.delivecrous.entities.Ingredient;
import com.imt.framework.web.delivecrous.entities.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

public class FavorisTest {
	
	private static User user;
	private static Dish dish;
	
	@BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhone("1234567890");
        user.setLogin("john_doe");
        user.setPassword("securePassword123");
        user.setAdresse("123 Main St");
        user.setSoldeCarteCrous(100.0);
        
        dish = new Dish();
        dish.setTitle("Burger");
    	dish.setDescription("Plat constitue de pain et steaks");
    	dish.setPrice(15);
    	dish.setImagePath("https://www.thecookierookie.com/wp-content/uploads/2023/04/featured-stovetop-burgers-recipe.jpg");
    	List<Ingredient> ingredients = new ArrayList<>();
    	
    	Ingredient ingredient1 = new Ingredient();
    	ingredient1.setName("Pain");
    	ingredient1.setCalorie(100);
    	ingredient1.setCategories(Set.of("FAT"));
    	Set<String> categories = new HashSet<>(Set.of("WHEAT"));
    	categories.add("SESAME");
    	ingredient1.setCategories(categories);
    	ingredients.add(ingredient1);
    	
    	Ingredient ingredient2 = new Ingredient();
    	ingredient2.setName("STEAK");
    	ingredient2.setCalorie(200);
    	ingredient2.setCategories(Set.of("MEAT"));
    	ingredients.add(ingredient2);
    	
        dish.setIngredientList(ingredients);
    }
	

    @Test
    public void testConstructorAndGetters() {
        Favoris favoris = new Favoris(user, dish);
        
        assertNull(favoris.getId()); // L'ID doit être null car il est généré automatiquement
        assertEquals(user, favoris.getUser());
        assertEquals(dish, favoris.getPlat());
    }

    @Test
    public void testSetters() {
        Favoris favoris = new Favoris();
        user.setFirstName("Harry");
        dish.setPrice(15.0);
        favoris.setUser(user);
        favoris.setPlat(dish);

        assertEquals("Harry", favoris.getUser().getFirstName());
        assertEquals(15.0, favoris.getPlat().getPrice());
    }

}
