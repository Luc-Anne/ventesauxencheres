package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.UtilisateurManager;
import fr.eni.ventesauxencheres.bo.Utilisateur;

@WebServlet("/utilisateur/desinscription")
public class Desinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher rd = null;

		Utilisateur uc = (Utilisateur) session.getAttribute("utilisateurConnecte");
		
		int id = uc.getNo_utilisateur();
		
//		System.out.println(uc);
		
		try {
			if (UtilisateurManager.getInstance().deleteById(id)) {
				session.invalidate();
				session.setAttribute("sucessDelete", "Suppression de l'utilisateur réussie");
				rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
				if (rd != null) {
					rd.forward(request, response);
				} else {
					session.setAttribute("sucessDelete", "Suppression de l'utilisateur réussie");
					rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
					if (rd != null) {
						rd.forward(request, response);
					}
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
