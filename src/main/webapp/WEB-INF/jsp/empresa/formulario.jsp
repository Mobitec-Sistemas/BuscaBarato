<%-- 
    Document   : formulario
    Created on : 05/10/2016, 13:29:17
    Author     : Sensum
--%>

<h1>Cadastro de Empresas</h1>

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

<form class="form-horizontal" action="<c:url value="/empresa/cadastro"/>" method="POST">

    <input type="hidden" name="empresa.codigo" value="${empresa.codigo}">
    
    <mt:campoTexto type="text" id="nome" label="Empresa:" name="empresa.nome" value="${empresa.nome}" placeholder="Nome da Empresa" />

    <mt:campoTexto type="email" id="email" label="E-mail:" name="empresa.email" value="${empresa.email}" placeholder="E-mail da Empresa" />
    
    <mt:campoTexto type="text" id="cep" label="CEP:" name="empresa.enderecoEmpresaCollection[0].endereco.cep" value="${empresa.enderecoEmpresaCollection[0].endereco.cep}" placeholder="CEP da Empresa" />
    
    <mt:campoTexto type="text" id="logradouro" label="Logradouro:" name="empresa.enderecoEmpresaCollection[0].endereco.logradouro" value="${empresa.enderecoEmpresaCollection[0].endereco.logradouro}" placeholder="Rua, Avenida, Tracessa..." />    
    
    <mt:campoTexto type="number" id="numero" label="Número:" name="empresa.enderecoEmpresaCollection[0].numero" value="${empresa.enderecoEmpresaCollection[0].numero}" placeholder="Número da Empresa" />    
    
    <mt:campoTexto type="text" id="bairro" label="Bairro:" name="empresa.enderecoEmpresaCollection[0].endereco.bairro.nome" value="${empresa.enderecoEmpresaCollection[0].endereco.bairro.nome}" placeholder="Bairro da Empresa" />
    
    <mt:campoTexto type="text" id="cidade" label="Cidade:" name="empresa.enderecoEmpresaCollection[0].endereco.bairro.cidade.nome" value="${empresa.enderecoEmpresaCollection[0].endereco.bairro.cidade.nome}" placeholder="Cidade da Empresa" />
    
    <mt:campoTexto type="text" id="estado" label="Estado:" name="empresa.enderecoEmpresaCollection[0].endereco.bairro.cidade.estado.nome" value="${empresa.enderecoEmpresaCollection[0].endereco.bairro.cidade.estado.nome}" placeholder="Estado da Empresa" />    
    
    <!-- Latitude e Longitude -->
    <mt:campoTexto type="Number" step="any" id="latitude" label="Latitude:" name="empresa.latitude" value="${empresa.latitude}" placeholder="Coordenadas GPS - Latitude" />    
    
    <mt:campoTexto type="Number" step="any" id="longitude" label="Longitude:" name="empresa.longitude" value="${empresa.longitude}" placeholder="Coordenadas GPS - Longitude" />    
    
    <!-- Botão de enviar -->
    <div class="form-group">
        <div class="col-xs-12">
            <button type="submit" class="btn btn-default center-block">Cadastrar</button>
        </div>
    </div>

</form>

<!-- JS das máscaras -->    
<script type="text/JavaScript" src="<c:url value="/js/jquery.mask.min.js"/>"></script>
<script type="text/javascript">

    // Adiciona as máscaras no campo
    $('#cep').mask('00000-000');

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
                //document.getElementById(addressType).value = val;
                document.getElementById(componentForm[addressType][1]).value = val;
            }
        }

        var latitude = place.geometry.location.lat();
        var longitude = place.geometry.location.lng();

        document.getElementById('latitude').value = latitude;
        document.getElementById('longitude').value = longitude;
        document.getElementById('nome').value = place.name;

    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?libraries=places&language=pt-BR&callback=initAutocomplete&key=AIzaSyDPbVO3kBAlAkfzmZVWqGokiPm65loumnc" async defer></script>