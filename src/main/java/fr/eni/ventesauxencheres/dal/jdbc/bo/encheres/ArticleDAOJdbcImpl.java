package fr.eni.ventesauxencheres.dal.jdbc.bo.encheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Article;
import fr.eni.ventesauxencheres.bo.encheres.Categorie;
import fr.eni.ventesauxencheres.bo.encheres.Enchere;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.dao.encheres.ArticleDAO;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL = ""
			+ "SELECT a.no_article, a.nom_article, a.description, a.date_debut_enchere, a.date_fin_enchere, a.prix_initial, a.prix_vente, a.etat_vente, "
			+ "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, "
			+ "c.no_categorie, c.libelle, "
			+ "r.rue AS Rrue, r.code_postal AS Rcode_postal, r.ville AS Rville, "
			+ "e.date_enchere, e.montant_enchere, "
			+ "bidder.no_utilisateur as bidderNo,bidder.pseudo as bidderPseudo,bidder.nom as bidderNom,bidder.prenom as bidderPrenom,bidder.email as bidderEmail,bidder.mot_de_passe as bidderMDP,bidder.telephone as bidderTel,bidder.rue as bidderRue,bidder.code_postal as bidderCP,bidder.ville as bidderVille,bidder.credit as bidderCredit,bidder.administrateur as bidderAd "
			+ "FROM ARTICLES a "
			+ "LEFT JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "LEFT JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+ "LEFT JOIN RETRAITS r ON a.no_article = r.no_article "
			+ "LEFT JOIN ENCHERES e ON a.no_article = e.no_article "
			+ "LEFT JOIN UTILISATEURS as bidder on bidder.no_utilisateur = e.no_utilisateur ";

	//INSERT INTO ARTICLES_VENDUS
	private static final String INSERT = "INSERT INTO ARTICLES "
						+ "(nom_article,"	//1
						+ "description, " //2
						+ "date_debut_enchere," //3
						+ "date_fin_enchere, " //4
						+ "prix_initial, " //5
						+ "no_utilisateur, " //6
						+ "no_categorie) "//7
						+ " VALUES (?,?,?,?,?,?,?);";

	private static final String UPDATE = "UPDATE ARTICLES SET "
						+ "nom_article = ?, "	//1
						+ "description = ?, " //2
						+ "date_debut_enchere = ?, " //3
						+ "date_fin_enchere = ?, " //4
						+ "prix_initial = ?, " //5
						+ "no_utilisateur = ?, " //6
						+ "no_categorie = ?, "//7
						+ "etat_vente = ? "//8
						+ "WHERE no_article = ? "; //9

	private static final String SELECT_BY_ID = SELECT_ALL
			+ "WHERE a.no_article = ? ";

	private static final String SELECT_BY_ID_UTILISATEUR = SELECT_ALL
			+ "WHERE a.no_utilisateur = ? ";

	@Override
	@Deprecated
	public Article insert(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);){
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
            stmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			stmt.setInt(5, article.getMiseAPrix());
			stmt.setInt(6, article.getVendeur().getNoClient());
			stmt.setInt(7, article.getCategorieArticle().getNoCategorie());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur insertion ", e);
		}
		return article;
	}

	@Override
	@Deprecated
	public Article selectById(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement statement = cnx.prepareStatement(SELECT_BY_ID)) {
				statement.setInt(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					return createInstanceArticleFromResultSet(rs);
				}
				return null;
			} catch (SQLException e) {
				throw new DALException("Erreur selectById(" + id + ")", e);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur Connexion ?? la base de donn??es", e);
		}
	}

	@Override
	@Deprecated
	public List<Article> selectByIdClient(int idClient) throws DALException{
		List<Article> articleList=new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID_UTILISATEUR);) {
				stmt.setInt(1, idClient);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Article art = createInstanceArticleFromResultSet(rs);
					articleList.add(art);
				}
				return articleList;
			} catch (SQLException e) {
				throw new DALException("Probleme appel ?? la dal", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme appel ?? la dal", e);
		}
	}

	@Override
	@Deprecated
	public List<Article> selectAll() throws DALException {
		List<Article> articleList=new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				Statement stmt = cnx.createStatement();
				ResultSet rs=stmt.executeQuery(SELECT_ALL);
			){
				while(rs.next()) {
					Article art = createInstanceArticleFromResultSet(rs);
					articleList.add(art);
				}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel ?? la dal", e);
		}
		return articleList;
	}

	@Override
	@Deprecated
	public void update(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(UPDATE);) {
				cnx.setAutoCommit(false);
				stmt.setString(1, article.getNomArticle());
				stmt.setString(2, article.getDescription());
				stmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
	            stmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
				stmt.setInt(5, article.getMiseAPrix());
				stmt.setInt(6, article.getVendeur().getNoClient());
				stmt.setInt(7, article.getCategorieArticle().getNoCategorie());
				stmt.setString(8, article.getEtatVente());
				stmt.setInt(9, article.getNoArticle());
				stmt.executeUpdate();
				cnx.commit();
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Probleme connexion", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme connexion", e);
		}
	}

	@Override
	@Deprecated
	public void delete(int id) throws DALException {
	}

	@Deprecated
	private Article createInstanceArticleFromResultSet(ResultSet rs) throws SQLException {
		Client vendeur = new Client (
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

		Categorie categorie = new Categorie(
			rs.getInt("no_categorie"),
			rs.getString("libelle")
		);

		Client encherisseur = new Client (
			rs.getInt("bidderNo"),
			rs.getString("bidderPseudo"),
			rs.getString("bidderNom"),
			rs.getString("bidderPrenom"),
			rs.getString("bidderEmail"),
			rs.getString("bidderTel"),
			rs.getString("bidderRue"),
			rs.getString("bidderCP"),
			rs.getString("bidderVille"),
			rs.getString("bidderMDP"),
			rs.getInt("bidderCredit"),
			rs.getBoolean("bidderAd")
		);

		Enchere enchere = null;
		if(rs.getDate("date_enchere")!=null) {
			enchere = new Enchere(
					LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime()),
					rs.getInt("montant_enchere"),
					encherisseur
				);
			}

		Article article = new Article (
			rs.getInt("no_article"),
			rs.getString("nom_article"),
			rs.getString("description"),
			LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()),
			LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()),
			rs.getInt("prix_initial"),
			rs.getInt("prix_vente"),
			rs.getString("etat_vente"),
			categorie,
			vendeur,
			enchere
		);

		Retrait retrait = null;
		if (rs.getString("Rrue") != null) {
			retrait = new Retrait (
				rs.getString("Rrue"),
				rs.getString("Rcode_postal"),
				rs.getString("Rville"),
				article
			);
		}

		article.setRetrait(retrait);

		return article;
	}

	@Override
	@Deprecated
	public List<Article> selectListHome(String typeQuery, int idClientConnecte, String motCle, String libelle) throws DALException {
		String query = "";
		if ("OpenedBids".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " a.etat_vente='EC' AND a.no_utilisateur!=? " //String SELECT_OPENED_BIDS_STRING = "a.etat_vente='EC' AND a.no_utilisateur!=? "
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		} else if ("MyBids".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " a.etat_vente='EC' AND e.no_utilisateur=? " //String  SELECT_MY_BIDS_STRING= "a.etat_vente='EC' AND e.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		} else if ("MyWonBids".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " (a.etat_vente='VD' OR a.etat_vente='RT') AND e.no_utilisateur=? " //String SELECT_MY_WON_BIDS_STRING="(a.etat_vente='VD' OR a.etat_vente='RT') AND e.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		} else if ("MyCurrentSales".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " a.etat_vente='EC' AND a.no_utilisateur=? " // String SELECT_MY_CURRENT_SALES_STRING="a.etat_vente='EC' AND a.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		} else if ("MyUnstartedSales".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " a.etat_vente='CR' AND a.no_utilisateur=? " //String SELECT_MY_UNSTARTED_SALES_STRING="a.etat_vente='CR' AND a.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		} else if ("MyClosedSales".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " (a.etat_vente='VD' OR a.etat_vente='RT') AND a.no_utilisateur=? " //String SELECT_MY_CLOSED_SALES_STRING ="(a.etat_vente='VD' OR a.etat_vente='RT') AND a.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? "
					+ " AND c.libelle like ? ";
		}
		List<Article> articleList=new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			ResultSet rs=null;
			if (query.equals("")) {
				query = SELECT_ALL + " WHERE " + "a.nom_article like ? " + " AND c.libelle like ? ";
				PreparedStatement stmt = cnx.prepareStatement(query);
				stmt.setString(1, "%" + motCle + "%");
				stmt.setString(2, "%" + libelle+ "%");
				rs=stmt.executeQuery();
			} else {
				PreparedStatement stmt = cnx.prepareStatement(query);
				stmt.setInt(1,idClientConnecte);
				stmt.setString(2, "%" + motCle + "%");
				stmt.setString(3, "%" + libelle+ "%");
				rs=stmt.executeQuery();
			}
			while(rs.next()) {
				Article art = createInstanceArticleFromResultSet(rs);
				articleList.add(art);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel ?? la dal", e);
		}
	}

}