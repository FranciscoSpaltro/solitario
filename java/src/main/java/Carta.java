public class Carta {
    // Ver como definir las constantes PALOS Y VALORES

    // Atributos
    private String valor;
    private String palo;
    private boolean bocaArriba;

    // Métodos
    public Carta(String valor, String palo, boolean bocaArriba){
        this.valor = valor;
        this.palo = palo;
        this.bocaArriba = bocaArriba;
    }

    public String verPalo(){
        return this.palo;
    }

    public String verValor(){
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
