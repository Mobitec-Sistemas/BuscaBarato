<%-- 
    Document   : login
    Created on : 16/06/2016, 14:50:38
    Author     : Sensum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach var="error" items="${errors}">
            ${error.category} - ${error.message}<br />
        </c:forEach>
        
        <form action="${linkTo[UsuarioController].login}" method="POST">
            <span>${login}</span>
            <label for="login">Login:</label>
            <input type="text" id="login" name="usuario.login">
            <br>
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="usuario.senha" />
            
            <input type="submit" value="Login"/>
        </form>
            
    </body>
</html>
