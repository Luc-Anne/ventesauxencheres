package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String CONNEXION = "SELECT * FROM dbo.UTILISATEURS WHERE email = ? AND mot_de_passe = ? ";
	private static final String INSCRIPTION = "INSERT INTO UTILISATEURS	(pseudo, nom,prenom, email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "delete from UTILISATEURS where no_utilisateur = ?";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?,telephone=?, "
			+ "rue=? ,code_postal=?,ville=? ,mot_de_passe=?,credit=? ,administrateur=? " + "WHERE no_utilisateur=?; ";

	private static final String GET_UTILISATEUR_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit from UTILISATEURS where no_utilisateur = ?";

	private static final String AFFICHER_LIST_UTILISATEURS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit from UTILISATEURS";

	private static final String CHECK_UTILISATEUR = "";
	private static final String SELECT_ALL_SELLER="SELECT u.no_utilisateur,u.pseudo,u.telephone,a.no_article,a.nom_article,a.description,\r\n"
			+ "a.prix_initial,a.prix_vente,a.date_debut_enchere,a.date_fin_enchere ,a.no_categorie,a.etat_vente,\r\n"
			+ "r.rue,r.code_postal,r.ville \r\n"
			+ "FROM UTILISATEURS as u \r\n"
			+ "JOIN ARTICLES_VENDUS as a on u.no_utilisateur=a.no_utilisateur \r\n"
			+ "JOIN RETRAITS as r on a.no_article=r.no_article;";

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

		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(INSCRIPTION);) {
				// cnx.setAutoCommit(false);
				// (pseudo, nom,prenom,
				// email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
				stmt.setString(1, u.getPseudo());
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
				// cnx.rollback();
				throw new DALException("Erreur insertion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
		return enregistre;

	}

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

	@Override
	public Utilisateur update(Utilisateur userToUpdated) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(UPDATE);) {
				cnx.setAutoCommit(false);
				stmt.setString(1, userToUpdated.getPseudo());
				stmt.setString(2, userToUpdated.getNom());
				stmt.setString(3, userToUpdated.getPrenom());
				stmt.setString(4, userToUpdated.getEmail());
				stmt.setString(5, userToUpdated.getTelephone());
				stmt.setString(6, userToUpdated.getRue());
				stmt.setString(7, userToUpdated.getCodePostal());
				stmt.setString(8, userToUpdated.getVille());
				stmt.setString(9, userToUpdated.getMotDePasse());
				stmt.setInt(10, userToUpdated.getCredit());
				stmt.setBoolean(11, userToUpdated.isAdministrateur());
				stmt.setInt(12, userToUpdated.getNoUtilisateur());
				stmt.executeUpdate();
				cnx.commit();
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Probleme connexion", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme connexion", e);
		}
		return userToUpdated;

	}

	@Override
	public Utilisateur getUtilisateurById(int id) throws DALException {
		Utilisateur utilisateur = null;
		ResultSet rs = null;
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(GET_UTILISATEUR_BY_ID);){
			
			

		} catch (SQLException e) {
			throw new DALException("Probleme connexion", e);
		} 
		return utilisateur;
	}

	@Override

	public List<Utilisateur> getAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateursExistants = new ArrayList<Utilisateur>();

		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(AFFICHER_LIST_UTILISATEURS);){

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return listeUtilisateursExistants;

	}

	@Override
	public Utilisateur checkUtilisateur(String email, String password) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAllSellers() throws DALException {
		List<Utilisateur> sellerList=new ArrayList<Utilisateur>();
		List<Article> artToSaleList=new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				Statement stmt = cnx.createStatement();
				ResultSet rs=stmt.executeQuery(SELECT_ALL_SELLER);){
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
					boolean administrateur=rs.getBoolean("administrateur");;
					
					//Récupérer information des catégories des articles
					int noCategorie=rs.getInt("no_categorie");
					String libelle=rs.getString("libelle");
										
					//Récupérer information de l' articles en vente
					int noArticle=rs.getInt("credit");
					String nomArticle=rs.getString("nom_article");
					String description=rs.getString("description");
					LocalDateTime dateDebutEncheres=LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime());
					LocalDateTime dateFinEncheres=LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime());
					int miseAPrix=rs.getInt("prix_initital");
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
					
					//Ajout du vendeur à la liste des vendeurs
					sellerList.add(seller);					
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return sellerList;
	}
	
	

//	
//	public Utilisateur checkUtilisateur (String email, String password) throws DALException {
//		Utilisateur u = null;
//		
//		
//		try (Connection cnx = ConnectionProvider.getConnection_VAE();){
//			try (PreparedStatement stmt = cnx.prepareStatement(CHECK_UTILISATEUR);){
//				
//				
//				
//				
//				cnx.setAutoCommit(false);
//				stmt.setString(1, email);
//				stmt.setString(2, password);
//
//				ResultSet resultSet = stmt.executeQuery();
//				if (resultSet.next()) {
//					u = new Utilisateur(resultSet.getInt("no_utilisateur"), resultSet.getString("pseudo"),
//							resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),
//							resultSet.getString("telephone"), resultSet.getString("rue"),
//							resultSet.getString("code_postal"), resultSet.getString("ville"),
//							resultSet.getString("mot_de_passe"), resultSet.getInt("credit"),
//							resultSet.getBoolean("administrateur"));
//					cnx.commit();
//			}
//				
//			} catch (Exception e) {
////				cnx.rollback();
//				throw new DALException("Probleme checkUtilisateur", e);
//			}
//			
//		} catch (Exception e) {
//			throw new DALException("Probleme checkUtilisateur", e);
//		}
//		
//		return u;
//	}
//	

}