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
 *  Récupérer un attribut de communication globale si un message a été émis dans une servlet
 *  (même à travers un sendRedirect), le transmettre à la réponse puis la supprimer de la session
 *  pour qu'il ne s'affiche qu'une fois
 */
public class GlobalFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 5641058120626900022L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		request.setAttribute("messageGlobal", session.getAttribute("messageGlobal"));
		session.removeAttribute("messageGlobal");
	}
	
}
