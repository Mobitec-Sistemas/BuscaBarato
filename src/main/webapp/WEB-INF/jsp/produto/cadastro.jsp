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
        <select id="marcas"></select>
        
        <div ng-app="MyApp" ng-controller="MyController">
            <select ng-model="ddlMarcas" ng-options="marca.nome for marca in Marcas track by marca.codigo">
            </select>
            
            <ul>
            <li ng-repeat="x in Marcas">
              {{ x.codigo + ', ' + x.nome }}
            </li>
          </ul>
        </div>
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
        });
    </script>
</html>
