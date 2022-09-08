package fr.eni.ventesauxencheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static DataSource bdd_MARIADB_ENIVEA;

	static {
		try {
			Context context = new InitialContext();
			bdd_MARIADB_ENIVEA = (DataSource)context.lookup("java:comp/env/jdbc/mariaDB/pool_cnx_ENIVAE");
			System.out.println("Contexte de base de données : MARIADB");
		} catch (NamingException e) {
			//e.printStackTrace();
		}
	}

	public static Connection getConnection_VAE() throws SQLException {
		return bdd_MARIADB_ENIVEA.getConnection();
	}

}
