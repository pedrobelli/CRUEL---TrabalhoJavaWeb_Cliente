package utils;

public class MesEnum {
    public enum Mes {
        JANEIRO("Janeiro", 1),
        FEVEREIRO("Fevereiro", 2),
        MARCO("Março", 3),
        ABRIL("Abril", 4),
        MAIO("Maio", 5),
        JUNHO("Junho", 6),
        JULHO("Julho", 7),
        AGOSTO("Agosto", 8),
        SETEMBRO("Setembro", 9),
        OUTUBRO("Outubro", 10),
        NOVEMBRO("Novembro", 11),
        DEZEMBRO("Dezembro", 12);
        
        private int cod;
        private String nome;

        Mes(String nome, int cod){
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
