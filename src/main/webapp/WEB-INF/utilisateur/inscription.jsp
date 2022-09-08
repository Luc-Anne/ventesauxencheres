<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.util.Errors" %>
<!DOCTYPE html>
<html lang="fr">
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Inscription</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
	    <div class="mx-auto text-center">
            <h1>Inscription</h1>
        </div>
		<div class="row g-8">
			<div class="col-md-12 col-lg-12">
				<form class="needs-validation" novalidate  action="" method="post">
					<div class="row g-3">
                        <h4 class="mb-3">Informations de compte</h4>
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
							<label for="pseudo" class="form-label">Pseudo</label>
							<input type="text" class="form-control ${isInvalid}" id="pseudo" required name="pseudo" value="${param.pseudo}">
							<div class="invalid-feedback">${IF}</div>
						</div>
						<div class="col-12">
                            <c:set var="isInvalid" value=""></c:set>
                            <c:set var="IF" value=""></c:set>
                            <c:if test="${erreurs.contains('utilisateur.courriel_vide')}">
                                <c:set var="isInvalid" value="is-invalid"></c:set>
                                <c:set var="IF" value="${IF} Il faut nécessairement indiquer votre courriel."></c:set>
                            </c:if>
                            <c:if test="${erreurs.contains('utilisateur.courriel_tropLong')}">
                                <c:set var="isInvalid" value="is-invalid"></c:set>
                                <c:set var="IF" value="${IF} L'courriel est trop long."></c:set>
                            </c:if>
                            <c:if test="${erreurs.contains('utilisateur.courriel_manque@')}">
                                <c:set var="isInvalid" value="is-invalid"></c:set>
                                <c:set var="IF" value="${IF} Il n'y a pas de @ dans votre courriel."></c:set>
                            </c:if>
                            <c:if test="${erreurs.contains('utilisateur.courriel_deja_pris')}">
                                <c:set var="isInvalid" value="is-invalid"></c:set>
                                <c:set var="IF" value="${IF} Ce courriel est déjà utilisé par un membre."></c:set>
                            </c:if>
                            <label for="courriel" class="form-label">Courriel</label>
                            <input type="email" class="form-control ${isInvalid}" id="courriel" placeholder="you@exemple.com" required name="courriel" value="${param.courriel}">
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
                            <label for="motDePasse" class="form-label">Mot de Passe</label>
                            <input type="password" class="form-control ${isInvalid}" id="motDePasse" required name="motDePasse">
                            <div class="invalid-feedback">${IF}</div>
                        </div>
                        <div class="col-6">
                            <label for="motDePasseConfirmation" class="form-label">Confirmer le mot de Passe</label>
                            <input type="password" class="form-control" id="motDePasseConfirmation" required name="motDePasseConfirmation">
                        </div>
                        <h4 class="mb-3">Informations personnelles</h4>
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
							<label for="nom" class="form-label">Nom</label>
							<input type="text" class="form-control ${isInvalid}" id="nom" required name="nom" value="${param.nom}">
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
							<label for="prenom" class="form-label">Prénom</label>
							<input type="text" class="form-control ${isInvalid}" id="prenom" required name="prenom" value="${param.prenom}">
							<div class="invalid-feedback">${IF}</div>
						</div>
						<div class="col-12">
							<c:set var="isInvalid" value=""></c:set>
							<c:set var="IF" value=""></c:set>
							<c:if test="${erreurs.contains('utilisateur.telephone_tropLong')}">
								<c:set var="isInvalid" value="is-invalid"></c:set>
								<c:set var="IF" value="${IF} Le numéro de téléphone est trop long."></c:set>
							</c:if>
							<label for="telephone" class="form-label">Numéro de Télephone</label>
							<input type="text" class="form-control" id="telephone" name="telephone" value="${param.telephone}">
							<div class="invalid-feedback">${IF}</div>
						</div>
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
							<label for="rue" class="form-label">Rue</label>
							<input type="text" class="form-control ${isInvalid}" id="rue" required name="rue" value="${param.rue}">
							<div class="invalid-feedback">${IF}</div>
						</div>
						<div class="col-3">
							<c:set var="isInvalid" value=""></c:set>
							<c:set var="IF" value=""></c:set>
							<c:if test="${erreurs.contains('utilisateur.codePostal_vide')}">
								<c:set var="isInvalid" value="is-invalid"></c:set>
								<c:set var="IF" value="${IF} Il faut nécessairement indiquer un code postal pour votre adresse."></c:set>
							</c:if>
							<c:if test="${erreurs.contains('utilisateur.codePostal_tropLong')}">
								<c:set var="isInvalid" value="is-invalid"></c:set>
								<c:set var="IF" value="${IF} Le code postal est trop long."></c:set>
							</c:if>
							<label for="code_postal" class="form-label">Code Postal</label>
							<input type="text" class="form-control ${isInvalid}" id="code_postal" required name="code_postal" value="${param.code_postal}">
							<div class="invalid-feedback">${IF}</div>
						</div>
						<div class="col-6">
							<c:set var="isInvalid" value=""></c:set>
							<c:set var="IF" value=""></c:set>
							<c:if test="${erreurs.contains('utilisateur.ville_vide')}">
								<c:set var="isInvalid" value="is-invalid"></c:set>
								<c:set var="IF" value="${IF} Il faut nécessairement indiquer une ville pour votre adresse."></c:set>
							</c:if>
							<c:if test="${erreurs.contains('utilisateur.ville_tropLong')}">
								<c:set var="isInvalid" value="is-invalid"></c:set>
								<c:set var="IF" value="${IF} Le nom de la ville est trop long."></c:set>
							</c:if>
							<label for="ville" class="form-label">Ville</label>
							<input type="text" class="form-control ${isInvalid}" id="ville" required name="ville" value="${param.ville}">
							<div class="invalid-feedback">${IF}</div>
						</div>
						<div class="col-3">
                            <c:set var="isInvalid" value=""></c:set>
                            <c:set var="IF" value=""></c:set>
                            <c:if test="${erreurs.contains('utilisateur.pays_tropLong')}">
                                <c:set var="isInvalid" value="is-invalid"></c:set>
                                <c:set var="IF" value="${IF} Le nom du pays est trop long."></c:set>
                            </c:if>
                            <label for="address" class="form-label">Pays (FRANCE par défaut)</label>
                            <input type="text" class="form-control ${isInvalid}" id="pays" required name="pays" value="${param.pays}" placeholder="FRANCE">
                            <div class="invalid-feedback">${IF}</div>
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