<%-- 
    Document   : login
    Created on : 16/06/2016, 14:50:38
    Author     : Sensum
--%>


<div class="col-sm-3 container" ng-app="MyApp" ng-controller="MyController">

    <form class="form-signin" action="<c:url value="usuario" />" method="POST" ng-submit="enviarForm()">

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


        <label for="inputEmail" class="sr-only">Usu�rio</label>
        <input type="text" id="inputUsuario" name="usuario.login" class="form-control" placeholder="Usu�rio" required autofocus ng-model="login" >
        <label for="inputPassword" class="sr-only">Senha</label>
        <input type="password" id="inputPassword" name="usuario.senha" class="form-control" placeholder="Senha" required ng-model="senha">

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me" ng-checked="lembrarSenha" ng-model="lembrarSenha"> Lembrar-me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Efetuar Login</button>

    </form>



    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/js/ie10-viewport-bug-workaround.js" />"></script>

    <!-- Carrega o usu�rio e senha do Cook -->
    <script type="text/JavaScript">       
        var app = angular.module('MyApp', ['ngCookies']);
        app.controller('MyController', ['$scope', '$cookies', function($scope, $cookies) {
            // Retrieving a cookie
            $scope.login = $cookies.get('login');
            $scope.senha = $cookies.get('senha');
            $scope.lembrarSenha = $cookies.get('lembrarSenha') == 'true';

            $scope.enviarForm = function(){
                if($scope.lembrarSenha) {
                    $cookies.put('login', $scope.login);
                    $cookies.put('senha', $scope.senha);
                    $cookies.put('lembrarSenha', $scope.lembrarSenha);
                }
                else {
                    $cookies.remove('login');
                    $cookies.remove('senha');
                    $cookies.remove('lembrarSenha');
                }
            }

        }]);

    </script>

</div>