<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/fragments/taglib.jspf" %>
<%@ page import="fr.eni.ventesauxencheres.controllers.util.Errors" %>
<!DOCTYPE html>
<html lang="fr">
<head>
	<%@ include file="/WEB-INF/fragments/commonHead.jspf" %>
	<title>${initParam.debutTitre} Inscription</title>
</head>
<body class="bg-light">
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<main class="container">
	    <div class="mx-auto text-center">
            <h1>Inscription</h1>
        </div>
        <c:set var="typeForm" value="inscription"></c:set>
		<%@ include file="/WEB-INF/utilisateur/clientForm.jspf" %>
	</main>
	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>