public class Carta {
    // Ver como definir las constantes PALOS Y VALORES

    // Atributos
    private char valor;
    private String palo;
    private boolean bocaArriba;

    // Métodos
    public Carta(char valor, String palo, boolean bocaArriba){
        this.valor = valor;
        this.palo = palo;
        this.bocaArriba = bocaArriba;
    }

    public String verPalo(){
        return this.palo;
    }

    public char verValor(){
        return this.valor;
    }

    public boolean estaBocaArriba(){
        return bocaArriba;
    }

    public boolean darVuelta(){
        this.bocaArriba = !this.bocaArriba;
        return this.bocaArriba;
    }
}
