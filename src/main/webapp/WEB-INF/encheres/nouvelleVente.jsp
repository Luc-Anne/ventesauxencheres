<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@page import="fr.eni.ventesauxencheres.bll.encheres.CategorieMgr"%>
<%@page import="fr.eni.ventesauxencheres.bo.encheres.Categorie"%>
<%@page import="fr.eni.ventesauxencheres.bo.utilisateur.Client"%>
<%@ include file="/WEB-INF/main/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/main/commonHead.jspf"%>
	<title>${initParam.debutTitre} Nouvelle vente</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/main/header.jspf"%>
	<main class="container">
		<div class="mx-auto text-center">
			<h1>Nouvelle vente</h1>
		</div>
	    <%Client utilisateur = (Client) session.getAttribute("clientConnecte"); %>
		<form method="post" action="${Url.AJOUTER_ARTICLE.getUrl()}">
			<div class="mb-3 mt-4">
				<label for="inputTextArticle" class="form-label">Article</label> 
				<input type="text" class="form-control" id="inputTextArticle" name="article">
			</div>
			<div class="mt-4">Description</div>
			<div class="form-floating">
				<textarea class="form-control" id="floatingTextarea2" style="height: 100px" name="description"></textarea>
				<label for="floatingTextarea2">Description</label>
			</div>
			<div class="mt-4">Catégorie</div>
			<div>
				<select class="form-select" id="floatingSelectGrid"	aria-label="Floating label select example" name="categorie">
					 <%
					 for (Categorie categorie : CategorieMgr.getInstance().getAll()){
					 %>
					<option value="<%= categorie.getNoCategorie()%>"> <%=categorie.getLibelle() %></option>
					<%} %> 
				</select>
			</div>
			<div class="mb-3">
				<label for="inputNumberMiseAPrix" class="form-label">Mise à prix</label>
				 <input	type="number" class="form-control" id="inputNumberMiseAPrix" name="miseAPrix">
			</div>
			<div class="mb-3">
				<label for="inputDateDebutenchere" class="form-label">Date début enchère</label>
				 <input	type="datetime-local" class="form-control" id="inputDateDebutenchere"	name="dateDebutEnchere">
			</div>
			<div class="mb-3">
				<label for="inputDateFinenchere" class="form-label">Date fin enchère</label>
				 <input	type="datetime-local" class="form-control" id="inputDateFinenchere"	name="dateFinEnchere">
			</div>
			<fieldset>
				<legend >Retrait</legend>
				<div class="mb-3">
					<label for="exampleInputText" class="form-label">Rue</label> 
					<input type="text" class="form-control" id="exampleInputText" name="rue" value="${sessionScope.clientConnecte.adresse.rue}">
				</div>
				<div class="mb-3">
					<label for="exampleInputText" class="form-label">Code Postale</label>
					<input	type="text" class="form-control" id="exampleInputText" name="codePostale" value="<%=utilisateur.getCodePostal() %>">
				</div>
				<div class="mb-3">
					<label for="exampleInputText" class="form-label">Ville</label> 
					<input type="text" class="form-control" id="exampleInputText" name="ville" value="<%=utilisateur.getVille() %>">
				</div>
			</fieldset>
			<button type="submit" class="w-100 btn btn-primary btn-lg">Ajouter l'article</button>
			<br><br>
		</form>
		<form action="${Url.HOME.getUrl()}" method="get" class="row col-md-12 col-lg-12">
			<button class="w-100 col-4 btn btn-warning btn-lg" class="mb-3" name="action" value="annuler">Annuler</button>
		</form>
	</main>
	<%@ include file="/WEB-INF/main/footer.jspf"%>
</body>
</html>