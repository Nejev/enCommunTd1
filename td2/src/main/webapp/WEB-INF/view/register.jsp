<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="viewHelper/headerMain.jsp" %>
<h1>S'enregistrer</h1>
<div class="left">
    <form action=${URL_POST_REGISTER} method="post">
        Login :<input name="login" id="login" class="form-control">
        </br>
        Password :<input type="password" name="password" id="password" class="form-control">
        </br>
        Surnom :<input name="surnom" id="surnom" class="form-control">
        </br>
        <input type="submit" value="Enregistrer" class="btn btn-primary">
    </form>
</div>
<%@ include file="viewHelper/footerMain.jsp" %>
