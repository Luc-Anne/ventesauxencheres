package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Retrait;
import fr.eni.ventesauxencheres.dal.ConnectionProvider;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String  INSERT_LIEU ="INSERT INTO dbo.RETRAITS (no_article,rue, code_postal , ville) VALUES (?,?,?,?)";
	
	@Override
	public Retrait insert(Retrait u) throws DALException {
		Retrait lieuDeRetrait = new Retrait();
		
		try (Connection cnx = ConnectionProvider.getConnection_VAE();
				PreparedStatement stmt = cnx.prepareStatement(INSERT_LIEU);){
			stmt.setInt(1, lieuDeRetrait.getId());
			stmt.setString(2, u.getRue());
			stmt.setString(3, u.getCodePostal());
			stmt.setString(4, u.getCodePostal());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Insert lieu de retrait échoué",e);
		}
		return lieuDeRetrait;
	}

	@Override
	public Retrait selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Retrait userToUpdated) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

}