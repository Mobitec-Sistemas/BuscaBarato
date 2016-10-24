<h2>Lista de Empresas</h2>

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

<table  class="table table-striped">
    <thead>
        <tr>
            <th class="col_codigo">Código</th>
            <th>Nome</th>
            <th>E-Mail</th>
            <th>Cidade</th>
            <th>Estado</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${empresaList}" var="empresa">
            <tr>
                <td>${empresa.codigo}</td>
                <td>${empresa.nome}</td>
                <td>${empresa.email}</td>
                <td>${empresa.bairro.cidade.nome}</td>
                <td>${empresa.bairro.cidade.estado.nome}</td>
                
                <td class="col_icone">
                    <a href="<c:url value="/empresa/cadastro/${empresa.codigo}"/>">
                        <img src="<c:url value="/imagem/editar.png"/>" alt="Edita o produto" title="Editar Empresa">
                    </a>
                </td>
                
                <td class="col_icone">
                    <a href="javascript:void(0)" ng-click="excluirEmpresa(${empresa.codigo});">
                        <img src="<c:url value="/imagem/Delete.png"/>" alt="Excluir a empresa" title="Excluir Empresa">
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<button onclick="location.href = '<c:url value="/empresa/cadastro"/>';">Novo</button>
<!-- <input type="button" onclick="location.href = '<c:url value="/empresa/cadastro"/>';" value="Novo" /> -->
