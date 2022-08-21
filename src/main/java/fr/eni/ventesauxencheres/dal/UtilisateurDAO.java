package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	Utilisateur connexion(String email, String motDePasse) throws DALException;
	public boolean inscription(Utilisateur u) throws DALException ;
	public boolean deleteById(int id) throws DALException;
	public void update(Utilisateur userToUpdated) throws DALException;
	public Utilisateur checkUtilisateur (String email, String password) throws DALException;
	public Utilisateur getUtilisateurById(int id) throws DALException;
	public List<Utilisateur> getAllUtilisateur() throws DALException ;

	Utilisateur selectUtilisateurConnecte(String email, String motDePasse) throws DALException;
	Utilisateur selectByPseudo(String speudo) throws DALException;
}
