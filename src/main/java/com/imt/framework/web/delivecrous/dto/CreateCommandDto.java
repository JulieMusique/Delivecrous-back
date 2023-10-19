/**
 * 
 */
package com.imt.framework.web.delivecrous.dto;

import java.util.List;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 8 sept. 2023
 */
public class CreateCommandDto {
	private Long idUser;
	private String deliveryAdress; // Adresse de livraison
	private List<ComposeDto> composeItems;

    // Constructeurs, getters et setters
	public CreateCommandDto() {}

	/**
	 * @param idUser
	 * @param orderDate
	 * @param deliveryAdress
	 * @param dueDate
	 * @param orderStatus
	 * @param duration
	 * @param totalAmount
	 */
	public CreateCommandDto(Long idUser, String deliveryAdress, List<ComposeDto> composeItems) {
		this.idUser = idUser;
		this.deliveryAdress = deliveryAdress;
		this.composeItems = composeItems;
	}

	/**
	 * @return the idUser
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	/**
	 * @return the deliveryAdress
	 */
	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	/**
	 * @param deliveryAdress the deliveryAdress to set
	 */
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	/**
	 * @return the composeItems
	 */
	public List<ComposeDto> getComposeItems() {
		return composeItems;
	}

	/**
	 * @param composeItems the composeItems to set
	 */
	public void setComposeItems(List<ComposeDto> composeItems) {
		this.composeItems = composeItems;
	}

	@Override
	public String toString() {
		return "CreateCommandDto [idUser=" + idUser + ", deliveryAdress=" + deliveryAdress
				+ ", composeItems=" + composeItems + "]";
	}
}
