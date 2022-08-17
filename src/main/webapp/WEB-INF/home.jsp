<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>HOME PAGE</p>
<!-- Test Connexion  -->
<p> ${sessionScope.utilisateurConnecte.nom } </p>
<p> ${sessionScope.utilisateurConnecte.prenom } </p>
<p> ${sessionScope.utilisateurConnecte.telephone } </p>
<p> ${messageSucces} </p>
<!--  -->
<!-- Test Profil -->
<a href="${pageContext.request.contextPath}/utilisateur/profil">Mon profil</a>
<!--  -->
</body>
</html>