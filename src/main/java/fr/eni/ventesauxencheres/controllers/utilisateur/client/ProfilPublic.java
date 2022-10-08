package fr.eni.ventesauxencheres.controllers.utilisateur.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;

/**
 * Servlet implementation class profil
 */
@WebServlet("/profil/*")
public class ProfilPublic extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudoProfilDemande = request.getPathInfo().substring(1);
		Client client = null;
		try {
			client = ClientMgr.getInstance().getByPseudo(pseudoProfilDemande);
			if (client == null) {
				response.sendError(404);
			} else {
				request.setAttribute("client", client);
				RequestDispatcher rd = request.getRequestDispatcher(Url.PROFIL_PUBLIC.getJsp());
				if (rd != null) {
					rd.forward(request, response);
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}