//
// This file was generated by the JPA Modeler
//
package br.com.mobitec.buscabarato.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
@PrimaryKeyJoinColumn(name="cod_pessoa")
public class Empresa extends Pessoa implements Serializable {

    @Column(name = "latitude", table = "empresa", precision=13, scale=10)
    @NotNull(message = "A latitude não pode ficar em branco")
    private BigDecimal latitude;

    @NotNull(message = "A longitude não pode ficar em branco")
    @Column(name = "longitude", table = "empresa", precision=13, scale=10)
    private BigDecimal longitude;

    @Column(name = "logradouro", table = "empresa", length = 100)
    @NotNull(message = "O logradouro não pode ficar em branco")
    private String logradouro;
    
    @Column(name = "numero", table = "empresa", length = 10)
    private String numero;

    @Column(name = "cep", table = "empresa", length = 8)
    private String cep;

    @ManyToOne(targetEntity = Bairro.class)
    @JoinColumn(name = "cod_bairro")
    //@NotNull(message = "O bairro não pode ficar em branco")
    private Bairro bairro;

    @OneToMany(targetEntity = TabelaPreco.class, mappedBy = "empresa")
    private List<TabelaPreco> tabelaPrecoCollection;

    public Empresa() {

    }

    public BigDecimal  getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    public List<TabelaPreco> getTabelaPrecoCollection() {
        return this.tabelaPrecoCollection;
    }

    public void setTabelaPrecoCollection(List<TabelaPreco> tabelaPrecoCollection) {
        this.tabelaPrecoCollection = tabelaPrecoCollection;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the bairro
     */
    public Bairro getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}
