<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/main/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf" %>
	<title>${initParam.debutTitre} Tableau de bord</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/main/header.jspf" %>
	<main class="container">
		<div class="mx-auto text-center">
			<h1>Tableau de bord</h1>
		</div>
		<!-- Afficher la liste des utilisateurs avec un lien par utilisateur vers la vue de gestion des utilisateurs /admin/utilisateur -->
	</main>
	<%@ include file="/WEB-INF/main/footer.jspf" %>
</body>
</html>