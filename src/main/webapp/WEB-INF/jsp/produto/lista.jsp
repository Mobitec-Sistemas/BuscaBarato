<%-- 
    Document   : lista
    Created on : 28/05/2016, 15:01:27
    Author     : Fabio
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
        <h3>Lista de Produtos</h3>
        <ul>
        <c:forEach items="${produtoList}" var="produto">
            <li>
                ${produto.codigo} - ${produto.descricao}
            </li>
        </c:forEach>
        </ul>

    </body>
</html>
