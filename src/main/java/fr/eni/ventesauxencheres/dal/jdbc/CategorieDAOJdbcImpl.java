package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Categorie;
import fr.eni.ventesauxencheres.dal.CategorieDAO;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String GET_CATEGORIE_BY_ID = "SELECT no_categorie, libelle FROM dbo.CATEGORIES WHERE no_categorie = ? ";
	private static final String GET_ALL_CATEGORIE ="SELECT * FROM  dbo.CATEGORIES";
	
	@Override
	public Categorie insert(Categorie u) throws DALException {
		return null;
	}

	@Override
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
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categories = new ArrayList<>();
		Categorie categorie = new Categorie();
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
	public void update(Categorie userToUpdated) throws DALException {

	}

	@Override
	public void delete(int id) throws DALException {

	}

}