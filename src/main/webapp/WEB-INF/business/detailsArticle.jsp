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
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
		<%
		// Supprimer ces lignes
		Article article = (Article)request.getAttribute("article");
		%>
		<!-- Afficher les détails d'un article -->
		<h3>${article.nomArticle}</h3>
		<ul>
			<li>${article.description}</li>
			<li>
				<%
				// Supprimer ces lignes
				LocalDateTime dateDebutEncheres = article.getDateDebutEncheres();
				%>
					${dateDebutEncheres}
				<%
				Date dateDebutEncheresDate = java.sql.Timestamp.valueOf(dateDebutEncheres);
				%>
				<c:set var="dateFormate" value="<%=java.sql.Timestamp.valueOf(dateDebutEncheres)%>" />
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dateFormate}" />
			</li>
			<%
			// Supprimer ces lignes
			LocalDateTime dateFinEncheres = article.getDateFinEncheres();
			Date dateFinEncheresDate = java.sql.Timestamp.valueOf(dateDebutEncheres);
			%>
			<c:set var="dateFormate" value="<%=dateFinEncheresDate%>" />
			<c:if test="${article.etatVente == 'VD' || article.etatVente == 'RT'}">
				<li><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dateFinEncheresDate}" /></li>
			</c:if>
			<li>${article.miseAPrix}</li>
			<c:if test="${article.etatVente == 'VD' || article.etatVente == 'RT'}">
				<li>${article.prixVente}</li>
			</c:if>
			<li>${article.etatVente}</li>
			<li>${article.categorieArticle.libelle}</li>
			<li><a href="${Url.PROFIL_PUBLIC.getUrl()}${article.vendeur.pseudo}">${article.vendeur.pseudo}</a></li>
			<c:if test="${not empty article.retrait}">
				<li>${article.retrait.rue}</li>
				<li>${article.retrait.codePostal}</li>
				<li>${article.retrait.ville}</li>
			</c:if>
			<c:if test="${empty article.retrait}">
				<li>${article.utilisateur.rue}</li>
				<li>${article.utilisateur.codePostal}</li>
				<li>${article.utilisateur.ville}</li>
			</c:if>
		</ul>
		<c:if test="${article.etatVente == 'EC' && not empty enchere}">
			<table class="table">
				<tr>
					<th scope="col">Dernier enrichisseur</th>
					<th scope="col">Montant de l'enchère</th>
					<th scope="col">Date</th>
				</tr>
				<tr>
					<td>${enchere.encherisseur.pseudo}</td>
					<td>${enchere.montantEnchere}</td>
					<%
					// Supprimer ces lignes
					Enchere enchere = (Enchere)request.getAttribute("enchere");
					LocalDateTime dateEncheres = enchere.getDateEnchere();
					Date dateEncheresDate = java.sql.Timestamp.valueOf(dateEncheres);
					%>
					<c:set var="dateFormate" value="<%=dateEncheresDate%>" />
					<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dateFormate}" /></td>
				</tr>
			</table>
		</c:if>

		<c:if test="${article.etatVente == 'EC'}">
			<button>Miser</button>
		</c:if>
		<c:if test="${article.etatVente == 'VD'} && ${acheteur == sessionScope.utilisateurConnecte}">
			<button>Confirmer le retrait</button>
		</c:if>
		<c:if test="${article.etatVente == 'RT'}">
			<p>Retrait effectué</p>
		</c:if>
		
		
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