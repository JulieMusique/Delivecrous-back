package com.imt.framework.web.delivecrous;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Ingredient;
import com.imt.framework.web.delivecrous.entities.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TestIntFavoris{

	@Autowired
	private TestRestTemplate restTemplate;
	private static final String URL = "http://localhost:8080/DelivCROUS";// url du serveur REST. Cet url peut être celle d'un serveur distant
	private static Long idUser;
	private static Long idDish;
	
	private String getURLWithPort(String uri) {
		return URL + uri;
	}
	
	@Test
	public void testSaveDish() throws Exception {
		Dish dish = new Dish();
        // Execute the createDish method
        dish.setTitle("Burger1");
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créez un objet HttpEntity avec l'objet JSON et les en-têtes
        HttpEntity<Dish> requestEntity = new HttpEntity<>(dish, headers);

        // Envoyez la requête POST avec l'objet HttpEntity
        ResponseEntity<Dish> dishEntitySaved = restTemplate.postForEntity(getURLWithPort("/dishs"), requestEntity, Dish.class);
        assertNotNull(dishEntitySaved);
		// On vérifie le code de réponse HTTP est celui attendu
		assertEquals(HttpStatus.NO_CONTENT, dishEntitySaved.getStatusCode());
	}
	
	@Test
	public void testSaveUser() throws Exception {
	    // Préparez les données de l'utilisateur
	    User user = new User();
	    user.setLastName("Potter");
	    user.setFirstName("Harry");
	    user.setEmail("harry.potter1@gmail.com");
	    user.setPhone("0620152423");
	    user.setAdresse("");
	    user.setLogin("harry");
	    user.setPassword("harrypotter2001");
	    user.setSoldeCarteCrous(100.0);
	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créez un objet HttpEntity avec l'objet JSON et les en-têtes
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Envoyez la requête POST avec l'objet HttpEntity
        ResponseEntity<User> userEntitySaved = restTemplate.postForEntity(getURLWithPort("/users"), requestEntity, User.class);
        assertNotNull(userEntitySaved);
		// On vérifie le code de réponse HTTP est celui attendu
		assertEquals(HttpStatus.CREATED, userEntitySaved.getStatusCode());
	}
	
	@Test
	public void testCreateFavoris() throws Exception {
	    ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
	            getURLWithPort("/users"), 
	            HttpMethod.GET, 
	            null, 
	            new ParameterizedTypeReference<List<User>>() {}
	    );
	    
	    assertNotNull(responseEntity);
	    List<User> userCollections = responseEntity.getBody();
	    
	    if (userCollections != null && !userCollections.isEmpty()) {
	    	User user = userCollections.stream()
	    			  .filter(customer -> "harry.potter1@gmail.com".equals(customer.getEmail()))
	    			  .findAny()
	    			  .orElse(null);
	    	ResponseEntity<List<Dish>> dishresponseEntity = restTemplate.exchange(
		            getURLWithPort("/dishs?searchedTitle=Burger1"), 
		            HttpMethod.GET, 
		            null, 
		            new ParameterizedTypeReference<List<Dish>>() {}
		    );
		    
		    assertNotNull(dishresponseEntity);
		    List<Dish> dishCollections = dishresponseEntity.getBody();
		    idUser = user.getId();
		    if (dishCollections != null && !dishCollections.isEmpty()) {
		    	Dish dish = dishCollections.get(0);
		    	idDish = dish.getIdDish();
		    	URI uri = UriComponentsBuilder.fromHttpUrl(URL)
		                .path("/favoris/{userId}/{dishId}")
		                .buildAndExpand(idUser, idDish)
		                .toUri();
		    	System.out.println(uri.toString());
		        ResponseEntity<Void> responseEntity2 = restTemplate.exchange(uri, HttpMethod.POST, null, Void.class);
		        assertNotNull(responseEntity2);
		        assertEquals(HttpStatus.NO_CONTENT, responseEntity2.getStatusCode());
		    }
	    }
	}
	
	@Test
	public void testDeleteFavoris() throws Exception {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .path("/favoris/{userId}/{dishId}")
                .buildAndExpand(idUser, idDish)
                .toUri();
		ResponseEntity<Void> responseEntity2 = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
		assertNotNull(responseEntity2);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity2.getStatusCode());
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .path("/users/{userId}")
                .buildAndExpand(idUser)
                .toUri();
		ResponseEntity<Void> responseEntity2 = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
		assertNotNull(responseEntity2);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity2.getStatusCode());
	}
	
	@Test
	public void testDeleteDish() throws Exception {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .path("/dishs/{dishId}")
                .buildAndExpand(idDish)
                .toUri();
		ResponseEntity<Void> responseEntity2 = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
		assertNotNull(responseEntity2);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity2.getStatusCode());
	}
	
}
