//
// This file was generated by the JPA Modeler
//
package br.com.mobitec.buscabarato.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

    @Column(name = "codigo", table = "cidade", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @FormParam("codigo")
    private Integer codigo;

    @Column(name = "nome", table = "cidade", unique = false, updatable = true, insertable = true, nullable = false, length = 50, scale = 0, precision = 0)
    @Basic
    @FormParam("nome")
    private String nome;

    @ManyToOne(optional = true, targetEntity = Estado.class)
    @JoinColumn(name = "cod_estado", insertable = true, nullable = true, unique = false, updatable = true)
    private Estado codEstado;

    @OneToMany(targetEntity = Bairro.class, mappedBy = "codCidade")
    private List<Bairro> bairroCollection;

    public Cidade() {

    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getCodEstado() {
        return this.codEstado;
    }

    public void setCodEstado(Estado codEstado) {
        this.codEstado = codEstado;
    }

    public List<Bairro> getBairroCollection() {
        return this.bairroCollection;
    }

    public void setBairroCollection(List<Bairro> bairroCollection) {
        this.bairroCollection = bairroCollection;
    }
}