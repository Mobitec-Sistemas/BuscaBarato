/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#alertaErro').hide();
$('#alertaSucesso').hide();
$('#carregando').hide();

// Carrega os combos de cidade e estado
var app = angular.module('MyApp', ["ngSanitize", "angular.filter"]);

app.controller('MyController', function ($scope, $http) {

    $scope.atualizaGrid = function() {

        $('#carregando').show();
        var cUrl = "preco/";
        if( typeof $scope.cmbEmpresa !== "undefined")
            cUrl += $scope.cmbEmpresa + "/";
        cUrl += $scope.txtProduto;

        //$http.get("${linkTo[TabelaPrecoController].lista}/"+ $scope.cmbEmpresa + "/"+ $scope.txtProduto ).then(
        //$http.get("preco/"+ $scope.txtProduto ).then(
        $http.get( cUrl ).then(
            function(response) {
                $scope.tabelaPrecoList = response.data.tabelaPrecoList;
                $('#carregando').hide();
            }, 
            function(response) {
                $scope.tabelaPrecoList = [];
                $('#carregando').hide();
            }
        );
    }

    $scope.registrarPreco = function(tabelaPreco) {

        if(tabelaPreco.preco > 0) {

            $("#confirmacaoPreco").dialog({
                resizable: false,
                height: "auto",
                modal: true,
                buttons: {
                    "Sim": function () {

                        // Remove a imagem do parâmetro
                        var imagem = tabelaPreco.produto.imagem;
                        tabelaPreco.produto.imagem = "";

                        var parametros = formatarParametro(tabelaPreco, "tabelaPreco");
                        tabelaPreco.produto.imagem = imagem;


                        //var parametros = $.param(tabelaPreco);
                        $http({
                            method: 'POST',
                            url: 'registrarPreco',
                            data: parametros,
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        })
                        .success(function (data, status, headers) {
                            $scope.apresentaMensagem(1, "Preço alterado com sucesso!");
                        })
                        .error(function (data, status, header, config) {
                            var inicio = data.indexOf("<body>") + 6;
                            var fim = data.indexOf("</body>");

                            $scope.apresentaMensagem(2, data.substring(inicio, fim));
                        });

                        $(this).dialog('close');
                    },
                    "Não": function () {
                        $(this).dialog('close');
                    }
                }
            });
        }

    };

    $scope.apresentaMensagem = function(tipo, mensagem) {
        if(tipo === 1) {
            // Sucesso
            $scope.mensagemSucesso = mensagem;
            $('#alertaErro').hide();    
            $("#alertaSucesso").fadeIn(900).delay(10000).fadeOut(900);
        }
        else {
            // Erro
            $scope.mensagemErro = mensagem;
            $('#alertaSucesso').hide();                
            $("#alertaErro").fadeIn(900).delay(10000).fadeOut(900);
        }
    };

});
