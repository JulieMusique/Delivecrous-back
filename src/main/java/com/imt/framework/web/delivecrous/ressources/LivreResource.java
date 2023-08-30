/**
 * 
 */
package com.imt.framework.web.delivecrous.ressources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.imt.framework.web.delivecrous.entities.Livre;
import com.imt.framework.web.delivecrous.repositories.LivreRepository;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 29 août 2023
 * Cette ressource comporte les méthodes exposées sur une entité.
 * Ce service va permettre de récupérer tous les livres que la bibliothèque contient.
 */
@Path("books")
public class LivreResource {

	@Autowired
	private LivreRepository livreRepository;
	
	@GET
	@Produces(value = "application/json")
	public List<Livre> getBooks(@QueryParam("maxPrice") Double maxPrice){
		if(maxPrice != null) {
			return livreRepository.getBooksWithMaxPriceFilter(maxPrice);
		}
		return livreRepository.findAll();
	}
	
	@POST
	@Consumes(value= "application/json")
	public void createBook(@NotNull @RequestBody Livre livre) {
		livreRepository.save(livre);
	}
	
	@PUT
	@Consumes(value="application/json")
	@Path("/{id}")
	public void updateBook(@NotNull @PathParam("id") Long id, @RequestBody Livre livre) throws Exception{
		Optional<Livre> searchedBook = livreRepository.findById(id);
		if(!searchedBook.isEmpty()) {
			Livre bookToUpdate = searchedBook.get();
			bookToUpdate.setAuthor(livre.getAuthor());
			bookToUpdate.setPrice(livre.getPrice());
			bookToUpdate.setNbPages(livre.getNbPages());
			bookToUpdate.setTitre(livre.getTitre());
			livreRepository.save(bookToUpdate);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteBook(@NotNull @PathParam("id") Long id) {
		livreRepository.deleteById(id);
	}
}
