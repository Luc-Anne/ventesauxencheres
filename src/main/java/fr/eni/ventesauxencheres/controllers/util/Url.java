package fr.eni.ventesauxencheres.controllers.util;

public enum Url {
	// CONTEXT from UrlContextInitializer class
	CONTEXT("",""),

	// Datas
	CSS_STYLE("/css/style.css",""),
	CSS_BOOTSTRAP("/css/bootstrap.min.css",""),
	CSS_BOOTSTRAP_BUNDLE("/css/bootstrap.bundle.min.js",""),
	// Navigation
	HOME("/home","main/home.jsp"),
	// Utilisateur
	CONNEXION("/connexion","utilisateur/connexion.jsp"),
	DECONNEXION("/moncompte/deconnexion",""),
		// Client
		INSCRIPTION("/inscription","utilisateur/client/inscription.jsp"),
		DESINSCRIPTION("/moncompte/desinscription",""),
		COMPTE_PROFIL("/moncompte/profil","utilisateur/client/profil.jsp"),
		PROFIL_PUBLIC("/profil/","utilisateur/client/profilPublic.jsp"), //Ajouter un pseudo juste à la suite
		// Administrateur
		ADMIN_TABLEAUDEBORD("/admin","administrateur/tableaudebord.jsp"),
	// Enchères
	AJOUTER_ARTICLE("/encheres/ajouter","encheres/nouvelleVente.jsp"),
	DETAILS_ARTICLE("/encheres","encheres/detailsArticle.jsp"); // le paramètre no_article est nécessaire pour la Servlet

	private String url;
	private String jsp;

	Url(String url, String jsp) {
		this.url = url;
		this.jsp = jsp;
	}

	public static void setContext(String context) {
		CONTEXT.url = context;
	}

	public String getUrl() {
		return CONTEXT.url + url;
	}

	public String getJsp() {
		return "/WEB-INF/" + jsp;
	}

}
