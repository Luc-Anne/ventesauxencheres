package fr.eni.ventesauxencheres.controllers.util;

public enum Url {
	// CONTEXT from UrlContextInitializer class
	CONTEXT("",""),

	// Datas
	CSS_STYLE("/css/style.css",""),
	CSS_BOOTSTRAP("/css/bootstrap.min.css",""),
	CSS_BOOTSTRAP_BUNDLE("/css/bootstrap.bundle.min.js",""),
	// Navigation
	HOME("/home","home.jsp"),
	// Utilisateur
	INSCRIPTION("/inscription","utilisateur/inscription.jsp"),
	DESINSCRIPTION("/moncompte/desinscription",""),
	CONNEXION("/connexion","utilisateur/connexion.jsp"),
	DECONNEXION("/moncompte/deconnexion",""),
	COMPTE_PROFIL("/moncompte/profil","utilisateur/profil.jsp"),
	// Administrateur
	ADMIN_TABLEAUDEBORD("/admin","administrateur/tableaudebord.jsp"),
	// Business
	PROFIL_PUBLIC("/profil/","utilisateur/profilPublic.jsp"), //Ajouter un pseudo juste à la suite
	AJOUTER_ARTICLE("/encheres/ajouter","article/nouvelleVente.jsp"),
	DETAILS_ARTICLE("/encheres","business/detailsArticle.jsp"); // le paramètre no_article est nécessaire pour la Servlet

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
