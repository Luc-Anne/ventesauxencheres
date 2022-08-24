<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ventesauxencheres.bo.Article" %>
<%@ page import="fr.eni.ventesauxencheres.bo.Enchere" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Article {article.nomArticle}</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
		<%
		Article article = (Article)request.getAttribute("article");
		%>
		<!-- Titre -->
		<div class="mx-auto text-center">
			<h1>
			<c:if test="${typeAffichage == 'typeEncherir' || typeAffichage == 'typeBack'}">
				DétailsVente
			</c:if>
			<c:if test="${typeAffichage == 'typeRetrait'}">
				${article.encherisseur.pseudo} a remporté la vente
			</c:if>
			<c:if test="${typeAffichage == 'typeGagne'}">
				Bous avez remporté la vente
			</c:if>
			</h1>
		</div>
		<!-- Afficher les détails d'un article -->
		<ul class="list-group">
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Description</div>
					${article.description}
				</div>
			</li>
			<c:if test="${typeAffichage == 'typeEncherir'}">
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Categorie</div>
					${article.categorieArticle.libelle}
				</div>
			</li>
			</c:if>
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Meilleure offre</div>
					<c:if test="${not empty article.enchere}">
						${article.enchere.montantEnchere} credit
						<c:if test="${typeAffichage == 'typeEncherir'}">
							 par ${article.enchere.encherisseur.pseudo}
						</c:if>
						<c:if test="${typeAffichage == 'typeRetrait'}">
							par <a href="${Url.PROFIL_PUBLIC.getUrl()}${article.enchere.encherisseur.pseudo}">${article.enchere.encherisseur.pseudo}</a>
						</c:if>
						<c:if test="${typeAffichage == 'typeBack'}">
							<!-- Rien -->
						</c:if>
					</c:if>
					<c:if test="${empty article.enchere}">
						Aucune enchère n'a été faite
					</c:if>
				</div>
			</li>
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Mise à prix</div>
					${article.miseAPrix}
				</div>
			</li>
			<c:if test="${typeAffichage == 'typeEncherir' || typeAffichage == 'typeRetrait'}">
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Fin des enchères</div>
					<%
					LocalDateTime dateFinEncheres = article.getDateFinEncheres();
					Date dateFinEncheresDate = java.sql.Timestamp.valueOf(dateFinEncheres);
					%>
					<c:set var="dateFormate" value="<%=dateFinEncheresDate%>" />
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dateFormate}" />
				</div>
			</li>
			</c:if>
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Retrait</div>
					<c:if test="${not empty article.retrait}">
						${article.retrait.rue}<br>
						${article.retrait.codePostal}
						${article.retrait.ville}
					</c:if>
					<c:if test="${empty article.retrait}">
						${article.vendeur.rue}<br>
						${article.vendeur.codePostal}
						${article.vendeur.ville}
					</c:if>
				</div>
			</li>
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Vendeur</div>
					${article.vendeur.pseudo}
				</div>
			</li>
			<c:if test="${typeAffichage == 'typeGagne'}">
			<li class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Téléphone</div>
					${article.vendeur.telephone}
				</div>
			</li>
			</c:if>
		</ul>
		<c:if test="${typeAffichage == 'typeEncherir'}">
			<input type="number" name="mise" value="${article.miseAPrix + 1}" min="${article.miseAPrix}">
			<button type="submit">Miser</button>
		</c:if>
		<c:if test="${typeAffichage == 'typeRetrait'}">
			<button>Confirmer le retrait</button>
		</c:if>
		<c:if test="${typeAffichage == 'typeBack' || typeAffichage == 'typeGagne'}">
			<button>Back</button>
		</c:if>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>