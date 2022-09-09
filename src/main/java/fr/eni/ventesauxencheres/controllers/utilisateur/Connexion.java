package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bll.utilisateur.ProfilMgr;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("motDePasse");

		try {
			String typeOfProfil = ProfilMgr.getInstance().typeOfProfil(email, password);
			HttpSession session = request.getSession();
			if (typeOfProfil.equals("Client")) {
				Client client = ClientMgr.getInstance().connexion(email, password);
				session.setAttribute("clientConnecte", client);
				// Passer un attribut à travers un sendRedirect
				session.setAttribute("messageGlobal", "Bonjour " + client.getPrenom() + " " + client.getNom());
				response.sendRedirect(Url.HOME.getUrl());
			} else if (typeOfProfil.equals("Administrateur")) {
				// TODO When Administrateur will be operational
				response.sendRedirect(Url.ADMIN_TABLEAUDEBORD.getUrl());
			} else {
				session.setAttribute("messageGlobal", "Email ou mot de passe incorrect");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/connexion.jsp");
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}