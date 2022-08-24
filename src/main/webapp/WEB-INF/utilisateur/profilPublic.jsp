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
			<ul class="list-group">
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Nom</div>
						${utilisateur.nom}
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Prénom</div>
						${utilisateur.prenom}
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Email</div>
						${utilisateur.email}
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Téléphone</div>
						${utilisateur.telephone}				
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Rue</div>
						${utilisateur.rue}				
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Code Postal</div>
						${utilisateur.codePostal}
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-start">
					<div class="ms-2 me-auto">
						<div class="fw-bold">Ville</div>
						${utilisateur.ville}
					</div>
				</li>
			</ul>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>