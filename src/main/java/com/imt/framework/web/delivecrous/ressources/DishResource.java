package com.imt.framework.web.delivecrous.ressources;

import com.imt.framework.web.delivecrous.entities.Allergen;
import com.imt.framework.web.delivecrous.entities.Category;
import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Path("dishs")  //sur url, pour accéder aux plats
public class DishResource {
    @Autowired
    private DishRepository dishRepository;

    @GET
    @Produces(value="application/json")
    public List<Dish> getDishs(@QueryParam("maxPrice") Double maxPrice,
                               @QueryParam("selectedCategory") String selectedCategory) {
        if(maxPrice != null)
            return dishRepository.getDishsWithMaxPriceFilter(maxPrice);
        if(selectedCategory != null){
            return dishRepository.getDishsWithSelectedCategory(selectedCategory);
        }
        return dishRepository.findAll();
    }

    @POST
    @Consumes(value="application/json")
    public void createDish(@NotNull @RequestBody Dish dish) throws Exception {
        for(int i=0; i<dish.getIngredientList().size(); i++){
            Category.testCategories(dish.getIngredientList().get(i).getCategories());
            Allergen.testAllergens(dish.getIngredientList().get(i).getAllergenList());
        }
        Category.initDishCategories(dish);
        Allergen.initDishAllergens(dish);
        dishRepository.save(dish);
    }

    @PUT
    @Consumes(value="application/json")
    @Path("/{idDish}")
    public void updateDish(@NotNull @PathParam("idDish") Long id, @RequestBody Dish dish) throws Exception {
        Optional<Dish> searchDish = dishRepository.findById(id);
        if(searchDish.isPresent()){
            Dish dishToUpdate = searchDish.get();
            dishToUpdate.setTitle(dish.getTitle());
            dishToUpdate.setDescription(dish.getDescription());
            dishToUpdate.setPrice(dish.getPrice());
            dishToUpdate.setImagePath(dish.getImagePath());
            dishToUpdate.setIngredientList(dish.getIngredientList());
            //need to be after ingredients update
            Category.initDishCategories(dishToUpdate);
            Allergen.initDishAllergens(dishToUpdate);
            dishRepository.save(dishToUpdate);
        } else
            throw new Exception("Dish inconnu");
    }

    @DELETE
    @Path("/{idDish}")
    public void deleteDish(@NotNull @PathParam("idDish") Long idDish){
        dishRepository.deleteById(idDish);
    }
}

/*
DROP TABLE DISH_INGREDIENT_LIST;
DROP TABLE INGREDIENT;
DROP TABLE COMPOSE
DROP TABLE FAVORIS
DROP TABLE USERS
DROP TABLE DISH;


http://localhost:8080/DelivCROUS/dishs
http://localhost:8080/h2
 */