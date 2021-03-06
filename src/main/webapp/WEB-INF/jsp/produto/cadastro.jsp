<%-- 
    Document   : cadastro
    Created on : 12/07/2016, 13:36:14
    Author     : Sensum
--%>

<h2>Cadastro de Produtos</h2>

<!-- Importa a parte do html que mostra as mensagens de erro -->
<c:if test="${not empty errors}">
    <div class="alert alert-danger" role="alert">
        <ul class="errors">
            <c:forEach items="${errors}" var="error">
                <li>
                    ${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
                    ${error.message} <!-- a mensagem de erro de valida��o -->
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form class="form-horizontal" action="${linkTo[ProdutoController].cadastro}" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="produto.codigo" value="${produto.codigo}">

    <mt:campoTexto type="text" id="inputDescricao" label="Descri��o" name="produto.descricao" value="${produto.descricao}" placeholder="Descri��o do Produto" />
    
    <mt:campoTexto type="text" id="inputMedida" label="Medida" name="produto.medida" value="${produto.medida}" placeholder="Unidade de medida do Produto" />

    <div class="form-group" id="wrapper">
        <label for="fileUpload" class="control-label col-xs-2">Imagem</label>
        <!-- <input id="filaImagem" type="file" name="imagem" /> -->
        <div class="col-xs-10">
            <input id="fileUpload" type="file" class="form-control" name="imagem" accept="image/jpeg, image/png" ><br />
            <div id="image-holder"></div>
        <div class="col-xs-10">
    </div>

            
    <div class="form-group col-xs-12">
        <button type="submit" class="btn btn-default center-block">Cadastrar</button>
    </div>

</form>

<script type="text/JavaScript">
    
    /*var app = angular.module('MyApp', []);
    app.controller('MyController', function ($scope, $http) {

        $http.get("${linkTo[MarcaController].listaJson}").then(function (response) {
            $scope.Marcas = response.data.marcas;

            $('#marcas').value = produto.marca.codigo;
        });

    });*/

    // Faz o previw da imagem que ser� feito o upload
    $("#fileUpload").on('change', function () {

        if (typeof (FileReader) != "undefined") {
            var image_holder = $("#image-holder");
            image_holder.empty();

            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image",
                    "style": "width: 200px;"
                }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else{
            //alert("Este navegador nao suporta FileReader.");
        }
    });
</script>
