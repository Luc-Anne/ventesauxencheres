package fr.eni.ventesauxencheres.controllers.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.ERROR
	}
	, urlPatterns = {
		"/*"
	}
)
public class GlobalFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 5641058120626900022L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Instanciation des interfaces pratiques
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		
		// Récupérer un attribut même à travers un sendRedirect
		request.setAttribute("messageGlobal", session.getAttribute("messageGlobal"));
		session.removeAttribute("messageGlobal");
		
		// Gestion de l'information d'acceptation des cookies dans la session
		// (et un cookie si la session se coupe avant, alias ne pas redemander toutes les 5 minutes)
		Cookie acceptationCookie = new Cookie("acceptationCookie", "false");
		// Dans le cas où on ne sait pas (aucune information dans la session)
		System.out.println(session.getAttribute("cookieAccepte"));
		if (session.getAttribute("cookieAccepte") == null) {
			String validationUtilisateur = request.getParameter("acceptationCookie");
			if (validationUtilisateur != null) {
				// Si l'utilisateur vient de valider l'acceptation
				// Alors créer un cookie et enregistrer l'information dans la session
				acceptationCookie.setValue("true");
				session.setAttribute("cookieAccepte", true);
			} else {
				// Aller chercher si l'information n'a pas déjà été enregistré dans un cookie
				// S'il en trouve un, cela veut dire qu'il avait accepté précédemment
				// S'il n'en trouve pas, cela veut dire qu'il n'avait pas accepté précédemment (false par défaut)
				Cookie[] cookies = httpRequest.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("acceptationCookie")) {
						if (cookie.getValue().equals("true")) {
							acceptationCookie.setValue("true");
							session.setAttribute("cookieAccepte", true);
						} else {
							// acceptationCookie.setValue("false"); // Valeur par défaut
							session.setAttribute("cookieAccepte", false);
						}
					}
				}
			}
		}
		httpResponse.addCookie(acceptationCookie);
		
		// Continuer vers la servlet appelée
		chain.doFilter(request, response);		
	}
	
}
