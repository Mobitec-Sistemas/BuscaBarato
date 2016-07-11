<%-- 
    Document   : index
    Created on : 28/05/2016, 11:02:55
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
        <h1>Página inicial do Busca Barato</h1>
        <h2>Olá ${usuario.nome}</h2>
        <a href="${linkTo[ProdutoController].lista}">Produtos</a>
        <br>
        <a href="${linkTo[UsuarioController].login}">Login</a>
        <br>
        <a href="${linkTo[EstadoController].lista}">Estado</a>
        <br>
        <a href="cidade">Cidade</a>
        <br>
        <a href="${linkTo[BairroController].lista}">Bairro</a>
    </body>
</html>
