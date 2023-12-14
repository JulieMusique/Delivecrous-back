package com.imt.framework.web.delivecrous;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.Collection;
import java.util.List;

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

import com.imt.framework.web.delivecrous.entities.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TestIntUser{

	@Autowired
	private TestRestTemplate restTemplate;
	private static final String URL = "http://localhost:8080/DelivCROUS";// url du serveur REST. Cet url peut être celle d'un serveur distant

	private String getURLWithPort(String uri) {
		return URL + uri;
	}
    
	@Test
	public void testFindAllUsers() throws Exception {

		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity(getURLWithPort("/users"), Object.class);
		assertNotNull(responseEntity);
		@SuppressWarnings("unchecked")
		Collection<User> userCollections = (Collection<User>) responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(userCollections);
		assertEquals(4, userCollections.size());
	}
	
	@Test
	public void testSaveUser() throws Exception {
	    // Préparez les données de l'utilisateur
	    User user = new User();
	    user.setLastName("Potter");
	    user.setFirstName("Harry");
	    user.setEmail("harry.potter2@gmail.com");
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
	public void testDeleteUser() throws Exception {
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
	    			  .filter(customer -> "harry.potter2@gmail.com".equals(customer.getEmail()))
	    			  .findAny()
	    			  .orElse(null);
	    	Long idUser = user.getId();
	        URI uri = UriComponentsBuilder.fromHttpUrl(URL)
	                .path("/users/{id}")
	                .buildAndExpand(idUser)
	                .toUri();

	        System.out.println(uri.toString());
	        ResponseEntity<Void> responseEntity2 = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);
	        assertNotNull(responseEntity2);
	        assertEquals(HttpStatus.NO_CONTENT, responseEntity2.getStatusCode());
	    }
	}

}
