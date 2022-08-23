package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ArticleDAO;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL = ""
		+ "SELECT a.no_article, a.nom_article, a.description, a.date_debut_enchere, a.date_fin_enchere, a.prix_initial, a.prix_vente, a.etat_vente, "
		+ "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, "
		+ "c.no_categorie, c.libelle, "
		+ "r.rue AS Rrue, r.code_postal AS Rcode_postal, r.ville AS Rville, "
		+ "e.date_enchere, e.montant_enchere, "
		+ "bidder.no_utilisateur as bidderNo,bidder.pseudo as bidderPseudo,bidder.nom as bidderNom,bidder.prenom as bidderPrenom,bidder.email as bidderEmail,bidder.mot_de_passe as bidderMDP,bidder.telephone as bidderTel,bidder.rue as bidderRue,bidder.code_postal as bidderCP,bidder.ville as bidderVille,bidder.credit as bidderCredit,bidder.administrateur as bidderAd "
		+ "FROM ARTICLES_VENDUS a "
		+ "LEFT JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
		+ "LEFT JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
		+ "LEFT JOIN RETRAITS r ON a.no_article = r.no_article "
		+ "LEFT JOIN ENCHERES e ON a.no_article = e.no_article "
		+ "LEFT JOIN UTILISATEURS as bidder on bidder.no_utilisateur = e.no_utilisateur ";

	//INSERT INTO ARTICLES_VENDUS 
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS "
						+ "(nom_article,"	//1
						+ "description, " //2
						+ "date_debut_enchere," //3
						+ "date_fin_enchere, " //4
						+ "prix_initial, " //5
						+ "no_utilisateur, " //6
						+ "no_categorie) "//7
						+ " VALUES (?,?,?,?,?,?,?);";
	
	private static final String SELECT_BY_ID = SELECT_ALL
			+ "WHERE a.no_article = ? ";

	@Override
	public Article insert(Article article) throws DALException {
		ResultSet rs= null;

		
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);){
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			
			stmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
            stmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
			stmt.setInt(5, article.getMiseAPrix());
			stmt.setInt(6, article.getVendeur().getNoUtilisateur());
			stmt.setInt(7, article.getCategorieArticle().getNoCategorie());

//			Timestamp dateDebutEC = Timestamp.valueOf(article.getDateDebutEncheres());
//			Timestamp dateFinEC = Timestamp.valueOf(article.getDateFinEncheres());
//			System.out.println(dateDebutEC);
//			stmt.setTimestamp(3, dateDebutEC);
//			stmt.setTimestamp(4, dateFinEC);
			//java.sql.Date.valueof
//			stmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDateDebutEncheres()));
//			stmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getDateFinEncheres()));
			
			
			
//			stmt.setInt(6, article.getNo_utilisateur());
//			stmt.setInt(7, article.getCategorie());


			stmt.executeUpdate();
			

			
		} catch (SQLException e) {
			throw new DALException("Erreur insertion ", e);
		}

		return article;
	}

	@Override
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
			throw new DALException("Erreur Connexion à la base de données", e);
		}
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> articleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				Statement stmt = cnx.createStatement();
				ResultSet rs=stmt.executeQuery(SELECT_ALL);
			){
				while(rs.next()) {
					Article art = createInstanceArticleFromResultSet(rs);
					articleList.add(art);
				}
				return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel à la dal", e);
		}
	}

	@Override
	public void update(Article userToUpdated) throws DALException {
		
	}

	@Override
	public void delete(int id) throws DALException {
		
	}

	private Article createInstanceArticleFromResultSet(ResultSet rs) throws SQLException {
		Utilisateur vendeur = new Utilisateur (
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
		
		Utilisateur encherisseur = new Utilisateur (
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
	
}