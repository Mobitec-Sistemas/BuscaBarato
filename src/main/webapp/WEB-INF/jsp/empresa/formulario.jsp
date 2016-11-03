<%-- 
    Document   : formulario
    Created on : 05/10/2016, 13:29:17
    Author     : Sensum
--%>

<h2>Cadastro de Empresas</h2>

<c:if test="${not empty errors}">
    <div class="alert alert-danger" role="alert">
        <ul class="errors">
            <c:forEach items="${errors}" var="error">
                <li>
                    ${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
                    ${error.message} <!-- a mensagem de erro de validação -->
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form class="form-horizontal" action="<c:url value="/empresa/cadastro"/>" method="POST" ng-controller="MyController">

    <input type="hidden" name="empresa.codigo" value="${empresa.codigo}">

    <mt:campoTexto type="text" id="nome" label="Empresa:" name="empresa.nome" value="${empresa.nome}" placeholder="Nome da Empresa" autofocus="autofocus" />

    <mt:campoTexto type="email" id="email" label="E-mail:" name="empresa.email" value="${empresa.email}" placeholder="E-mail da Empresa" />

    <mt:campoTexto type="text" id="cep" label="CEP:" name="empresa.cep" value="${empresa.cep}" placeholder="CEP da Empresa" />

    <mt:campoTexto type="text" id="logradouro" label="Logradouro:" name="empresa.logradouro" value="${empresa.logradouro}" placeholder="Rua, Avenida, Tracessa..." />    

    <mt:campoTexto type="text" id="numero" label="Número:" name="empresa.numero" value="${empresa.numero}" placeholder="Número da Empresa" />    

    <mt:campoTexto type="text" id="bairro" label="Bairro:" name="empresa.bairro.nome" value="${empresa.bairro.nome}" placeholder="Bairro da Empresa" />

    <div class="form-group">
        <label form="estado" class="control-label col-sm-2">Estado:</label>
        <div class="col-sm-10">
            <select id="estado" 
                    class="form-control"
                    name="empresa.bairro.cidade.estado.codigo"
                    ng-model="ddlEstado" 
                    ng-options="estado.nome for estado in Estados track by estado.codigo" 
                    ng-change="listarCidade(ddlEstado)">
            </select>
        </div>
    </div>

    <div class="form-group">
        <label form="cidade" class="control-label col-sm-2">Cidade:</label>
        <div class="col-sm-10">
            <select id="cidade" 
                    class="form-control" 
                    name="empresa.bairro.cidade.codigo"
                    ng-model="ddlCidade" 
                    ng-options="cidade.nome for cidade in Cidades track by cidade.codigo">
            </select>
        </div>
    </div>

    <!-- Latitude e Longitude -->
    <mt:campoTexto type="text" step="any" id="latitude" label="Latitude:" name="empresa.latitude" value="${empresa.latitude}" placeholder="Coordenadas GPS - Latitude" />    

    <mt:campoTexto type="text" step="any" id="longitude" label="Longitude:" name="empresa.longitude" value="${empresa.longitude}" placeholder="Coordenadas GPS - Longitude" />    

    <!-- Botão de enviar -->
    <div class="form-group">
        <div class="col-xs-12">
            <button type="submit" class="btn btn-default center-block">Cadastrar</button>
        </div>
    </div>

</form>

<script type="text/javascript">

    // Adiciona as máscaras no campo
    $('#cep').mask('00000-000');
    $('#latitude').mask('D0ZZ.ZZZZZZZZZZ', {translation: {
            'D': {pattern: /[-]/, optional: true},
            'Z': {pattern: /[0-9]/, optional: true}}
    });
    $('#longitude').mask('D0ZZ.ZZZZZZZZZZ', {translation: {
            'D': {pattern: /[-]/, optional: true},
            'Z': {pattern: /[0-9]/, optional: true}}
    });

    // Auto complete utilizando o google maps
    var placeSearch, autocomplete;
    var componentForm = {
        route: ['long_name', 'logradouro'],
        street_number: ['short_name', 'numero'],
        locality: ['long_name', 'cidade'],
        administrative_area_level_1: ['short_name', 'estado'],
        /*country: 'long_name',*/
        postal_code: ['short_name', 'cep'],
        sublocality_level_1: ['long_name', 'bairro']
    };

    var componentSelect = {
        administrative_area_level_1: '',
        locality: ''
    };

    function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        var input = document.getElementById('nome');

        // Pega a localização atual do usuário
        var defaultBounds = new google.maps.LatLngBounds(
                new google.maps.LatLng(3.919395, -63.844094),
                new google.maps.LatLng(3.919395, -63.844094));

        navigator.geolocation.getCurrentPosition(function (position) {
            var geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            defaultBounds = circle.getBounds();
        });

        var options = {
            bounds: defaultBounds,
            types: ['geocode', 'establishment']
        };

        // Cria o objeto de autocomplemento
        autocomplete = new google.maps.places.Autocomplete(input, options);


        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
    }

    // [START region_fillform]
    function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
            document.getElementById(componentForm[component][1]).value = '';
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            if (componentForm[addressType]) {
                var val = place.address_components[i][componentForm[addressType][0]];
                document.getElementById(componentForm[addressType][1]).value = val;
            }

            if (typeof componentSelect[addressType] === "string")
                componentSelect[addressType] = val;
        }

        // Seta a latitude e longitude
        var latitude = place.geometry.location.lat();
        var longitude = place.geometry.location.lng();
        document.getElementById('latitude').value = latitude;
        document.getElementById('longitude').value = longitude;
        document.getElementById('nome').value = place.name;

        // Seta os combos de cidade e estado
        $('#estado').scope().listarCidadeEstado(componentSelect.administrative_area_level_1, componentSelect.locality);

        //$('#estado').val(componentSelect.administrative_area_level_1).trigger('input');
        //$('#estado').scope().listarCidade()

    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?libraries=places&language=pt-BR&callback=initAutocomplete&key=AIzaSyDPbVO3kBAlAkfzmZVWqGokiPm65loumnc" async defer></script>

<script type="text/JavaScript">
    // Carrega os combos de cidade e estado
    var app = angular.module('MyApp', []);
    app.controller('MyController', function ($scope, $http) {

    $http.get("${linkTo[EstadoController].lista}").then(
    function(response){
    $scope.Estados = response.data.estados;
    }, 
    function(response){
    $scope.Estados = [];
    }
    );

    $scope.listarCidade = function(uf) {
    $http.get("${linkTo[CidadeController].listaCidadesEstado}"+ uf.codigo).then(
    function(response){
    $scope.Cidades = response.data.cidades;
    }, 
    function(response){
    $scope.Cidades = [];
    }
    );
    }

    $scope.listarCidadeEstado = function(uf, cidade) {
    $http({
    method: 'GET',
    url: '<c:url value="/cidade"/>',
    params: {'cidade.nome': cidade, 'cidade.estado.sigla': uf}
    }).then(function successCallback(response) {

    if(response.data.cidades.length > 0)
    {
    $scope.ddlEstado = response.data.cidades[0].estado;

    // Carrega a lista de cidades do estado
    $scope.listarCidade(response.data.cidades[0].estado);

    $scope.ddlCidade = response.data.cidades[0];
    }

    }, function errorCallback(response) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
    });   
    }

    });

</script>
