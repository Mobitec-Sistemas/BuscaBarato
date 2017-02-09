/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import static br.com.caelum.vraptor.view.Results.http;
import br.com.mobitec.buscabarato.model.Empresa;
import br.com.mobitec.buscabarato.model.Produto;
import br.com.mobitec.buscabarato.model.TabelaPreco;
import br.com.mobitec.buscabarato.model.service.facade.EmpresaFacade;
import br.com.mobitec.buscabarato.model.service.facade.TabelaPrecoFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Classe que gerencia o preço dos produtos
 * @author Sensum
 */
@Controller
public class TabelaPrecoController {
    
    private TabelaPrecoFacade tabelaPrecoFacade;
    private EmpresaFacade empresaFacade;
    private Result result;
    private Validator validator;
    
    /**
     * @deprecated CDI eyes only
     */
    protected TabelaPrecoController() {
    }
    
    @Inject
    public TabelaPrecoController(TabelaPrecoFacade tabelaPrecoFacade,
            EmpresaFacade empresaFacade,
            Result result,
            Validator validator) {
        
        this.tabelaPrecoFacade = tabelaPrecoFacade;
        this.empresaFacade = empresaFacade;
        this.result = result;
        this.validator = validator;
    }
    
    @Get("/preco")
    public void lista() {        
        List<TabelaPreco> lista = tabelaPrecoFacade.findAll();
                        
        result.use(Results.representation()).from(lista, "tabelaPrecoList").serialize();
    }
    
    /**
     * Lista as tabelas de preço de uma determinada empresa
     * @param empresa a ser utilizada na pesquisa 
     */
    @Get("/preco/{empresa.codigo}")
    public void lista(Empresa empresa) {
        /*List<TabelaPreco> lista = tabelaPrecoFacade.listarProdutosEmpresa(empresa);
        
        result.use(Results.representation()).from(lista, "tabelaPrecoList")
                .include("produto")
                .include("produto.imagem")
                //.include("produto.marca")
                .include("empresa")
                .serialize();*/
        this.lista(empresa, null);
    }
    
    /**
     * Lista os produtos de todas as empresas
     * @param produto 
     */
    @Get("/preco/{produto.descricao}")
    public void lista(Produto produto) {
        
        this.lista(null, produto);
    }
    
    /**
     * Lista o preço de um determinado produto e empresa
     * @param empresa a ser pesquisada
     * @param produto a ser pesquisado
     */
    @Get("/preco/{empresa.codigo}/{produto.descricao}")
    public void lista(Empresa empresa, Produto produto) {
        List<TabelaPreco> lista = tabelaPrecoFacade.listarProdutosEmpresa(empresa, produto);
        
        result.use(Results.representation()).from(lista, "tabelaPrecoList")
                .include("produto")
                .include("produto.imagem")
                //.include("produto.marca")
                .include("empresa")
                .serialize();
    } 
    
    /**
     * Tela de consulta de preços dos Mercados
     */
    @Get("/consultaPreco")
    public void consultaPreco()
    {
        List<Empresa> listaEmpresa = empresaFacade.findAll();
        result.include("empresaList", listaEmpresa);
    }
    
    /**
     * Tela de consulta de preços dos Mercados
     * @param latitude atual do usuário
     * @param longitude atual do usuário
     */
    @Get("/consultaPreco/{latitude}/{longitude}")
    public void consultaPreco(BigDecimal latitude, BigDecimal longitude)
    {
        if((latitude != null && longitude != null) || (latitude == BigDecimal.ZERO && longitude == BigDecimal.ZERO) ) {
            List<Empresa> listaEmpresa = empresaFacade.listar(latitude, longitude);
            result.include("empresaList", listaEmpresa);
        }
        else {
            result.redirectTo(this).consultaPreco();
        }
    }
        
    /**
     * Retistra o preço de um produto
     * @param tabelaPreco a ser registrado
     */
    @Post("/registraPreco")
    @Consumes( value = {"application/json", "application/x-www-form-urlencoded"} )
    @Transactional
    public void registrarPreco(TabelaPreco tabelaPreco) {
        
        boolean novo = tabelaPreco.getAlteracao() == null;
        tabelaPreco.setAlteracao(new Date());
        
        validator.validate(tabelaPreco);
                
        if( !validator.hasErrors() ) {
            
            if(novo)
                tabelaPrecoFacade.create(tabelaPreco);
            else
                tabelaPrecoFacade.edit(tabelaPreco);
            
        }
        else {
            String retorno = "";
            
            for(Message mensagem : validator.getErrors()){
                retorno += retorno + mensagem.getMessage() + ". ";
            }
            validator.onErrorUse(http()).sendError(500, retorno); 
        }
        
        result.nothing(); // apenas retorna o código de sucesso (HTTP 200 OK).
        
    }
        
}
