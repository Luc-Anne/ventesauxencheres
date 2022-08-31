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
/**
 * 
 * Gestion de l'acceptation des cookies par l'utilisateur par l'enregistrement de son choix
 * dans le contexte de session
 *
 */
public class CookieAcceptationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 5641058120626900022L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Instanciation des interfaces pratiques
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		
		// Si l'information manque dans la session
		if ((session.getAttribute("cookieAccepte") == null)) {
			if (request.getParameter("acceptationCookie") != null) {
				// Si l'utilisateur vient de valider l'acceptation
				cookieAccepted(httpResponse, session);
			} else {
				// Sinon aller chercher si l'information n'a pas déjà été enregistré dans un cookie
				Cookie[] cookies = httpRequest.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("acceptationCookie")) {
							if (cookie.getValue().equals("true")) {
								// Si on trouve le cookie ayant enregistré l'information d'acceptation
								cookieAccepted(httpResponse, session);
							}
						}
					}
				}
			}
		}

		chain.doFilter(request, response);
	}
	
	private void cookieAccepted(HttpServletResponse httpResponse, HttpSession session) {
		// Créer un cookie, l'envoyer dans la réponse et enregistrer l'information dans la session
		Cookie acceptationCookie = new Cookie("acceptationCookie", "true");
		acceptationCookie.setHttpOnly(true);
		acceptationCookie.setMaxAge(100000);
		httpResponse.addCookie(acceptationCookie);
		session.setAttribute("cookieAccepte", true);
	}
	
}
