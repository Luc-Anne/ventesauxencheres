<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Se connecter</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<!-- 
	<c:if test="${messageConnexion == 'Connexion réussie !'}">
		<p class="fs-3 text-center text-success">${messageConnexion}</p>
	</c:if>
	 -->
	<div class="container mt-5">
		<form action="${Url.CONNEXION.getUrl()}" method="post">
			<div class="mb-3">
				<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" placeholder="Adresse mail">
			</div>
			<div class="mb-3">
				<input type="password" class="form-control" id="exampleInputPassword1" name="motDePasse" placeholder="Mot de passe">
			</div>
			<c:if test="${not empty echecConnexion}">
				<div class="form-invalid-feedback">Email ou mot de passe incorrect.</div>
			</c:if>
			<!-- FONCTIONNALITE 1002 Se souvenir de moi 
		<div class="mb-3 form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1">
			<label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		-->
			<button type="submit" class="btn btn-primary">Se connecter</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>
</body>
</html>