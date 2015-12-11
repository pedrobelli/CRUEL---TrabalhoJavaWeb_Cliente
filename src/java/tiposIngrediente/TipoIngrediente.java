package tiposIngrediente;

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
