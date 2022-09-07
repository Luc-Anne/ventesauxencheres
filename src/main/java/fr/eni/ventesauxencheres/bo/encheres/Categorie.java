package fr.eni.ventesauxencheres.bo.encheres;

public class Categorie {

	// Attributes
	private int noCategorie;
	private String designation;
	// Association attributes
	private Categorie parent;

	// Constructors
	public Categorie() {}

	public Categorie(int noCategorie, String designation) {
		this.noCategorie = noCategorie;
		this.designation = designation;
	}

	public Categorie(String designation) {
		this.designation = designation;
	}

	// Getters and Setters
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Categorie getCategorieParent() {
		return parent;
	}

	public void setCategorieParent(Categorie categorieParent) {
		this.parent = categorieParent;
	}

	// Other methods

}
