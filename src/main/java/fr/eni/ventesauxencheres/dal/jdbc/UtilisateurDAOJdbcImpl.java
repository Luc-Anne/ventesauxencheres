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

	private static final String CONNEXION = "SELECT * FROM dbo.UTILISATEURS WHERE email = ? AND mot_de_passe = ? ";
	private static final String INSCRIPTION = 
			"INSERT INTO UTILISATEURS	(pseudo, nom,prenom, email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "delete from UTILISATEURS where no_utilisateur = ?";
	private static final String UPDATE="UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?,telephone=?, "
			+ "rue=? ,code_postal=?,ville=? ,mot_de_passe=?,credit=? ,administrateur=? "
			+ "WHERE no_utilisateur=?; ";
	
	private static final String CHECK_UTILISATEUR ="";

	@Override
	public Utilisateur connexion(String email, String motDePasse) throws DALException {
		Utilisateur u = null;

		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(CONNEXION)) {

				cnx.setAutoCommit(false);
				stmt.setString(1, email);
				stmt.setString(2, motDePasse);

				ResultSet resultSet = stmt.executeQuery();
				if (resultSet.next()) {
					u = new Utilisateur(resultSet.getInt("no_utilisateur"), resultSet.getString("pseudo"),
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

	public boolean inscription(Utilisateur u) throws DALException {
		boolean enregistre = false;

		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			try (PreparedStatement stmt = cnx.prepareStatement(INSCRIPTION);){
				//cnx.setAutoCommit(false);
				//(pseudo, nom,prenom, email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
				stmt.setString(1,u.getPseudo() );
				stmt.setString(2, u.getNom());
				stmt.setString(3, u.getPrenom());
				stmt.setString(4, u.getEmail());
				stmt.setString(5, u.getTelephone());
				stmt.setString(6, u.getRue());
				stmt.setString(7, u.getCodePostal());
				stmt.setString(8, u.getVille());
				stmt.setString(9, u.getMotDePasse());
				stmt.setInt(10, u.getCredit());
				stmt.setBoolean(11, u.isAdministrateur());
				cnx.commit();
				
				int i = stmt.executeUpdate();

				if (i == 1) {
					enregistre = true;
				}
				
				
			} catch (SQLException e) {
				//cnx.rollback();
				throw new DALException("Erreur insertion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
		return enregistre;

	}
	

	public boolean deleteById(int id) throws DALException {
		boolean supprime = false;
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(DELETE);) {
				stmt.setInt(1, id);
				stmt.executeUpdate();
				int i = stmt.executeUpdate();
				if (i == 1) {
					supprime = true;
					System.out.println("Suppresssion réussie");
				}

			} catch (SQLException e) {
				throw new DALException("Erreur Suppresssion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme Suppresssion", e);
		}
		return supprime;
	}

	@Override
	public Utilisateur update(Utilisateur userToUpdated) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(UPDATE);) {
				cnx.setAutoCommit(false);
				stmt.setString(1,userToUpdated.getPseudo());
				stmt.setString(2,userToUpdated.getNom());
				stmt.setString(3,userToUpdated.getPrenom());
				stmt.setString(4,userToUpdated.getEmail());
				stmt.setString(5,userToUpdated.getTelephone());
				stmt.setString(6,userToUpdated.getRue());
				stmt.setString(7,userToUpdated.getCodePostal());
				stmt.setString(8,userToUpdated.getVille());
				stmt.setString(9,userToUpdated.getMotDePasse());
				stmt.setInt(10,userToUpdated.getCredit());
				stmt.setBoolean(11,userToUpdated.isAdministrateur());
				stmt.setInt(12, userToUpdated.getNoUtilisateur());
				stmt.executeUpdate();
				cnx.commit();				
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Probleme connexion", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme connexion", e);
		}		
		return userToUpdated;

	}

	@Override
	public Utilisateur checkUtilisateur(String email, String password) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
//	
//	public Utilisateur checkUtilisateur (String email, String password) throws DALException {
//		Utilisateur u = null;
//		
//		
//		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
//			try (PreparedStatement stmt = cnx.prepareStatement(CHECK_UTILISATEUR);){
//				
//				
//				
//				
//				cnx.setAutoCommit(false);
//				stmt.setString(1, email);
//				stmt.setString(2, password);
//
//				ResultSet resultSet = stmt.executeQuery();
//				if (resultSet.next()) {
//					u = new Utilisateur(resultSet.getInt("no_utilisateur"), resultSet.getString("pseudo"),
//							resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),
//							resultSet.getString("telephone"), resultSet.getString("rue"),
//							resultSet.getString("code_postal"), resultSet.getString("ville"),
//							resultSet.getString("mot_de_passe"), resultSet.getInt("credit"),
//							resultSet.getBoolean("administrateur"));
//					cnx.commit();
//			}
//				
//			} catch (Exception e) {
////				cnx.rollback();
//				throw new DALException("Probleme checkUtilisateur", e);
//			}
//			
//		} catch (Exception e) {
//			throw new DALException("Probleme checkUtilisateur", e);
//		}
//		
//		return u;
//	}
//	

}