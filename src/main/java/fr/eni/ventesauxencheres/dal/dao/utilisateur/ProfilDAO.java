package fr.eni.ventesauxencheres.dal.dao.utilisateur;

import fr.eni.ventesauxencheres.bo.utilisateur.Profil;
import fr.eni.ventesauxencheres.exceptions.DALException;

public interface ProfilDAO {
	String typeOfProfil(String email, byte[] hashmotDePasse) throws DALException;
	void updateMotDePasse(Profil profil, byte[] hashmotDePasse) throws DALException;
}
