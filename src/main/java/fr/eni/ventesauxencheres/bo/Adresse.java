package fr.eni.ventesauxencheres.bo;

public class Adresse {

	// Attributes
	private int no_adresse;
	private String rue;
	private String codePostal;
	private int ville;
	private String pays;
	// Association attributes

	// Constructors
	public Adresse() {}

	public Adresse(int no_adresse, String rue, String codePostal, int ville ,String pays) {
		this.no_adresse = no_adresse;
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
	public int getNo_adresse() {
		return no_adresse;
	}

	public void setNo_adresse(int no_adresse) {
		this.no_adresse = no_adresse;
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
