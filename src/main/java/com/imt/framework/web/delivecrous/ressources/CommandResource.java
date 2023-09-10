/**
 * 
 */
package com.imt.framework.web.delivecrous.ressources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestBody;

import com.imt.framework.web.delivecrous.entities.Allergen;
import com.imt.framework.web.delivecrous.entities.Category;
import com.imt.framework.web.delivecrous.entities.Command;
import com.imt.framework.web.delivecrous.entities.Compose;
import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Ingredient;
import com.imt.framework.web.delivecrous.entities.QuantityDishKey;
import com.imt.framework.web.delivecrous.entities.Users;
import com.imt.framework.web.delivecrous.repositories.CommandRepository;
import com.imt.framework.web.delivecrous.repositories.ComposeRepository;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.repositories.UserRepository;

import dto.ComposeDto;
import dto.CreateCommandDto;
import dto.DishDto;
import dto.IngredientDto;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 30 août 2023
 *  * Cette ressource comporte les méthodes exposées sur une entité.
 * Ce service va permettre de récupérer tous les livres que la bibliothèque contient.
 */
@Path("commands")
public class CommandResource {
	@Autowired
	private CommandRepository commandRepository;

	@Autowired
	private ComposeRepository composeRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GET
	@Produces(value = "application/json")
	public List<Command> getCommands(){
		return commandRepository.findAll();
	}
	
	@POST
	@Consumes(value= "application/json")
	public void createCommand(@NotNull @RequestBody CreateCommandDto command) throws Exception {
		System.out.println(command);
		double totalAmount = 0;
		Command newCommand = new Command();
		Users user = userRepository.getUserWithIdFilter(command.getIdUser());
		if( user != null) {
			newCommand.setIdUser(user);
		}else {
			//renvoyer pour créer un utilisateur 
			// ou ne pas autoriser de mettre dans le panier
			// ou mettre un utilisateur anonyme par défaut
		}
		newCommand.setIdUser(user);
	    newCommand.setOrderDate(command.getOrderDate());
        newCommand.setDeliveryAdress(command.getDeliveryAdress());
        newCommand.setDueDate(command.getDueDate());
        newCommand.setOrderStatus(command.getOrderStatus());
        newCommand.setDuration(command.getDuration());
        commandRepository.save(newCommand);
        System.out.println(newCommand);
		for (ComposeDto composeItem : command.getComposeItems()) {
			System.out.println(composeItem);
            Compose composeEntry = new Compose();
            /*Dish dish = dishRepository.existsTitle(composeItem.getDish().getTitle());
            System.out.println(dish);
            System.out.println(newCommand.getIdCommand());
            if( dish != null) {
            	QuantityDishKey key = new QuantityDishKey(newCommand.getIdCommand(), dish.getIdDish());
            	composeEntry.setId(key);
            	composeEntry.setDish(dish);
            }else {
            	dish = new Dish();
            	DishDto dishDto = composeItem.getDish();
                dish.setTitle(dishDto.getTitle());
                dish.setDescription(dishDto.getDescription());
                dish.setCategories(dishDto.getCategories());
                dish.setPrice(dishDto.getPrice());
                dish.setImage(dishDto.getImage());

                // Vous devez également convertir la liste des ingrédients
                List<Ingredient> ingredients = new ArrayList<>();
                for (IngredientDto ingredientDto : dishDto.getIngredientList()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(ingredientDto.getName());
                    ingredient.setCalorie(ingredientDto.getCalorie());
                    // Autres attributs de Ingredient
                    ingredients.add(ingredient);
                }
                dish.setIngredientList(ingredients);*/
            	Dish dish = composeItem.getDish();
            	composeEntry.setDish(dish);
            	dish = composeEntry.getDish();
                for(int i=0; i< dish.getIngredientList().size(); i++){
                    Category.testCategories(dish.getIngredientList().get(i).getCategories());
                    Allergen.testAllergens(dish.getIngredientList().get(i).getAllergenList());
                }
                Category.initDishCategories(dish);
                Allergen.initDishAllergens(dish);
            	dishRepository.save(dish);
            	QuantityDishKey key = new QuantityDishKey(newCommand.getIdCommand(), dish.getIdDish());
            	composeEntry.setId(key);
            	composeEntry.setDish(dish);
            //}
            composeEntry.setCommand(newCommand);
            composeEntry.setQuantity(composeItem.getQuantity());
            totalAmount += composeEntry.getDish().getPrice() * composeEntry.getQuantity();
            // Sauvegarder chaque entrée Compose dans la base de données
            System.out.println(composeEntry);
            composeRepository.save(composeEntry);
        }
		newCommand.setTotalAmount(totalAmount);
		commandRepository.save(newCommand);
	}
	
	@PUT
	@Consumes(value="application/json")
	@Path("/{id}")
	public void updateCommand(@NotNull @PathParam("id") Long id, @RequestBody Command command) throws Exception{
		Optional<Command> searchedCommand = commandRepository.findById(id);
		if(!searchedCommand.isEmpty()) {
			Command commandToUpdate = searchedCommand.get();
			commandToUpdate.setIdUser(command.getIdUser());
			commandToUpdate.setOrderDate(command.getOrderDate());
			commandToUpdate.setDeliveryAdress(command.getDeliveryAdress());
			commandToUpdate.setDuration(command.getDuration());
			commandToUpdate.setOrderStatus(command.getOrderStatus());
			commandToUpdate.setTotalAmount(command.getTotalAmount());
			commandRepository.save(commandToUpdate);
		}
	}
	
	@PUT
	@Path("/{idCommand}/add/{idDish}")
	public void addDishToCommand(@NotNull @PathParam("idCommand") Long idCommand, @PathParam("idDish") Long idDish) {
		Command command = commandRepository.findById(idCommand).get();
		Dish dish = dishRepository.findById(idDish).get();
		QuantityDishKey key = new QuantityDishKey(command.getIdCommand(), dish.getIdDish());
		Compose compose = composeRepository.findByKey(idCommand, idDish);
		if( compose != null) {
			compose.setQuantity(compose.getQuantity() + 1);
		}else {
			compose = new Compose();
			compose.setId(key);
			compose.setDish(dish);
			compose.setCommand(command);
			compose.setQuantity(1);
		}
		commandRepository.save(command);
		composeRepository.save(compose);
	}
	
	@DELETE
	@Path("/{idCommand}/remove/{idDish}")
	public void removeDishFromCommand(@NotNull @PathParam("idCommand") Long idCommand, @PathParam("idDish") Long idDish) {
		Command command = commandRepository.findById(idCommand).get();
		Compose compose = composeRepository.findByKey(idCommand, idDish);
		if( compose != null) {
			if(compose.getQuantity() == 1) {
				composeRepository.delete(compose);
			}else {				
				compose.setQuantity(compose.getQuantity() - 1);
				composeRepository.save(compose);
			}
		}
		commandRepository.save(command);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteBook(@NotNull @PathParam("id") Long id) {
		//supprimer dans compose
		commandRepository.deleteById(id);
	}
}
