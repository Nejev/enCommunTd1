<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerMain.jsp" %>
<h1>Se connecter</h1>
<form action="/td3_war/login/loginP" method="post">
    Login :<input name="login" id="login" class="form-control">
    </br>
    Password :<input type="password" name="password" id="password" class="form-control">
    </br>
    <input type="submit" value="Connexion" class="btn btn-primary">
</form>
<%@ include file="viewHelper/footerMain.jsp" %>
