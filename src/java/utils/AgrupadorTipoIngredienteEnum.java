package utils;

public class AgrupadorTipoIngredienteEnum {
    public enum AgrupadorTipoIngrediente {
        CARNE("Carne", 1),
        ARROZ("Arroz", 2),
        FEIJAO("Feijão", 3),
        ACOMPANHAMENTO("Acompanhamento", 4),
        SALADA("Salada", 5),
        SOBREMESA("Sobremesa", 6);
        
        private int cod;
        private String nome;

        AgrupadorTipoIngrediente(String nome, int cod){
            this.nome = nome;
            this.cod = cod;
        }

        public int getCod(){
            return this.cod;
        }
        
        public String getNome(){
            return this.nome;
        }
    }
}
