/**
 * 
 */
package com.imt.framework.web.delivecrous.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 30 août 2023
 */

/*userId	Long	ID de l'utilisateur
orderDate	Date	Date de la commande
deliveryAddress	String	Adresse de livraison
totalAmount	Double	Montant total de la commande
dueDate	Date	Date et heure attendues
orderStatus	String	Statut de la commande
duration	Integer	Durée restante (minutes)*/
@Entity
@Table(name="Command")
public class Command {
	@Id
	@GeneratedValue
	private Long idCommand;
	@ManyToOne
    @JoinColumn
	private User idUser;
	//@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate; // Date de la commande
	private String deliveryAdress; // Adresse de livraison
	private Date dueDate; // Date et heure attendues
	private String orderStatus; // Statut de la commande)
	private Integer duration; // Durée restante (minutes)
	private Double totalAmount; // Montant total de la commande
	
	@OneToMany(mappedBy = "command", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("command")
	private List<Compose> composeItems;
	
	public Command() {
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
	 * @return the idCommand
	 */
	public Long getIdCommand() {
		return idCommand;
	}

	/**
	 * @param idCommand the idCommand to set
	 */
	public void setIdCommand(Long idCommand) {
		this.idCommand = idCommand;
	}

	/**
	 * @return the idUser
	 */
	public User getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the composeItems
	 */
	public List<Compose> getComposeItems() {
		return composeItems;
	}

	/**
	 * @param composeItems the composeItems to set
	 */
	public void setComposeItems(List<Compose> composeItems) {
		this.composeItems = composeItems;
	}

	@Override
	public String toString() {
		return "Command [idCommand=" + idCommand + ", idUser=" + idUser + ", orderDate=" + orderDate
				+ ", deliveryAdress=" + deliveryAdress + ", dueDate=" + dueDate + ", orderStatus=" + orderStatus
				+ ", duration=" + duration + ", totalAmount=" + totalAmount + "]";
	}
	
}
