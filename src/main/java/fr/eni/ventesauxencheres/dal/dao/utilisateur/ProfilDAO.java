package fr.eni.ventesauxencheres.dal.dao.utilisateur;

import fr.eni.ventesauxencheres.exceptions.DALException;

public interface ProfilDAO {
	String typeOfProfil(String email, byte[] hashmotDePasse) throws DALException;
}
