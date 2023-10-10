/**
 * 
 */
package com.imt.framework.web.delivecrous.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imt.framework.web.delivecrous.entities.Compose;
import com.imt.framework.web.delivecrous.entities.QuantityDishKey;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 4 sept. 2023
 */
@Repository
public interface ComposeRepository extends JpaRepository<Compose, QuantityDishKey>{
	@Query("select c from Compose c where c.id.idCommand = :idCommand and c.id.idDish = :idDish")
    Compose findByKey(@Param("idCommand") Long idCommand, @Param("idDish") Long idDish);
	
}

