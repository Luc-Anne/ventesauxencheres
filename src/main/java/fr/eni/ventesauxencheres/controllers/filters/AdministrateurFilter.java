package fr.eni.ventesauxencheres.controllers.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.controllers.Url;

/**
 * Servlet Filter implementation class AdministrateurFilter
 */
@WebFilter(
		dispatcherTypes = {
			DispatcherType.REQUEST, 
			DispatcherType.FORWARD
		}
		, urlPatterns = {
			"/admin/*",
			"/test/*"
		}
)
public class AdministrateurFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = -430253391637810057L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Faire appel à la méthode session de l'objet de type HttpServletRequest
		//transformer l objet ServletRequest request en type HttpServletRequest
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		Object utilisateur = httpRequest.getSession().getAttribute("utilisateurConnecte");
		
		if (utilisateur == null || !((Utilisateur)utilisateur).isAdministrateur()) {
			// Ici vue redirigee vers la vue home, mais l'url avec profil est conservé
			// httpRequest.getRequestDispatcher("/home").forward(httpRequest, response); 
			// Privilegier une redirection vers la servlet home
			HttpServletResponse httpResponse=(HttpServletResponse) response;
			httpResponse.sendRedirect(Url.HOME.getUrl());
		}else {
			chain.doFilter(request, response);
		}			
	}

}
