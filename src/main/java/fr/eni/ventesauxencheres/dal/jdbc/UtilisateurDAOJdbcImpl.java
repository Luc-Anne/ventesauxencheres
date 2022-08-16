package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String CONNEXION="SELECT * FROM dbo.UTILISATEURS WHERE email = ? AND mot_de_passe = ? ";
	
	@Override
	public Utilisateur connexion(String email, String motDePasse) throws DALException {
		Utilisateur u = null; 
		
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			try (PreparedStatement stmt = cnx.prepareStatement(CONNEXION)){
				
				cnx.setAutoCommit(false);
				stmt.setString(1, email);
				stmt.setString(2, motDePasse);
				
				ResultSet resultSet = stmt.executeQuery();
				if(resultSet.next()) {
					u = new Utilisateur(
							resultSet.getInt("no_utilisateur"),
							resultSet.getString("pseudo"),
							resultSet.getString("nom"),
							resultSet.getString("prenom"),
							resultSet.getString("email"),
							resultSet.getString("telephone"),
							resultSet.getString("rue"),
							resultSet.getString("code_postal"),
							resultSet.getString("ville"),
							resultSet.getString("mot_de_passe"),
							resultSet.getInt("credit"),
							resultSet.getBoolean("administrateur")
							);
					cnx.commit();
					return u;
				}
				
				
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur Connexion", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
		
		return null;
	}

}
