<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/main/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf" %>
	<c:if test="${not empty client}">
		<title>${initParam.debutTitre} Profil de ${utilisateur.pseudo}</title>
	</c:if>
</head>
<body class="bg-light">
<body>
	<%@ include file="/WEB-INF/main/header.jspf" %>
	<main class="container">
		<div class="mx-auto text-center">
			<h1>Profil de ${client.pseudo}</h1>
		</div>
		<c:if test="${not empty client}">
			<h4>Identité</h4>
			<ul class="list-group">
			  <li class="list-group-item d-flex justify-content-between align-items-start">
	                <div class="ms-2 me-auto">
	                    <div class="fw-bold">Pseudo</div>
	                    ${client.pseudo}
	                </div>
	            </li>
	            <li class="list-group-item d-flex justify-content-between align-items-start">
	                <div class="ms-2 me-auto">
	                    <div class="fw-bold">Nom Prénom</div>
	                    ${client.nom}&nbsp;${client.prenom}
	                </div>
	            </li>
	        </ul>
	        <h4>Coordonnées</h4>
	        <ul class="list-group">
	            <li class="list-group-item d-flex justify-content-between align-items-start">
	                <div class="ms-2 me-auto">
	                    <div class="fw-bold">Courriel</div>
	                    ${client.courriel}
	                </div>
	            </li>
	            <li class="list-group-item d-flex justify-content-between align-items-start">
	                <div class="ms-2 me-auto">
	                    <div class="fw-bold">Téléphone</div>
	                    ${client.telephone}
	                </div>
	            </li>
	            <li class="list-group-item d-flex justify-content-between align-items-start">
	                <div class="ms-2 me-auto">
	                    <div class="fw-bold">Adresse</div>
	                    <div>${client.adresseDomicile.rue}</div>
	                    <div>${client.adresseDomicile.codePostal}&nbsp;${client.adresseDomicile.ville}&nbsp;${client.adresseDomicile.pays}</div>
	                </div>
	            </li>
	        </ul>
        </c:if>
	</main>
	<%@ include file="/WEB-INF/main/footer.jspf" %>
</body>
</html>