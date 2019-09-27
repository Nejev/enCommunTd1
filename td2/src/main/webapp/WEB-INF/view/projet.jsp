<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerLog.jsp" %>
<h1>Les projets</h1>
<div class="left">
    <h2>Mes projets (Dirigeant)</h2>
    <ul class="list-group">
        <c:forEach items="${listDirigeP}" var="dirigeP">
            <li class="list-group-item">
                    ${dirigeP.getIntituleP()} <a
                    href=${URL_DELETE_PROJET}?intituleP=${dirigeP.getIntituleP()}>Supprimer</a>
            </li>
        </c:forEach>
    </ul>
    <h2>Mes projets (Participant)</h2>
    <ul class="list-group">
        <c:forEach items="${listParticipeP}" var="participeP">
            <li class="list-group-item">
                    ${participeP.getIntituleP()} <a
                    href=${URL_UPDATE_QUIT_PROJET}?intituleP=${participeP.getIntituleP()}>Quitter</a>
            </li>
        </c:forEach>
    </ul>
    <h2>Projets existants (<span class="green">Compétent</span>/<span class="red">Non compétent</span>)</h2>
    <ul class="list-group">
        <c:forEach items="${listCompetenceP}" var="competenceP">
            <li class="list-group-item">
                <span class="green"> ${competenceP.getIntituleP()}</span> <a
                    href=${URL_UPDATE_PARTICIPE_PROJET}?intituleP=${competenceP.getIntituleP()}>Participer</a>
            </li>
        </c:forEach>
        <c:forEach items="${listAutreP}" var="autreP">
            <li class="list-group-item">
                <span class="red"> ${autreP.getIntituleP()} </span>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="right">
    <h2>Ajouter/modifier un projet</h2>
    <form action=${URL_POST_PROJET} method="post">
        Intitulé :<input name="intitule" id="intitule" class="form-control">
        </br>
        Description :<input name="description" id="description" class="form-control">
        </br>
        <input type="submit" value="Ajouter" class="btn btn-primary">
    </form>
</div>
<%@ include file="viewHelper/footerMain.jsp" %>
