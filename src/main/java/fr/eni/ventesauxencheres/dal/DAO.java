package fr.eni.ventesauxencheres.dal;

import java.util.List;

public interface DAO<T> {
	// CRUD
	T insert(T u) throws DALException ;
	T selectById(int id) throws DALException;
	List<T> selectAll() throws DALException ;
	void update(T userToUpdated) throws DALException;
	void delete(int id) throws DALException;
}
