/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * Busca o CEP do CEP aberto
 */
function buscarCEP(pCEP) {

    $http.defaults.headers.common.Authorization = 'Token token=99af931de527e2e67fa1a63d35d641b9';

    $http({
        method: 'GET',
        url: 'http://www.cepaberto.com/api/v2/ceps.json?cep=88311300'
    }).then(function successCallback(response) {
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
}
