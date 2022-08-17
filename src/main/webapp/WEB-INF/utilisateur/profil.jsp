<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Profil</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
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

	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>