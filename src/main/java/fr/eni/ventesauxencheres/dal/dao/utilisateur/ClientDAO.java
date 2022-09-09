package fr.eni.ventesauxencheres.dal.dao.utilisateur;

import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.exceptions.DALException;

public interface ClientDAO {
	Client insert(Client client, byte[] motDePasse) throws DALException ;
	Client selectById(int id) throws DALException;
	List<Client> selectAll() throws DALException ;
	void update(Client client) throws DALException;
	void delete(Client client) throws DALException;

	Client connexion(String email, byte[] hashmotDePasse) throws DALException;
	Client selectByPseudo(String speudo) throws DALException;
}
