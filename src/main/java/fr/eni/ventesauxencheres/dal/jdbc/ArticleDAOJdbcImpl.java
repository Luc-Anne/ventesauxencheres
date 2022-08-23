package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ArticleDAO;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String BASE_JOIN = ""
		+ "SELECT a.no_article, a.nom_article, a.description, a.date_debut_enchere, a.date_fin_enchere, a.prix_initial, a.prix_vente, a.etat_vente, "
		+ "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, "
		+ "c.no_categorie, c.libelle, "
		+ "r.rue AS Rrue, r.code_postal AS Rcode_postal, r.ville AS Rville "
		+ "FROM ARTICLES_VENDUS a "
		+ "LEFT JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
		+ "LEFT JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
		+ "LEFT JOIN RETRAITS r ON a.no_article = r.no_article ";

	private static final String SELECT_ALL="SELECT a.no_article,a.nom_article,a.description,a.date_debut_enchere,a.date_fin_enchere,a.prix_initial,a.prix_vente,a.etat_vente,\r\n"
		+ "c.libelle,c.no_categorie,\r\n"
		+ "a.no_utilisateur,seller.pseudo,seller.nom,seller.prenom,seller.email,seller.mot_de_passe,seller.telephone,seller.rue,seller.code_postal,seller.ville,seller.administrateur,seller.credit,\r\n"
		+ "r.rue,r.ville,r.code_postal,\r\n"
		+ "e.montant_enchere,e.date_enchere,\r\n"
		+ "bidder.no_utilisateur as bidderNo,bidder.pseudo as bidderPseudo,bidder.nom as bidderNom,bidder.prenom as bidderPrenom,bidder.email as bidderEmail,bidder.mot_de_passe as bidderMDP,bidder.telephone as bidderTel,bidder.rue as bidderRue,bidder.code_postal as bidderCP,bidder.ville as bidderVille,bidder.credit as bidderCredit,bidder.administrateur as bidderAd \r\n"
		+ "FROM ARTICLES_VENDUS as a\r\n"
		+ "JOIN UTILISATEURS as seller on seller.no_utilisateur=a.no_utilisateur\r\n"
		+ "JOIN CATEGORIES as c on c.no_categorie=a.no_categorie\r\n"
		+ "LEFT JOIN RETRAITS as r  on r.no_article=a.no_article\r\n"
		+ "LEFT JOIN ENCHERES as e on e.no_article=a.no_article\r\n"
		+ "LEFT JOIN UTILISATEURS as bidder on bidder.no_utilisateur=e.no_utilisateur;";

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
	
	private static final String SELECT_BY_ID = BASE_JOIN
			+ "WHERE a.no_article = ? ";

	@Override
	public Article insert(Article article) throws DALException {
		ResultSet rs= null;

		
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);){
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			
			stmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDateDebutEncheres()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getDateFinEncheres()));
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
				while(rs.next()) {
					return createArticleFromResultSet(rs);
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
					//Récupérer informations du vendeur
					int noUtilisateurVendeur=rs.getInt("no_utilisateur");
					String pseudoVendeur=rs.getString("pseudo");
					String nomVendeur=rs.getString("nom");
					String prenomVendeur=rs.getString("prenom");
					String emailVendeur=rs.getString("email");
					String telephoneVendeur=rs.getString("telephone");
					String rueVendeur=rs.getString("rue");
					String codePostalVendeur=rs.getString("code_postal");
					String villeVendeur=rs.getString("ville");
					String motDePasseVendeur=rs.getString("mot_de_passe");
					int creditVendeur=rs.getInt("credit");
					boolean administrateurVendeur=rs.getBoolean("administrateur");
					
					//Récupérer informations de l'encherisseur Bidder
					int noUtilisateurBidder=rs.getInt("bidderNo");
					String pseudoBidder=rs.getString("bidderPseudo");
					String nomBidder=rs.getString("bidderNom");
					String prenomBidder=rs.getString("bidderPrenom");
					String emailBidder=rs.getString("bidderEmail");
					String telephoneBidder=rs.getString("bidderTel");
					String rueBidder=rs.getString("bidderRue");
					String codePostalBidder=rs.getString("bidderCP");
					String villeBidder=rs.getString("bidderVille");
					String motDePasseBidder=rs.getString("bidderMDP");
					int creditBidder=rs.getInt("bidderCredit");
					boolean administrateurBidder=rs.getBoolean("bidderAd");
					
					//Récupérer informations des catégories des articles
					int noCategorie=rs.getInt("no_categorie");
					String libelle=rs.getString("libelle");
										
					//Récupérer informations de l' articles en vente
					int noArticle=rs.getInt("no_article");
					String nomArticle=rs.getString("nom_article");
					String description=rs.getString("description");
					LocalDateTime dateDebutEncheres=LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime());
					LocalDateTime dateFinEncheres=LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime());
					int miseAPrix=rs.getInt("prix_initial");
					int prixVente=rs.getInt("prix_vente");
					String etatVente=rs.getString("etat_vente");

					//Récupérer informations du retrait
					String rueRetrait=rs.getString("rue");
					String codePostalRetrait=rs.getString("code_postal");
					String villeRetrait=rs.getString("ville");
					Article article;
					
					//Récuérer informations des enchères
					int montantEnchere = rs.getInt("montant_enchere");
					LocalDateTime dateEnchereRecupere = null;
					//La colonne date_enchere peut etre vide
					if(rs.getDate("date_enchere")!=null) {
						LocalDateTime dateEnchere = LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime());				
						dateEnchereRecupere=dateEnchere;
					}else {
						LocalDateTime dateEnchere = null;
						dateEnchereRecupere=dateEnchere;					
					}
									
					//Création des objets métiers
					Utilisateur seller=new Utilisateur(noUtilisateurVendeur, pseudoVendeur,  nomVendeur, prenomVendeur,  emailVendeur,  telephoneVendeur, rueVendeur, codePostalVendeur, villeVendeur, motDePasseVendeur, creditVendeur,administrateurVendeur);
					Utilisateur bidder=new Utilisateur(noUtilisateurBidder, pseudoBidder,  nomBidder, prenomBidder,  emailBidder,  telephoneBidder, rueBidder, codePostalBidder, villeBidder, motDePasseBidder, creditBidder,administrateurBidder);
					Categorie cat = new Categorie(noCategorie,libelle);
					Categorie categorieArticle=cat;
					Utilisateur vendeur=seller;
					Utilisateur encherisseur=bidder;
					Article art = new Article(noArticle, nomArticle,  description,  dateDebutEncheres, dateFinEncheres,  miseAPrix,  prixVente,  etatVente,  categorieArticle, vendeur);
					Retrait rt=new Retrait(rueRetrait, codePostalRetrait, villeRetrait, art);
					//Sur le même article, il peut y avoir plusieurs enchères
					Enchere enchere= new Enchere(dateEnchereRecupere, montantEnchere,encherisseur, art);
					articleList.add(art);
					System.out.println("Liste des encherisseurs");
					System.out.println(encherisseur);
					System.out.println("Liste des vendeurs");
					System.out.println(vendeur);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme appel à la dal", e);
		}
		
		return articleList;
	}

	@Override
	public void update(Article userToUpdated) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	private Article createArticleFromResultSet(ResultSet rs) throws SQLException {
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
			vendeur
			// null
		);
		
		Retrait retrait = null;
		if (rs.getString("rue") != null) {
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