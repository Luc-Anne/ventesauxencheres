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
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Logo</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/home">Accueil</a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/utilisateur/profil">Profil</a>
      <a class="nav-item nav-link" href="#">Panier</a>
      <a class="nav-item nav-link" href="#">Déconnexion</a>
    </div>
  </div>
</nav>
<main id="profil-main">
	<div class="row">
		<div class="col-sm-6 col-md-5 col-lg-6">
		<br><br>
			<h1>Informations</h1>
		<br><br>	
		</div>
	</div>
<!-- Récupérer les informations d'une connexion -->
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>pseudo : ${sessionScope.utilisateurConnecte.pseudo}</span>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>nom : ${sessionScope.utilisateurConnecte.nom }</span> 
		</div>
	</div>	
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<span>prénom : ${utilisateurConnecte.prenom}</span>			
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>email : ${utilisateurConnecte.email}</span>
		</div>
	</div>		
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>téléphone : ${utilisateurConnecte.telephone}</span>
		</div>
	</div>	
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>rue :  ${utilisateurConnecte.rue}</span> 
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>code postal :  ${utilisateurConnecte.code_postal}</span> 
		</div>
	</div>	
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<span>ville : ${utilisateurConnecte.ville}</span>			
		</div>
	</div>		
</main>


</body>
</html>