package fr.eni.ventesauxencheres.controllers;

import java.util.ArrayList;
import java.util.List;

public class Errors {

	List<String> erreurs;
	
	public Errors() {
		erreurs = new ArrayList<>();
	}
	
	public void add(String erreur) {
		erreurs.add(erreur);
	}
	
	public void addAll(List<String> listErreurs) {
		for (String erreur : listErreurs) {
			this.add(erreur);
		}
	}
	
	public boolean contains(String typeErreur) {
		return erreurs.contains(typeErreur);
	}
	
	public List<String> getErreurs() {
		return erreurs;
	}
	
}
