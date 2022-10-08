<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.util.Url" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>Erreur 403</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<div id="messageGlobal">
		<p>Vous n'avez pas la permission d'afficher cette page</p>
	</div>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>