package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	// TODO Enchere a un CRUD de base différent
	private static final String SELECT_BY_OBJECT = ""
			+ "SELECT * "
			+ "FROM ENCHERES e "
			+ "LEFT JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "WHERE no_article = ? ";

	@Override
	public Enchere insert(Enchere u) throws DALException {
		return null;
	}

	@Override
	public Enchere selectById(int id) throws DALException {
		return null;
	}

	@Override
	public List<Enchere> selectAll() {
		List<Enchere> listes = new ArrayList<>();

		return listes;
	}
	
	public Enchere selectByArticle(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement statement = cnx.prepareStatement(SELECT_BY_OBJECT)) {
				statement.setInt(1, article.getNoArticle());
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					Utilisateur utilisateur = new Utilisateur (
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur")
					);
					return new Enchere(
						LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(), rs.getTime("date_enchere").toLocalTime()),
						rs.getInt("montant_enchere"),
						utilisateur,
						article
					);
				}
				return null;
			} catch (SQLException e) {
				throw new DALException("Erreur " + this.getClass().getSuperclass().getCanonicalName() + " - "
						+ this.getClass().getCanonicalName(), e);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur Connexion à la base de données", e);
		}
	}

	

	@Override
	public void update(Enchere userToUpdated) throws DALException {

	}

	@Override
	public void delete(int id) throws DALException {

	}

}