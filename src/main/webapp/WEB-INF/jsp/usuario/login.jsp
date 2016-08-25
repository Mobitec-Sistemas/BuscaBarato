<%-- 
    Document   : login
    Created on : 16/06/2016, 14:50:38
    Author     : Sensum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="MyApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="Buscador de Preços">
        <meta name="author" content="Mobitec Sistemas">
        <link rel="icon" href="imagem/BuscaBarato.png">

        <title>Busca Barato - Login</title>

        <!-- Bootstrap core CSS -->
        <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">
        <link href="css/jquery-ui.css" rel="stylesheet">
        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular-cookies.js"></script>
        
        <style>
            p {
                margin: 10px 0 10px;
            }
        </style>
    </head>

    <body ng-controller="MyController">
        <div class="container">

            <form class="form-signin" action="${linkTo[UsuarioController].login}" method="POST" ng-submit="enviarForm()">

                <h2 class="form-signin-heading">Acesso ao sistema Busca Barato</h2>

                <!-- Mensagem de Erro -->
                <c:forEach var="error" items="${errors}">
                    <div class="ui-widget">
                        <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                            <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                                <strong>Erro:</strong> ${error.message}.</p>
                        </div>
                    </div>
                </c:forEach>


                <label for="inputEmail" class="sr-only">Usuário</label>
                <input type="text" id="inputUsuario" name="usuario.login" class="form-control" placeholder="Usuário" required autofocus ng-model="login" >
                <label for="inputPassword" class="sr-only">Senha</label>
                <input type="password" id="inputPassword" name="usuario.senha" class="form-control" placeholder="Senha" required ng-model="senha">

                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me" ng-checked="lembrarSenha" ng-model="lembrarSenha"> Lembrar-me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Efetuar Login</button>

            </form>

        </div>

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="js/ie10-viewport-bug-workaround.js"></script>
        
        <!-- Carrega o usuário e senha do Cook -->
        <script type="text/JavaScript">       
            var app = angular.module('MyApp', ['ngCookies']);
            app.controller('MyController', ['$scope', '$cookies', function($scope, $cookies) {
                // Retrieving a cookie
                $scope.login = $cookies.get('login');
                //$scope.senha = $cookies.get('senha');
                $scope.lembrarSenha = $cookies.get('lembrarSenha') == 'true';
                
                $scope.enviarForm = function(){
                    if($scope.lembrarSenha) {
                        $cookies.put('login', $scope.login);
                        //$cookies.put('senha', $scope.senha);
                        $cookies.put('lembrarSenha', $scope.lembrarSenha);
                    }
                    else {
                        $cookies.remove('login');
                        //$cookies.remove('senha');
                        $cookies.remove('lembrarSenha');
                    }
                }
            
            }]);
                  
        </script>

    </body>
</html>
