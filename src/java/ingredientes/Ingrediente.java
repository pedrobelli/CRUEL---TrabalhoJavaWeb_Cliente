package ingredientes;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private int    id;
    private String nome;
    private String descricao;
    private int    tipoIngrediente;

    public Ingrediente(int id, String nome, String descricao, int tipoIngrediente) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoIngrediente = tipoIngrediente;
    }

    Ingrediente() {
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipoIngrediente() {
        return tipoIngrediente;
    }

    public void setTipoIngrediente(int tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }

      
    
    
}
