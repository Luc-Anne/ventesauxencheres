package fr.eni.ventesauxencheres.controllers.administrateur;

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

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;

/**
 * Servlet Filter implementation class AdministrateurFilter
 */
@WebFilter(
	dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
	urlPatterns = {"/admin/*", "/test/*"}
)
public class AdminRestrictedFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = -430253391637810057L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// TODO DO the same for administrateur ?
		Object administrateur = httpRequest.getSession().getAttribute("administrateurConnecte");
		if (administrateur == null) {
			((HttpServletResponse)response).sendError(403);
		} else {
			chain.doFilter(request, response);
		}
	}

}
