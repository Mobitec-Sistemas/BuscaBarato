<%-- 
    Document   : mensagemSucessoErro
    Created on : 21/02/2017, 17:15:54
    Author     : Sensum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Tag personalizada para as mensagem de sucesso e erro" pageEncoding="UTF-8"%>

<div id="alertaErro" class="alert alert-danger" role="alert" style="display:none">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    <span class="sr-only">Erro:</span>
    {{mensagemErro}}
</div>
<div id="alertaSucesso" class="alert alert-success" role="alert" style="display:none">
    <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
    <span class="sr-only">Sucesso:</span>
    {{ mensagemSucesso }}
</div>

