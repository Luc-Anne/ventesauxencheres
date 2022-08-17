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

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
  
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//(pseudo, nom,prenom, email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("password");
		String credit = request.getParameter("credit");
		String administrateur = request.getParameter("status");
		
		//Utilisateur u = new Utilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur);

		Utilisateur u = new Utilisateur();
		try {
			boolean entergistre = UtilisateurManager.getInstance().inscription(u);
			
			if (entergistre) {
				session.setAttribute("sucessMessage", "Inscription réussie ");
				
				//faire une redirection
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
				
				if (rd != null) {
					rd.forward(request, response);
				}

			} else {
				session.setAttribute("erreurMesage", "Inscription Echoué");
				
			}
		} catch (BLLException e) {
			
			e.printStackTrace();
		}
		
		

	
	}

}
