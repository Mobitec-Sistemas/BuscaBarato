<%-- 
    Document   : campoPreco
    Created on : 23/03/2017, 14:47:50
    Author     : Fábio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Tag personalizada para o campo de preços" pageEncoding="UTF-8"%>

<span class="input-group-addon">R$</span>
<input type="number" step="0.01" min="0" max="999,999.99" class="form-control preco" placeholder="Preço do Produto" ng-model="tabelaPreco.preco">                                

<c:if test="${not empty usuarioLogado.usuario}">
    <span class="input-group-btn">
        <button type="button" class="clearfix btn btn-default" aria-label="Alterar Preço" ng-click="registrarPreco(tabelaPreco);">
            <img src="<c:url value="/imagem/Confirmar.png"/>" alt="Alterar Preço" title="Alterar Preço">
        </button>
    </span>
</c:if>