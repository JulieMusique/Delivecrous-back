package com.imt.framework.web.delivecrous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imt.framework.web.delivecrous.entities.Category;
import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Ingredient;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.ressources.DishResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DishResourceTest {

    private static DishResource dishResource;
    private static DishRepository mockDishRepository;

    @BeforeAll
    public static void setUp() {
        mockDishRepository = mock(DishRepository.class);
        dishResource = new DishResource();
        dishResource.setDishRepository(mockDishRepository);
    }

    @Test
    public void testCreateDish() throws Exception {
        // Set up test data
        Dish dish = new Dish();

        // Execute the createDish method
        dish.setTitle("Burger");
    	dish.setDescription("Plat constitue de pain et steaks");
    	dish.setPrice(15);
    	dish.setImagePath("https://www.thecookierookie.com/wp-content/uploads/2023/04/featured-stovetop-burgers-recipe.jpg");
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
        
        dishResource.createDish(dish);
        // Verify that the required methods were called
        verify(mockDishRepository, times(1)).save(dish);
    }
    
    @Test
    public void testGetDishs() {
    	// Mocking data for testing
    	Double maxPrice = 10.0;
    	String selectedCategory = Category.FAT.toString();
    	String searchedTitle = "Burger";
    	System.out.println();
    	// Mocking repository behavior
    	when(mockDishRepository.getDishsWithMaxPriceFilter(maxPrice)).thenReturn(Collections.emptyList());
    	when(mockDishRepository.getDishsWithSelectedCategory(selectedCategory)).thenReturn(Collections.emptyList());
    	when(mockDishRepository.getDishsWithSearchedTitle(searchedTitle)).thenReturn(Collections.emptyList());
    	when(mockDishRepository.findAll()).thenReturn(Collections.emptyList());
    	
    	// Testing with different parameters
    	List<Dish> result1 = dishResource.getDishs(maxPrice, null, null);
    	List<Dish> result2 = dishResource.getDishs(null, selectedCategory, null);
    	List<Dish> result3 = dishResource.getDishs(null, null, searchedTitle);
    	List<Dish> result4 = dishResource.getDishs(null, null, null);
    	
    	// Verifying repository method calls
    	verify(mockDishRepository, times(1)).getDishsWithMaxPriceFilter(maxPrice);
    	verify(mockDishRepository, times(1)).getDishsWithSelectedCategory(selectedCategory);
    	verify(mockDishRepository, times(1)).getDishsWithSearchedTitle(searchedTitle);
    	verify(mockDishRepository, times(1)).findAll();
    	
    	// Add more assertions based on your actual business logic
    	// For example, assert that the returned results match your expectations
    	assertEquals(Collections.emptyList(), result1);
    	assertEquals(Collections.emptyList(), result2);
    	assertEquals(Collections.emptyList(), result3);
    	assertEquals(Collections.emptyList(), result4);
    }


    @Test
    public void testUpdateDish() throws Exception {
        // Set up test data
        Long dishId = 1L;
        Dish existingDish = new Dish();
        existingDish.setIdDish(dishId);

        Dish updatedDish = new Dish();
        updatedDish.setTitle("UpdatedTitle");

        // Mock repository behavior
        when(mockDishRepository.findById(dishId)).thenReturn(Optional.of(existingDish));

        // Execute the updateDish method
        dishResource.updateDish(dishId, updatedDish);

        // Verify that the required methods were called
        verify(mockDishRepository, times(1)).findById(dishId);
        verify(mockDishRepository, times(1)).save(existingDish);
    }

    @Test
    public void testUpdateDishNotFound() {
        // Set up test data
        Long dishId = 12L;
        Dish updatedDish = new Dish();
        updatedDish.setTitle("UpdatedTitle");

        // Mock repository behavior for a non-existent dish
        when(mockDishRepository.findById(dishId)).thenReturn(Optional.empty());

        // Execute the updateDish method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            dishResource.updateDish(dishId, updatedDish);
        });

        // Verify that the exception message is as expected
        assertEquals("Dish inconnu", exception.getMessage());

        // Verify that the repository method was called
        verify(mockDishRepository, times(1)).findById(dishId);
    }

    @Test
    public void testDeleteDish() {
        // Set up test data
        Long dishId = 1L;

        // Execute the deleteDish method
        dishResource.deleteDish(dishId);

        // Verify that the repository method was called
        verify(mockDishRepository, times(1)).deleteById(dishId);
    }
}
