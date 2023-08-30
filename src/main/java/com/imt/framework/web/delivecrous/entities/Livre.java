/**
 * 
 */
package com.imt.framework.web.delivecrous.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 29/08/2023
 */
@Entity
public class Livre {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "auteur")
	private String author;
	@Column(name= "titre")
	private String titre;
	private Integer nbPages;
	@Column(name="price")
	private Integer price;
	public String getAuthor() {
		return author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Integer getNbPages() {
		return nbPages;
	}
	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
