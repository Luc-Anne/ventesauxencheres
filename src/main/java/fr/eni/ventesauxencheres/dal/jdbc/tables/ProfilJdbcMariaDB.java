package fr.eni.ventesauxencheres.dal.jdbc.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.bo.utilisateur.Profil;

public class ProfilJdbcMariaDB {

	public static Client insertClient(Connection cnx, Client client, byte[] hashedMotDePasse) throws SQLException {
		String query = "INSERT INTO PROFIL (pseudo, courriel, mot_de_passe, date_enregistrement, no_client)"
				    + " VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement st = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
			cnx.setAutoCommit(false);
			st.setString(1, client.getPseudo());
			st.setString(2, client.getCourriel());
			st.setBytes(3, hashedMotDePasse);
			st.setTimestamp(4, Timestamp.valueOf(client.getDateEnregistrement()));
			st.setInt(5, client.getNoClient());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				client.setNoProfil(rs.getInt(1));
			}
			return client;
		}
	}

	public static void update(Connection cnx, Profil profil) throws SQLException {
		String query = "UPDATE PROFIL SET"
					 + " pseudo = ?,"
				     + " courriel = ?"
				     + " WHERE no_profil = ?";
		try (PreparedStatement st = cnx.prepareStatement(query);) {
			st.setString(1, profil.getPseudo());
			st.setString(2, profil.getCourriel());
			st.setInt(3, profil.getNoProfil());
			st.executeUpdate();
		}
	}

	public static void delete(Connection cnx, Profil profil) throws SQLException {
		String query = "DELETE FROM PROFIL"
					+ " WHERE no_profil = ?";
		try (PreparedStatement st2 = cnx.prepareStatement(query);) {
			st2.setInt(1, profil.getNoProfil());
			st2.executeUpdate();
		}
	}


}
