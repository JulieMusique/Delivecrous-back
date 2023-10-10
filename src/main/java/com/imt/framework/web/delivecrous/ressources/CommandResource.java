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
import jakarta.ws.rs.core.Response;

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
	public Command createCommand(@NotNull @RequestBody CreateCommandDto command) throws Exception {
		
		double totalAmount = 0;
		Command newCommand = new Command();
		List<User> users = userRepository.getUserWithIdFilter(command.getIdUser());
		System.out.println(users);
		if(users.size() != 0) {
			newCommand.setIdUser(users.get(0));
		}else{
			return null;
		}
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
		return commandRepository.save(newCommand);
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
	
	/*@PUT
	@Path("/{idCommand}/add/{idDish}")
	public Command addDishToCommand(@NotNull @PathParam("idCommand") Long idCommand, @PathParam("idDish") Long idDish) {
		Command command = commandRepository.findById(idCommand).get();
		if(command == null) {
			return null;
		}
		Dish dish = dishRepository.findById(idDish).get();
		if(dish == null) {
			return null;
		}
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
		composeRepository.save(compose);
		return commandRepository.save(command);
		
	}*/
	
	@PUT
	@Path("/{idCommand}/add/{idDish}")
	public Response addDishToCommand(
	    @NotNull @PathParam("idCommand") Long idCommand,
	    @PathParam("idDish") Long idDish) {

	    try {
	        // Retrieve the Command object
	        Command command = commandRepository.findById(idCommand).orElse(null);

	        // Check if the Command object was found
	        if (command == null) {
	            return Response.status(Response.Status.NOT_FOUND)
	                .entity("Command not found")
	                .build();
	        }

	        // Retrieve the Dish object
	        Dish dish = dishRepository.findById(idDish).orElse(null);

	        // Check if the Dish object was found
	        if (dish == null) {
	            return Response.status(Response.Status.NOT_FOUND)
	                .entity("Dish not found")
	                .build();
	        }

	        // Rest of your code for composition and saving entities...
	        QuantityDishKey key = new QuantityDishKey(command.getIdCommand(), dish.getIdDish());
	        System.out.println(key);
			Compose compose = composeRepository.findByKey(idCommand, idDish);
			System.out.println(compose);
			if( compose != null) {
				compose.setQuantity(compose.getQuantity() + 1);
			}else {
				compose = new Compose();
				compose.setId(key);
				compose.setDish(dish);
				compose.setCommand(command);
				compose.setQuantity(1);
			}
			composeRepository.save(compose);
			commandRepository.save(command);
	        // Return a success response with the updated Command
	        return Response.ok(command).build();
	    } catch (Exception e) {
	        // Handle other exceptions (e.g., database errors)
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	            .entity("An error occurred")
	            .build();
	    }
	}

	
	@GET
	@Produces(value = "application/json")
	@Path("/history/{idUser}")
	public List<Command> getHistory( @PathParam("idUser") Long idUser) {
		return commandRepository.findHistoryCommands(idUser, Date.valueOf(LocalDate.now()));
	}
	
	@GET
	@Produces(value = "application/json")
	@Path("/notOrdered/{idUser}")
	public List<Command> getCommandsNotOrdered( @PathParam("idUser") Long idUser) {
		return commandRepository.findCommandsNotOrdered(idUser);
	}
	
	@GET
	@Produces(value = "application/json")
	@Path("/delivered/{idUser}")
	public List<Command> getCommandsDelivered( @PathParam("idUser") Long idUser) {
		return commandRepository.findDeliveredCommands(idUser, Date.valueOf(LocalDate.now()));
	}
	
	@DELETE
	@Path("/{idCommand}/remove/{idDish}")
	public Command removeDishFromCommand(@NotNull @PathParam("idCommand") Long idCommand, @PathParam("idDish") Long idDish) {
		Command command = commandRepository.findById(idCommand).get();
		if(command == null) {
			return null;
		}
		Compose compose = composeRepository.findByKey(idCommand, idDish);
		if( compose != null) {
			if(compose.getQuantity() == 1) {
				composeRepository.delete(compose);
			}else {				
				compose.setQuantity(compose.getQuantity() - 1);
				composeRepository.save(compose);
			}
		}
		return commandRepository.save(command);
	}
	
	@DELETE
	@Path("/{id}")
	public Command deleteCommand(@NotNull @PathParam("id") Long id) {
		System.out.println(id);
		//supprimer dans compose
		Command command = commandRepository.findById(id).get();
		if(command == null) {
			return null;
		}
		for(Compose dish : command.getComposeItems()) {
			composeRepository.deleteById(dish.getId());
		}
		commandRepository.deleteById(id);
		return command;
	}
}
