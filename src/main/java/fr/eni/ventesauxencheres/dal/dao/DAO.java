package fr.eni.ventesauxencheres.dal.dao;

import java.util.List;

import fr.eni.ventesauxencheres.exceptions.DALException;

public interface DAO<T> {
	// CRUD
	T insert(T u) throws DALException ;
	T selectById(int id) throws DALException;
	List<T> selectAll() throws DALException ;
	void update(T userToUpdated) throws DALException;
	void delete(int id) throws DALException;
}
