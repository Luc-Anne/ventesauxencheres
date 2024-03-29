<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.Errors" %>
<!DOCTYPE html>
<html lang="fr">
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Inscription</title>
</style>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
		<main class="container">
		    <div class="mx-auto text-center">
            	<h1>Inscription</h1>
        	</div>
			<div class="row g-8">
				<div class="col-md-12 col-lg-12">
					<h4 class="mb-3">Informations Générales à fournir</h4>
					<form class="needs-validation" novalidate  action="" method="post">
						<div class="row g-3">

							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.pseudo_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le pseudo doit être renseigné."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.pseudo_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le pseudo est trop long."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.pseudo_avecEspace')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le pseudo ne doit pas avoir d'espace."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.pseudo_deja_pris')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Ce pseudo est déjà utilisé par un membre."></c:set>
								</c:if>
								<label for="username" class="form-label">Pseudo</label>
								<input type="text" class="form-control ${isInvalid}" id="username" required name="pseudo" value="${param.pseudo}">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<div class="col-sm-6">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.nom_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer votre nom."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.nom_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le nom est trop long."></c:set>
								</c:if>
								<label for="lastName" class="form-label">Nom</label>
								<input type="text" class="form-control ${isInvalid}" id="lastName" required name="nom" value="${param.nom}">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<div class="col-sm-6">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.prenom_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer votre prénom."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.prenom_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le prénom est trop long."></c:set>
								</c:if>
								<label for="firstName" class="form-label">Prénom</label>
								<input type="text" class="form-control ${isInvalid}" id="firstName" required name="prenom" value="${param.prenom}">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.email_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer votre mail."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.email_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} L'email est trop long."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.email_manque@')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il n'y a pas de @ dans votre mail."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.email_deja_pris')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Cet email est déjà utilisé par un membre."></c:set>
								</c:if>
								<label for="email" class="form-label">Email</label>
								<input type="email" class="form-control ${isInvalid}" id="email" placeholder="you@exemple.com" required name="email" value="${param.email}">
								<div class="invalid-feedback">${IF}</div>
							</div>

							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.telephone_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le numéro de téléphone est trop long."></c:set>
								</c:if>
								<label for="numerotelephone" class="form-label">Numéro de Télephone</label>
								<input type="text" class="form-control" id="numerotelephone" name="telephone" value="${param.telephone}">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<h4 class="mb-3">Adresse Complète</h4>
							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.rue_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer le numéro et nom de la rue votre adresse."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.rue_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le numéro et le nom de la rue sont trop longs."></c:set>
								</c:if>
								<label for="address" class="form-label">Rue</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="rue" value="${param.rue}">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<div class="col-3">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.codePostal_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer un code postale pour votre adresse."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.codePostal_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le code postal est trop long."></c:set>
								</c:if>
								<label for="address" class="form-label">Code Postale</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="code_postal" value="${param.code_postal}">
								<div class="invalid-feedback">${IF}</div>
							</div>

							<div class="col-9">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.ville_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer une ville pour votre adresse."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.ville_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le nom de la bille est trop long."></c:set>
								</c:if>
								<label for="address" class="form-label">Ville</label>
								<input type="text" class="form-control ${isInvalid}" id="address" required name="ville" value="${param.ville}">
								<div class="invalid-feedback">${IF}</div>
							</div>

							<div class="col-6">
								<c:set var="isInvalid" value=""></c:set>
								<c:set var="IF" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.motDePasse_vide')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Il faut nécessairement indiquer un mot de passe valide (au moins 8 caractères)"></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.motDePasse_tropLong')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} Le mot de passe est trop long."></c:set>
								</c:if>
								<c:if test="${erreurs.contains('utilisateur.motDePasse_faible')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
									<c:set var="IF" value="${IF} "></c:set>
								</c:if>
								<label for="password" class="form-label">Mot de Passe</label>
								<input type="password" class="form-control ${isInvalid}" id="password" required name="password">
								<div class="invalid-feedback">${IF}</div>
							</div>
							<div class="col-6">
								<label for="passwordConfirmation" class="form-label">Confirmer le mot de Passe</label>
								<input type="password" class="form-control" id="passwordConfirmation" required name="passwordConfirmation">
							</div>
						</div>
						<hr class="my-4">
						<button class="w-100 btn btn-primary btn-lg" type="submit">S'inscrire</button>
					</form>
				</div>
			</div>
	</main>

	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>