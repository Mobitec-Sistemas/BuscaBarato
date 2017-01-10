
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

<div class="table-responsive" ng-app="MyApp" ng-controller="MyController">
    <h2>Lista de Marcas</h2>

    <div class="alert alert-danger" role="alert" ng-bind-html="Mensagem" id="idMensagemErro"></div>
    
    <table  class="table table-striped">
        <thead>
            <tr>
                <th class="col_codigo">Código</th>
                <th>Marca</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${marcaList}" var="marca">
                <tr id="marca_${marca.codigo}">
                    <td>${marca.codigo}</td>
                    <td>${marca.nome}</td>
                    <td class="col_icone">
                        <a href="${linkTo[MarcaController].lista}/${marca.codigo}">
                            <img src="<c:url value="/imagem/editar.png"/>" alt="Edita a marca" title="Editar Marca">
                        </a>
                    </td>
                    <td class="col_icone">
                        <a href="javascript:void(0)" ng-click="excluirMarca(${marca.codigo});">
                            <img src="<c:url value="/imagem/Delete.png"/>" alt="Excluir a marca" title="Excluir Marca">
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="form-group col-xs-12">
        <input type="button" class="btn btn-default center-block" onclick="location.href = '${linkTo[MarcaController].lista}/0';" value="Novo" />
    </div>
    
</div>

<script type="text/JavaScript">
    // Oculta o campo de mensagem de erro
    $("#idMensagemErro").fadeOut(1);
            
    var app = angular.module('MyApp', ["ngSanitize"]);
    app.controller('MyController', function ($scope, $http) {

        $scope.excluirMarca = function(codMarca) {

            var link = "${linkTo[MarcaController].excluir}"
            link = link.concat(codMarca)
            
            $http.delete(link)
            .success(function (data, status, headers) {
                $scope.ServerResponse = data;

                $scope.Mensagem = data;

                // Remove o registro da tela
                var tr = $("#marca_"+ codMarca); //.closest('tr');
                tr.fadeOut(500, function(){ 
                    tr.remove(); 
                }); 
                
                // Oculta o campo de mensagem de erro
                $("#idMensagemErro").fadeOut(500);

            })
            .error(function (data, status, header, config) {
                /*$scope.ServerResponse = "Data: " + data +
                "<br><br><br><br>status: " + status +
                "<br><br><br><br>headers: " + header +
                "<br><br><br><br>config: " + config;*/
                
                // Mostra a mensagem de Erro
                $("#idMensagemErro").fadeIn(500);

                var inicio = data.indexOf("<body>");
                var fim = data.indexOf("</body>");
                $scope.Mensagem = data.substring(inicio, fim);
                
            });

        };

    });
</script>

