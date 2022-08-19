<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Accueil</title>
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<!--  -->

<!-- Test Connexion  -->
<p> ${sessionScope.utilisateurConnecte.nom } </p>
<p> ${sessionScope.utilisateurConnecte.prenom } </p>
<p> ${sessionScope.utilisateurConnecte.telephone } </p>
<p> ${messageSucces} </p>
<!--  -->
<form action="${pageContext.request.contextPath}/moncompte/desinscription" method="post">
	<button class="btn btn-danger">Delete</button>
</form>



<%@ include file="/WEB-INF/fragments/main.jspf" %>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>