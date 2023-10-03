/**
 * 
 */
package com.imt.framework.web.delivecrous.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imt.framework.web.delivecrous.entities.Command;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 30 août 2023
 * * Le but de ce fichier est de manipuler le CRUD sur notre ressource
 * La classe Component permet de pouvoir faire de l'injection de dépendance dessus
 */

public interface CommandRepository extends JpaRepository<Command, Long>{
	@Query("select c from Command c where c.idUser = :idUser")
    List<Command> findCommandsByidUser(@Param("idUser") Long idUser);
	@Query("select c from Command c where c.dueDate < :currentDate")
	List<Command> findHistoryCommands(@Param("currentDate") Date currentDate);
	@Query("select c from Command c where c.dueDate = :currentDate")
	List<Command> findDeliveredCommands(@Param("currentDate") Date currentDate);
	@Query("select c from Command c where c.orderDate is null")
	List<Command> findCommandsNotOrdered();
	/*@Query("select c from Command c where c.idCommand = :idCommand")
	Command findById(@Param("idUser") Long idCommand);*/
}
