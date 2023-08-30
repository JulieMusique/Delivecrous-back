package com.imt.framework.web.delivecrous.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.imt.framework.web.delivecrous.entities.Livre;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 29 août 2023
 * Le but de ce fichier est de manipuler le CRUD sur notre ressource
 * La classe Component permet de pouvoir faire de l'injection de dépendance dessus
 */
@Component
public interface LivreRepository extends JpaRepository<Livre, Long> {

	@Query("select l from Livre l where l.price < :maxPrice")
	List<Livre> getBooksWithMaxPriceFilter(@Param("maxPrice") Double maxPrice);
}
