package fr.eni.ventesauxencheres.controllers.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ventesauxencheres.bll.AdresseManager;
import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.ClientManager;
import fr.eni.ventesauxencheres.bll.ProfilManager;
import fr.eni.ventesauxencheres.bo.Adresse;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.controllers.util.Errors;
import fr.eni.ventesauxencheres.controllers.util.Url;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Errors erreurs = new Errors();
		request.setAttribute("erreurs", erreurs);

		Client client = new Client(
				request.getParameter("pseudo").trim(),
				request.getParameter("courriel").trim(),
				request.getParameter("nom"),
				request.getParameter("prenom"),
				ClientManager.DEFAULT_CREDIT,
				request.getParameter("telephone")
				);
		// AdresseDomicile
		String pays = "".equals(request.getParameter("pays")) ? AdresseManager.DEFAULT_PAYS : request.getParameter("pays");
		Adresse adresseDomicile = new Adresse(
				request.getParameter("rue"),
				request.getParameter("code_postal"),
				request.getParameter("ville"),
				pays
				);
		client.setAdresseDomicile(adresseDomicile);

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
			client = ClientManager.getInstance().save(client, motDePasse);
			// Connecte
			request.getSession().setAttribute("clientConnecte", client);
			request.getSession().setAttribute("messageGlobal", "Bienvenue sur le site !");
			response.sendRedirect(Url.HOME.getUrl());
			return;
		} catch (BLLException e) {
			e.printStackTrace();
			erreurs.addAll(ClientManager.getInstance().invalidCause(client));
			erreurs.addAll(ProfilManager.getInstance().invalidCauseMotDePasse(motDePasse));
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
		erreurs.addAll(ClientManager.getInstance().invalidCause(client));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/utilisateur/inscription.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

}
