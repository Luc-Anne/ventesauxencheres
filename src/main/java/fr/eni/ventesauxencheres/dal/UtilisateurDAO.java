package fr.eni.ventesauxencheres.dal;

import fr.eni.ventesauxencheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	Utilisateur connexion(String email, String motDePasse) throws DALException;
	Utilisateur selectUtilisateurConnecte(String email, String motDePasse) throws DALException;
	Utilisateur selectByPseudo(String speudo) throws DALException;
}
