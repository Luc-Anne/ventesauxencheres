<%@ page import="fr.eni.ventesauxencheres.controllers.Url" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur 500</title>
</head>
<body>
	<p>Le serveur a rencontr� un probl�me.</p>
	<br>
	<p>Veuillez vous redirigez vers la page d'accueil :</p>
	<a href="${Url.HOME.getUrl()}">Page d'accueil</a>
</body>
</html>