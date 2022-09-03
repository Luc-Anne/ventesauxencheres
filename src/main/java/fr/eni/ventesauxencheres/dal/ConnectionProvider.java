package fr.eni.ventesauxencheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class ConnectionProvider {

	private static DataSource bdd_SQLSERVER_ENIVEA;
	private static DataSource bdd_MARIADB_ENIVEA;

	static {
		try {
			Context context = new InitialContext();
			bdd_SQLSERVER_ENIVEA = (DataSource)context.lookup("java:comp/env/jdbc/sqlServer/pool_cnx_ENIVAE");
			System.out.println("Using bdd_SQLSERVER_ENIVEA");
		} catch (NamingException e) {
			//e.printStackTrace();
		}
		try {
			Context context = new InitialContext();
			bdd_MARIADB_ENIVEA = (DataSource)context.lookup("java:comp/env/jdbc/mariaDB/pool_cnx_ENIVAE");
			System.out.println("Using bdd_MARIADB_ENIVEA");
		} catch (NamingException e) {
			//e.printStackTrace();
		}
	}

	public static Connection getConnection_VAE() throws SQLException {
		if (bdd_SQLSERVER_ENIVEA != null) {
			return bdd_SQLSERVER_ENIVEA.getConnection();
		}
		if (bdd_MARIADB_ENIVEA != null) {
			return bdd_MARIADB_ENIVEA.getConnection();
		}
		return null;
	}

}
