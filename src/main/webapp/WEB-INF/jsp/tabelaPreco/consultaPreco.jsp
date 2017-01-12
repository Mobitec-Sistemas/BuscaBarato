<%-- 
    Document   : formulario
    Created on : 07/11/2016, 17:49:53
    Author     : Sensum
--%>

<h2>Preços dos produtos</h2>

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
    
    <div id="caixa-confirmacao" title="Alterar Preço">
        <p>Deseja alterar o preço deste produto?</p>
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
            <tr ng-repeat="tabelaPreco in tabelaPrecoList">
                <td style="vertical-align: middle;" class="text-center">
                    <img class="img-thumbnail imagem-grid" alt="Imagem do produto" data-ng-src="data:image/png;base64,{{tabelaPreco.produto.imagem}}" data-err-src="<c:url value="/imagem/produto_sem_foto.png"/>" />
                </td>
                <td>{{ tabelaPreco.produto.descricao }} {{ tabelaPreco.produto.marca.nome }} {{ tabelaPreco.produto.medida }}</td>
                <td>{{ tabelaPreco.alteracao | date: "dd/MM/yyyy HH:mm:ss" }}</td>
                <td style="width: 150px;">
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
        
        $scope.registrarPreco = function(tabelaPreco) {
            
            // Abre a confirmação da alteração
            //$( "#caixa-confirmacao" ).dialog( "open" );
            
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
                $scope.mensagemSucesso = "Preço alterado com sucesso!";
        
                $('#alertaErro').hide();    
                $("#alertaSucesso").fadeIn(900).delay(10000).fadeOut(900);
            })
            .error(function (data, status, header, config) {
                var inicio = data.indexOf("<body>") + 6;
                var fim = data.indexOf("</body>");
                $scope.mensagemErro = data.substring(inicio, fim);
                
                $('#alertaSucesso').hide();                
                $("#alertaErro").fadeIn(900).delay(10000).fadeOut(900);
            });
        };
        
    });


    
</script>