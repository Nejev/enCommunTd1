<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerMain.jsp" %>
<h1>Les compétences</h1>
<a id="deco" href=${URL_DELETE_LOGIN}>Deconnexion</a>
<div class="container">
    <div class="left">
        <h2>Mes compétences</h2>
        <ul>
            <c:forEach items="${listCompetenceUser}" var="competenceUser">
                <li class="list-group-item">
                        ${competenceUser.getType().getIntituleC()} (niveau ${competenceUser.getNiveau()}) ${competenceUser.getCommentaire()} <a href=${URL_DELETE_COMPETENCEMEMBRE}?intituleCM=${competenceUser.getType().getIntituleC()}>Supprimer</a>
                </li>
            </c:forEach>
        </ul>
        <br>
        <h2>Liste des compétences existantes</h2>
        <ul>
            <c:forEach items="${listCompetence}" var="competence">
                <li class="list-group-item">
                        ${competence.getIntituleC()} ${competence.getDescriptionC()}
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="right">
        <h2>Ajouter/modifier mes compétences</h2>
        <form action=${URL_POST_COMPETENCEMEMBRE} method="post">
            Compétence:
            <select class="form-control" id="intituleCM" name="intituleCM">
                <c:forEach items="${listCompetence}" var="competenceP">
                    <option>
                            ${competenceP.getIntituleC()}
                    </option>
                </c:forEach>
            </select>
            Niveau :<input name="niveau" id="niveau" class="form-control">
            </br>
            Description :<input name="descriptionCM" id="descriptionCM" class="form-control">
            </br>
            <input type="submit" value="Ajouter" class="btn btn-primary">
        </form>
        <br>
        <h2>Ajouter/modifier une compétence</h2>
        <form action=${URL_POST_COMPETENCE} method="post">
            Intitulé :<input name="intitule" id="intitule" class="form-control">
            </br>
            Description :<input name="description" id="description" class="form-control">
            </br>
            <input type="submit" value="Ajouter" class="btn btn-primary">
        </form>
    </div>
</div>
<%@ include file="viewHelper/footerMain.jsp" %>
