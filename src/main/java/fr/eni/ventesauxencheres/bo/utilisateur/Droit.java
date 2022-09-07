package fr.eni.ventesauxencheres.bo.utilisateur;

public class Droit {

	// Attributes
	private int no_droit;
	private String designation;
	// Association attributes

	// Constructors
	public Droit() {}

	public Droit(int no_droit, String designation) {
		this.no_droit = no_droit;
		this.designation = designation;
	}

	// Getters and Setters
	public int getNo_droit() {
		return no_droit;
	}

	public void setNo_droit(int no_droit) {
		this.no_droit = no_droit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	// Other methods

}
