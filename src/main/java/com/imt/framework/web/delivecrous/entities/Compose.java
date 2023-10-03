/**
 * 
 */
package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 30 août 2023
 */
@Entity
@Table(name = "Compose")
public class Compose {
	
	@EmbeddedId
	private QuantityDishKey id;
	
	@ManyToOne
	@MapsId("idDish") // signifie que nous lions ces champs à une partie de la clé, et ce sont les clés étrangères d'une relation plusieurs-à-un
	@JoinColumn(name = "idDish")
	private Dish dish;
 
    @ManyToOne
    @MapsId("idCommand")
    @JoinColumn(name = "idCommand")
    private Command command;
    
	private Integer quantity;
	
	/**
	 * @param dish
	 * @param command
	 * @param quantity
	 */
	public Compose(Dish dish, Command command, Integer quantity) {
		this.dish = dish;
		this.command = command;
		this.quantity = quantity;
	}
	
	public Compose() {}
	
	
	/**
	 * @return the id
	 */
	public QuantityDishKey getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(QuantityDishKey id) {
		this.id = id;
	}

	/**
	 * @return the dish
	 */
	public Dish getDish() {
		return dish;
	}
	/**
	 * @param dish the dish to set
	 */
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Comporter [id=" + id + ", dish=" + dish + ", command=" + command + ", quantity=" + quantity + "]";
	}	
}
