//
// This file was generated by the JPA Modeler
//
package br.com.mobitec.buscabarato.model;

import java.io.Serializable;
import java.util.Objects;

public class TabelaPrecoPK implements Serializable {

    private int codEmpresa;

    private int codProduto;

    public TabelaPrecoPK() {

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.codEmpresa;
        hash = 59 * hash + this.codProduto;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TabelaPrecoPK other = (TabelaPrecoPK) obj;
        if (this.codEmpresa != other.codEmpresa) {
            return false;
        }
        if (this.codProduto != other.codProduto) {
            return false;
        }
        return true;
    }

}
