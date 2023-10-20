package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DishRepository extends JpaRepository<Dish, Long> {
    /**
     * @param maxPrice : max price for a dish
     * @return dishs from h2 database with a price lower than maxPrice
     */
    @Query("select d from Dish d where d.price < :maxPrice")
    List<Dish> getDishsWithMaxPriceFilter(@Param("maxPrice") Double maxPrice);

    /**
     * @param selectedCategory : category selected for a dish
     * @return dishs who contain selectedCategory in their set
     */
    @Query(value = "SELECT * FROM Dish WHERE ARRAY_CONTAINS(categories, :selectedCategory)", nativeQuery = true)
    List<Dish> getDishsWithSelectedCategory(@Param("selectedCategory") String selectedCategory);

    /**
     * @param searchedTitle : category selected for a dish
     * @return dishs who contain selectedCategory in their set
     */
    @Query(value = "SELECT * FROM Dish WHERE LOWER(title) LIKE LOWER(:searchedTitle) || '%'", nativeQuery = true)
    List<Dish> getDishsWithSearchedTitle(@Param("searchedTitle") String searchedTitle);
}