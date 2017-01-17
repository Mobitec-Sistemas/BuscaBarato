/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Adiciona as máscaras no campo

// Márcara para valores
 //$('.preco').mask("#.##0,00", {reverse: true});
 //$('.preco').mask("##0,00", {reverse: true});

// CEP
$('#cep').mask('00000-000');

// Latitude e longitude
$('#latitude').mask('D0ZZ.ZZZZZZZZZZ', {translation: {
        'D': {pattern: /[-]/, optional: true},
        'Z': {pattern: /[0-9]/, optional: true}}
});
$('#longitude').mask('D0ZZ.ZZZZZZZZZZ', {translation: {
        'D': {pattern: /[-]/, optional: true},
        'Z': {pattern: /[0-9]/, optional: true}}
});

// Formata os parâmetros para ser envido via http
function formatarParametro(pParametro, pPrefixo) {
    var retorno = "";
    
    if ( pPrefixo === undefined )
        pPrefixo = "";
    else
        pPrefixo += ".";
    
    for (var item in pParametro) {
        
        if( retorno !== "" )
                retorno += "&";
        
        if( typeof pParametro[item] === "object" )
        {
            retorno += formatarParametro(pParametro[item], pPrefixo + item);
        }
        else {
            retorno += pPrefixo + item +"="+ pParametro[item];
        }
    }
    
    return retorno;
}

// Formata os campos da classe preco
$(document).on('focus', '.preco', function(){
    if (!this.value)
        this.value = '0.00';
});
$(document).on('blur', '.preco', function(){
    if (!this.value)
        this.value = '0.00';
});
/*$(document).on('keyup', '.preco', function(){
    var value = this.value.replace(/\./g, ''); // Remove ponto

    // Remove todos os zeros à esquerda
    while (1) {
        if (value[0] === '0')
            value = value.substr(1);
        else
            break;
    }

    // Se o número não tiver tamannho 3 insere zeros à esquerda
    while (1) {
        if (value.length < 3)
            value = '0' + value;
        else
            break;
    }

    var result = value.substr(0, value.length - 2);
    result += '.' + value.substr(value.length - 2);
    this.value = result;
});*/

$(document).on('change', '.preco', function(){
    if(!this.validity.valid) {
        this.value = "0.00";
    }
});
