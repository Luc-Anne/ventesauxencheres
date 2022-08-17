<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
<title>Accueil</title>
</head>
<body>
<!-- Proposition navbar à améliorer -->
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
<!--  -->
<p>HOME PAGE</p>
<!-- Test Connexion  -->
<p> ${sessionScope.utilisateurConnecte.nom } </p>
<p> ${sessionScope.utilisateurConnecte.prenom } </p>
<p> ${sessionScope.utilisateurConnecte.telephone } </p>
<p> ${messageSucces} </p>
<!--  -->

</body>
</html>