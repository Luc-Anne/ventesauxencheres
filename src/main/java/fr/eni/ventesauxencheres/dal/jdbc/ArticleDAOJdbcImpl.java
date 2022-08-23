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
import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ArticleDAO;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String SELECT_ALL="SELECT a.no_article,a.nom_article,a.description,a.date_debut_enchere,a.date_fin_enchere,a.prix_initial,a.prix_vente,a.etat_vente, \r\n"
			+ "c.libelle,c.no_categorie, \r\n"
			+ "a.no_utilisateur,seller.pseudo,seller.nom,seller.prenom,seller.email,seller.mot_de_passe,seller.telephone,seller.rue,seller.code_postal,seller.ville,seller.administrateur,seller.credit, \r\n"
			+ "r.rue,r.ville,r.code_postal \r\n"
			+ "FROM ARTICLES_VENDUS as a \r\n"
			+ "JOIN UTILISATEURS as seller on seller.no_utilisateur=a.no_utilisateur \r\n"
			+ "JOIN CATEGORIES as c on c.no_categorie=a.no_categorie \r\n"
			+ "LEFT JOIN RETRAITS as r  on r.no_article=a.no_article;";
	
	
	
	//INSERT INTO ARTICLES_VENDUS 
	private static final String INSERT 
						="INSERT INTO ARTICLES_VENDUS \r\n"
						+ "(nom_article,"	//1
						+ "description, " //2
						+ "date_debut_enchere," //3
						+ "date_fin_enchere, " //4
						+ "prix_initial, " //5
						+ "no_utilisateur, " //6
						+ "no_categorie) "//7
						+ " VALUES (?,?,?,?,?,?,?);"; 
	

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> articleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				Statement stmt = cnx.createStatement();
				ResultSet rs=stmt.executeQuery(SELECT_ALL);
			){
				while(rs.next()) {
					//Récupérer information du vendeur
					int noUtilisateur=rs.getInt("no_utilisateur");
					String pseudoVendeur=rs.getString("pseudo");
					String nomVendeur=rs.getString("nom");
					String prenomVendeur=rs.getString("prenom");
					String email=rs.getString("email");
					String telephone=rs.getString("telephone");
					String rue=rs.getString("rue");
					String codePostal=rs.getString("code_postal");
					String ville=rs.getString("ville");
					String motDePasse=rs.getString("mot_de_passe");
					int credit=rs.getInt("credit");
					boolean administrateur=rs.getBoolean("administrateur");
					
					//Récupérer information des catégories des articles
					int noCategorie=rs.getInt("no_categorie");
					String libelle=rs.getString("libelle");
										
					//Récupérer information de l' articles en vente
					int noArticle=rs.getInt("no_article");
					String nomArticle=rs.getString("nom_article");
					String description=rs.getString("description");
					LocalDateTime dateDebutEncheres=LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime());
					LocalDateTime dateFinEncheres=LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime());
					int miseAPrix=rs.getInt("prix_initial");
					int prixVente=rs.getInt("prix_vente");
					String etatVente=rs.getString("etat_vente");

					//Récupérer information du retrait
					String rueRetrait=rs.getString("rue");
					String codePostalRetrait=rs.getString("code_postal");
					String villeRetrait=rs.getString("ville");
					Article article;
					
					//Création des objets métiers
					Utilisateur seller=new Utilisateur(noUtilisateur, prenomVendeur,  nomVendeur, prenomVendeur,  email,  telephone, rue, codePostal, ville, motDePasse, credit,administrateur);
					Categorie cat = new Categorie(noCategorie,libelle);
					Categorie categorieArticle=cat;
					Utilisateur vendeur=seller;					
					Article art = new Article(noArticle, nomArticle,  description,  dateDebutEncheres, dateFinEncheres,  miseAPrix,  prixVente,  etatVente,  categorieArticle, vendeur);
					Retrait rt=new Retrait(rueRetrait, codePostalRetrait, villeRetrait, art);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

}