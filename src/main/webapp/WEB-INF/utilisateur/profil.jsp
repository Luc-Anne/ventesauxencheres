<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Profil</title>
</head>
<body class="bg-light">
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<div class="container">
		<main>
			<div class="row g-8">
				<div class="col-md-12 col-lg-12">
					<h4 class="mb-3">Informations Générales à modifier</h4>
					<form class="needs-validation" novalidate  action="${Url.COMPTE_PROFIL.getUrl()}" method="post">
						<div class="row g-3">
							<div class="col-12">
								<c:set var="isInvalid" value=""></c:set>
								<c:if test="${erreurs.contains('utilisateur.pseudo_deja_pris')}">
									<c:set var="isInvalid" value="is-invalid"></c:set>
								</c:if>
								<label for="username" class="form-label">Pseudo</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control ${isInvalid}" id="username" placeholder="${sessionScope.utilisateurConnecte.pseudo}" required name="pseudo" value="${sessionScope.utilisateurConnecte.pseudo}">
									<div class="invalid-feedback">Il faut nécessairement indiquer le Pseudo</div>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">Nom</label> <input
									type="text" class="form-control" id="lastName" placeholder="${sessionScope.utilisateurConnecte.nom}"
									required name="nom" value="${sessionScope.utilisateurConnecte.nom}">
								<div class="invalid-feedback">Il faut nécessairement indiquer le Nom</div>
							</div>
							<div class="col-sm-6">
								<label for="firstName" class="form-label">Prénom</label>
								 <input	type="text" class="form-control" id="firstName" placeholder="${sessionScope.utilisateurConnecte.prenom}"
									required name="prenom" value="${sessionScope.utilisateurConnecte.prenom}">
								<div class="invalid-feedback">Il faut nécessairement indiquer le Prenom</div>
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email</label> 
								<input type="email" class="form-control" id="email"
									placeholder="${sessionScope.utilisateurConnecte.email}" name="email" value="${sessionScope.utilisateurConnecte.email}">
								<div class="invalid-feedback">Entrer un email Valid</div>
							</div>

							<div class="col-12">
								<label for="numerotelephone" class="form-label">Numéro de Télephone</label> 
								<input type="text" class="form-control"	id="numerotelephone" placeholder="${sessionScope.utilisateurConnecte.telephone}" required name="telephone" value="${sessionScope.utilisateurConnecte.telephone}">
								<div class="invalid-feedback">Merci de rensigner un numéro de téléphone valide</div>
							</div>
							<h4 class="mb-3">Adresse Complète</h4>
							<div class="col-12">
								<label for="address" class="form-label">Rue</label> 
								<input type="text" class="form-control" id="address" placeholder="${sessionScope.utilisateurConnecte.rue}" required name="rue" value="${sessionScope.utilisateurConnecte.rue}">
								<div class="invalid-feedback">Merci d'indiquer un numéro et nom de rue Valides</div>
							</div>	
							<div class="col-12">
								<label for="address" class="form-label">Code Postale</label> 
								<input type="text" class="form-control" id="address" placeholder="${sessionScope.utilisateurConnecte.codePostal}" required name="code_postal" value="${sessionScope.utilisateurConnecte.codePostal}">
								<div class="invalid-feedback">Code Postale Requis</div>
							</div>
							<div class="col-12">
								<label for="address" class="form-label">Ville</label> 
								<input type="text" class="form-control" id="address" placeholder="${sessionScope.utilisateurConnecte.ville}" required name="ville" value="${sessionScope.utilisateurConnecte.ville}">
								<div class="invalid-feedback">Indiquer le nom de la ville</div>
							</div>	
							<div class="col-12">
								<label for="password" class="form-label">Mot de Passe</label> 
								<input 	type="password" class="form-control" id="password"	placeholder="${sessionScope.utilisateurConnecte.motDePasse}" required name="password" value="${sessionScope.utilisateurConnecte.motDePasse}">
								<div class="invalid-feedback">Code Postale Requis</div>
							</div>												
						</div>
						<hr class="my-4">
						<button class="w-100 btn btn-primary btn-lg" type="submit">Enregistrer les modifications</button>
					</form>
				</div>
			</div>
			<form action="${Url.DESINSCRIPTION.getUrl()}" method="post">
				<button class="btn btn-danger">Delete</button>
			</form>
		</main>

	</div>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>