import java.util.Collections;
import java.util.ArrayList;

public class Klondike extends Solitario {

    // Métodos
    // NOTA: Los cimientos y las pilas están ordenados de 0 a n-1, siendo n la cantidad de cimientos o pilas
    public Klondike(Variante tipo) {
        super(tipo);
        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cimientos.add(new Cimiento(i));
        }
        basura = new Basura();
    }

    public Klondike(Variante tipo, boolean prueba) {
        super(tipo, prueba);
        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cimientos.add(new Cimiento(i));
        }
        basura = new Basura();
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
        Carta primeraCartaOrigen = pilaOrigen.obtenerCarta(comienzoSegmento);

        boolean movimientoPermitido = reglasMoverAPila(primeraCartaOrigen, pilaDestino);

        if(!movimientoPermitido)
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

        if (!reglasMoverACimiento(ultimaCartaPila, cimiento)){
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }

        cimiento.agregarCarta(ultimaCartaPila);
        puntos += 10;
        return true;
    }

    @Override
    protected boolean moverBasuraAPila(PilaDelTableau pila) {
        Carta cartaAAgregar = basura.extraerUltima();

        if(!reglasMoverAPila(cartaAAgregar, pila)){
            basura.agregarCarta(cartaAAgregar);
            return false;
        }

        pila.agregarCarta(cartaAAgregar);
        puntos += 5;
        return true;
    }

    @Override
    protected boolean moverBasuraACimiento(Cimiento cimiento) {
        Carta cartaBasura = basura.extraerUltima();

        if (!reglasMoverACimiento(cartaBasura, cimiento)){
            basura.agregarCarta(cartaBasura);
            return false;
        }

        cimiento.agregarCarta(cartaBasura);
        puntos += 10;
        return true;
    }

    @Override
    protected boolean moverBasuraAMazo() {
        if (! mazo.estaVacia())
            return false;

        int i = basura.cantidadCartas() - 1;
        while (i >= 0) {
            Carta carta = basura.extraerUltima();
            if(carta.estaBocaArriba())
                carta.darVuelta();
            mazo.agregarCarta(carta);
            i--;
        }

        if (puntos > 100)
            puntos -= 100;
        else
            puntos = 0;

        return true;
    }

    protected boolean moverMazoABasura(){
        if (mazo.estaVacia())
            return false;

        Carta cartaAMover = mazo.verUltima();
        mazo.extraerUltima();
        basura.agregarCarta(cartaAMover);
        return true;
    }


    @Override
    protected boolean moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino) {
        Carta ultimaCartaCimiento = cimiento.extraerUltima();

        boolean movimientoPermitido = reglasMoverAPila(ultimaCartaCimiento, pilaDestino);
        if (!movimientoPermitido) {
            cimiento.agregarCarta(ultimaCartaCimiento);
            return false;
        }

        // Llegado a este punto, el movimiento es válido
        pilaDestino.agregarCarta(ultimaCartaCimiento);

        if (puntos > 15)
            puntos -= 15;
        else
            puntos = 0;

        return true;
    }

    // Validaciones
    private boolean reglasMoverACimiento(Carta cartaAMover, Cimiento cimientoDestino){
        if (cimientoDestino.estaVacia() && cartaAMover.verValor() != Valor.AS)
            return false;

        if (cimientoDestino.estaVacia() && cartaAMover.verValor() == Valor.AS)
            return true;

        Carta ultimaCartaCimiento = cimientoDestino.verUltima();

        if (ultimaCartaCimiento.verPalo() != cartaAMover.verPalo())
            return false;

        if (ultimaCartaCimiento.verValor() == Valor.REY)
            return false;

        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        return cartaAMover.verValor() == Valor.values()[valorUltimaCartaCimiento.ordinal() + 1];
    }
    private boolean reglasMoverAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino){
        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() != Valor.REY)
            return false;
        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() == Valor.REY) {

            return true;
        }

        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        // Chequeo de reglas
        if (!primeraCartaAMover.estaBocaArriba())
            return false;

        if (primeraCartaAMover.verColor() == ultimaCartaDestino.verColor())
            return false;
        if (ultimaCartaDestino.verValor() == Valor.AS)
            return false;
        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        return primeraCartaAMover.verValor() == Valor.values()[valorUltimaCartaDestino.ordinal() - 1];
    }
}