package fr.eni.ventesauxencheres.dal.jdbcMariaDB.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class ClientJdbcMariaDB {

	public static Client insert(Connection cnx, Client client) throws DALException {
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
			int noClient = -1;
			if (rs.next()) {
				noClient = rs.getInt(1);
			}
			client.setNoClient(noClient);
		} catch (SQLException e) {
			throw new DALException("Insert CLIENT Table");
		}
		return client;
	}

	public Client selectById(Connection cnx, int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> selectAll(Connection cnx) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Connection cnx, Client type) throws DALException {
		// TODO Auto-generated method stub
		
	}

	public void delete(Connection cnx, int id) throws DALException {
		// TODO Auto-generated method stub
		
	}


}
