package fr.eni.ventesauxencheres.controllers;

public enum Url {
	// Use ${Url.$$$.getUrl()} in jsp
	// Use Url.$$$.getUrl() Servlet and jsp
	
	// Datas
	CSS_STYLE("/css/style.css"),
	CSS_BOOTSTRAP("/css/bootstrap.min.css"),
	CSS_BOOTSTRAP_BUNDLE("/css/bootstrap.bundle.min.js"),
	// Navigation
	HOME("/home"),
	// Utilisateur
	INSCRIPTION("/inscription"),
	DESINSCRIPTION("/moncompte/desincription"), // Filter on /moncompte/
	COMPTE_PROFIL("/moncompte/profil"), // Filter on /moncompte/
	CONNEXION("/connexion"),
	DECONNEXION("/moncompte/deconnexion"), // Filter on /moncompte/
	// Administrateur
	ADMIN_TABLEAUDEBORD("/admin"),
	// Business
	PROFIL_PUBLIC("/profil/");
	
	
	private String url;
	
	Url(String url) {
		this.url = url; 
	}

	public String getUrl() {
		return "/ENI-VentesAuxEncheres" + url;
	}
	
	public String getServletUrl() {
		return url;
	}

}
