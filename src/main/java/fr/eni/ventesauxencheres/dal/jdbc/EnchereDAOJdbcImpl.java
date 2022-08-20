package fr.eni.ventesauxencheres.dal.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.Enchere;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	@Override
	public List<Enchere> selectAll() {
		List<Enchere> listes = new ArrayList<>();
		
		
		
		return listes;
	}

	@Override
	public Enchere insert(Enchere u) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Enchere userToUpdated) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}