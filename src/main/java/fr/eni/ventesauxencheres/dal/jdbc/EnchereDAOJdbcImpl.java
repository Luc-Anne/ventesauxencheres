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

	private static final String SELECT_BY_OBJECT = "" + "SELECT * " + "FROM ENCHERES " + "WHERE no_article = ? "
			+ "ORDER BY date_enchere DESC";

	@Override
	public Enchere insert(Enchere u) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectById(int id) throws DALException {
		// TODO Auto-generated method stub
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
				while (rs.next()) {
					// TODO mettre ça dans la requête
					Utilisateur utilisateur = new UtilisateurDAOJdbcImpl().selectById(rs.getInt("no_utilisateur"));
					listes.add(new Enchere(
							LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),
									rs.getTime("date_enchere").toLocalTime()),
							rs.getInt("montant_enchere"), utilisateur, article));
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

	private static final String FIND_BY_NOM_ARTICLE_AND_ALL_CATEGORIE = "SELECT * FROM dbo.ARTICLES_VENDUS A , dbo.CATEGORIES C, dbo.ENCHERES E, dbo.UTILISATEURS U WHERE A.no_categorie = C.no_categorie AND E.no_article = A.no_article and U.no_utilisateur = E.no_utilisateur AND A.nom_article like ?";

	private static final String FIND_BY_NOM_ARTICLE_AND_LIBLLE_CATEGORIE = "SELECT * FROM dbo.ARTICLES_VENDUS A , dbo.CATEGORIES C, dbo.ENCHERES E, dbo.UTILISATEURS U WHERE A.no_categorie = C.no_categorie AND E.no_article = A.no_article and U.no_utilisateur = E.no_utilisateur	AND A.nom_article like ?  AND C.libelle like ?";

	@Override
	public List<Enchere> selectByMotCleAndByLibelle(String motCle, String libelleCategorie) throws DALException {
		ResultSet rs = null;
		List<Enchere> enchereListe = new ArrayList<>();
		PreparedStatement stmt = null;
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			libelleCategorie = "Toutes";
			if (libelleCategorie.equalsIgnoreCase("Toutes")) {
				stmt = cnx.prepareStatement(FIND_BY_NOM_ARTICLE_AND_ALL_CATEGORIE);
				stmt.setString(1, "%" + motCle + "%");
				rs = stmt.executeQuery();
			} else {
				stmt = cnx.prepareStatement(FIND_BY_NOM_ARTICLE_AND_LIBLLE_CATEGORIE);
				rs = stmt.executeQuery();
				stmt.setString(1, "%" + motCle + "%");
				stmt.setString(2, "%" + libelleCategorie + "%");
				rs = stmt.executeQuery();
			}

			while (rs.next()) {
				

			}
			enchereListe.add(enchere);
			System.out.println(enchereListe);
		} catch (Exception e) {
			throw new DALException("Erreur DAL selectByMotCleAndByLibelle", e);
		}
		return enchereListe;

	}

	@Override
	public void update(Enchere userToUpdated) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}

}