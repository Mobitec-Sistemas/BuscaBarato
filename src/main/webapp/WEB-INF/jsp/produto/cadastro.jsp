<%-- 
    Document   : cadastro
    Created on : 12/07/2016, 13:36:14
    Author     : Sensum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de produto</title>
        <script type="text/JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    </head>
    <body>

        <form class="form-signin" action="${linkTo[ProdutoController].cadastro}" method="POST">
            <input type="hidden" name="produto.codigo" value="${produto.codigo}">
            
            <label for="inputDescricao">Descrição</label>
            <input type="text" id="inputDescricao" name="produto.descricao" autofocus required >
            <br>
            <span class="error" style="color: red">${errors.from('descricao')}</span>
            <br>

            <div ng-app="MyApp" ng-controller="MyController">
                <label for="selectMarca">Marca</label>
                <select id="selectMarca" name="produto.marcas[0].codigo" ng-model="ddlMarcas" ng-options="marca.nome for marca in Marcas track by marca.codigo"></select>
            </div>
            <br>

            <label for="inputMedida">Medida</label>
            <input type="text" id="inputMedida" name="produto.medida" required>
            <span class="error">${errors.from('produto.medida')}</span>
            
            <button type="submit">Cadastrar</button>
        </form>
        
    </body>
    
    <script type="text/JavaScript">
        //get a reference to the select element
        var $select = $('#marcas');
 
        //request the JSON data and parse into the select element
        $.getJSON('../marca', function(data){
             
            //clear the current content of the select
            $select.html('');

            //iterate over the data and append a select option
            $.each(data.marcas, function(key, val){ 
                $select.append('<option id="' + val.codigo + '">' + val.nome + '</option>');
            })
        });
  
        var app = angular.module('MyApp', []);
        app.controller('MyController', function ($scope, $http) {
            
            $http.get("../marca").then(function (response) {
                $scope.Marcas = response.data.marcas;
            });
            
            $http.get("../tipo_produto").then(function (response) {
                $scope.TiposProdutos = response.data.tiposProdutos;
            });
        });
    </script>
</html>
