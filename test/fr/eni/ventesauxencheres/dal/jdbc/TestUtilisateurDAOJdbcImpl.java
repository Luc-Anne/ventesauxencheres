package fr.eni.ventesauxencheres.dal.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bo.Utilisateur;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

@WebServlet("/test/TestUtilisateurDAOJdbcImpl")
public class TestUtilisateurDAOJdbcImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";		
		// Test Method 1
		pw.append("\nMethod updateById : \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : sans modification de l'utilisateur\n");
		message = "";
		//Il faut un utilisateur pour tester
		try {
			Utilisateur user = FactoryDAO.getUtilisateurDAO().selectUtilisateurConnecte("pivoine@campus.fr","azerty2");
			if (user != null) {
				FactoryDAO.getUtilisateurDAO().update(user);
				message+="Aucune exception levée. Test OK";
			}else {
				message+="utilisateur non trouvé. Test incomplet";
			}

			
		} catch (DALException e) {
			e.printStackTrace();
		}

		pw.append(message);
		// Test 2 ###################################################
		pw.append("\nTest 2 : modification 1 champ : pseudo \n");
		message = "";
		try {
			Utilisateur user = FactoryDAO.getUtilisateurDAO().selectUtilisateurConnecte("pivoine@campus.fr","azerty2");
			if (user != null) {
				user.setPseudo("PivModifiee");
				FactoryDAO.getUtilisateurDAO().update(user);				
				message+="Aucune exception levée.Pseudo Modifié Test OK";
			}else {
				message+="utilisateur non trouvé. Test incomplet";
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}		
		// Test 3 ###################################################
		
		  pw.append("\nTest 3 : modification plusieurs champs :prenom, mdp,email \n");
		  message = ""; 
		  try { Utilisateur user =  FactoryDAO.getUtilisateurDAO().connexion("pivoine@campus.fr","azerty2"); 
			  if (user != null) { 
				  user.setPrenom("Pivoinita"); user.setEmail("piv@modif.fr");
				  user.setMotDePasse("xcvvb123"); FactoryDAO.getUtilisateurDAO().update(user);
				  message+="Aucune exception levée. prenom, mdp,email modifiés ! Test OK";
			  }else { 
				  message+="utilisateur non trouvé. Test incomplet"; 
				  }		  
		  } catch (DALException e) { 
		  message = ""; 
		  try { Utilisateur user = FactoryDAO.getUtilisateurDAO().selectUtilisateurConnecte("pivoine@campus.fr","azerty2"); 
			  if (user != null) { 
				  user.setPrenom("Pivoinita"); user.setEmail("piv@modif.fr");
				  user.setMotDePasse("xcvvb123"); FactoryDAO.getUtilisateurDAO().update(user);
				  message+="Aucune exception levée. prenom, mdp,email modifiés ! Test OK";
			  }else {
				  message+="utilisateur non trouvé. Test incomplet"; 
				  }	  		  
		  } catch (DALException e1) {
			  e1.printStackTrace(); 
		  } 
		  pw.append(message);
		 
		// Test 4 ###################################################
		pw.append("\nTest 4 : modification plusieurs champs : nom,rue,ville, code postal,telephone \n");
		message = "";
		try {
			Utilisateur user = FactoryDAO.getUtilisateurDAO().selectUtilisateurConnecte("peony@flower.com","azerty");
			if (user != null) {
				user.setNom("BlancheDAL");
				user.setCodePostal("75000");
				user.setVille("FlowerCityDAL");
				user.setTelephone("02787777777");
				user.setRue("8 rue des pétales");
				FactoryDAO.getUtilisateurDAO().update(user);				
				message+="Aucune exception levée.  Test OK";
			}else {
				message+="utilisateur non trouvé. Test incomplet";
			}			
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		pw.append(message);		
		// Test Method 2
		pw.append("\nMethod 2:  \n");
		message = "";	
		
		// Test 5 ###################################################
		pw.append("\nTest 3 : Afficher tous les vendeurs \n");
		
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
	}
