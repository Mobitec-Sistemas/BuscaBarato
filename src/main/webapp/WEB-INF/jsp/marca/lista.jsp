<%-- 
    Document   : marcas
    Created on : 05/08/2016, 13:35:16
    Author     : Sensum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busca Barato - Lista de Marcas</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.js"></script>
        <script src="http://code.angularjs.org/1.0.3/angular-sanitize.js"></script>
        <script type="text/JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    </head>
    <body ng-app="MyApp" ng-controller="MyController">
        <h3>Lista de Marcas</h3>
        
        <div ng-bind-html="Mensagem"></div>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Marca</th>
            </tr>
            
            <c:forEach items="${marcaList}" var="marca">
                <tr id="marca_${marca.codigo}">
                    <td>${marca.codigo}</td>
                    <td>${marca.nome}</td>
                    <td><a href="${linkTo[MarcaController].lista}/${marca.codigo}">Editar</a></td>
                    <td><a href="javascript:void(0)" ng-click="excluirMarca(${marca.codigo});">Excluir</a></td>
                </tr>
            </c:forEach>

        </table>
        
        <input type="button" onclick="location.href='${linkTo[MarcaController].lista}/0';" value="Novo" />
                
        <script type="text/JavaScript">
            var app = angular.module('MyApp', ["ngSanitize"]);
            app.controller('MyController', function ($scope, $http) {

                $scope.excluirMarca = function(codMarca){
                    
                    var link = "${linkTo[MarcaController].excluir}"
                    link = link.concat(codMarca)
                                        
                    /*$http.delete(link).then(function (response) {
                        $scope.Marcas = response.data.marcas;
                    });*/
                    $http.delete(link)
                        .success(function (data, status, headers) {
                            $scope.ServerResponse = data;
                    
                            $scope.Mensagem = data;
                            
                            // Remove o registro da tela
                            var tr = $("#marca_"+ codMarca); //.closest('tr');

                            tr.fadeOut(500, function(){ 
                                tr.remove(); 
                            }); 
                            
                        })
                        .error(function (data, status, header, config) {
                            /*$scope.ServerResponse = "Data: " + data +
                            "<br><br><br><br>status: " + status +
                            "<br><br><br><br>headers: " + header +
                            "<br><br><br><br>config: " + config;
                            
                            //"<html><head><title>Error</title></head><body>Não é permitido excluir esta marca pois ela possui produtos relacionados</body></html>"
                            */
                   
                            var inicio = data.indexOf("<body>");
                            var fim = data.indexOf("</body>");
                            $scope.Mensagem = data.substring(inicio, fim);
                        });
                    
                };
                
            });
        </script>

    </body>
</html>
