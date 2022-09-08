package fr.eni.ventesauxencheres.dal;

public interface ProfilDAO {
	String typeOfProfil(String email, byte[] hashmotDePasse) throws DALException;
}
