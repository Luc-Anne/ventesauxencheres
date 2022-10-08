package fr.eni.ventesauxencheres.dal.jdbc.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ventesauxencheres.bo.utilisateur.Profil;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ProfilDAO;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class ProfilDAOJdbcImpl implements ProfilDAO {

	@Override
	public String typeOfProfil(String email, byte[] hashmotDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			String query = "SELECT *"
						+ " FROM PROFIL"
						+ " WHERE courriel = ? AND mot_de_passe = ?";
			try (PreparedStatement st = cnx.prepareStatement(query);) {
				st.setString(1, email);
				st.setBytes(2, hashmotDePasse);
				ResultSet rs = st.executeQuery();
				String typeOfProfil = "";
				if (rs.next()) {
					rs.getInt("no_client");
					if (!rs.wasNull()) {
						typeOfProfil = "Client";
					}
					rs.getInt("no_administrateur");
					if (!rs.wasNull()) {
						typeOfProfil = "Administrateur";
					}
				}
				return typeOfProfil;
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur select type de profil", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public void updateMotDePasse(Profil profil, byte[] hashmotDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			String query = "UPDATE PROFIL"
						+ " SET mot_de_passe = ?"
						+ " WHERE no_profil = ?";
			try (PreparedStatement st = cnx.prepareStatement(query);) {
				st.setBytes(1, hashmotDePasse);
				st.setInt(2, profil.getNoProfil());
				st.executeUpdate();
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur de modification de mot de passe", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

}