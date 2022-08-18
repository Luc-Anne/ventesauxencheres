<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<div class="container">
		<main>
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
								<label for="username" class="form-label">Pseudo</label>
								<div class="input-group has-validation">
									</span> <input type="text" class="form-control" id="username"
										placeholder="Indiquer ici le pseudo ..." required name="pseudo">
									<div class="invalid-feedback">Il faut nécessairement
										indiquer le Pseudo</div>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">Nom</label> <input
									type="text" class="form-control" id="lastName" placeholder="Inésrer le nom"
									value="" required name="nom">
								<div class="invalid-feedback">Il faut nécessairement
									indiquer le Nom</div>
							</div>
							<div class="col-sm-6">
								<label for="firstName" class="form-label">Prénom</label> <input
									type="text" class="form-control" id="firstName" placeholder=""
									value="" required name="prenom">
								<div class="invalid-feedback">Il faut nécessairement
									indiquer le Prenom</div>
							</div>
							<div class="col-12">
								<label for="email" class="form-label">Email </span></label> <input
									type="email" class="form-control" id="email"
									placeholder="you@exemple.com" name="email">
								<div class="invalid-feedback">Entrer un email Valid</div>
							</div>

							<div class="col-12">
								<label for="numerotelephone" class="form-label">Numéro
									de Télephone</label> <input type="text" class="form-control"
									id="numerotelephone" placeholder="(+33) 6 xx xx xx xx" required name="telephone">
								<div class="invalid-feedback">Merci de rensigner un numéro
									de téléphone valide</div>
							</div>
							<h4 class="mb-3">Adresse Complète</h4>
							<div class="col-12">
								<label for="address" class="form-label">Rue</label> <input
									type="text" class="form-control" id="address"
									placeholder="3 Rue Michael Faraday" required name="rue">
								<div class="invalid-feedback">Merci d'indiquer un numéro
									et nom de rue Valides</div>
							</div>
							<div class="col-12">
								<label for="address" class="form-label">Code Postale</label> <input
									type="text" class="form-control" id="address"
									placeholder="44800" required  name="code_postal">
								<div class="invalid-feedback">Code Postale Requis</div>
							</div>

							<div class="col-12">
								<label for="address" class="form-label">Ville</label> <input
									type="text" class="form-control" id="address"
									placeholder="Saint-HERBLAIN" required name="ville">
								<div class="invalid-feedback">Indiquer le nom de la ville</div>
							</div>

							<div class="col-12">
								<label for="password" class="form-label">Mot de Passe</label> <input
									type="password" class="form-control" id="password"
									placeholder="........" required name="password">
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
								<input class="form-check-input" type="checkbox" 
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

	<script src="${pageContext.request.contextPath}/css/form-validation.js"></script>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>


<!--  
				<div class="col-md-5 col-lg-4 order-md-last">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">Your cart</span> <span
							class="badge bg-primary rounded-pill">3</span>
					</h4>
					<ul class="list-group mb-3">
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Product name</h6>
								<small class="text-muted">Brief description</small>
							</div> <span class="text-muted">$12</span>
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Second product</h6>
								<small class="text-muted">Brief description</small>
							</div> <span class="text-muted">$8</span>
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">Third item</h6>
								<small class="text-muted">Brief description</small>
							</div> <span class="text-muted">$5</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between bg-light">
							<div class="text-success">
								<h6 class="my-0">Promo code</h6>
								<small>EXAMPLECODE</small>
							</div> <span class="text-success">−$5</span>
						</li>
						<li class="list-group-item d-flex justify-content-between"><span>Total
								(USD)</span> <strong>$20</strong></li>
					</ul>

					<form class="card p-2">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Promo code">
							<button type="submit" class="btn btn-secondary">Redeem</button>
						</div>
					</form>
				</div>
				
				-->