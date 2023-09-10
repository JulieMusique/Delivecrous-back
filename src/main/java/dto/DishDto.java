/**
 * 
 */
package dto;

import java.util.List;
import java.util.Set;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 8 sept. 2023
 */
public class DishDto {
	private String title;
    private String description;
    private Set<String> categories;
    private Double price;
    private String image;
    private List<IngredientDto> ingredientList;
    private Set<String> allergenList;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the categories
	 */
	public Set<String> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the ingredientList
	 */
	public List<IngredientDto> getIngredientList() {
		return ingredientList;
	}
	/**
	 * @param ingredientList the ingredientList to set
	 */
	public void setIngredientList(List<IngredientDto> ingredientList) {
		this.ingredientList = ingredientList;
	}
	/**
	 * @return the allergenList
	 */
	public Set<String> getAllergenList() {
		return allergenList;
	}
	/**
	 * @param allergenList the allergenList to set
	 */
	public void setAllergenList(Set<String> allergenList) {
		this.allergenList = allergenList;
	}
	@Override
	public String toString() {
		return "DishDto [title=" + title + ", description=" + description + ", categories=" + categories + ", price="
				+ price + ", image=" + image + ", ingredientList=" + ingredientList + ", allergenList=" + allergenList
				+ "]";
	}
}
