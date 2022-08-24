<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<c:if test="${not empty utilisateur}">
		<title>${initParam.debutTitre} Profil de ${utilisateur.pseudo}</title>
	</c:if>
</head>

<body class="bg-light">
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
		<div class="mx-auto text-center">
			<h1>Profil de ${utilisateur.pseudo}</h1>
		</div>
		<c:if test="${not empty utilisateur}">
			<ul>
				<li>${utilisateur.nom}</li>
				<li>${utilisateur.prenom}</li>
				<li>${utilisateur.email}</li>
				<li>${utilisateur.telephone}</li>
				<li>${utilisateur.rue}</li>
				<li>${utilisateur.codePostal}</li>
				<li>${utilisateur.ville}</li>
			</ul>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>