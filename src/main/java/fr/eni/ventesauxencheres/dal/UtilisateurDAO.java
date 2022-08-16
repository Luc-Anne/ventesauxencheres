package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	Utilisateur connexion(String email, String motDePasse) throws DALException;
}
