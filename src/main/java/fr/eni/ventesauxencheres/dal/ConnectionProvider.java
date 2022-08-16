package fr.eni.ventesauxencheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class ConnectionProvider {
	
	private static DataSource bdd_VEA;
	
	static {
		try {
			Context context = new InitialContext();
			bdd_VEA = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx/ventes_aux_encheres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection_VAE() throws SQLException {
		Connection cnx = bdd_VEA.getConnection();
		// cnx.setAutoCommit(false); /* TODO Use this line for default */
		return cnx;
	}
	
}
