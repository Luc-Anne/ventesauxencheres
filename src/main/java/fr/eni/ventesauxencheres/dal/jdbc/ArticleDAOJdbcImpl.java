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
	
	private static final String SELECT_BY_ID_UTILISATEUR = SELECT_ALL
			+ "WHERE a.no_utilisateur = ? ";
	
	private static final String SELECT_OPENED_BIDS = SELECT_ALL
			+ "WHERE a.etat_vente='EC' AND a.no_utilisateur!=?";
	//à exclure no utilisateur = id profil connecte mode vendeur, profil tom vendeur
		
	private static final String SELECT_MY_BIDS = SELECT_ALL
			+ "WHERE  a.etat_vente='EC' AND e.no_utilisateur=?";
	//no utilisateur = id profil connecte. Temporairement restriction sur samuel, profil encherisseur connecte
	
	private static final String SELECT_MY_WON_BIDS = SELECT_ALL
			+ "WHERE (a.etat_vente='VD' OR a.etat_vente='RT') AND e.no_utilisateur=?";
	//à exclure no utilisateur = id profil connecte	mode encherriseur.Temporairement restriction sur samuel, profil encherisseur connecte
	
	private static final String SELECT_MY_CURRENT_SALES = SELECT_ALL
			+ "WHERE a.etat_vente='EC' AND a.no_utilisateur=?";
	//à exclure no utilisateur = id profil connecte mode vendeur, profil tom vendeur
	
	private static final String SELECT_MY_UNSTARTED_SALES = SELECT_ALL
			+ "WHERE a.etat_vente='CR' AND a.no_utilisateur=?";
	//à exclure no utilisateur = id profil connecte mode vendeur, profil tom vendeur	
	private static final String SELECT_MY_CLOSED_SALES = SELECT_ALL
			+ "WHERE (a.etat_vente='VD' OR a.etat_vente='RT') AND a.no_utilisateur=?";
	//à exclure no utilisateur = id profil connecte mode vendeur, profil tom vendeur	
	
	// récupérer un artciel via son nom 
	private static final String FIND_BY_NOM_ARTICLE_AND_ALL_CATEGORIE = SELECT_ALL 
			+"WHERE A.nom_article like ?";

	private static final String FIND_BY_NOM_ARTICLE_AND_LIBLLE_CATEGORIE = SELECT_ALL
			+" WHERE A.nom_article like ?  AND C.libelle like ?";
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
	
	public List<Article> selectByIdUtilisateur(int idUtilisateur) throws DALException{
		List<Article> articleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			try (PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID_UTILISATEUR);) {
				stmt.setInt(1, idUtilisateur);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Article art = createInstanceArticleFromResultSet(rs);
					articleList.add(art);
				}
				return articleList;
			} catch (SQLException e) {
				throw new DALException("Probleme appel à la dal", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme appel à la dal", e);
		}
	}
	
	// Methodes pour afficher les listes d'articles dans le home
		public List<Article> selectOpenedBids(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
				PreparedStatement stmt = cnx.prepareStatement(SELECT_OPENED_BIDS);
				stmt.setInt(1, idUtilisateurConnecte);
				ResultSet rs=stmt.executeQuery();
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
		
		public List<Article> selectMyBids(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
					PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_BIDS);
					stmt.setInt(1, idUtilisateurConnecte);
					ResultSet rs=stmt.executeQuery();
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
		
		public List<Article> selectMyWonBids(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
				PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_WON_BIDS);
				stmt.setInt(1, idUtilisateurConnecte);
				ResultSet rs=stmt.executeQuery();
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
		
		public List<Article> selectMyCurrentSales(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
					PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_CURRENT_SALES);
					stmt.setInt(1, idUtilisateurConnecte);
					ResultSet rs=stmt.executeQuery();
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
		
		public List<Article> selectUnstartedSales(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
				PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_UNSTARTED_SALES);
				stmt.setInt(1, idUtilisateurConnecte);
				ResultSet rs=stmt.executeQuery();
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
		
		public List<Article> selectClosedSales(int idUtilisateurConnecte) throws DALException{
			List<Article> articleList=new ArrayList<Article>();
			try (Connection cnx = ConnectionProvider.getConnection_VAE();){
				PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_CLOSED_SALES);
				stmt.setInt(1, idUtilisateurConnecte);
				ResultSet rs=stmt.executeQuery();
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel à la dal", e);
		}
		return articleList;
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
	
	public List<Article> selectByMotCleAndByLibelle(String motCle)throws DALException {
		List<Article> articleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
			
			PreparedStatement stmt = cnx.prepareStatement(FIND_BY_NOM_ARTICLE_AND_ALL_CATEGORIE);
			stmt.setString(1, "%" + motCle + "%");
			
			ResultSet rs=stmt.executeQuery();
				while(rs.next()) {
					Article art = createInstanceArticleFromResultSet(rs);
					articleList.add(art);
					System.out.println("On est rentré dans le RS");				
				}
				System.out.println("Voici la liste des articles : "+articleList);
				return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel à la dal", e);
		}
	}

	//Non fonctionelle en attente d'amélioration
	@Override
	public List<Article> selectListHome(String typeQuery, int idUtilisateurConnecte, String motCle) throws DALException {
		String query = "";
		if ("OpenedBids".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " a.etat_vente='EC' AND a.no_utilisateur!=? " //String SELECT_OPENED_BIDS_STRING = "a.etat_vente='EC' AND a.no_utilisateur!=? "
					+ " AND " + "a.nom_article like ? ";
		} else if ("MyBids".equals(typeQuery)) {
				query = SELECT_ALL
						+ " WHERE "
						+ " a.etat_vente='EC' AND e.no_utilisateur=? " //String  SELECT_MY_BIDS_STRING= "a.etat_vente='EC' AND e.no_utilisateur=?"
						+ " AND " + "a.nom_article like ? "; 
		}else if ("MyWonBids".equals(typeQuery)) {
			query = SELECT_ALL
					+ " WHERE "
					+ " (a.etat_vente='VD' OR a.etat_vente='RT') AND e.no_utilisateur=? " //String SELECT_MY_WON_BIDS_STRING="(a.etat_vente='VD' OR a.etat_vente='RT') AND e.no_utilisateur=?"
					+ " AND " + "a.nom_article like ? ";
	}else if ("MyCurrentSales".equals(typeQuery)) {
		query = SELECT_ALL
				+ " WHERE "
				+ " a.etat_vente='EC' AND a.no_utilisateur=? " // String SELECT_MY_CURRENT_SALES_STRING="a.etat_vente='EC' AND a.no_utilisateur=?"
				+ " AND " + "a.nom_article like ? ";
	}else if ("MyUnstartedSales".equals(typeQuery)) {
		query = SELECT_ALL
				+ " WHERE "
				+ " a.etat_vente='CR' AND a.no_utilisateur=? " //String SELECT_MY_UNSTARTED_SALES_STRING="a.etat_vente='CR' AND a.no_utilisateur=?"
				+ " AND " + "a.nom_article like ? ";
	}else if ("MyClosedSales".equals(typeQuery)) {
		query = SELECT_ALL
				+ " WHERE "
				+ " (a.etat_vente='VD' OR a.etat_vente='RT') AND a.no_utilisateur=? " //String SELECT_MY_CLOSED_SALES_STRING ="(a.etat_vente='VD' OR a.etat_vente='RT') AND a.no_utilisateur=?"
				+ " AND " + "a.nom_article like ? ";
	}
		List<Article> articleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();){			
			ResultSet rs=null;
			if (query.equals("")) {
				query = SELECT_ALL;
				PreparedStatement stmt = cnx.prepareStatement(query);
				rs=stmt.executeQuery();
			} else {
				PreparedStatement stmt = cnx.prepareStatement(query);
				stmt.setInt(1,idUtilisateurConnecte);
				stmt.setString(2, "%" + motCle + "%");				
				rs=stmt.executeQuery();
			}
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
}