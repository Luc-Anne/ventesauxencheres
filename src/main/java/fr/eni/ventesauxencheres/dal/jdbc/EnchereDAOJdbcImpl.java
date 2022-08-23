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
			+ "FROM ENCHERES "
			+ "WHERE no_article = ? "
			+ "ORDER BY date_enchere DESC";

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
	
	public List<Enchere> selectByArticle(Article article) throws DALException {
		List<Enchere> listes = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement statement = cnx.prepareStatement(SELECT_BY_OBJECT)) {
				statement.setInt(1, article.getNoArticle());
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					// TODO mettre ça dans la requête
					Utilisateur utilisateur = new UtilisateurDAOJdbcImpl().selectById(rs.getInt("no_utilisateur"));
					listes.add(new Enchere(
							LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(), rs.getTime("date_enchere").toLocalTime()),
							rs.getInt("montant_enchere"),
							utilisateur,
							article
						)
					);
				}
				return null;
			} catch (SQLException e) {
				throw new DALException("Erreur " + this.getClass().getSuperclass().getCanonicalName() + " - " + this.getClass().getCanonicalName(), e);
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