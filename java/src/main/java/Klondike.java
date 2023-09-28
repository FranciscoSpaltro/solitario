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
        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

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

        if (cimiento.estaVacia() && ultimaCartaPila.verValor() != Valor.AS) {
            pila.agregarCarta(ultimaCartaPila);
            return false;
        }

        if (cimiento.estaVacia() && ultimaCartaPila.verValor() == Valor.AS){
            cimiento.agregarCarta(ultimaCartaPila);
            return true;
        }

        Carta ultimaCartaCimiento = cimiento.verUltima();

        // Validación de reglas

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
        puntos += 10;
        return true;
    }

    @Override
    protected boolean moverBasuraAPila(PilaDelTableau pila) {
        Carta cartaAAgregar = basura.extraerUltima();
        Carta ultimaCartaPila = pila.obtenerCarta(pila.cantidadCartas() - 1);

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
        puntos += 5;
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
        puntos += 10;
        return true;
    }

    @Override
    protected boolean moverBasuraAMazo() {
        if (! mazo.estaVacia())
            return false;

        ArrayList<Carta> cartasEnBasura = basura.extraerUltimasN(basura.cantidadCartas());
        Collections.sort(cartasEnBasura, Collections.reverseOrder());
        mazo.anexarCartas(cartasEnBasura);

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
        Carta ultimaCartaPila = pilaDestino.extraerUltima();

        // Validaciones
        if (pilaDestino.estaVacia() && ultimaCartaCimiento.verValor() != Valor.REY){
            cimiento.agregarCarta(ultimaCartaCimiento);
            return false;
        }
        if (!ultimaCartaCimiento.verPalo().equals(ultimaCartaPila.verPalo())) {
            cimiento.agregarCarta(ultimaCartaPila);
            return false;
        }
        if (ultimaCartaCimiento.verValor() == Valor.REY) {
            cimiento.agregarCarta(ultimaCartaPila);
            return false;
        }
        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        if (ultimaCartaPila.verValor() != Valor.values()[valorUltimaCartaCimiento.ordinal() + 1]){
            cimiento.agregarCarta(ultimaCartaPila);
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


}