//
// This file was generated by the JPA Modeler
//
package br.com.mobitec.buscabarato.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

@Entity
@Table(name = "tabela_preco")
public class TabelaPreco implements Serializable {

    @Column(name = "cod_empresa", table = "tabela_preco", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @FormParam("codEmpresa")
    private int codEmpresa;

    @Column(name = "cod_produto", table = "tabela_preco", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @FormParam("codProduto")
    private int codProduto;

    @Column(name = "preco", table = "tabela_preco", unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 2, precision = 10)
    @Basic
    @FormParam("preco")
    private BigDecimal preco;

    @ManyToOne(optional = false, targetEntity = Empresa.class)
    @JoinColumn(name = "cod_empresa", insertable = false, nullable = true, unique = false, updatable = false)
    private Empresa empresa;

    @ManyToOne(optional = false, targetEntity = Produto.class)
    @JoinColumn(name = "cod_produto", insertable = false, nullable = true, unique = false, updatable = false)
    private Produto produto;

    public TabelaPreco() {

    }

    public int getCodEmpresa() {
        return this.codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}