<%-- 
    Document   : edita
    Created on : 24/08/2016, 11:44:15
    Author     : Sensum
--%>

<ul class="errors">     
    
    <c:forEach items="${errors}" var="error">
        <li>
            ${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
            ${error.message} <!-- a mensagem de erro de validação -->
        </li>
    </c:forEach>
</ul>

<form class="form-signin" action="${linkTo[MarcaController].cadastro}" method="POST">
    <label form="marca">Marca: </label>
    <input type="text" id="marca" name="marca.nome" value="${marca.nome}" autofocus required />
    <input type="hidden" name="marca.codigo" value="${marca.codigo}" />
    <br>
    <button type="submit">Cadastrar</button>
</form>