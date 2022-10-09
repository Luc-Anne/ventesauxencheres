<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.util.Url" %>
<%@ include file="/WEB-INF/main/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf" %>
	<title>Erreur 500</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/main/header.jspf" %>
	<div id="messageGlobal">
		<p>Le serveur a rencontré un problème</p>
	</div>
	<%@ include file="/WEB-INF/main/footer.jspf" %>
</body>
</html>