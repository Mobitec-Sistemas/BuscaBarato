
<div class="table-responsive" ng-app="MyApp" ng-controller="MyController">
    <h2>Lista de Marcas</h2>

    <div class="alert alert-danger" role="alert" ng-bind-html="Mensagem" id="idMensagemErro"></div>
    
    <table  class="table table-striped">
        <thead>
            <tr>
                <th>Código</th>
                <th>Marca</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${marcaList}" var="marca">
                <tr id="marca_${marca.codigo}">
                    <td>${marca.codigo}</td>
                    <td>${marca.nome}</td>
                    <td><a href="${linkTo[MarcaController].lista}/${marca.codigo}">Editar</a></td>
                    <td><a href="javascript:void(0)" ng-click="excluirMarca(${marca.codigo});">Excluir</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <input type="button" onclick="location.href = '${linkTo[MarcaController].lista}/0';" value="Novo" />

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

