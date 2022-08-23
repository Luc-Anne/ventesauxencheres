<%@ page import="fr.eni.ventesauxencheres.controllers.Url" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>Erreur 404</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
		<p>Le serveur a rencontré un problème.</p>
		<br>
		<p>Veuillez vous redirigez vers la page d'accueil :</p>
		<a href="${Url.HOME.getUrl()}">Page d'accueil</a>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>