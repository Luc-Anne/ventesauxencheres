package fr.eni.ventesauxencheres.controllers.utilisateur;

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

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(
	dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
	urlPatterns = {"/moncompte/*", "/encheres/article", "/nouvelleVente/add"}
)
public class UtilisateurConnecteRestrictedFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 5641058120626900022L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// TODO DO the same for administrateur ?
		Object client = httpRequest.getSession().getAttribute("clientConnecte");
		if (client == null) {
			((HttpServletResponse)response).sendError(403);
		} else {
			chain.doFilter(request, response);
		}
	}

}
