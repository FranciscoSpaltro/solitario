package solitario;

import java.io.Serializable;

public class Carta implements Serializable {
    // Atributos
    private final Valor valor;
    private final Palo palo;
    private boolean bocaArriba;

    // Métodos
    public Carta(Valor valor, Palo palo, boolean bocaArriba){
        this.valor = valor;
        this.palo = palo;
        this.bocaArriba = bocaArriba;
    }

    public Palo verPalo(){
        return this.palo;
    }

    public Valor verValor(){
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
