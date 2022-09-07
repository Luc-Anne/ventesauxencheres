package fr.eni.ventesauxencheres.bo.utilisateur;

import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.Adresse;

public class Client extends Profil {

	// Attributes
	private int noClient;
	private String nom;
	private String prenom;
	private boolean actif;
	private float credit;
	private String telephone;
	// Association attributes
	private Adresse adresseDomicile;

	// Constructors
	public Client() {}

	public Client(int noProfil, String pseudo, String courriel, LocalDateTime dateEnregistrement,
			int noClient, String nom, String prenom, boolean actif, float credit , String telephone) {
		super(noProfil, pseudo, courriel, dateEnregistrement);
		this.noClient = noClient;
		this.nom = nom;
		this.prenom = prenom;
		this.actif = actif;
		this.credit = credit;
		this.telephone = telephone;
	}

	public Client(String pseudo, String courriel,
			String nom, String prenom, float credit , String telephone) {
		super(pseudo, courriel);
		this.nom = nom;
		this.prenom = prenom;
		this.credit = credit;
		this.telephone = telephone;
	}

	// Getters and Setters
	public int getNoClient() {
		return noClient;
	}

	public void setNoClient(int noClient) {
		this.noClient = noClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Adresse getAdresseDomicile() {
		return adresseDomicile;
	}

	public void setAdresseDomicile(Adresse adresseDomicile) {
		this.adresseDomicile = adresseDomicile;
	}

	// Other methods
	public boolean equals(Client client) {
		return (client != null && this.getNoClient() == client.getNoClient());
	}

}
