<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Accueil</title>
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<!--  -->

<!-- Test Connexion  -->
<p> ${sessionScope.utilisateurConnecte.nom } </p>
<p> ${sessionScope.utilisateurConnecte.prenom } </p>
<p> ${sessionScope.utilisateurConnecte.telephone } </p>
<p> ${messageSucces} </p>
<!--  -->


	<main>
	
		<!-- if (Non connecté || Connecté) -->
		<div>
			<h3>Liste des enchères</h3>
			<!--  foreach (pour chacune des enchères) : -->
				<!-- if(l'utilisateurConnecte est le vendeur de l'article de l'enchère) -->
					<!-- Afficher cette enchère en mode vendeur -->
					
				<!-- else -->
					<!-- Afficher cette enchère en mode enchérissable -->
					
				<!-- endif -->
			<!-- end foreach -->
		</div>
		
	</main>



<%@ include file="/WEB-INF/fragments/main.jspf" %>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>