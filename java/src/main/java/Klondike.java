import java.util.Collections;
import java.util.ArrayList;

public class Klondike extends Solitario {

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
        if (!primeraCartaOrigen.estaBocaArriba())
            return false;
        if (pilaDestino.estaVacia() && primeraCartaOrigen.verValor() != Valor.REY)
            return false;
        if (primeraCartaOrigen.verColor() == ultimaCartaDestino.verColor())
            return false;
        if (ultimaCartaDestino.verValor() == Valor.AS)
            return false;
        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();
        if (primeraCartaOrigen.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1] )
            return false;

        // Llegado a este punto, el movimiento es válido
        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (!pilaDestino.anexarCartas(cartasAMover)) {
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
        if (cimiento.estaVacia() && ultimaCartaPila.verValor() != Valor.AS) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }
        if (!ultimaCartaCimiento.verPalo().equals(ultimaCartaPila.verPalo())) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }
        if (ultimaCartaCimiento.verValor() == Valor.REY) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }
        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        if (ultimaCartaPila.verValor() != Valor.values()[valorUltimaCartaCimiento.ordinal() + 1]){
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
        if (pila.estaVacia() && cartaAAgregar.verValor() != Valor.REY) {
            basura.agregarCarta(cartaAAgregar);
            return false;
        }
        if (cartaAAgregar.verColor() == ultimaCartaPila.verColor()) {
            basura.agregarCarta(cartaAAgregar);
            return false;
        }
        if (ultimaCartaPila.verValor() == Valor.AS){
            basura.agregarCarta(cartaAAgregar);
            return false;
        }
        Valor valorUltimaCartaPila = ultimaCartaPila.verValor();
        if (cartaAAgregar.verValor() != Valor.values()[valorUltimaCartaPila.ordinal() - 1] ){
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
        if (cimiento.estaVacia() && ultimaCartaCimiento.verValor() != Valor.REY) {
            basura.agregarCarta(cartaBasura);
            return false;
        }
        if (ultimaCartaCimiento.verColor() == cartaBasura.verColor()) {
            basura.agregarCarta(cartaBasura);
            return false;
        }
        if (ultimaCartaCimiento.verValor() == Valor.REY) {
            basura.agregarCarta(cartaBasura);
            return false;
        }
        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        if (cartaBasura.verValor() != Valor.values()[valorUltimaCartaCimiento.ordinal() + 1]){
            basura.agregarCarta(cartaBasura);
            return false;
        }

        cimiento.agregarCarta(cartaBasura);
        return true;
    }

    @Override
    protected boolean moverBasuraAMazo() {
        if (! mazo.estaVacia())
            return false;

        ArrayList<Carta> cartasEnBasura = basura.extraerUltimasN(basura.cantidadCartas());
        Collections.sort(cartasEnBasura, Collections.reverseOrder());
        mazo.anexarCartas(cartasEnBasura);

        return true;
    }
}