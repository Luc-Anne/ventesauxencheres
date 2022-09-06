package fr.eni.ventesauxencheres.controllers.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class UrlContextInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Url.setContext(sce.getServletContext().getContextPath());
	}

}