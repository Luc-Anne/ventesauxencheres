<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ventes aux Enchères - Se connecter</title>
	<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
		crossorigin="anonymous">
</head>
<body>
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
</body>
</html>