/**
 * 
 */
package com.imt.framework.web.delivecrous.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 1 sept. 2023
 */
@Embeddable
public class QuantityDishKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="idDish")
	private Long idDish;
	
	@Column(name="idCommand")
	private Long idCommand;
	
	public QuantityDishKey() {
	}

	public QuantityDishKey(Long idCommand, Long idDish) {
		System.out.println(idCommand);
		System.out.println(idDish);
		this.idCommand = idCommand;
		this.idDish = idDish;
	}

	/**
	 * @return the commandId
	 */
	public Long getIdCommand() {
		return idCommand;
	}

	/**
	 * @param commandId the commandId to set
	 */
	public void setIdCommand(Long idCommand) {
		this.idCommand = idCommand;
	}

	/**
	 * @return the dishId
	 */
	public Long getIdDish() {
		return idDish;
	}

	/**
	 * @param dishId the dishId to set
	 */
	public void setIdDish(Long dishId) {
		this.idDish = dishId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCommand, idDish);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuantityDishKey other = (QuantityDishKey) obj;
		return Objects.equals(idCommand, other.idCommand) && Objects.equals(idDish, other.idDish);
	}

	@Override
	public String toString() {
		return "QuantityDishKey [commandId=" + idCommand + ", dishId=" + idDish + "]";
	}
}
