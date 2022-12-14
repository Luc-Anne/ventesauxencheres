<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/main/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf" %>
	<title>${initParam.debutTitre} Se connecter</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/main/header.jspf" %>
    <main class="container">
	    <div class="mx-auto text-center">
			<h1>Se connecter</h1>
		</div>
		<div class="mt-5">
			<form action="${Url.CONNEXION.getUrl()}" method="post">
				<div class="mb-3">
					<input type="email" class="form-control" aria-describedby="emailHelp" name="email" placeholder="Courriel">
				</div>
				<div class="mb-3">
					<input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe">
				</div>
				<c:if test="${not empty echecConnexion}">
					<div class="form-invalid-feedback">Email ou mot de passe incorrect.</div>
				</c:if>
				<button class="w-100 btn btn-primary btn-lg" type="submit">Se connecter</button>
			</form>
		</div>
	</main>
	<%@ include file="/WEB-INF/main/footer.jspf"%>
</body>
</html>