package com.imt.framework.web.delivecrous.ressources;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Favoris;
import com.imt.framework.web.delivecrous.entities.User;
import com.imt.framework.web.delivecrous.repositories.DishRepository;
import com.imt.framework.web.delivecrous.repositories.FavorisRepository;
import com.imt.framework.web.delivecrous.repositories.UserRepository;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Path("favoris")
public class FavorisResource {
	@Autowired
	private FavorisRepository favorisRepository;
	
	public void setFavorisRepository(FavorisRepository favorisRepository) {
    	this.favorisRepository = favorisRepository;
    }

	@Autowired
	private UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

	@Autowired
	private DishRepository dishRepository;
	
	public void setDishRepository(DishRepository dishRepository) {
    	this.dishRepository = dishRepository;
    }
	
	@GET
	@Path("/{userId}")
	@Produces("application/json")
	public List<Dish> getFavorises(@PathParam("userId") Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			List<Favoris> favorises = favorisRepository.findByUser(user);

			// Créez une liste pour stocker les plats extraits des objets Favoris
			List<Dish> favoriteDishes = new ArrayList<>();

			// Parcourez la liste des objets Favoris et extrayez les plats
			for (Favoris favoris : favorises) {
				favoriteDishes.add(favoris.getPlat());
			}

			return favoriteDishes;
		} else {
			throw new NotFoundException("Utilisateur introuvable");
		}
	}
	
	@GET
	@Path("check/{userId}/{dishId}")
	@Produces("application/json")
	public Boolean getFavorisesexistants(@PathParam("userId") Long userId, @PathParam("dishId") Long dishId) {
		// Recherchez l'utilisateur par son ID
		User user = userRepository.findById(userId).orElse(null);

		// Recherchez le plat par son ID
		Dish dish = dishRepository.findById(dishId).orElse(null);

		if (user != null && dish != null) {
			Favoris favorisdeja = favorisRepository.findByUserAndPlat(user, dish);

			if (favorisdeja != null) {

				return true;}
			else{
				return false;
			}
		} else {
			throw new NotFoundException("Utilisateur introuvable");
		}
	}
	
	@POST
	@Path("/{userId}/{dishId}")
	public void addFavoris(@PathParam("userId") Long userId, @PathParam("dishId") Long dishId) {
		User user = userRepository.findById(userId).orElse(null);
		Dish dish = dishRepository.findById(dishId).orElse(null);

		if (user != null && dish != null) {

			Favoris favoris = new Favoris();
			favoris.setUser(user);
			favoris.setPlat(dish);
			favorisRepository.save(favoris);
		} else {
			throw new NotFoundException("Utilisateur ou plat introuvable");
		}
	}

	@DELETE
	@Path("/{userId}/{dishId}")
	public void removeFavoris(@PathParam("userId") Long userId, @PathParam("dishId") Long dishId) {
		// Recherchez l'utilisateur par son ID
		User user = userRepository.findById(userId).orElse(null);

		// Recherchez le plat par son ID
		Dish dish = dishRepository.findById(dishId).orElse(null);

		if (user != null && dish != null) {
			// Recherchez l'entrée de favoris correspondant à l'utilisateur et au plat
			Favoris favorisToDelete = favorisRepository.findByUserAndPlat(user, dish);

			if (favorisToDelete != null) {
				// Supprimez l'entrée de favoris
				favorisRepository.delete(favorisToDelete);
			} else {
				throw new NotFoundException("Entrée de favoris introuvable pour cet utilisateur et ce plat");
			}
		} else {
			throw new NotFoundException("Utilisateur ou plat introuvable");
		}
	}





}