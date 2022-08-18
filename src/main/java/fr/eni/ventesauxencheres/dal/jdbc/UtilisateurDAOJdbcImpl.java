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
	
	
	/*
	 * HttpSession session = req.getSession();
		session.removeAttribute("userObj");
		session.setAttribute("succMsg", "User Logout Sucessfully");
		resp.sendRedirect("user_login.jsp");
	 * 
	 */

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

}