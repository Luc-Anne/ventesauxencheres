package fr.eni.ventesauxencheres.controllers.util;

public enum Url {
	// Use : Url.$$$.getUrl()
	
	// Datas
	CSS_STYLE("/css/style.css"),
	CSS_BOOTSTRAP("/css/bootstrap.min.css"),
	CSS_BOOTSTRAP_BUNDLE("/css/bootstrap.bundle.min.js"),
	// Navigation
	HOME("/home"),
	// Utilisateur
	INSCRIPTION("/inscription"),
	DESINSCRIPTION("/moncompte/desinscription"),
	CONNEXION("/connexion"),
	DECONNEXION("/moncompte/deconnexion"),
	COMPTE_PROFIL("/moncompte/profil"),
	// Administrateur
	ADMIN_TABLEAUDEBORD("/admin"),
	// Business
	PROFIL_PUBLIC("/profil/"), //Ajouter un pseudo juste à la suite
	AJOUTER_ARTICLE("/encheres/ajouter"),
	DETAILS_ARTICLE("/encheres"); // le paramètre no_article est nécessaire pour la Servlet	

	private String url;
	
	Url(String url) {
		this.url = url; 
	}

	public String getUrl() {
		return "/ventesauxencheres" + url;
	}

}
