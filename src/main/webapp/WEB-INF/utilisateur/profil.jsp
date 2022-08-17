<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/styles.css" type="text/css" rel="stylesheet">
<title>Profil</title>
</head>
<body>
<main id="profil">
	<div class="row">
		<div class="col-sm-6 col-md-5 col-lg-6">
			<h1>Profil - Informations</h1>
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
			<span>prénom : ${utilisateurConnecte.prenom} </span>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>email :</span>
		</div>
	</div>		
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>prénom :</span>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>téléphone :</span>
		</div>
	</div>	
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>rue :</span>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>code postal :</span>
		</div>
	</div>	
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<span>ville :</span>
		</div>
	</div>		
</main>


</body>
</html>