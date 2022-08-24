package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.eni.ventesauxencheres.dal.ConnectionProvider;

@WebListener
public class ArticleEtatVenteJdbcListener implements ServletContextListener {

	Thread taskEtatVenteJdbc;
	
	public void contextInitialized(ServletContextEvent sce) {
		
        taskEtatVenteJdbc = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Démarrage de taskEtatVenteJdbc");
					while (!taskEtatVenteJdbc.isInterrupted()) {
						Thread.sleep(5000);
						executerProcedureStockee();
					}
				} catch (InterruptedException e) {
					System.out.println("Interruption de taskEtatVenteJdbc");
				}
			}
			
			private static final String updateArticle = "{ call updateArticle() }";
			
			private void executerProcedureStockee() {
				try (Connection cnx = ConnectionProvider.getConnection_VAE()) {
					try (CallableStatement statement = cnx.prepareCall(updateArticle)) {
						statement.execute();
					} catch (SQLException e) {
						//System.out.println("Erreur execution de la prodécure stockée updateArticle");
						System.out.println(e.getMessage());
					}
				} catch (SQLException e) {
					System.out.println("Erreur de connexion");
				}
			}
        });
        
        taskEtatVenteJdbc.start();
    }
	
	public void contextDestroyed(ServletContextEvent sce) { 
        if(taskEtatVenteJdbc != null && taskEtatVenteJdbc.isAlive()) {
        	taskEtatVenteJdbc.interrupt();
        }
   }

}
