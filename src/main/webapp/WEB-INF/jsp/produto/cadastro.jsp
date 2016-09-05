<%-- 
    Document   : cadastro
    Created on : 12/07/2016, 13:36:14
    Author     : Sensum
--%>

<h2>Cadastro de Produtos</h2>

<form class="form-signin" action="${linkTo[ProdutoController].cadastro}" method="POST">
    <input type="hidden" name="produto.codigo" value="${produto.codigo}">

    <div class="form-group">
        <label for="inputDescricao">Descrição</label>
        <input type="text" id="inputDescricao" name="produto.descricao" autofocus required placeh >
        <span class="error" style="color: red">${errors.from('descricao')}</span>
    </div>
    
    <div ng-app="MyApp" ng-controller="MyController" class="form-group">
        <label for="selectMarca">Marca</label>
        <select id="selectMarca" name="produto.marcas[0].codigo" ng-model="ddlMarcas" ng-options="marca.nome for marca in Marcas track by marca.codigo"></select>
    </div>
        
    <div class="form-group">
        <label for="inputMedida">Medida</label>
        <input type="text" id="inputMedida" name="produto.medida" required>
        <span class="error">${errors.from('produto.medida')}</span>
    <div class="form-group">

    <button type="submit">Cadastrar</button>
</form>

<script type="text/JavaScript">
    //get a reference to the select element
    var $select = $('#marcas');

    //request the JSON data and parse into the select element
    $.getJSON('../marcaJson', function(data){

        //clear the current content of the select
        $select.html('');

        //iterate over the data and append a select option
        $.each(data.marcas, function(key, val){ 
            $select.append('<option id="' + val.codigo + '">' + val.nome + '</option>');
        })
    });

    var app = angular.module('MyApp', []);
        app.controller('MyController', function ($scope, $http) {

        $http.get("../marcaJson").then(function (response) {
            $scope.Marcas = response.data.marcas;
        });

        $http.get("../tipo_produto").then(function (response) {
            $scope.TiposProdutos = response.data.tiposProdutos;
        });
    });
</script>
