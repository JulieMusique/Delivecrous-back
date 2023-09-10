/**
 * 
 */
package dto;

import java.util.List;

/**
 * @author julie.catteau@etu.imt-nord-europe.fr
 * @date 8 sept. 2023
 */
public class IngredientDto {
	private String name;
    private Integer calorie;
    private List<String> categories;
    private List<String> allergenList;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the calorie
	 */
	public Integer getCalorie() {
		return calorie;
	}
	/**
	 * @param calorie the calorie to set
	 */
	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}
	/**
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	/**
	 * @return the allergenList
	 */
	public List<String> getAllergenList() {
		return allergenList;
	}
	/**
	 * @param allergenList the allergenList to set
	 */
	public void setAllergenList(List<String> allergenList) {
		this.allergenList = allergenList;
	}
}
