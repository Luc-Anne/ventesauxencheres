package fr.eni.ventesauxencheres.controllers.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 * Définit l'encodage UTF-8 pour toute requête entrante.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	private String encoding = "utf-8";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// place your code here
		request.setCharacterEncoding(encoding);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String encodingParam = fConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			this.encoding = encodingParam;
		}
	}

}
