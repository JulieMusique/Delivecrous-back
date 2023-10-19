package com.imt.framework.web.delivecrous.dto;

import com.imt.framework.web.delivecrous.entities.Dish;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 8 sept. 2023
 */
public class ComposeDto {
	private Dish dish;
	private Integer quantity;
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
		return "ComposeDto [dish=" + dish + ", quantity=" + quantity + "]";
	}
}
