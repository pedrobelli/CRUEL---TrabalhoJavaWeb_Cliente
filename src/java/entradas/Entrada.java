/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Layla
 */
public class Entrada implements Serializable {
    private int id;
    private Date data;
    private int tipoCliente;
    private double valorRefeicao;

    public Entrada(int id, Date data, int tipoCliente, double tipoRefeicao) {
        this.id = id;
        this.data = data;
        this.tipoCliente = tipoCliente;
        this.valorRefeicao = tipoRefeicao;
    }

    public Entrada() {
    }
    

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

    public double getValorRefeicao() {
        return valorRefeicao;
    }

    public void setValorRefeicao(double tipoRefeicao) {
        this.valorRefeicao = tipoRefeicao;
    }
    
    public String getDataFormatada(){
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(getData());
    }
    
    
}
