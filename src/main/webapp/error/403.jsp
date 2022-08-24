<%@ page import="fr.eni.ventesauxencheres.controllers.Url" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>Erreur 404</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<div id="messageGlobal">
		<p>Vous n'avez pas la permission d'afficher cette page</p>
	</div>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>