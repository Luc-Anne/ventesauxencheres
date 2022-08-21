package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Article;
import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.ArticleDAO;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String SELECT_ALL="SELECT a.no_article,a.nom_article,a.description,a.date_debut_enchere,a.date_fin_enchere,a.prix_initial,a.prix_vente,a.etat_vente,\r\n"
			+ "c.libelle,c.no_categorie,\r\n"
			+ "a.no_utilisateur,seller.pseudo,seller.nom,seller.prenom,seller.email,seller.telephone,seller.rue,seller.code_postal,seller.ville,seller.administrateur,seller.credit,\r\n"
			+ "r.rue,r.ville,r.code_postal\r\n"
			+ "FROM ARTICLES_VENDUS as a\r\n"
			+ "JOIN UTILISATEURS as seller on seller.no_utilisateur=a.no_utilisateur\r\n"
			+ "JOIN CATEGORIES as c on c.no_categorie=a.no_categorie\r\n"
			+ "LEFT JOIN RETRAITS as r  on r.no_article=a.no_article;";

	@Override
	public Article insert(Article u) throws DALException {
		// TODO Auto-generated method stub
		return null;
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
					String pseudo=rs.getString("pseudo");
					String nom=rs.getString("nom");
					String prenom=rs.getString("prenom");
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
					int noArticle=rs.getInt("credit");
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
					Utilisateur seller=new Utilisateur(noUtilisateur, pseudo,  nom, prenom,  email,  telephone, rue, codePostal, ville, motDePasse, credit,administrateur);
					Categorie cat = new Categorie(noCategorie,libelle);
					Categorie categorieArticle=cat;
					Utilisateur vendeur=seller;					
					Article art = new Article( nomArticle,  description,  dateDebutEncheres, dateFinEncheres,  miseAPrix,  prixVente,  etatVente,  categorieArticle, vendeur);
					Retrait rt=new Retrait(rueRetrait, codePostalRetrait, villeRetrait, art);
					articleList.add(art);
					System.out.println(art);
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