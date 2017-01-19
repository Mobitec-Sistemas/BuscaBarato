<%-- 
    Document   : formulario
    Created on : 07/11/2016, 17:49:53
    Author     : Sensum
--%>

<h2>Pre�os dos produtos</h2>

<div ng-controller="MyController">

    <div id="alertaErro" class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Erro:</span>
        {{mensagemErro}}
    </div>
    <div id="alertaSucesso" class="alert alert-success" role="alert">
        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
        <span class="sr-only">Sucesso:</span>
        {{ mensagemSucesso }}
    </div>
        
    <div class="form-group">
        <label form="mercado" class="control-label col-sm-2">Mercado:</label>
        <div class="col-sm-10">
            <select name="codigoEmpresa" id="idCodigoEmpresa" class="form-control" ng-model="cmbEmpresa" ng-change="atualizaGrid(cmbEmpresa)" placeholder="Selecione um mercado">
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
                <input type="search" id="idProduto" name="nomeProduto" ng-model="txtProduto" class="form-control" placeholder="Pesquisa de Produto">
                <span class="input-group-btn"><!--Estava faltando esse span-->
                    <button ng-click="filtrarGrid(txtProduto)" class="btn btn-info" value="Procurar">
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
                <th>Pre�o</th>
            </tr>
        </thead>
        <tbody>            
            <tr ng-repeat="tabelaPreco in tabelaPrecoList | filter:txtProduto">
                <td style="vertical-align: middle;" class="text-center">
                    <img class="img-thumbnail imagem-grid" alt="Imagem do produto" data-ng-src="data:image/png;base64,{{tabelaPreco.produto.imagem}}" data-err-src="<c:url value="/imagem/produto_sem_foto.png"/>" />
                </td>
                <td>{{ tabelaPreco.produto.descricao }} {{ tabelaPreco.produto.marca.nome }} {{ tabelaPreco.produto.medida }}</td>
                <td>{{ tabelaPreco.alteracao | date: "dd/MM/yyyy HH:mm:ss" }}</td>
                <td style="width: 150px;">
                    <div class="input-group">
                        <span class="input-group-addon">R$</span>
                        <input type="number" step="0.01" min="0" max="999,999.99" class="form-control preco" placeholder="Pre�o do Produto" ng-model="tabelaPreco.preco">
                    </div>
                </td>
                <td class="col_icone">
                    <a href="javascript:void(0)" ng-click="registrarPreco(tabelaPreco);">
                        <img src="<c:url value="/imagem/Confirmar.png"/>" alt="Alterar Pre�o" title="Alterar Pre�o">
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
                    
<script src="<c:url value="/js/dialog_sim_nao.js"/>" type="text/javascript"></script>
<script>

    $('#alertaErro').hide();
    $('#alertaSucesso').hide();

    // Carrega os combos de cidade e estado
    var app = angular.module('MyApp', ["ngSanitize"]);
    
    app.controller('MyController', function ($scope, $http) {

        $scope.atualizaGrid = function(empresa) {
            $http.get("${linkTo[TabelaPrecoController].lista}/"+ empresa).then(
                function(response) {
                    $scope.tabelaPrecoList = response.data.tabelaPrecoList;
                }, 
                function(response) {
                    $scope.tabelaPrecoList = [];
                }
            );
        }
        
        $scope.filtrarGrid = function(produto) {
            alert(produto);
        }
        
        $scope.registrarPreco = function(tabelaPreco) {
            
            if(tabelaPreco.preco > 0) {

                // Remove a imagem do par�metro
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
                    /*$scope.mensagemSucesso = "Pre�o alterado com sucesso!";

                    $('#alertaErro').hide();    
                    $("#alertaSucesso").fadeIn(900).delay(10000).fadeOut(900);*/
                    $scope.apresentaMensagem(1, "Pre�o alterado com sucesso!");
                })
                .error(function (data, status, header, config) {
                    var inicio = data.indexOf("<body>") + 6;
                    var fim = data.indexOf("</body>");
                    /*$scope.mensagemErro = data.substring(inicio, fim);

                    $('#alertaSucesso').hide();                
                    $("#alertaErro").fadeIn(900).delay(10000).fadeOut(900);*/
                    $scope.apresentaMensagem(2, data.substring(inicio, fim));
                });
            }
            else {
                $scope.apresentaMensagem(2, "Pre�o inv�lido!")
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


    
</script>