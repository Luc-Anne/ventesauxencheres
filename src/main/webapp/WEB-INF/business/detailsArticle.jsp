<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} $$$$$$$$$$$$$$$$$$$$$$</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main>
		<!-- Afficher les détails d'un article -->
		
		<!-- Les boutons d'actions seront à définir en fonction de :
			- utilisateurConnecte
			- état de la vente
		 -->
		 
		 <!-- Boutons :
		 	- Modifier la vente
		 	- Faire une offre (d'enchère)
		 	- Valider le retrait
		  -->
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>