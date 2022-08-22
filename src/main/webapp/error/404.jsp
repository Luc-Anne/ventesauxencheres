<%@ page import="fr.eni.ventesauxencheres.controllers.Url" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur 404</title>
</head>
<body>
	<p>La page demandée n'existe pas.</p>
	<br>
	<p>Veuillez vous redirigez vers la page d'accueil :</p>
	<a href="${Url.HOME.getUrl()}">Page d'accueil</a>
</body>
</html>