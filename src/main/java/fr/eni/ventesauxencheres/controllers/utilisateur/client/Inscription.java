package fr.eni.ventesauxencheres.controllers.utilisateur.client;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.dependencies.AdresseMgr;
import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bll.utilisateur.ProfilMgr;
import fr.eni.ventesauxencheres.bo.dependencies.Adresse;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Errors;
import fr.eni.ventesauxencheres.controllers.util.Url;
import fr.eni.ventesauxencheres.exceptions.BLLException;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Url.INSCRIPTION.getJsp());
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Errors erreurs = new Errors();
		request.setAttribute("erreurs", erreurs);

		// AdresseDomicile
		String pays = "".equals(request.getParameter("pays")) ? AdresseMgr.DEFAULT_PAYS : request.getParameter("pays");
		Adresse adresseDomicile = new Adresse(
				request.getParameter("rue"),
				request.getParameter("code_postal"),
				request.getParameter("ville"),
				pays
				);
		Client client = new Client(
				request.getParameter("pseudo").trim(),
				request.getParameter("courriel").trim(),
				LocalDateTime.now(),
				request.getParameter("nom"),
				request.getParameter("prenom"),
				ClientMgr.DEFAULT_ACTIF,
				ClientMgr.DEFAULT_CREDIT,
				request.getParameter("telephone"),
				adresseDomicile
				);

		// Vérifier que les deux mots de passe sont les mêmes
		String motDePasse = request.getParameter("motDePasse");
		String motDePasseConfirmation = request.getParameter("motDePasseConfirmation");
		if(!motDePasse.equals(motDePasseConfirmation)) {
			request.getSession().setAttribute("messageGlobal", "Les deux mots de passe ne correspondent pas");
			reafficherInscriptionQuandErreur(request, response, client, erreurs);
			return;
		}

		try {
			// Inscrire
			client = ClientMgr.getInstance().save(client, motDePasse);
			request.getSession().setAttribute("messageGlobal", "Bienvenue sur le site !");
			response.sendRedirect(Url.CONNEXION.getUrl());
			return;
		} catch (BLLException e) {
			e.printStackTrace();
			erreurs.addAll(ProfilMgr.getInstance().invalidCauseMotDePasse(motDePasse));
			CharSequence erreurAChercher = "un_profil_pseudo";
			if (e.getCause() != null && e.getCause().getCause() != null) {
				if (e.getCause().getCause().getMessage().contains(erreurAChercher)) {
					erreurs.add("utilisateur.pseudo_deja_pris");
				}
			}
			erreurAChercher = "un_profil_courriel";
			if (e.getCause() != null && e.getCause().getCause() != null) {
				if (e.getCause().getCause().getMessage().contains(erreurAChercher)) {
					erreurs.add("utilisateur.courriel_deja_pris");
				}
			}
			reafficherInscriptionQuandErreur(request, response, client, erreurs);
			return;
		}

	}

	private void reafficherInscriptionQuandErreur(HttpServletRequest request, HttpServletResponse response, Client client, Errors erreurs) throws ServletException, IOException {
		erreurs.addAll(ClientMgr.getInstance().invalidCause(client));
		RequestDispatcher rd = request.getRequestDispatcher(Url.INSCRIPTION.getJsp());
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
