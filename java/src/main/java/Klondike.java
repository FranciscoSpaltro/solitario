import com.sun.tools.javac.util.List;
import java.util.Stack;

public class Klondike extends Solitario{

    // Métodos
    public Klondike(String variante) {
        super(variante);
    }

    @Override
    void inicializarJuego() {
        //4 Cimientos, 7 pilas con 1, 2, 4 .. 7 cartas donde sólo se ve la ultima;
        this.repartirCartas(super.mazo);

    }

    @Override
    protected boolean jugadorPerdio() {
        // reglas que implican que perdió.

        return false;
    }

    @Override
    protected void repartirCartas(Mazo mazo) {}

    @Override
    protected boolean moverPilaAPila(Stack<Carta> pilaOrigen, Stack<Carta> pilaDestino, int n) {
        return false;
    }


    protected boolean moverPilaAPila(List<Carta> pilaOrigen, List<Carta> pilaDestino, int n){
        return false;
    } // Esta mal, no me retes

    @Override
    //protected boolean moverPilaACimiento(Stack<Carta> pila, Cimiento cimiento) {
    protected boolean moverPilaACimiento(Carta carta, Cimiento cimiento) {
        Carta cimientoUltima = cimiento.verUltima();

        if (cimiento.estaVacia() && cimientoUltima.verValor() == '1'){
            cimiento.agregarCarta(carta);
            return true;
        }

        String paloUltima = cimientoUltima.verPalo();
        char valorUltima = cimientoUltima.verValor();
        if (paloUltima.equals(carta.verPalo()) && valorUltima == carta.verValor()){
            cimiento.agregarCarta(carta);
            return true;
        }
        return false;
    }

    @Override
    protected boolean moverBasuraAPila(Stack<Carta> pila) {
        Carta cartaAgregar = basura.extraerUltima();

        Carta cartaUltima = pila.peek();
        char valorUltima = cartaUltima.verValor();
        String paloUltima = cartaUltima.verPalo();
        return true;
        // deberíamos cambiar la definición de carta me parece, para que tenga un atributo que sea el color.
    }

    @Override
    protected boolean moverBasuraACimiento(Cimiento cimiento) {
        return false;
    }
}
