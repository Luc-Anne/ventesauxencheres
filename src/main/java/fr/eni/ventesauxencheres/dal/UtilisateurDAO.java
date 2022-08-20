package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur>{
	Utilisateur selectUtilisateurConnecte(String email, String motDePasse) throws DALException;
}
