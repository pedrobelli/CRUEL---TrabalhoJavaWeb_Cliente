package cardapios;

public class CardapioIngrediente  implements java.io.Serializable {


     private Integer id;
     private Integer tipoRefeicao;
     private Integer cardapioId;
     private Integer ingredienteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(Integer tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public Integer getCardapioId() {
        return cardapioId;
    }

    public void setCardapioId(Integer cardapioId) {
        this.cardapioId = cardapioId;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }
     
    

}


