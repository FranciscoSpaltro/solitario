import java.util.ArrayList;
import  java.util.List;
import java.util.Stack;
abstract class Solitario {
    //Atributos
    String variante;
    Mazo mazo;
    List<Cimiento> cimientos;
    List<Stack<Carta>> pilas; // ? esto es así o que onda
    Basura basura;

    //Métodos


    public Solitario(String variante) {
        this.variante = variante;
        mazo = new Mazo();
        mazo.mezclar();
        inicializarJuego();
    }

    abstract void inicializarJuego();

    public boolean jugadorGano(){
        for (Cimiento cimiento : cimientos){
            if (!cimiento.estaCompleto())
                return false;
        }
        return true;
    }

    protected abstract boolean jugadorPerdio();

    void reiniciar(){
        inicializarJuego();
    }

    protected abstract void repartirCartas(Mazo mazo);

    protected abstract boolean moverPilaAPila(Stack<Carta> pilaOrigen, Stack<Carta> pilaDestino, int n);

    //protected abstract boolean moverPilaACimiento(Stack<Carta>pila, Cimiento cimiento);
    protected abstract boolean moverPilaACimiento(Carta carta, Cimiento cimiento);

    protected abstract boolean moverBasuraAPila(Stack<Carta> pila);

    protected abstract boolean moverBasuraACimiento(Cimiento cimiento);

}
