<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.ventesauxencheres.bll.CategorieManager"%>
<%@ page import="fr.eni.ventesauxencheres.bo.Categorie"%>
<%@ page import="fr.eni.ventesauxencheres.bo.Article" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>

<%@ include file="/WEB-INF/fragments/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Accueil</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<!--main bloc-->
    <main class="container">
        <!--title-->
        <div class="mx-auto text-center">
            <h1>Enchères</h1>
        </div>
        <c:if test="${empty utilisateurConnecte}">      
        	<form class="form-filter border mb-3" action="${Url.HOME.getUrl()}" method="post">
	            <div class="row">
	                <div class="col-md-6 mb-3">
	                	<div class="form-group">
	                            <label for="filter-input">Filtre</label>
	                            <input type="text" class="form-control" id="filter-input" placeholder="articles contenant..." name ="motCle">
	                    </div>
	                    <div class="form-group">
	                        <label for="categories-select">Catégories</label>
	                        <select class="form-select" id="floatingSelectGrid"	aria-label="Floating label select example" name="categorie">
							 <option selected>Toutes</option>
								 <%for (Categorie categorie : CategorieManager.getInstance().getAll()){ %>
								 <option value="<%= categorie.getLibelle()%>"> <%=categorie.getLibelle() %></option>
								<%} %> 
							</select>
	                    </div>
	                </div>
	            </div>
	            <button class="btn btn-primary btn-lg btn-block col-12" type="submit">
	            	Rechercher
	            </button>
        	</form>
		 </c:if>        
        <!--filtre-->
        <c:if test="${not empty utilisateurConnecte}">
        <form class="form-filter border mb-3" action="${Url.HOME.getUrl()}" method="post">
            <div class="row">
                <!--Partie gauche-->
                <div class="col-md-6 mb-3">
                    <div class="form-group">
                            <label for="filter-input">Filtre</label>
                            <input type="text" class="form-control" id="filter-input" placeholder="articles contenant..." name ="motCle">
                    </div>
                    <div class="form-group">
                        <label for="categories-select">Catégories</label>
                        <select class="form-select" id="floatingSelectGrid"	aria-label="Floating label select example" name="categorie">
						 <option selected>Toutes</option>
						 <%for (Categorie categorie : CategorieManager.getInstance().getAll()){ %>
						 <option value="<%= categorie.getLibelle()%>"> <%=categorie.getLibelle() %></option>
						<%} %> 
					</select>
                    </div>
                </div>
                <!--Partie droite-->
                <div class="col-md-6 mb-3">  
	<div class="form-check">
		<label class="form-check-label"> <input type="radio"
			class="form-check-input" checked name="typeEncheres" value="achats"
			id="achats">Achats
		</label>
	</div>
	<div class="form-group">
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input" name="encheres"
				value="ouvertes" id="ouvertes">Enchères ouvertes
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"
				name="encheres" value="encours" id="encours">Mes enchères en cours
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"
				name="encheres" value="remportees" id="remportees">Mes enchères
				remportées
			</label>
		</div>
	</div>
	<div class="form-check">
		<label class="form-check-label"> <input type="radio"
			class="form-check-input" name="typeEncheres"
			value="ventes" id="ventes">Ventes
		</label>
	</div>
	<div class="form-group">
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"
				name="ventes" value="venteencours" id="venteencours">Mes ventes en
				cours
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"
				name="ventes" value="nondebutees" id="nondebutees">Mes ventes non
				débutées
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input"
				name="ventes" value="terminees" id="terminees">Mes ventes terminées
			</label>
		</div>
	</div>



                </div>
            </div>
            <button class="btn btn-primary btn-lg btn-block col-12" type="submit">
            	Rechercher
            </button>
        </form>
        </c:if>
     
        <!--enchères-->

	        <!-- Liste des articles mode non connecté -->
	        <div class="row justify-content-center border-top card-deck">
	           <c:forEach items="${articlesList}" var="itemArticle">
	           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
	            </c:forEach>
	     	</div>
    
    
   	        <!-- Liste des articles mode  connecté --> 
 		 <c:if test="${not empty utilisateurConnecte}">  
  	        <div class="row justify-content-center border-top card-deck">
	           <c:forEach items="${enchereListeHome}" var="itemArticle">
	           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
	            </c:forEach>
	     	</div> 
	     	</c:if> 
