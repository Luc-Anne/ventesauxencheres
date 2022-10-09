package fr.eni.ventesauxencheres.dal.bo.encheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.encheres.Categorie;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.dao.encheres.CategorieDAO;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String GET_CATEGORIE_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ? ";
	private static final String GET_ALL_CATEGORIE ="SELECT * FROM  CATEGORIES";

	@Override
	@Deprecated
	public Categorie insert(Categorie u) throws DALException {
		return null;
	}

	@Override
	@Deprecated
	public Categorie selectById(int id) throws DALException {
		Categorie categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
			PreparedStatement stmt = cnx.prepareStatement(GET_CATEGORIE_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int noCategorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				categorie = new Categorie(noCategorie, libelle);
			}
		} catch (Exception e) {
			throw new DALException("Récupération échoué" ,e);
		}
		return categorie;
	}

	@Override
	@Deprecated
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categories = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(GET_ALL_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS);){

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                int no_categorie = rs.getInt("no_categorie");
                String libelle = rs.getString("libelle");
                categories.add(new Categorie(no_categorie, libelle));
            }

		} catch (SQLException e) {
			throw new DALException("Select All ne fonctionne pas" ,e);
		}
		return categories;
	}

	@Override
	@Deprecated
	public void update(Categorie userToUpdated) throws DALException {

	}

	@Override
	@Deprecated
	public void delete(int id) throws DALException {

	}

}