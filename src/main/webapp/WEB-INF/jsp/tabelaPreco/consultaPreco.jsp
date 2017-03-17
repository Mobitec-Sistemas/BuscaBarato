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
        
    <div class="form-group">
        <label form="mercado" class="control-label col-sm-2">Mercado:</label>
        <div class="col-sm-10">
            <select name="codigoEmpresa" id="idCodigoEmpresa" class="form-control" ng-model="cmbEmpresa" placeholder="Selecione um mercado">
                <c:forEach items="${empresaList}" var="empresa">
                    <option value='${empresa.codigo}'>${empresa.nome} - ${empresa.bairro.cidade.nome}/${empresa.bairro.cidade.estado.nome}</option>
                </c:forEach>
            </select>

        </div>
    </div>

    <div class="form-group">
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
        
    <table class="table table-striped">
        <thead>
            <tr>
                <th></th>
                <th>Produto</th>
                <th>Alterado em</th>
                <th>Preço</th>
            </tr>
        </thead>
        <tbody>            
            <tr ng-repeat="tabelaPreco in tabelaPrecoList"> <!-- | filter:txtProduto"> -->
                <td style="vertical-align: middle;" class="text-center">
                    <img class="img-thumbnail imagem-grid" alt="Imagem do produto" data-ng-src="data:image/png;base64,{{tabelaPreco.produto.imagem}}" data-err-src="<c:url value="/imagem/produto_sem_foto.png"/>" />
                </td>
                <td>{{ tabelaPreco.produto.descricao }} {{ tabelaPreco.produto.medida }}</td>
                <td>{{ tabelaPreco.alteracao | date: "dd/MM/yyyy HH:mm:ss" }}</td>
                <td style="min-width: 150px; width: 150px;">
                    <div class="input-group">
                        <span class="input-group-addon">R$</span>
                        <input type="number" step="0.01" min="0" max="999,999.99" class="form-control preco" placeholder="Preço do Produto" ng-model="tabelaPreco.preco">
                    </div>
                </td>
                <td class="col_icone">
                    <a href="javascript:void(0)" ng-click="registrarPreco(tabelaPreco);">
                        <img src="<c:url value="/imagem/Confirmar.png"/>" alt="Alterar Preço" title="Alterar Preço">
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
    
    <div class="col-sm-10" id="carregando" style="display:none">
        <center><img src="<c:url value="/imagem/carregando.gif"/>"/></center>
    </div>
                    
                    
</div>
                    
<script src="<c:url value="/js/dialog_sim_nao.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/precos.js"/>" type="text/javascript"></script>
<script>

    /*$('#alertaErro').hide();
    $('#alertaSucesso').hide();
    $('#carregando').hide();

    // Carrega os combos de cidade e estado
    var app = angular.module('MyApp', ["ngSanitize"]);
    
    app.controller('MyController', function ($scope, $http) {
        
        $scope.atualizaGrid = function() {
            
            $('#carregando').show();
            
            $http.get("${linkTo[TabelaPrecoController].lista}/"+ $scope.cmbEmpresa + "/"+ $scope.txtProduto ).then(
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
        
    });*/


    
</script>