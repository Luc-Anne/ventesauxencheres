package fr.eni.ventesauxencheres.dal.jdbcMariaDB.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ventesauxencheres.bo.dependencies.Adresse;

public class AdresseJdbcMariaDB {

	public static Adresse insert(Connection cnx, Adresse adresse) throws SQLException {
		String query = "INSERT INTO ADRESSE (rue, code_postal, ville, pays)"
					+ " VALUES (?, ?, ?, ?)";
		try (PreparedStatement st = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
			st.setString(1, adresse.getRue());
			st.setString(2, adresse.getCodePostal());
			st.setString(3, adresse.getVille());
			st.setString(4, adresse.getPays());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				adresse.setNoAdresse(rs.getInt(1));
			}
			return adresse;
		}
	}

	public static void update(Connection cnx, Adresse adresse) throws SQLException {
		String query = "UPDATE ADRESSE SET"
					 + " rue = ?,"
				     + " code_postal = ?,"
				     + " ville = ?,"
				     + " pays = ?"
				     + " WHERE no_adresse = ?";
		try (PreparedStatement st3 = cnx.prepareStatement(query);) {
			st3.setString(1, adresse.getRue());
			st3.setString(2, adresse.getCodePostal());
			st3.setString(3, adresse.getVille());
			st3.setString(4, adresse.getPays());
			st3.setInt(5, adresse.getNoAdresse());
			st3.executeUpdate();
		}
	}

	public static void delete(Connection cnx, Adresse adresse) throws SQLException {
		String query = "DELETE FROM ADRESSE"
					+ " WHERE no_adresse = ?";
		try (PreparedStatement st = cnx.prepareStatement(query);) {
			st.setInt(1, adresse.getNoAdresse());
			st.executeUpdate();
		}
	}

}
