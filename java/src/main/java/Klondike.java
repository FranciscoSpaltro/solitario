import com.sun.tools.javac.util.List;

import java.util.ArrayList;
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
    protected void repartirCartas(Mazo mazo) {
        int cartasOcultas = 0;
        for (PilaDelTableau pila : pilasTableau) {
            for (int i = 0; i < cartasOcultas; i++) {
                pila.agregarCarta(mazo.extraerUltima());
            }
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
            cartasOcultas++;
        }
    }

    @Override
    protected boolean moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) {
        int comienzoSegmento = pilaOrigen.cantidadCartas() - n;
        Carta primeraCartaOrigen = pilaOrigen.mostrarCarta(comienzoSegmento);
        Carta ultimaCartaDestino = pilaDestino.mostrarCarta(pilaDestino.cantidadCartas() - 1);

        // Chequeo de reglas
        if (! primeraCartaOrigen.estaBocaArriba())
            return false;
        if (pilaDestino.estaVacia() && primeraCartaOrigen.verValor() != 13)
            return false;
        if (primeraCartaOrigen.verColor() == ultimaCartaDestino.verColor())
            return false;
        if (primeraCartaOrigen.verValor() != ultimaCartaDestino.verValor() - 1)
            return false;

        // Llegado a este punto, el movimiento es válido
        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (! pilaDestino.anexarCartas(cartasAMover)){
            pilaOrigen.anexarCartas(cartasAMover);
            return false;
        }

        return true;
    }

    @Override
    protected boolean moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) {
        Carta ultimaCartaPila = pila.extraerUltima();
        Carta ultimaCartaCimiento = cimiento.verUltima();

        // Validación de reglas
        if (cimiento.estaVacia() && ultimaCartaPila.verValor() != 1) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }
        if (! ultimaCartaCimiento.verPalo().equals(ultimaCartaPila.verPalo())) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }
        if (ultimaCartaPila.verValor() != ultimaCartaCimiento.verValor() + 1) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }

        cimiento.agregarCarta(ultimaCartaPila);
        return false;
    }

    @Override
    protected boolean moverBasuraAPila(PilaDelTableau pila) {
        Carta cartaAAgregar = basura.extraerUltima();
        Carta ultimaCartaPila = pila.mostrarCarta(pila.cantidadCartas() - 1);

        // Chequeo de reglas
        if (pila.estaVacia() && cartaAAgregar.verValor() != 13) {
            basura.agregarCarta(cartaAAgregar);
            return false;
        }
        if (cartaAAgregar.verColor() == ultimaCartaPila.verColor()) {
            basura.agregarCarta(cartaAAgregar);
            return false;
        }
        if (cartaAAgregar.verValor() != ultimaCartaPila.verValor() - 1){
            basura.agregarCarta(cartaAAgregar);
            return false;
        }

        pila.agregarCarta(cartaAAgregar);
        return true;
    }

    @Override
    protected boolean moverBasuraACimiento(Cimiento cimiento) {
        Carta ultimaCartaCimiento = cimiento.extraerUltima();
        Carta cartaBasura = basura.extraerUltima();

        // Chequeo de reglas
        if (cimiento.estaVacia() && ultimaCartaCimiento.verValor() != 1){
            basura.agregarCarta(cartaBasura);
            return false;
        }
        if (ultimaCartaCimiento.verColor() == cartaBasura.verColor()){
            basura.agregarCarta(cartaBasura);
            return false;
        }
        if (cartaBasura.verValor() != ultimaCartaCimiento.verValor() + 1){
            basura.agregarCarta(cartaBasura);
            return false;
        }

        cimiento.agregarCarta(cartaBasura);
        return true;
    }
}
