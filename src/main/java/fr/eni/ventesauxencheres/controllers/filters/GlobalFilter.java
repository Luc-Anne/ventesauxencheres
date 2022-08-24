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
		HttpSession session = ((HttpServletRequest) (request)).getSession();
		// Récupérer un attribut même à travers un sendRedirect
		request.setAttribute("messageGlobal", session.getAttribute("messageGlobal"));
		session.removeAttribute("messageGlobal");
		//Acceptation des cookies si tel est le cas
		if (session.getAttribute("cookieNonAccepte") == null) {
			if (request.getParameter("acceptationCookie") != null) {
				session.setAttribute("cookieNonAccepte", true);
			}
		}
		chain.doFilter(request, response);		
	}
	
}
