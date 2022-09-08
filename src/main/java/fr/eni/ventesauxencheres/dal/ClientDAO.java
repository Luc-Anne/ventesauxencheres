package fr.eni.ventesauxencheres.dal;

import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public interface ClientDAO {
	Client insert(Client u, byte[] motDePasse) throws DALException ;
	Client selectById(int id) throws DALException;
	List<Client> selectAll() throws DALException ;
	void update(Client userToUpdated) throws DALException;
	void delete(int id) throws DALException;

	Client connexion(String email, String motDePasse) throws DALException;
	Client selectClientConnecte(String email, String motDePasse) throws DALException;
	Client selectByPseudo(String speudo) throws DALException;
}
