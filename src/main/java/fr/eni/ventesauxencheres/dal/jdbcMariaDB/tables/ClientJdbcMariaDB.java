package fr.eni.ventesauxencheres.dal.jdbcMariaDB.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public class ClientJdbcMariaDB {

	public static Client insert(Connection cnx, Client client) throws SQLException {
		String query = "INSERT INTO CLIENT (nom, prenom, actif, credit, no_adresse, telephone)"
				     + " VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement st = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
			st.setString(1, client.getNom());
			st.setString(2, client.getPrenom());
			st.setBoolean(3, client.isActif());
			st.setFloat(4, client.getCredit());
			st.setInt(5, client.getAdresseDomicile().getNoAdresse());
			st.setString(6, client.getTelephone());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				client.setNoClient(rs.getInt(1));
			}
			return client;
		} catch (SQLException e) {
			throw e;
		}
	}

	public static void update(Connection cnx, Client client) throws SQLException {
		String query = "UPDATE CLIENT SET"
					+ " nom = ?,"
					+ " prenom = ?,"
					+ " actif = ?,"
					+ " credit = ?,"
					+ " telephone = ?"
					+ " WHERE no_client = ?";
		try (PreparedStatement st = cnx.prepareStatement(query);) {
			st.setString(1, client.getNom());
			st.setString(2, client.getPrenom());
			st.setBoolean(3, client.isActif());
			st.setFloat(4, client.getCredit());
			st.setString(5, client.getTelephone());
			st.setInt(6, client.getNoClient());
			st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	public static void delete(Connection cnx, Client client) throws SQLException {
		String query = "DELETE FROM CLIENT"
					+ " WHERE no_client = ?";
		try (PreparedStatement st = cnx.prepareStatement(query);) {
			st.setInt(1, client.getNoClient());
			st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

}
