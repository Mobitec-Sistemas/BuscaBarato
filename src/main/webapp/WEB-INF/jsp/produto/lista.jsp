<%-- 
    Document   : lista de Produtos
    Created on : 28/05/2016, 15:01:27
    Author     : Fabio
--%>


<h2>Lista de Produtos</h2>

<input type="hidden" value="${pagina}" />

<c:if test="${not empty errors}">
    <div class="alert alert-danger" role="alert">
        <ul class="errors">
            <c:forEach items="${errors}" var="error">
                <li>
                    ${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
                    ${error.message} <!-- a mensagem de erro de validação -->
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<!-- Paginação -->
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li class='${(pagina == 1 ? "disabled" : "")}'>
            <a href="${linkTo[ProdutoController].lista}/${pagina == 1 ? pagina : pagina-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        
        <c:forEach var="i" begin="1" end="${total_pagina}">       
            <li class='${(pagina == i ? "active" : "")}'><a href="${linkTo[ProdutoController].lista}/${i}">${i}</a></li>
        </c:forEach>
        
        <li class='${(total_pagina == pagina ? "disabled" : "")}'>
            <a href="${linkTo[ProdutoController].lista}/${ total_pagina == pagina ? pagina : pagina+1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>

<table  class="table table-striped">
    <thead>
        <tr>
            <th class="col_codigo">Código</th>
            <th>Produto</th>
            <th>Marca</th>
            <th>Imagem</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${produtoList}" var="produto">
            <tr>
                <td style="vertical-align: middle;">${produto.codigo}</td>
                <td style="vertical-align: middle;">${produto.descricao}</td>
                <td style="vertical-align: middle;" class="text-center"><img class="img-thumbnail imagem-grid" alt="Imagem do produto" src="data:image/png;base64,${produto.getImagemBase64()}" /></td>
                
                <td style="vertical-align: middle;" class="col_icone">
                    <a href="${linkTo[ProdutoController].cadastro}/${produto.codigo}">
                        <img src="<c:url value="/imagem/editar.png"/>" alt="Edita o produto" title="Editar Produto">
                    </a>
                </td>
                
                <td style="vertical-align: middle;" class="col_icone">
                    <a href="javascript:void(0)" ng-click="excluirProduto(${produto.codigo});">
                        <img src="<c:url value="/imagem/Delete.png"/>" alt="Excluir o produto" title="Excluir Produto">
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="form-group col-xs-12">
    <input type="button" class="btn btn-default center-block" onclick="location.href='${linkTo[ProdutoController].cadastro}';" value="Novo" />
</div>