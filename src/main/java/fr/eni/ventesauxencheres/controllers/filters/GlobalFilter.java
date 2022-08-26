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
		DispatcherType.FORWARD
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
		
		// ### Gestion de l'information d'acceptation des cookies dans la session
		// ### (et un cookie si la session est coupé, alias ne pas redemander toutes les 5 minutes)
		System.out.println("Avant traitement Acceptation Cookie - " + session.getAttribute("cookieAccepte"));
		// Dans le cas où on a pas l'information dans la session
		// alors cookieAccepte = null dans la session
		if ((session.getAttribute("cookieAccepte") == null)) {
			String validationUtilisateur = request.getParameter("acceptationCookie");
			if (validationUtilisateur != null) {
				// Si l'utilisateur vient de valider l'acceptation
				// Alors créer un cookie et enregistrer l'information dans la session
				Cookie acceptationCookie = new Cookie("acceptationCookie", "true");
				acceptationCookie.setHttpOnly(true);
				acceptationCookie.setMaxAge(100000);
				httpResponse.addCookie(acceptationCookie);
				session.setAttribute("cookieAccepte", true);
			} else {
				// Aller chercher si l'information n'a pas déjà été enregistré dans un cookie
				// S'il en trouve un, cela veut dire qu'il avait accepté précédemment
				// S'il n'en trouve pas, cela veut dire qu'il n'avait pas accepté précédemment (on laisse en l'état)
				Cookie[] cookies = httpRequest.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("acceptationCookie")) {
							if (cookie.getValue().equals("true")) {
								// Alors créer un cookie et enregistrer l'information dans la session
								Cookie acceptationCookie = new Cookie("acceptationCookie", "true");
								acceptationCookie.setHttpOnly(true);
								acceptationCookie.setMaxAge(100000);
								httpResponse.addCookie(acceptationCookie);
								session.setAttribute("cookieAccepte", true);
							}
						}
					}
				}
			}
		}
		System.out.println("Après traitement Acceptation Cookie - " + session.getAttribute("cookieAccepte") + "\n---");
		// Continuer vers la servlet appelée
		chain.doFilter(request, response);
	}
	
}
