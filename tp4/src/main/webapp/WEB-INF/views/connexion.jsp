<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page session="false"%>
<html>
<head>
    <title>Connexion au mur</title>
</head>
<body>

<form:form action="connexion" method="post" modelAttribute="user">

    login :
    <form:label path="login"/>
    <form:input path="login" /><br/>

    mot de passe:
    <form:label path="password"/>
    <form:password path="password" /><br/>


    <button type="submit">Login</button>
</form:form>
</body>
</html>
