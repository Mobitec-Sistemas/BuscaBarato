//
// This file was generated by the JPA Modeler
//
package br.com.mobitec.buscabarato.model;

import br.com.mobitec.buscabarato.validacao.Email;
import br.com.mobitec.buscabarato.validacao.Unique;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoa", uniqueConstraints = {@UniqueConstraint(columnNames ={"login", "email"})})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

    @Column(name = "codigo", table = "pessoa", nullable = false)
    @Id
    @SequenceGenerator(name="pessoa_codigo_seq", sequenceName="pessoa_codigo_seq", allocationSize = 1)
    @GeneratedValue(generator="pessoa_codigo_seq", strategy=GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "nome", table = "pessoa", nullable = false, length = 50)
    @Basic
    @NotNull(message = "O nome nao pode ficar em branco")
    @Size(min = 5, max = 50, message = "O nome deve ter de 5 a 50 caracteres")
    private String nome;

    @Column(name = "login", table = "pessoa", length = 30, unique = true)
    @Basic
    @Unique(table=Pessoa.class, field="login", message = "Este login já foi utilizado!")
    private String login;

    @Column(name = "senha", table = "pessoa", length = 30)
    @Basic
    private String senha;

    @Column(nullable = false, length = 50, unique = true)
    @Size(max = 50)
    @Unique(table=Pessoa.class, field="email", message = "Este e-mail já foi cadastrado!")
    @Email
    private String email;

    @Column(name = "token", table = "pessoa", length = 50)
    private String token;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    public Pessoa() {

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

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
