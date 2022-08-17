<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
<title>Profil</title>
</head>
<body class="bg-light">

	<div class="container">
		<main>
			<div class="row g-8">
				<div class="col-md-12 col-lg-12">
					<h4 class="mb-3">Informations Générales à modifier</h4>
					<form class="needs-validation" novalidate  action="" method="post">
						<div class="row g-3">

							<div class="col-12">
								<label for="username" class="form-label">Pseudo</label>
								<div class="input-group has-validation">
									</span> <input type="text" class="form-control" id="username"
										placeholder="${sessionScope.utilisateurConnecte.pseudo}" required name="pseudo" value="${sessionScope.utilisateurConnecte.pseudo}">
									<div class="invalid-feedback">Il faut nécessairement
										indiquer le Pseudo</div>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">Nom</label> <input
									type="text" class="form-control" id="lastName" placeholder="${sessionScope.utilisateurConnecte.nom}"
									value="" required name="" value="${sessionScope.utilisateurConnecte.nom">
								<div class="invalid-feedback">Il faut nécessairement
									indiquer le Nom</div>
							</div>
							<div class="col-sm-6">
								<label for="firstName" class="form-label">Prénom</label> <input
									type="text" class="form-control" id="firstName" placeholder=""
									value="" required name="prenom" value="${sessionScope.utilisateurConnecte.prenom">
								<div class="invalid-feedback">Il faut nécessairement
									indiquer le Prenom</div>
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email </span></label> <input
									type="email" class="form-control" id="email"
									placeholder="you@exemple.com" name="email" value="${sessionScope.utilisateurConnecte.email">
								<div class="invalid-feedback">Entrer un email Valid</div>
							</div>

							<div class="col-12">
								<label for="numerotelephone" class="form-label">Numéro
									de Télephone</label> <input type="text" class="form-control"
									id="numerotelephone" placeholder="(+33) 6 xx xx xx xx" required name="telephone" value="${sessionScope.utilisateurConnecte.telephone">
								<div class="invalid-feedback">Merci de rensigner un numéro
									de téléphone valide</div>
							</div>
							<h4 class="mb-3">Adresse Complète</h4>
							<div class="col-12">
								<label for="address" class="form-label">Rue</label> <input
									type="text" class="form-control" id="address"
									placeholder="3 Rue Michael Faraday" required name="rue" value="${sessionScope.utilisateurConnecte.rue">
								<div class="invalid-feedback">Merci d'indiquer un numéro
									et nom de rue Valides</div>
							</div>
							<div class="col-12">
								<label for="address" class="form-label">Code Postale</label> <input
									type="text" class="form-control" id="address"
									placeholder="44800" required  name="code_postal" value="${sessionScope.utilisateurConnecte.code_postal">
								<div class="invalid-feedback">Code Postale Requis</div>
							</div>

							<div class="col-12">
								<label for="address" class="form-label">Ville</label> <input
									type="text" class="form-control" id="address"
									placeholder="Saint-HERBLAIN" required name="ville" value="${sessionScope.utilisateurConnecte.ville">
								<div class="invalid-feedback">Indiquer le nom de la ville</div>
							</div>

							<div class="col-12">
								<label for="password" class="form-label">Mot de Passe</label> <input
									type="password" class="form-control" id="password"
									placeholder="........" required name="password" value="${sessionScope.utilisateurConnecte.mot_de_passe">
								<div class="invalid-feedback">Code Postale Requis</div>
							</div>

							<div class="col-12">
								<label for="credit" class="form-label">Crédit</label> <input
									type="text" class="form-control" id=""
									credit""
									placeholder="48000" required name="credit">
								<div class="invalid-feedback">Indiquer le montant de vos
									crédits en cours</div>
							</div>
							<div></div>
							<h4 class="mb-3">Inidquer votre statuts</h4>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="flexCheckDefault" name="status"> <label class="form-check-label"
									for="flexCheckDefault"> Vous êtes Administrateur ? </label>
							</div>
						</div>
						<hr class="my-4">
						<button class="w-100 btn btn-primary btn-lg" type="submit">S'inscire</button>
					</form>
				</div>
			</div>
		</main>

	</div>


	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>