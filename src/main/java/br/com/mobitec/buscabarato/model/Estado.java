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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    @Column(name = "codigo", table = "estado", nullable = false)
    @Id
    private Integer codigo;

    @Column(name = "nome", table = "estado", length = 30)
    @Basic
    private String nome;
    
    /*@Column(name = "sigla", table = "estado", length = 2, columnDefinition="bpchar(2)")
    @Basic*/
    @Column(name = "sigla", table = "estado", length = 2, columnDefinition="bpchar(2)")
    private String sigla;

    @OneToMany(targetEntity = Cidade.class, mappedBy = "estado")
    private List<Cidade> cidadeCollection;

    public Estado() {

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

    public List<Cidade> getCidadeCollection() {
        return this.cidadeCollection;
    }

    public void setCidadeCollection(List<Cidade> cidadeCollection) {
        this.cidadeCollection = cidadeCollection;
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
