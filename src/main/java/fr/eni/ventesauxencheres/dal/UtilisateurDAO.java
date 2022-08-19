package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	Utilisateur connexion(String email, String motDePasse) throws DALException;
	public boolean inscription(Utilisateur u) throws DALException ;
	public boolean deleteById(int id) throws DALException;
	public Utilisateur update(Utilisateur userToUpdated) throws DALException;
	public Utilisateur checkUtilisateur (String email, String password) throws DALException;
	public Utilisateur getUtilisateurById(int id) throws DALException;
	public List<Utilisateur> getAllUtilisateur() throws DALException ;
	public List<Utilisateur> selectAllSellers() throws DALException ;
}
