/**
 * 
 */
package com.imt.framework.web.delivecrous.ressources;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.imt.framework.web.delivecrous.dto.ComposeDto;
import com.imt.framework.web.delivecrous.dto.CreateCommandDto;
import com.imt.framework.web.delivecrous.entities.Allergen;
import com.imt.framework.web.delivecrous.entities.Category;
import com.imt.framework.web.delivecrous.entities.Command;
import com.imt.framework.web.delivecrous.entities.Compose;
import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.QuantityDishKey;
import com.imt.framework.web.delivecrous.entities.User;
import com.imt.framework.web.delivecrous.repositories.CommandRepository;
import com.imt.framework.web.delivecrous.repositories.ComposeRepository;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.repositories.UserRepository;

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
	public String createCommand(@NotNull @RequestBody CreateCommandDto command) throws Exception {
		
		double totalAmount = 0;
		Command newCommand = new Command();
		User user = (User) userRepository.getUserWithIdFilter(command.getIdUser());
		
		if( user != null) {
			newCommand.setIdUser(user);
		}
		newCommand.setIdUser(user);
	    newCommand.setOrderDate(command.getOrderDate());
        newCommand.setDeliveryAdress(command.getDeliveryAdress());
        newCommand.setDueDate(command.getDueDate());
        newCommand.setOrderStatus(command.getOrderStatus());
        newCommand.setDuration(command.getDuration());
        commandRepository.save(newCommand);
  
		for (ComposeDto composeItem : command.getComposeItems()) {
            Compose composeEntry = new Compose();
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
            composeEntry.setCommand(newCommand);
            composeEntry.setQuantity(composeItem.getQuantity());
            totalAmount += composeEntry.getDish().getPrice() * composeEntry.getQuantity();
            // Sauvegarder chaque entrée Compose dans la base de données
            composeRepository.save(composeEntry);
        }
		newCommand.setTotalAmount(totalAmount);
		commandRepository.save(newCommand);
		return "La commande a bien été créée";
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
	
	@GET
	@Produces(value = "application/json")
	@Path("/history")
	public List<Command> getHistory() {
		return commandRepository.findHistoryCommands(Date.valueOf(LocalDate.now()));
	}
	
	@GET
	@Produces(value = "application/json")
	@Path("/notOrdered")
	public List<Command> getCommandsNotOrdered() {
		return commandRepository.findCommandsNotOrdered();
	}
	
	@GET
	@Produces(value = "application/json")
	@Path("/delivered")
	public List<Command> getCommandsDelivered() {
		return commandRepository.findDeliveredCommands(Date.valueOf(LocalDate.now()));
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
	public void deleteCommand(@NotNull @PathParam("id") Long id) {
		System.out.println(id);
		//supprimer dans compose
		Command command = commandRepository.findById(id).get();
		for(Compose dish : command.getComposeItems()) {
			composeRepository.deleteById(dish.getId());
		}
		commandRepository.deleteById(id);
	}
}
