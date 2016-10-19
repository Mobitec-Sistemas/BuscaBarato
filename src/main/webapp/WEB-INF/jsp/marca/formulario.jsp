<%-- 
    Document   : edita
    Created on : 24/08/2016, 11:44:15
    Author     : Sensum
--%>
<h1>Cadastro de Marcas</h1>

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


<form class="form-signin" action="${linkTo[MarcaController].cadastro}" method="POST">
    <div class="form-group">
        <label form="marca">Marca: </label>
        <input type="text" id="marca" name="marca.nome" value="${marca.nome}" autofocus placeholder="Nome da Marca">
        <input type="hidden" name="marca.codigo" value="${marca.codigo}">
        <br>
        <button type="submit" class="btn btn-default">Cadastrar</button>
    </div>
</form>