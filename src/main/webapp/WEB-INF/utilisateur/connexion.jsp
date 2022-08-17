<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Se connecter</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	${messageConnexion}
	<form action="${pageContext.request.contextPath}/utilisateur/connexion" method="post">
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">Adresse mail :</label>
			<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
		</div>
		<div class="mb-3">
			<label for="exampleInputPassword1" class="form-label">Mot de passe :</label>
			<input type="password" class="form-control" id="exampleInputPassword1" name="motDePasse">
		</div>
		<!-- FONCTIONNALITE 1002 Se souvenir de moi 
		<div class="mb-3 form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1">
			<label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		-->
		<button type="submit" class="btn btn-primary">Se connecter</button>
	</form>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>