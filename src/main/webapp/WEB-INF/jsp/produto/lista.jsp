<%-- 
    Document   : lista
    Created on : 28/05/2016, 15:01:27
    Author     : Fabio
--%>


<h3>Lista de Produtos</h3>
<ul>
    <c:forEach items="${produtoList}" var="produto">
        <li>
            ${produto.codigo} - ${produto.descricao}
        </li>
    </c:forEach>
</ul>
