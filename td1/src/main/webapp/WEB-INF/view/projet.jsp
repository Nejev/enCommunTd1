<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerMain.jsp" %>
<h1>Les projets</h1>
<a id="deco" href=${URL_DELETE_LOGIN}>Deconnexion</a>
<h3>Je dirige ce projet</h3>
<ul class="list-group">
    <c:forEach items="${listDirigeP}" var="dirigeP">
        <li class="list-group-item">
                ${dirigeP.getIntituleP()}
        </li>
    </c:forEach>
</ul>
<h3>Je fais parti de ce projet</h3>
<ul class="list-group">
    <c:forEach items="${listParticipeP}" var="participeP">
        <li class="list-group-item">
                ${participeP.getIntituleP()}
        </li>
    </c:forEach>
</ul>
<h3>Je possède les compétences pour ce projet</h3>
<ul class="list-group">
    <c:forEach items="${listCompetenceP}" var="competenceP">
        <li class="list-group-item">
                ${competenceP.getIntituleP()}
        </li>
    </c:forEach>
</ul>
<h3>Les autres projets</h3>
<ul class="list-group">
    <c:forEach items="${listAutreP}" var="autreP">
        <li class="list-group-item">
                ${autreP.getIntituleP()}
        </li>
    </c:forEach>
</ul>
<%@ include file="viewHelper/footerMain.jsp" %>
