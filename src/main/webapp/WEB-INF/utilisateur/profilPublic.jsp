<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<c:if test=${empty utilisateur}>
		<title>${initParam.debutTitre} Aucun profil</title>
	</c:if>
	<c:if test=${not empty utilisateur}>
		<title>${initParam.debutTitre} Profil de ${utilisateur.pseudo}</title>
	</c:if>
</head>

<body class="bg-light">
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main>
		<c:if test=${empty utilisateur}>
			<p>Ce pseudo n'est pas utilisé.</p>
			<a href="${pageContext.request.contextPath}/home">Page d'accueil</a>
		</c:if>
		<c:if test=${not empty utilisateur}>
			<ul>
				<li>${utilisateur.pseudo}</li>
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