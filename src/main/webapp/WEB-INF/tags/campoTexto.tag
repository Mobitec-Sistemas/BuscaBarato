<%-- 
    Document   : campoTexto do sistema já com mensagem de erro
    Created on : 21/10/2016, 17:49:41
    Author     : Sensum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Tag personalizada para o campo texto" pageEncoding="UTF-8"%>

<%@ attribute name="type" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="name" %>
<%@ attribute name="value" %>
<%@ attribute name="placeholder" %>
<%@ attribute name="step" %>

<!-- Verifica se tem erro -->
<c:set var="comErro" value="${false}" />
<c:set var="mensagemErro" value="" />
<c:set var="classeErro" value="" />

<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="error">

        <c:if test="${error.category == id }">
            <c:set var="comErro" value="${true}" />
            <c:set var="mensagemErro" value="${error.message}" />
            <c:set var="classeErro" value="has-error has-feedback" />
        </c:if>
    </c:forEach>
</c:if>

<div class="form-group ${classeErro}"> <!-- has-error has-feedback -->
    <label form="${id}" class="control-label col-xs-2">${label}</label>
    <div class="col-xs-10">
        <input type="${type}" step="${step}" id="${id}" class="form-control" name="${name}" value="${value}" placeholder="${placeholder}">
        
        <c:if test="${comErro}">
            <!-- Apresenta a mensagem de erro -->
            <span class="glyphicon glyphicon-remove form-control-feedback"></span>
            <span class="help-block">${mensagemErro}</span>
        </c:if>

    </div>
</div>