/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiposIngrediente;

import java.util.HashSet;
import java.util.Set;

public class TipoIngrediente  implements java.io.Serializable {


     private Integer id;
     private String nome;

    public TipoIngrediente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    TipoIngrediente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
    




}
