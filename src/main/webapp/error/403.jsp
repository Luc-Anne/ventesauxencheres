<%@ page import="fr.eni.ventesauxencheres.controllers.Url" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur 404</title>
</head>
<body>
	<p>Vous n'avez pas la permission d'afficher cette page.</p>
	<br>
	<p>Veuillez vous redirigez vers la page d'accueil :</p>
	<a href="${Url.HOME.getUrl()}">Page d'accueil</a>
</body>
</html>