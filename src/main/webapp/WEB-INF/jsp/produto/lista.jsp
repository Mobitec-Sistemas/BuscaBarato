<%-- 
    Document   : lista de Produtos
    Created on : 28/05/2016, 15:01:27
    Author     : Fabio
--%>


<h3>Lista de Produtos</h3>

<c:if test="${errors}">
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

<table  class="table table-striped">
    <thead>
        <tr>
            <th class="col_codigo">Código</th>
            <th>Produto</th>
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
                <td style="vertical-align: middle;" class="text-center"><img class="img-thumbnail" alt="Imagem do produto" src="data:image/png;base64,${produto.getImagemBase64()}" /></td>
                
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

<input type="button" onclick="location.href = '${linkTo[ProdutoController].cadastro}';" value="Novo" />
