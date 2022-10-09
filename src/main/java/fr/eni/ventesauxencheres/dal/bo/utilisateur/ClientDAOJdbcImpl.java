package fr.eni.ventesauxencheres.dal.bo.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.bo.BoObjectFactory;
import fr.eni.ventesauxencheres.dal.dao.utilisateur.ClientDAO;
import fr.eni.ventesauxencheres.dal.jdbcMariaDB.tables.ClientJdbcMariaDB;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class ClientDAOJdbcImpl implements ClientDAO {

	@Override
	public Client insert(Client client, byte[] hashedMotDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			cnx.setAutoCommit(false);
			Client newClient = client;
			int noProfil = -1;
			// Add in ADRESSE
			String query = "INSERT INTO ADRESSE (rue, code_postal, ville, pays)"
						+ " VALUES (?, ?, ?, ?)";
			try (PreparedStatement st3 = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
				st3.setString(1, client.getAdresseDomicile().getRue());
				st3.setString(2, client.getAdresseDomicile().getCodePostal());
				st3.setString(3, client.getAdresseDomicile().getVille());
				st3.setString(4, client.getAdresseDomicile().getPays());
				st3.executeUpdate();
				ResultSet rs = st3.getGeneratedKeys();
				int noAdresse = -1;
				if (rs.next()) {
					noAdresse = rs.getInt(1);
				}
				newClient.getAdresseDomicile().setNoAdresse(noAdresse);
				newClient = ClientJdbcMariaDB.insert(cnx, client);
					// Add in PROFIL
					query = "INSERT INTO PROFIL (pseudo, courriel, mot_de_passe, date_enregistrement, no_client)"
							+ " VALUES (?, ?, ?, ?, ?)";
					try (PreparedStatement st1 = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
						cnx.setAutoCommit(false);
						st1.setString(1, client.getPseudo());
						st1.setString(2, client.getCourriel());
						st1.setBytes(3, hashedMotDePasse);
						st1.setTimestamp(4, Timestamp.valueOf(client.getDateEnregistrement()));
						st1.setInt(5, newClient.getNoClient());
						st1.executeUpdate();
						rs = st1.getGeneratedKeys();
						if (rs.next()) {
							noProfil = rs.getInt(1);
						}
						cnx.commit();
						newClient.setNoProfil(noProfil);
						return newClient;
					}
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("insertion de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public Client selectById(int id) throws DALException {
		String query = "SELECT *"
					+ " FROM CLIENT c"
					+ " LEFT JOIN PROFIL f ON c.no_client = f.no_client"
					+ " LEFT JOIN ADRESSE a ON c.no_adresse = a.no_adresse"
					+ "	WHERE no_client = ?";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = BoObjectFactory.getInstance().createClient(rs);
					client.setAdresseDomicile(BoObjectFactory.getInstance().createAdresse(rs));
				}
				return client;
			} catch (SQLException e) {
				throw new DALException("selection de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public Client selectByPseudo(String pseudo) throws DALException {
		String query = "SELECT *"
					+ " FROM CLIENT c"
					+ " LEFT JOIN PROFIL f ON c.no_client = f.no_client"
					+ " LEFT JOIN ADRESSE a ON c.no_adresse = a.no_adresse"
					+ "	WHERE pseudo = ?";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				st.setString(1, pseudo);
				ResultSet rs = st.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = BoObjectFactory.getInstance().createClient(rs);
					client.setAdresseDomicile(BoObjectFactory.getInstance().createAdresse(rs));
				}
				return client;
			} catch (SQLException e) {
				throw new DALException("selection de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public List<Client> selectAll() throws DALException {
		String query = "SELECT *"
					+ " FROM CLIENT c"
					+ " LEFT JOIN PROFIL f ON c.no_client = f.no_client"
					+ " LEFT JOIN ADRESSE a ON c.no_adresse = a.no_adresse";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				ResultSet rs = st.executeQuery();
				List<Client> clients = new ArrayList<>();
				while (rs.next()) {
					Client client = BoObjectFactory.getInstance().createClient(rs);
					client.setAdresseDomicile(BoObjectFactory.getInstance().createAdresse(rs));
					clients.add(client);
				}
				return clients;
			} catch (SQLException e) {
				throw new DALException("selection de clients", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public void update(Client client) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			cnx.setAutoCommit(false);
			ClientJdbcMariaDB.update(cnx, client);
				// Update in PROFIL
				String query = "UPDATE PROFIL SET"
					 + " pseudo = ?,"
				     + " courriel = ?"
				     + " WHERE no_profil = ?";
				try (PreparedStatement st2 = cnx.prepareStatement(query);) {
					st2.setString(1, client.getPseudo());
					st2.setString(2, client.getCourriel());
					st2.setInt(3, client.getNoProfil());
					st2.executeUpdate();
					// Update in ADRESSE
					query = "UPDATE ADRESSE SET"
						 + " rue = ?,"
					     + " code_postal = ?,"
					     + " ville = ?,"
					     + " pays = ?"
					     + " WHERE no_adresse = ?";
					try (PreparedStatement st3 = cnx.prepareStatement(query);) {
						st3.setString(1, client.getAdresseDomicile().getRue());
						st3.setString(2, client.getAdresseDomicile().getCodePostal());
						st3.setString(3, client.getAdresseDomicile().getVille());
						st3.setString(4, client.getAdresseDomicile().getPays());
						st3.setInt(5, client.getAdresseDomicile().getNoAdresse());
						st3.executeUpdate();
						cnx.commit();
					}
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("mise à jour de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public void delete(Client client) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			cnx.setAutoCommit(false);
			// Delete in ADRESSE
			String query = "DELETE FROM ADRESSE"
						+ " WHERE no_adresse = ?";
			try (PreparedStatement st = cnx.prepareStatement(query);) {
				st.setInt(1, client.getAdresseDomicile().getNoAdresse());
				st.executeUpdate();
				// Delete in PROFIL
				query = "DELETE FROM PROFIL"
					 + " WHERE no_profil = ?";
				try (PreparedStatement st2 = cnx.prepareStatement(query);) {
					st2.setInt(1, client.getNoProfil());
					st2.executeUpdate();
					ClientJdbcMariaDB.delete(cnx, client);
					cnx.commit();
				}
			} catch (SQLException | NullPointerException e) {
				cnx.rollback();
				throw new DALException("suppression de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public Client connexion(String email, byte[] hashmotDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			String query = "SELECT *"
						+ " FROM CLIENT c"
						+ " LEFT JOIN PROFIL f ON c.no_client = f.no_client"
						+ " LEFT JOIN ADRESSE a ON c.no_adresse = a.no_adresse"
						+ " WHERE courriel = ? AND mot_de_passe = ? AND no_profil IS NOT NULL";
			try (PreparedStatement st = cnx.prepareStatement(query);) {
				st.setString(1, email);
				st.setBytes(2, hashmotDePasse);
				ResultSet rs = st.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = BoObjectFactory.getInstance().createClient(rs);
					client.setAdresseDomicile(BoObjectFactory.getInstance().createAdresse(rs));
				}
				return client;
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("selection client connecte", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

}