<%-- 
    	<c:if test="${not empty utilisateurConnecte}">
     		<c:if test="${empty param.motCle}">
     			<h3>motclé vide</h3>  
     		</c:if>   
      		<c:if test="${not empty param.motCle}">
     			<h3>il faut la liste avec le motcle</h3>  
     		</c:if>
     		${param.motCle} 		   	
     	</c:if>    --%>
        
    
    <!--  Ancienne version : Déprécié A CONSERVER -->
 <%--     	<!-- Affichage Achats  -->
     	<c:if test="${not empty utilisateurConnecte}">
     		<c:if test="${param.typeEncheres == 'achats'}">
				<c:choose>         
					<c:when test = "${param.encheres == 'ouvertes'}">
			            <h3>Bouton radio ACHAT + Encheres OUVERTES </h3>
				        <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${encheresOuvertesListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>			            
			         </c:when>			         
			         <c:when test = "${param.encheres == 'encours'}">
			            <h3>Bouton radio ACHAT + MES Encheres</h3>
			            <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${MesEncheresListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>				            
			         </c:when>			         
			         <c:otherwise>
			            <h3>Bouton radio ACHAT + MES Encheres remportées</h3>
			            <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${MesEncheresGagneesListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>			            
			         </c:otherwise>
				</c:choose>     		 
     		</c:if>      	
     	</c:if> 
      	<!-- Affichage Mes ventes  -->
     	<c:if test="${not empty utilisateurConnecte}">
     		<c:if test="${param.typeEncheres == 'ventes'}">
				<c:choose>         
					<c:when test = "${param.ventes == 'venteencours'}">
			            <h3>Bouton radio VENTE + venteencours </h3> 
			            <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${MesVentesEncoursListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>			                    		            
			         </c:when>			         
			         <c:when test = "${param.ventes == 'nondebutees'}">
			            <h3>Bouton radio VENTE + nondebutees</h3>
			            <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${MesVentesNonDebuteesListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>			            
			         </c:when>			         
			         <c:otherwise>
			            <h3>Bouton radio VENTE + Terminées</h3>
			            <div class="row justify-content-center border-top card-deck">
				           <c:forEach items="${MesVentesTermineesListe}" var="itemArticle">
				           	<%@ include file="/WEB-INF/business/articleDansListe.jspf" %>
				            </c:forEach>
				     	</div>			            
			         </c:otherwise>
				</c:choose>     		 
     		</c:if>      	
     	</c:if>  
     	
       	<!-- Affichage searchbar  -->
     	<c:if test="${not empty utilisateurConnecte}">
     		<c:if test="${empty param.motCle}">
     			<h3>motclé vide</h3>  
     		</c:if>   
      		<c:if test="${not empty param.motCle}">
     			<h3>il faut la liste avec le motcle</h3>  
     		</c:if>     		   	
     	</c:if>     		  	 --%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';

			window
					.addEventListener(
							'load',
							function() {
								checkAchats();
								checkVentes();
								achats.addEventListener('change', function(
										event) {
									checkAchats();
								}, false);
								ventes.addEventListener('change', function(
										event) {
									checkVentes();
								}, false);

								function checkAchats() {
									//id radio button achats
									var achats = document
											.getElementById('achats');
									if (achats.checked) {
										//id des checkbox
										document.getElementById('venteencours').disabled = true;
										document.getElementById('nondebutees').disabled = true;
										document.getElementById('terminees').disabled = true;
										document.getElementById('encours').disabled = false;
										document.getElementById('ouvertes').disabled = false;
										document.getElementById('remportees').disabled = false;
									}
								}
								function checkVentes() {
									//id radio button ventes
									var ventes = document
											.getElementById('ventes');
									if (ventes.checked) {
										//id des checkbox
										document.getElementById('venteencours').disabled = false;
										document.getElementById('nondebutees').disabled = false;
										document.getElementById('terminees').disabled = false;
										document.getElementById('encours').disabled = true;
										document.getElementById('ouvertes').disabled = true;
										document.getElementById('remportees').disabled = true;
									}
								}
							}, false);
		})();
	</script>  	   		 
    </main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>