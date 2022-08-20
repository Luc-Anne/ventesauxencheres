<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.Errors" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.84.0">
<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
<title>${initParam.debutTitre} Inscription</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/checkout/">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
		<main>
			<div class="container">
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4" src="img/inscription.jpg" alt=""
					width="350" height="200">
				<h2>Bienvenue Sur Le Formulaire d'Inscription</h2>
				<p class="lead">
					Merci de Renseigner les informations ci-dessous afin de vous
					connecter lors de la prochaine session
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">
  <path
							d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  <path
							d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z" />
</svg>
					</i>

				</p>
			</div>

			<div class="row g-8">
				<div class="col-md-12 col-lg-12">
					<h4 class="mb-3">Informations Générales à fournir</h4>
					<form class="needs-validation" novalidate  action="" method="post">
						<div class="row g-3">

							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.pseudo_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="username" class="form-label">Pseudo</label>
								<input type="text" class="form-control ${isInvalid}" id="username" required name="Pseudo">
								<div class="invalid-feedback">Il faut nécessairement indiquer un pseudo</div>
							</div>
							<div class="col-sm-6">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.nom_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="lastName" class="form-label">Nom</label>
								<input type="text" class="form-control ${isInvalid}" id="lastName" required name="nom">
								<div class="invalid-feedback">Il faut nécessairement indiquer votre nom</div>
							</div>
							<div class="col-sm-6">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.prenom_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="firstName" class="form-label">Prénom</label>
								<input type="text" class="form-control ${isInvalid}" id="firstName" required name="prenom">
								<div class="invalid-feedback">Il faut nécessairement indiquer votre prénom</div>
							</div>
							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.email_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.email_manque@')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="email" class="form-label">Email</label>
								<input type="email" class="form-control ${isInvalid}" id="email" placeholder="you@exemple.com" required name="email">
								<div class="invalid-feedback">Il faut nécessairement indiquer un email valide</div>
							</div>

							<div class="col-12">
								<label for="numerotelephone" class="form-label">Numéro de Télephone</label>
								<input type="text" class="form-control" id="numerotelephone" name="telephone">
							</div>
							<h4 class="mb-3">Adresse Complète</h4>
							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.rue_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="address" class="form-label">Rue</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="rue">
								<div class="invalid-feedback">Il faut nécessairement indiquer le numéro et nom de la rue votre adresse</div>
							</div>
							<div class="col-3">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.codePostal_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="address" class="form-label">Code Postale</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="code_postal">
								<div class="invalid-feedback">Il faut nécessairement indiquer un code postale pour votre adresse</div>
							</div>

							<div class="col-9">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.ville_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="address" class="form-label">Ville</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="ville">
								<div class="invalid-feedback">Il faut nécessairement indiquer une ville pour votre adresse</div>
							</div>

							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.motDePasse_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.motDePasse_faible')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="password" class="form-label">Mot de Passe</label>
								<input type="password" class="form-control ${isInvalid}" id="password" required name="password">
								<div class="invalid-feedback">Il faut nécessairement indiquer un mot de passe valide (au moins 8 caractères)</div>
							</div>
						</div>
						<hr class="my-4">
						<button class="w-100 btn btn-primary btn-lg" type="submit">S'inscire</button>
					</form>
				</div>
			</div>
		</div>
	</main>

	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>