/**
 * 
 */
package com.imt.framework.web.delivecrous;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.imt.framework.web.delivecrous.ressources.LivreResource;

import jakarta.ws.rs.ApplicationPath;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 29 août 2023
 * Le but de ce fichier et de référencer les classes dont les méthodes vont être exposées sur le serveur embarqué
 * Ce code va être exécuté au démarrage de l'application
 */
@Component
@ApplicationPath("demo")
@Configuration
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		register(LivreResource.class);
	}

}
