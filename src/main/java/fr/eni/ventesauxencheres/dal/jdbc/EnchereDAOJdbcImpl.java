package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	private static final String SELECT_BY_OBJECT = ""
			+ "SELECT * "
			+ "FROM ENCHERES e "
			+ "LEFT JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "WHERE no_article = ? ";
	
	private static final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";
	private static final String UPDATE2 = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ?, no_utilisateur = ? WHERE no_article = ?";
	private static final String SELECT_ALL = ""
			+ "SELECT a.no_article, a.nom_article, a.description, a.date_debut_enchere, a.date_fin_enchere, a.prix_initial, a.prix_vente, a.etat_vente, "
			+ "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, "
			+ "e.date_enchere, e.montant_enchere "
			+ "FROM ENCHERES e "
			+ "LEFT JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "LEFT JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article ";
	private static final String SELECT_BY_ID = SELECT_ALL
			+ "WHERE e.no_article = ? ";
	
	@Override
	public Enchere insert(Enchere enchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(INSERT);) {
				cnx.setAutoCommit(false);
				stmt.setInt(1, enchere.getEncherisseur().getNoUtilisateur());
				stmt.setInt(2, enchere.getArticle().getNoArticle());
				stmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
				stmt.setInt(4, enchere.getMontantEnchere());
				stmt.executeUpdate();
				cnx.commit();
				return enchere;
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur insertion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
	}

	@Override
	public Enchere selectById(int no_article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement statement = cnx.prepareStatement(SELECT_BY_ID)) {
				statement.setInt(1, no_article);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					Utilisateur encherisseur = new Utilisateur(
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
						LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime()),
						rs.getInt("montant_enchere"),
						encherisseur				
					);
				}
			} catch (SQLException e) {
				throw new DALException("Erreur selectById(" + no_article + ")", e);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur Connexion à la base de données", e);
		}
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
	public void update(Enchere enchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement stmt = cnx.prepareStatement(UPDATE);) {
				cnx.setAutoCommit(false);
				stmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
				stmt.setInt(2, enchere.getMontantEnchere());
				stmt.setInt(3, enchere.getEncherisseur().getNoUtilisateur());
				stmt.setInt(4, enchere.getArticle().getNoArticle());
				stmt.executeUpdate();
				cnx.commit();
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur insertion ", e);
			}
		} catch (SQLException e) {
			throw new DALException("Probleme insertion", e);
		}
	}

	@Override
	public void delete(int id) throws DALException {
		
	}
	
	public void remplacerEnchere(Enchere enchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection_VAE();) {
			try (PreparedStatement statementSelectAncienEncherisseur = cnx.prepareStatement(SELECT_BY_ID);) {
				cnx.setAutoCommit(false);
				// Recréditer l'ancien enchérisseur
				statementSelectAncienEncherisseur.setInt(1, enchere.getArticle().getNoArticle());
				ResultSet rs = statementSelectAncienEncherisseur.executeQuery();
				if(rs.next()) {
					// Récupérer l'ancienEnchérisseur
					Utilisateur ancienEncherisseur = new Utilisateur(
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
					// Lui RENDRE le crédit de l'enchère qu'il avait passé
					ancienEncherisseur.setCredit(ancienEncherisseur.getCredit() + rs.getInt("montant_enchere"));
					// Sauvegarder la modification
					try (PreparedStatement statementUpdateAncienEncherisseur = cnx.prepareStatement(
							  "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?,telephone=?, "
							+ "rue=? ,code_postal=?,ville=? ,mot_de_passe=?,credit=? ,administrateur=? "
							+ "WHERE no_utilisateur=? ");) {
						statementUpdateAncienEncherisseur.setString(1, ancienEncherisseur.getPseudo());
						statementUpdateAncienEncherisseur.setString(2, ancienEncherisseur.getNom());
						statementUpdateAncienEncherisseur.setString(3, ancienEncherisseur.getPrenom());
						statementUpdateAncienEncherisseur.setString(4, ancienEncherisseur.getEmail());
						statementUpdateAncienEncherisseur.setString(5, ancienEncherisseur.getTelephone());
						statementUpdateAncienEncherisseur.setString(6, ancienEncherisseur.getRue());
						statementUpdateAncienEncherisseur.setString(7, ancienEncherisseur.getCodePostal());
						statementUpdateAncienEncherisseur.setString(8, ancienEncherisseur.getVille());
						statementUpdateAncienEncherisseur.setString(9, ancienEncherisseur.getMotDePasse());
						statementUpdateAncienEncherisseur.setInt(10, ancienEncherisseur.getCredit());
						statementUpdateAncienEncherisseur.setBoolean(11, ancienEncherisseur.isAdministrateur());
						statementUpdateAncienEncherisseur.setInt(12, ancienEncherisseur.getNoUtilisateur());
						statementUpdateAncienEncherisseur.executeUpdate();
						// Récupérer le nouveauEnchérisseur
						Utilisateur nouvelEncherisseur = enchere.getEncherisseur();
						// Lui ENLEVER le crédit de l'enchère qu'il avait passé
						if (ancienEncherisseur.equals(nouvelEncherisseur)) {
							// Lui rajouter ce qu'on vient de lui enlever précédemment si c'est le même enchérisseur
							// Car la prochaine mise à jour de l'utilisateur va annuler celle qu'on vient de faire
							nouvelEncherisseur.setCredit(nouvelEncherisseur.getCredit() + rs.getInt("montant_enchere"));
						}
						nouvelEncherisseur.setCredit(nouvelEncherisseur.getCredit() - enchere.getMontantEnchere());
						// Sauvegarder la modification
						try (PreparedStatement statementUpdateNouvelEncherisseur = cnx.prepareStatement(
								  "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?,telephone=?, "
								+ "rue=? ,code_postal=?,ville=? ,mot_de_passe=?,credit=? ,administrateur=? "
								+ "WHERE no_utilisateur=? ");) {
							statementUpdateNouvelEncherisseur.setString(1, nouvelEncherisseur.getPseudo());
							statementUpdateNouvelEncherisseur.setString(2, nouvelEncherisseur.getNom());
							statementUpdateNouvelEncherisseur.setString(3, nouvelEncherisseur.getPrenom());
							statementUpdateNouvelEncherisseur.setString(4, nouvelEncherisseur.getEmail());
							statementUpdateNouvelEncherisseur.setString(5, nouvelEncherisseur.getTelephone());
							statementUpdateNouvelEncherisseur.setString(6, nouvelEncherisseur.getRue());
							statementUpdateNouvelEncherisseur.setString(7, nouvelEncherisseur.getCodePostal());
							statementUpdateNouvelEncherisseur.setString(8, nouvelEncherisseur.getVille());
							statementUpdateNouvelEncherisseur.setString(9, nouvelEncherisseur.getMotDePasse());
							statementUpdateNouvelEncherisseur.setInt(10, nouvelEncherisseur.getCredit());
							statementUpdateNouvelEncherisseur.setBoolean(11, nouvelEncherisseur.isAdministrateur());
							statementUpdateNouvelEncherisseur.setInt(12, nouvelEncherisseur.getNoUtilisateur());
							statementUpdateNouvelEncherisseur.executeUpdate();
							
							// Update ENCHERE avec la nouvelle enchere
							try (PreparedStatement statementUpdateEnchere = cnx.prepareStatement(UPDATE2);) {
								statementUpdateEnchere.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
								statementUpdateEnchere.setInt(2, enchere.getMontantEnchere());
								statementUpdateEnchere.setInt(3, enchere.getEncherisseur().getNoUtilisateur());
								statementUpdateEnchere.setInt(4, enchere.getArticle().getNoArticle());
								statementUpdateEnchere.executeUpdate();
								// Si tout se passe bien, commit
								cnx.commit();
								return;
							} catch (SQLException e) {
								cnx.rollback();
								throw new DALException("Erreur1", e);
							}
						} catch (SQLException e) {
							cnx.rollback();
							throw new DALException("Erreur2", e);
						}
					} catch (SQLException e) {
						cnx.rollback();
						throw new DALException("Erreur3", e);
					}
				} else {
					throw new SQLException("Utilisateur de l'enchère précédente vide");
				}
			} catch (SQLException e) {
				cnx.rollback();
				throw new DALException("Erreur4", e);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur Connexion à la base de données", e);
		}
	}

}