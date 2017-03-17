<%-- 
    Document   : formulario
    Created on : 07/11/2016, 17:49:53
    Author     : Sensum
--%>

<h2>Preços dos produtos</h2>

<div ng-controller="MyController">

    <!--
    <div id="alertaErro" class="alert alert-danger" role="alert" style="display:none">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Erro:</span>
        {{mensagemErro}}
    </div>
    <div id="alertaSucesso" class="alert alert-success" role="alert" style="display:none">
        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
        <span class="sr-only">Sucesso:</span>
        {{ mensagemSucesso }}
    </div>
    -->
    <mt:mensagemSucessoErro/>
    
    <div id="confirmacaoPreco" title="Alteração de Preço" style="display:none">
        <p>Confirma a alteração de preço deste produto?</p>
    </div>
        
    <div class="form-group row">
        <label form="produto" class="control-label col-sm-2">Produto:</label>
        <div class="col-sm-10">
            <div class="input-group">
                <input type="search" id="idProduto" name="nomeProduto" ng-model="txtProduto" onkeypress="(window.event.keyCode === 13 ? $('#btnProcurar').click() : '' )" class="form-control" placeholder="Pesquisa de Produto">
                <span class="input-group-btn"><!--Estava faltando esse span-->
                    <button ng-click="atualizaGrid()" class="btn btn-info" value="Procurar" id="btnProcurar">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </div>
    </div>
    
    <div class="col-sm-10" id="carregando" style="display:none">
        <center><img src="<c:url value="/imagem/carregando.gif"/>"/></center>
    </div>    
    
    <span ng-repeat="(key, value) in tabelaPrecoList | groupBy: 'produto.descricao'">
        
        <div style="background-color: #f5f5f5; padding: 10px"><h5 style="font-weight: bold">{{ key }}<h6></div>
        
        <div class="row container-fluid" style="margin: 10px 0px" ng-repeat="tabelaPreco in value">
            <div class="col-sm-8 col-xs-7">{{ tabelaPreco.empresa.nome }}</div>

            <div class="col-sm-4 col-xs-5 input-group">
                <div class="input-group">
                    <span class="input-group-addon">R$</span>
                    <input type="number" step="0.01" min="0" max="999,999.99" class="form-control preco" placeholder="Preço do Produto" ng-model="tabelaPreco.preco">                                

                    <span class="input-group-btn">
                        <button type="button" class="clearfix btn btn-default" aria-label="Alterar Preço" ng-click="registrarPreco(tabelaPreco);">
                            <img src="<c:url value="/imagem/Confirmar.png"/>" alt="Alterar Preço" title="Alterar Preço">
                        </button>
                    </span>
                </div>
            
            </div>
            
            
        </div>
        
    </span>
    
</div>
                    
<script src="<c:url value="/js/dialog_sim_nao.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/precos.js"/>" type="text/javascript"></script>
<!-- <script>

    $('#alertaErro').hide();
    $('#alertaSucesso').hide();
    $('#carregando').hide();

    // Carrega os combos de cidade e estado
    var app = angular.module('MyApp', ["ngSanitize", "angular.filter"]);
    
    
    app.controller('MyController', function ($scope, $http) {
        
        $scope.atualizaGrid = function() {
            
            $('#carregando').show();
            
            $http.get("${linkTo[TabelaPrecoController].lista}/"+ $scope.txtProduto ).then(
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
                                url: '${linkTo[TabelaPrecoController].registrarPreco}',
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

    
</script>-->