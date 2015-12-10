/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiposIngrediente;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Layla
 */
public class TipoIngrediente {
    private int id;
    private String nome;
    private Set ingredienteses = new HashSet(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set getIngredienteses() {
        return ingredienteses;
    }

    public void setIngredienteses(Set ingredienteses) {
        this.ingredienteses = ingredienteses;
    }

    
    
}
