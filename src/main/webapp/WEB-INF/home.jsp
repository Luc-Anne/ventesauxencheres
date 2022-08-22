<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Accueil</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<!--main bloc-->
    <main>
        <!--title-->
        <div class="mx-auto text-center">
            <h1>Enchères</h1>
        </div>
        <!--erreur-->
        <div class="d-flex alert-danger">
            <div class="col-3 p-2">
                <img class="small-icon" src="images/error.svg">
            </div>
        
            <ul class="col-9 list-unstyled p-2">
                <li>un message d'erreur éventuellement !</li>
                <li>un autre message....</li>
            </ul>
        </div>
        <!--filtre-->
        <form class="form-filter border mb-3" action="#" method="">
            <div class="row">
                <!--Partie gauche-->
                <div class="col-md-6 mb-3">
                    <div class="form-group">
                            <label for="filter-input">Filtre</label>
                            <input type="text" class="form-control" id="filter-input" name="q" placeholder="articles contenant...">
                    </div>
                    <div class="form-group">
                        <label for="categories-select">Catégories</label>
                        <select class="form-control" id="categories-select" name="categorie">
                            <option selected>Toutes</option>
                            <option name="categorie" value="">Informatique</option>
                            <option name="categorie" value="">Ameublement</option>
                            <option name="categorie" value="">Vêtement</option>
                            <option name="categorie" value="">Sport & Loisirs</option>
                        </select>
                    </div>
                </div>
                <!--Partie droite-->
                <div class="col-md-6 mb-3">  	
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" checked name="type-encheres" value="achats" id="achats">Achats
                        </label>
                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" checked name="encheres" value="ouvertes" id="ouvertes">Enchères ouvertes
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="encheres" value="encours" id="encours">Mes enchères en cours
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="encheres" value="remportees" id="remportees">Mes enchères remportées
                            </label>
                        </div>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="type-encheres" value="ventes" id="ventes">Ventes
                        </label>
                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="ventes" value="venteencours" id="venteencours">Mes ventes en cours
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="ventes" value="nondebutees" id="nondebutees">Mes ventes non débutées
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="ventes" value="terminees" id="terminees">Mes ventes terminées
                            </label>
                        </div>
                    </div>

                </div>
            </div>
            <button class="btn btn-primary btn-lg btn-block" type="submit">
            	<img class="small-icon" src="images/search.svg" alt="Eni Ecole">
            </button>
        </form>

        <!--enchères-->
        <div class="row justify-content-center border-top card-deck">
           <c:forEach items="${articleList}" var="itemArticle">
             <div class="col-12 col-sm-6 p-2" >
                 <div class="card">
                     <div class="card-header text-center">
                         <h4 class="my-0 font-weight-normal">${itemArticle.nomArticle}</h4>
                     </div>
                     <div class="d-flex">
                         <div class="col-3 p-2">
                             <img class="img-fluid img-thumbnail" src="images/photo.svg" alt="pas de photo" />
                         </div>
                         <ul class="col-9 list-unstyled p-2">
                             <li>Prix : ${itemArticle.miseAPrix} point(s)</li>
                             <li>Meilleure enchère :${itemArticle.prixVente} point(s)</li>
                             <li>Fin de l'enchère : ${itemArticle.dateFinEncheres} - dd-MM-yyyy HH:mm</li>
                             <li>Vendeur : ${itemArticle.vendeur.pseudo}</li>
                         </ul>
                     </div>
                     <a class="mt-3 btn btn-lg btn-block btn-primary" href="#" title="faire une enchère">
                         <img class="small-icon" src="images/bid.svg">
                     </a>
                 </div>
             </div>
            </c:forEach>
     </div>
    </main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>