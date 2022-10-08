package fr.eni.ventesauxencheres.controllers.utilisateur.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bll.utilisateur.ProfilMgr;
import fr.eni.ventesauxencheres.bo.dependencies.Adresse;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Errors;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.controllers.util.Url;

/**
 * Servlet implementation class profil
 */
@WebServlet("/moncompte/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Url.COMPTE_PROFIL.getJsp());
		if (rd != null) {
		rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Errors erreurs = new Errors();
		request.setAttribute("erreurs", erreurs);

		Client clientConnecte = (Client) request.getSession().getAttribute("clientConnecte");
		// AdresseDomicile
		Adresse adresseDomicile = new Adresse(
				clientConnecte.getAdresseDomicile().getNoAdresse(),
				request.getParameter("rue"),
				request.getParameter("code_postal"),
				request.getParameter("ville"),
				request.getParameter("pays")
				);
		Client client = new Client(
				clientConnecte.getNoProfil(),
				request.getParameter("pseudo").trim(),
				request.getParameter("courriel").trim(),
				clientConnecte.getDateEnregistrement(),
				clientConnecte.getNoClient(),
				request.getParameter("nom"),
				request.getParameter("prenom"),
				clientConnecte.isActif(),
				clientConnecte.getCredit(),
				request.getParameter("telephone"),
				adresseDomicile
				);

		// Vérifier que les deux mots de passe sont les mêmes
		// Dans le cas ou il y en a un qui a été envoyé
		String motDePasse = request.getParameter("motDePasse");
		if (motDePasse != null && !("".equals(motDePasse))) {
			String motDePasseConfirmation = request.getParameter("motDePasseConfirmation");
			if(!motDePasse.equals(motDePasseConfirmation)) {
				request.getSession().setAttribute("messageGlobal", "Les deux mots de passe ne correspondent pas");
				reafficherProfilQuandErreur(request, response, client, erreurs);
				return;
			}
		}

		try {
			// Modifier client
			ClientMgr.getInstance().modify(client);
			if (motDePasse != null && !("".equals(motDePasse))) {
				ProfilMgr.getInstance().modifyMotDePasse(clientConnecte, motDePasse);
			}
			request.getSession().setAttribute("clientConnecte", client);
			response.sendRedirect(Url.COMPTE_PROFIL.getUrl());
			return;
		} catch (BLLException e) {
			e.printStackTrace();
			if (motDePasse != null && !("".equals(motDePasse))) {
				erreurs.addAll(ProfilMgr.getInstance().invalidCauseMotDePasse(motDePasse));
			}
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
			reafficherProfilQuandErreur(request, response, client, erreurs);
			return;
		}

	}

	private void reafficherProfilQuandErreur(HttpServletRequest request, HttpServletResponse response, Client client, Errors erreurs) throws ServletException, IOException {
		erreurs.addAll(ClientMgr.getInstance().invalidCause(client));
		RequestDispatcher rd = request.getRequestDispatcher(Url.COMPTE_PROFIL.getJsp());
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
