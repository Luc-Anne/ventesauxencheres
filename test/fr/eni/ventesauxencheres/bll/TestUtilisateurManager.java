package fr.eni.ventesauxencheres.bll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;
import fr.eni.ventesauxencheres.dal.UtilisateurDAO;

@WebServlet("/test/TestUtilisateurManager")
public class TestUtilisateurManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";
		
		// Test Method 1
		pw.append("\nMethod updateById : \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : modifier email & mdp \n");
		message = "";		
		  try { Utilisateur user =
		  FactoryDAO.getUtilisateurDAO().selectUtilisateurConnecte("pivoine@campus.fr","azerty2");
		  user.setEmail("peony@flower.com"); user.setMotDePasse("azerty");
		  FactoryDAO.getUtilisateurDAO().update(user);
		  message+="Aucune exception levée. Test OK";
		  
		  } catch (DALException e) { // TODO Auto-generated catch block
		  message+="Erreur BLL. Echec update"; e.printStackTrace(); }
		 
		pw.append(message);
		// Test 2 ###################################################
		pw.append("\nTest 2 : modifier tous les autres champs sauf email & mdp \n");
		message = "";										
			try {
				Utilisateur userBll=UtilisateurManager.getInstance().connexion("peony@flower.com", "azerty");
				System.out.println("Info user de la BDD avant traitement : "+userBll);
				userBll.setNom("PeonynameBLL");
				userBll.setPrenom("PivoinetteBLL");
				UtilisateurManager.getInstance().modify(userBll);
				Utilisateur userBllnouveau=UtilisateurManager.getInstance().connexion("peony@flower.com", "azerty");
				System.out.println("Info user de la BDD après traitement : "+userBllnouveau);
				pw.append("\nTest 2 : Test OK \n");
			} catch (BLLException e) {
				e.printStackTrace();
			}		
		pw.append(message);
		// Test Method 2
		pw.append("\nMethode \n");
		// Test 3 ###################################################
		pw.append("\nTest 3 : \n");
		message = "";

		pw.append(message);
		// Test 4 ###################################################
		pw.append("\nTest 4 : \n");
		message = "";

		pw.append(message);
		// Display test results #####################################
		pw.flush();
	}

}
