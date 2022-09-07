package fr.eni.ventesauxencheres.bo.encheres;

public class Categorie {

	// Attributes
	private int no_categorie;
	private String designation;
	// Association attributes
	private Categorie parent;

	// Constructors
	public Categorie() {}

	public Categorie(int no_categorie, String designation) {
		this.no_categorie = no_categorie;
		this.designation = designation;
	}

	public Categorie(String designation) {
		this.designation = designation;
	}

	// Getters and Setters
	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
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
