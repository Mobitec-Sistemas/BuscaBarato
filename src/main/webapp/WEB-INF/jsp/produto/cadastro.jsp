<%-- 
    Document   : cadastro
    Created on : 12/07/2016, 13:36:14
    Author     : Sensum
--%>

<h2>Cadastro de Produtos</h2>

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

<form class="form-signin" action="${linkTo[ProdutoController].cadastro}" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="produto.codigo" value="${produto.codigo}">

    <div class="form-group">
        <label for="inputDescricao">Descrição</label>
        <input type="text" id="inputDescricao" name="produto.descricao" value="${produto.descricao}" autofocus placeholder="Descrição do Produto" >
        <br>
        <span class="error" style="color: red">${errors.from('descricao')}</span>
    </div>

    <div class="form-group">
        <label for="inputMedida">Medida</label>
        <input type="text" id="inputMedida" name="produto.medida" value="${produto.medida}" required placeholder="Unidade de medida">
        <br>
        <span class="error">${errors.from('produto.medida')}</span>
    </div>

    <!-- <div ng-app="MyApp" ng-controller="MyController" class="form-group">
        <label for="selectMarca">Marca</label>
        <select id="selectMarca" name="produto.marcas[0].codigo" value="${produto.marcas[0].codigo}" ng-model="ddlMarcas" ng-options="marca.nome for marca in Marcas track by marca.codigo"></select>
    </div> -->

    <!-- Tabela com as Marcas -->
    <div class="tabela_formulario">
        <table  class="table table-striped table-bordered table-condensed">
            <thead>
                <tr>
                    <th class="col_codigo">V</th>
                    <th>Marca</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="seqProduto" value="${0}" />
                <c:forEach items="${marcaList}" var="marca">
                    <tr>
                        <td style="vertical-align: middle;">
                            <input type="checkbox" name="produto.marcas[${seqProduto}].codigo" value="${marca.codigo}">
                        </td>
                        <td style="vertical-align: middle;">${marca.nome}</td>
                    </tr>
                    
                    <c:set var="seqProduto" value="${seqProduto + 1}" />
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Fim da tabela com as marcas -->

    <div class="form-group" id="wrapper">
        <label for="fileUpload">Imagem</label>
        <!-- <input id="filaImagem" type="file" name="imagem" /> -->
        <input id="fileUpload" type="file" name="imagem" accept="image/jpeg, image/png" ><br />
        <div id="image-holder"></div>
    </div>

    <div class="form-group">
        <button type="submit">Cadastrar</button>
    </div>

</form>

<script type="text/JavaScript">
    //get a reference to the select element 
    /*var $select = $('#marcas');

    //request the JSON data and parse into the select element
    $.getJSON('../marcaJson', function(data){

    //clear the current content of the select
    $select.html('');

    //iterate over the data and append a select option
    $.each(data.marcas, function(key, val){ 
    $select.append('<option id="' + val.codigo + '">' + val.nome + '</option>');
    })
    });*/

    var app = angular.module('MyApp', []);
    app.controller('MyController', function ($scope, $http) {

    $http.get("${linkTo[MarcaController].listaJson}").then(function (response) {
    $scope.Marcas = response.data.marcas;

    $('#marcas').value = "${produto.marcas[0].codigo}";

    //$scope.ddlMarcas = "${produto.marcas[0].codigo}";
    });

    /*$http.get("../tipo_produto").then(function (response) {
    $scope.TiposProdutos = response.data.tiposProdutos;
    });*/
    });

    // Faz o previw da imagem que será feito o upload
    $("#fileUpload").on('change', function () {

    if (typeof (FileReader) != "undefined") {

    var image_holder = $("#image-holder");
    image_holder.empty();

    var reader = new FileReader();
    reader.onload = function (e) {
    $("<img />", {
    "src": e.target.result,
    "class": "thumb-image"
    }).appendTo(image_holder);
    }
    image_holder.show();
    reader.readAsDataURL($(this)[0].files[0]);
    } else{
    //alert("Este navegador nao suporta FileReader.");
    }
    });
</script>
