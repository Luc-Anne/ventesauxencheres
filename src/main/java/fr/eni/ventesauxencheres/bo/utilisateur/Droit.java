package fr.eni.ventesauxencheres.bo.utilisateur;

public class Droit {

	// Attributes
	private int noDroit;
	private String designation;
	// Association attributes

	// Constructors
	public Droit() {}

	public Droit(int noDroit, String designation) {
		this.noDroit = noDroit;
		this.designation = designation;
	}

	// Getters and Setters
	public int getNoDroit() {
		return noDroit;
	}

	public void setNoDroit(int noDroit) {
		this.noDroit = noDroit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	// Other methods

}
