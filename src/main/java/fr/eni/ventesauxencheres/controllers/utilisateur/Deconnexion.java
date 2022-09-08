package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.controllers.util.Url;


@WebServlet("/moncompte/deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO When Administrateur will be operational
		request.getSession().removeAttribute("clientConnecte");
		request.getSession().setAttribute("messageGlobal", "Vous avez été déconnecté");
		response.sendRedirect(Url.HOME.getUrl());
	}

}
