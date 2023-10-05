package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DishRepository extends JpaRepository<Dish, Long> {  //Long pour l'id qui est de type long
    @Query("select d from Dish d where d.price < :maxPrice")  // : -> pour mettre un paramètre/variable
    List<Dish> getDishsWithMaxPriceFilter(@Param("maxPrice") Double maxPrice);

    @Query(value = "SELECT d FROM Dish d WHERE ARRAY_CONTAINS(d.categories, :selectedCategory)", nativeQuery = true)
    List<Dish> getDishsWithSelectedCategory(@Param("selectedCategory") String selectedCategory);

    /*@Query("SELECT * FROM DISH WHERE 'selectedAllergens' = ANY(ALLERGEN_LIST)")
    List<Dish> getDishsWithSelectedAllergens(@Param("selectedAllergens") List<String> selectedAllergens);*/

    /*@Query("SELECT * FROM DISH WHERE 'selectedCategories' = ANY(CATEGORIES ) AND 'selectedAllergens' = ANY(ALLERGEN_LIST)")
    List<Dish> getDishsWithSelectedCategoriesAndAllergens(@Param("selectedCategories") List<String> selectedCategories, @Param("selectedAllergens") List<String> selectedAllergens);*/

}
