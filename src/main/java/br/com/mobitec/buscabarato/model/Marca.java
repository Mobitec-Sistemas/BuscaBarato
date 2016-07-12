/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

/**
 *
 * @author Sensum
 */
@Entity
@Table(name = "marca")
public class Marca implements Serializable {
    
    @Column(name = "codigo", table = "marca", nullable = false)
    @Id
    @FormParam("codigo")
    private Integer codigo;

    @Column(name = "nome", table = "marca", length = 50, nullable = false)
    @Basic
    @FormParam("nome")
    private String nome;

    public Marca() {
    }
    
    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
