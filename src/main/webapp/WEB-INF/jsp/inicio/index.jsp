<%-- 
    Document   : index
    Created on : 28/05/2016, 11:02:55
    Author     : Fabio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Página inicial do Busca Barato</h1>
        <a href="${linkTo[ProdutoController].lista}">Produtos</a>
        <br>
        <a href="${linkTo[UsuarioController].login}">Login</a>
    </body>
</html>
