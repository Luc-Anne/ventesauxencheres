package fr.eni.ventesauxencheres.bo;

public class Adresse {

	// Attributes
	private int noAdresse;
	private String rue;
	private String codePostal;
	private int ville;
	private String pays;
	// Association attributes

	// Constructors
	public Adresse() {}

	public Adresse(int noAdresse, String rue, String codePostal, int ville ,String pays) {
		this.noAdresse = noAdresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	public Adresse(String rue, String codePostal, int ville ,String pays) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	// Getters and Setters
	public int getNoAdresse() {
		return noAdresse;
	}

	public void setNoAdresse(int noAdresse) {
		this.noAdresse = noAdresse;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public int getVille() {
		return ville;
	}

	public void setVille(int ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	// Other methods

}
