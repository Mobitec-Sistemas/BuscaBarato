/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//function showDialog(fnSim, fnNao) {
$(function() {
    /* caixa-confirmacao representa a id onde o caixa de confirmação deve ser criada no html */
    $( "#caixa-confirmacao" ).dialog({
      autoOpen: false,
      resizable: false,
      height:140,

      /* 
       * Modal desativa os demais itens da tela, impossibilitando interação com eles,
       * forçando usuário a responder à pergunta da caixa de confirmação
       */ 
      modal: true,

      /* Os botões que você quer criar */
      buttons: {
        "Sim": function() {
          $( this ).dialog( "close" );
          //alert("Você clicou em Sim!");
          fnSim();
        },
        "Não": function() {
          $( this ).dialog( "close" );
          //alert("Você clicou em Não");
          fnNao();
        }
      }
    });
  })