package fr.eni.ventesauxencheres.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.eni.ventesauxencheres.controllers.util.Url;

@WebListener
public class UrlContextInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Url.setContext(sce.getServletContext().getContextPath());
		// Destroy is immediatly
		this.contextDestroyed(sce);
	}

}