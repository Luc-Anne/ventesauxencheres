<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/main/taglib.jspf" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.util.Errors" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf" %>
	<title>${initParam.debutTitre} Mon Profil</title>
</head>
<body class="bg-light">
<body>
	<%@ include file="/WEB-INF/main/header.jspf" %>
		<main class="container">
			<div class="mx-auto text-center">
				<h1>Mon Profil</h1>
			</div>
			<c:set var="typeForm" value="modification"></c:set>
            <%@ include file="/WEB-INF/utilisateur/client/clientForm.jspf" %>
			<br>
			<!--
			<form action="${Url.DESINSCRIPTION.getUrl()}" method="post" class="row col-md-12 col-lg-12">
				<button class="w-50 col-4 btn btn-danger btn-lg" class="mb-3">Supprimer son compte</button>
				<div class="form-check w-50 col-4">
					<label class="form-check-label" for="confirmationSuppression">Cocher pour confirmer si vous supprimer votre compte</label>
					<input type="checkbox" class="form-check-input" checked name="confirmationSuppression" value="confirmationSuppression" id="confirmationSuppression">
				</div>
			</form>
            -->
		</main>
	<%@ include file="/WEB-INF/main/footer.jspf" %>
</body>
</html>