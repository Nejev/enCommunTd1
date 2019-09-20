<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerMain.jsp" %>
<h1>Bienvenue sur EnCommun ${login}</h1>
<a id="deco" href=${URL_DELETE_LOGIN}>Deconnexion</a>
<br>
<br>
<ul class="list-group">
    <li class="list-group-item">
        <a href=${URL_GET_PROJET}>Projets</a>
    </li>
    <li class="list-group-item">
        <a href=${URL_GET_COMPETENCE}>Compétences</a>
    </li>
</ul>
<%@ include file="viewHelper/footerMain.jsp" %>

