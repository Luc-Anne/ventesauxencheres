<%@page import="fr.eni.ventesauxencheres.bll.CategorieManager"%>
<%@page import="fr.eni.ventesauxencheres.bo.Categorie"%>
<%@page import="fr.eni.ventesauxencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf"%>
	<title>${initParam.debutTitre}Gestiond'un utilisateur</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf"%>
	<main>
		<div class="container">
			<%Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte"); %>
				<form method="post" action="${Url.AJOUTER_ARTICLE.getUrl()}">
					<!-- Article  -->
					<div class="mb-3 mt-4">
						<label for="inputTextArticle" class="form-label">Article</label> 
						<input type="text" class="form-control" id="inputTextArticle" name="article">
					</div>
					
					<!-- Description -->
					<div class="mt-4">Description</div>
					<div class="form-floating">
						<textarea class="form-control" id="floatingTextarea2" style="height: 100px" name="description"></textarea>
						<label for="floatingTextarea2">Description</label>
					</div>
						<!-- Categorie  -->
						<div class="mt-4">Catégorie</div>
						<div>
							<select class="form-select" id="floatingSelectGrid"	aria-label="Floating label select example" name="categorie">
								 <%for (Categorie categorie : CategorieManager.getInstance().getAll()){ %>
								<option value="<%= categorie.getNoCategorie()%>"> <%=categorie.getLibelle() %></option>
								<%} %> 
							</select>
						</div>
		
		<!-- 				Photo de l'article 
						<div class="mt-4">
							<label for="inputTextPhoto" class="form-label">Photo de l'article</label> 
							<input type="text" class="form-control" id="inputTextPhoto"  accept ="img/*" name="">
						</div> -->
		
						<!-- Mise à Prix -->
						<div class="mb-3">
							<label for="inputNumberMiseAPrix" class="form-label">Mise à prix</label>
							 <input	type="number" class="form-control" id="inputNumberMiseAPrix" name="miseAPrix">
						</div>
		
						<!-- Début de l'enchère -->
						<div class="mb-3">
							<label for="inputDateDebutenchere" class="form-label">Date début enchère</label>
							 <input	type="datetime-local" class="form-control" id="inputDateDebutenchere"	name="dateDebutEnchere">
						</div>
		
						<!-- Fin de l'enchère -->
										<div class="mb-3">
							<label for="inputDateFinenchere" class="form-label">Date fin enchère</label>
							 <input	type="datetime-local" class="form-control" id="inputDateFinenchere"	name="dateFinEnchere">
						</div>
		
						<!-- Retrait -->
						<fieldset>
							<legend >Retrait</legend>
							<div class="mb-3">
								<label for="exampleInputText" class="form-label">Rue</label> 
								<input type="text" class="form-control" id="exampleInputText" name="rue" value="<%=utilisateur.getRue() %>">
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

						<span><button type="submit" class="btn btn-primary mt-4">ENREGISTRER</button> &nbsp;&nbsp; <button type="submit" class="btn btn-warning mt-4">Annuler</button> </span>
						
				</form>
		</div>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>