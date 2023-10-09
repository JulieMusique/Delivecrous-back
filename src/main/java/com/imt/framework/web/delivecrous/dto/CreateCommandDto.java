/**
 * 
 */
package com.imt.framework.web.delivecrous.dto;

import java.sql.Date;
import java.util.List;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 8 sept. 2023
 */
public class CreateCommandDto {
	private Long idUser;
	private Date orderDate; // Date de la commande
	private String deliveryAdress; // Adresse de livraison
	private Date dueDate; // Date et heure attendues
	private String orderStatus; // Statut de la commande)
	private Integer duration; // Durée restante (minutes)
	private Double totalAmount; // Montant total de la commande
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
	public CreateCommandDto(Long idUser, Date orderDate, String deliveryAdress, Date dueDate, String orderStatus,
			Integer duration, Double totalAmount) {
		this.idUser = idUser;
		this.orderDate = orderDate;
		this.deliveryAdress = deliveryAdress;
		this.dueDate = dueDate;
		this.orderStatus = orderStatus;
		this.duration = duration;
		this.totalAmount = totalAmount;
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
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
		return "CreateCommandDto [idUser=" + idUser + ", orderDate=" + orderDate + ", deliveryAdress=" + deliveryAdress
				+ ", dueDate=" + dueDate + ", orderStatus=" + orderStatus + ", duration=" + duration + ", totalAmount="
				+ totalAmount + ", composeItems=" + composeItems + "]";
	}
}
