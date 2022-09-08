package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.ClientDAO;

public class ClientDAOJdbcImpl implements ClientDAO {

	private static final String CONNEXION = "SELECT * FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ? ";
	private static final String DELETE = "delete from UTILISATEURS where no_client = ?"

	private static final String AFFICHER_LIST_UTILISATEURS = "SELECT no_client, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit from UTILISATEURS";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";

	// CRUD
	@Override
	public Client insert(Client client, byte[] hashedMotDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			cnx.setAutoCommit(false);
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
				// Add in CLIENT
				query = "INSERT INTO CLIENT (nom, prenom, actif, credit, no_adresse, telephone)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
				try (PreparedStatement st = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
					st.setString(1, client.getNom());
					st.setString(2, client.getPrenom());
					st.setBoolean(3, client.isActif());
					st.setFloat(4, client.getCredit());
					st.setInt(5, noAdresse);
					st.setString(6, client.getTelephone());
					st.executeUpdate();
					rs = st.getGeneratedKeys();
					int noClient = -1;
					if (rs.next()) {
						noClient = rs.getInt(1);
					}
					// Add in PROFIL
					query = "INSERT INTO PROFIL (pseudo, courriel, mot_de_passe, no_client)"
							+ " VALUES (?, ?, ?, ?)";
					try (PreparedStatement st1 = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {
						cnx.setAutoCommit(false);
						st1.setString(1, client.getPseudo());
						st1.setString(2, client.getCourriel());
						st1.setBytes(3, hashedMotDePasse);
						st1.setInt(4, noClient);
						st1.executeUpdate();
						rs = st1.getGeneratedKeys();
						int noProfil = -1;
						if (rs.next()) {
							noProfil = rs.getInt(1);
						}
						// Read dateEnregistrement
						query = "SELECT dateEnregistrement FROM PROFIL WHERE no_profil = ?";
						try (PreparedStatement st2 = cnx.prepareStatement(query);) {
							st2.setInt(1, noProfil);
							rs = st2.executeQuery();
							LocalDateTime dateEnregistrement = null;
							if (rs.next()) {
								dateEnregistrement = LocalDateTime.of(rs.getDate("date_enregistement").toLocalDate(),
																	  rs.getTime("date_enregistement").toLocalTime());
							}
							cnx.commit();
							client.setNoClient(noClient);
							client.setNoProfil(noProfil);
							client.setDateEnregistrement(dateEnregistrement);
							client.getAdresseDomicile().setNoAdresse(noAdresse);
							return client;
						} catch (SQLException e) {
							cnx.rollback();
							throw new DALException("Erreur insertion dans Profil", e);
						}
					} catch (SQLException e) {
						cnx.rollback();
						throw new DALException("Erreur insertion dans Profil", e);
					}
				} catch (SQLException e) {
					cnx.rollback();
					throw new DALException("Erreur insertion dans Profil", e);
				}
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur insertion dans Client", e);
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
					+ "	WHERE no_client = ?";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = BoObjectFactory.getInstance().createClient(rs);
				}
				return client;
			} catch (SQLException e) {
				throw new DALException("Erreur selection de client", e);
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
					+ "	WHERE pseudo = ?";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				st.setString(1, pseudo);
				ResultSet rs = st.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = BoObjectFactory.getInstance().createClient(rs);
				}
				return client;
			} catch (SQLException e) {
				throw new DALException("Erreur selection de client", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public List<Client> selectAll() throws DALException {
		String query = "SELECT *"
					+ " FROM CLIENT c"
					+ " LEFT JOIN PROFIL f ON c.no_client = f.no_client";
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement st = cnx.prepareStatement(query);){
				ResultSet rs = st.executeQuery();
				List<Client> clients = new ArrayList<>();;
				if (rs.next()) {
					clients.add(BoObjectFactory.getInstance().createClient(rs));
				}
				return clients;
			} catch (SQLException e) {
				throw new DALException("Erreur selection de clients", e);
			}
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	public void update(Client client) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			cnx.setAutoCommit(false);
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
				query = "UPDATE PROFIL SET"
					 + " pseudo = ?,"
				     + " courriel = ?"
				     + " WHERE no_profil = ?";
				try (PreparedStatement st2 = cnx.prepareStatement(query);) {
					st2.setString(1, client.getPseudo());
					st2.setString(2, client.getCourriel());
					st2.setInt(3, client.getNoProfil());
					st2.executeUpdate();
					cnx.commit();
				} catch (SQLException e) {
					cnx.rollback();
					throw new DALException("Erreur mise à jour de profil", e);
				}
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur mise à jour de client", e);
			}			
		} catch (SQLException e) {
			throw new DALException("Connexion impossible", e);
		}
	}

	@Override
	@Deprecated
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(DELETE);) {
				stmt.setInt(1, id);
				stmt.executeUpdate();
				int i = stmt.executeUpdate();
				if (i == 1) {
					System.out.println("Suppresssion réussie");
				}
			} catch (SQLException e) {
				throw new DALException("Erreur Suppresssion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme Suppresssion", e);
		}
	}

	// Accesseurs spécifiques
	@Override
	@Deprecated
	public Client selectClientConnecte(String email, String motDePasse) throws DALException {
		Client u = null;
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(CONNEXION)) {
				cnx.setAutoCommit(false);
				stmt.setString(1, email);
				stmt.setString(2, motDePasse);
				ResultSet resultSet = stmt.executeQuery();
				if (resultSet.next()) {
					u = new Client(resultSet.getInt("no_client"), resultSet.getString("pseudo"),
							resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),
							resultSet.getString("telephone"), resultSet.getString("rue"),
							resultSet.getString("code_postal"), resultSet.getString("ville"),
							resultSet.getString("mot_de_passe"), resultSet.getInt("credit"),
							resultSet.getBoolean("administrateur"));
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

	@Override
	@Deprecated
	public Client connexion(String email, String motDePasse) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(CONNEXION);) {
				stmt.setString(1, email);
				stmt.setString(2, motDePasse);
				ResultSet rs = stmt.executeQuery();
				Client client = null;
				if (rs.next()) {
					client = new Client(rs.getInt("no_client"), rs.getString("pseudo"),
							rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
							rs.getString("telephone"), rs.getString("rue"),
							rs.getString("code_postal"), rs.getString("ville"),
							rs.getString("mot_de_passe"), rs.getInt("credit"),
							rs.getBoolean("administrateur"));
				}
				return client;
			} catch (SQLException e) {
				throw new DALException("Erreur insertion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
	}

}