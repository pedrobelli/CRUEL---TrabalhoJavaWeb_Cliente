/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Layla
 */
public class Entrada implements Serializable {
    private int id;
    private Date data;
    private int tipoCliente;
    private double tipoRefeicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public double getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(double tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }
    
    
}
