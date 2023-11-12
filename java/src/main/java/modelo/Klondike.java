package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Klondike extends Solitario {
    private Basura basura;

    // Métodos
    // NOTA: Los cimientos y las pilas están ordenados de 0 a n-1, siendo n la cantidad de cimientos o pilas

    public Klondike(Variante tipo, IMovimientoAPilaStrategy movimientoAPila, boolean prueba) {
        super(tipo);
        super.movimientoAPila = movimientoAPila;
        var palos = new ArrayList<Palo>(Arrays.asList(Palo.values()));
        mazo = new Mazo(palos, 1);

        if (!prueba) { // Creo el mazo, nuevo
            mazo.mezclar();
        }

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++) {
            cimientos.add(new Cimiento(i));
        }
        basura = new Basura();
    }

    @Override
    public void inicializarJuego() {
        // 4 Cimientos, 7 pilas con 1, 2, 4 ... 7 cartas donde solo se ve la última;
        this.repartirCartas(super.mazo);

    }

    public void reiniciar(){
        puntos = 0;
        // Al dejar sin referencia, la máquina virtual de Java elimina la memoria anterior
        var palos = new ArrayList<Palo>(Arrays.asList(Palo.values()));
        mazo = new Mazo(palos, 1);

        mazo.mezclar();
        pilasTableau = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++) {
            cimientos.add(new Cimiento(i));
        }
        basura = new Basura();
        this.inicializarJuego();
    }
    @Override
    public void repartirCartas(Mazo mazo) {
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

    //@Override
    //public void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException {
         /*int comienzoSegmento = pilaOrigen.cantidadCartas() - n;
        Carta primeraCartaOrigen = pilaOrigen.obtenerCarta(comienzoSegmento);
        try {
            validarMovimientoAPila(primeraCartaOrigen, pilaDestino);
        } catch (InvalidMovementException e) {
            throw e;
        }*/
/*
        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);
        try {
            movimientoAPila.validarMovimientoAPila(cartasAMover, pilaDestino);
        }catch (InvalidMovementException e){
            throw e;
        }
        // Llegado a este punto, el movimiento es válido

        if (pilaOrigen.cantidadCartasVisibles() == n)
            puntos += Constantes.PUNTOS_PILA_A_PILA_KLONDIKE;

        //ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= Constantes.PUNTOS_PILA_A_PILA_KLONDIKE;
            throw new InvalidMovementException(ErrorAlMover.ERROR_DE_PROGRAMA);
        }

    }*/

    public void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException {
        Carta ultimaCartaPila = pila.extraerUltima();

        try {
            validarMovimientoACimiento(ultimaCartaPila, cimiento);
        } catch (InvalidMovementException e) {
            if (!pila.estaVacia())  // Si solo quedaba una sola carta, al dar vuelta iba a fallar
                pila.verUltima().darVuelta();
            pila.agregarCarta(ultimaCartaPila);
            throw e;
        }

        cimiento.agregarCarta(ultimaCartaPila);

        puntos += Constantes.PUNTOS_PILA_A_CIMIENTO_KLONDIKE;
    }

    public void moverBasuraAPila(PilaDelTableau pila) throws InvalidMovementException {
        /*Carta cartaAAgregar = basura.extraerUltima();

        try {
            validarMovimientoAPila(cartaAAgregar, pila);
        } catch (InvalidMovementException e) {
            basura.agregarCarta(cartaAAgregar);
            throw e;
        }*/

        ArrayList<Carta> cartasAMover = basura.extraerUltimasN(1);

        try {
            movimientoAPila.validarMovimientoAPila(cartasAMover, pila);
        }catch (InvalidMovementException e){
            basura.agregarCarta(cartasAMover.get(0));
            throw e;
        }

        pila.agregarCarta(cartasAMover.get(0));
        puntos += 5;
    }

    public void moverBasuraACimiento(Cimiento cimiento) throws InvalidMovementException {
        Carta cartaBasura = basura.extraerUltima();
        try {
            validarMovimientoACimiento(cartaBasura, cimiento);
        } catch (InvalidMovementException e) {
            basura.agregarCarta(cartaBasura);
            throw e;
        }
        cimiento.agregarCarta(cartaBasura);
        puntos += Constantes.PUNTOS_PILA_A_CIMIENTO_KLONDIKE;
    }

    public Basura obtenerBasura(){
        return basura;
    }

    public void moverBasuraAMazo() throws InvalidMovementException {
        if (! mazo.estaVacia())
            throw new InvalidMovementException(ErrorAlMover.MAZO_VACIO);

        int i = basura.cantidadCartas() - 1;
        while (i >= 0) {
            Carta carta = basura.extraerUltima();
            if(carta.estaBocaArriba())
                carta.darVuelta();
            mazo.agregarCarta(carta);
            i--;
        }

        if (puntos > Constantes.PUNTOS_BASURA_A_MAZO_KLONDIKE)
            puntos -= Constantes.PUNTOS_BASURA_A_MAZO_KLONDIKE;
        else
            puntos = 0;
    }

    public void moverMazoABasura() throws InvalidMovementException {
        if (mazo.estaVacia())
            throw new InvalidMovementException(ErrorAlMover.MAZO_VACIO);

        Carta cartaAMover = mazo.verUltima();
        mazo.extraerUltima();
        if (!cartaAMover.estaBocaArriba())
            cartaAMover.darVuelta();
        basura.agregarCarta(cartaAMover);
    }


    public void moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino) throws InvalidMovementException {
        /*Carta ultimaCartaCimiento = cimiento.extraerUltima();

        try {
            validarMovimientoAPila(ultimaCartaCimiento, pilaDestino);
        } catch (InvalidMovementException e) {
            cimiento.agregarCarta(ultimaCartaCimiento);
            throw e;
        }*/

        ArrayList<Carta> cartasAMover = cimiento.extraerUltimasN(1);

        try {
            movimientoAPila.validarMovimientoAPila(cartasAMover, pilaDestino);
        }catch (InvalidMovementException e){
            throw e;
        }

        // Llegado a este punto, el movimiento es válido
        Carta ultimaCartaCimiento = cimiento.extraerUltima();
        pilaDestino.agregarCarta(ultimaCartaCimiento);

        if (puntos > Constantes.PUNTOS_CIMIENTO_A_PILA_KLONDIKE)
            puntos -= Constantes.PUNTOS_CIMIENTO_A_PILA_KLONDIKE;
        else
            puntos = 0;

    }

    // Validaciones
    public void validarMovimientoACimiento(Carta cartaAMover, Cimiento cimientoDestino) throws InvalidMovementException {
        if (cimientoDestino.estaVacia() && cartaAMover.verValor() != Valor.AS)
            throw new InvalidMovementException(ErrorAlMover.CIMIENTO_VACIO_NO_AS);

        if (cimientoDestino.estaVacia() && cartaAMover.verValor() == Valor.AS)
            return;

        Carta ultimaCartaCimiento = cimientoDestino.verUltima();

        if (ultimaCartaCimiento.verPalo() != cartaAMover.verPalo())
            throw new InvalidMovementException(ErrorAlMover.CIMIENTO_CARTAS_DISTINTO_PALO);

        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        if (cartaAMover.verValor() != Valor.values()[valorUltimaCartaCimiento.ordinal() + 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_ASCENDENTE);
    }

   /* public void validarMovimientoAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() != Valor.REY)
            throw new InvalidMovementException(ErrorAlMover.PILA_VACIA_NO_REY);

        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() == Valor.REY)
            return;

        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        if(primeraCartaAMover.verPalo().mismoColor(ultimaCartaDestino.verPalo()))
            throw new InvalidMovementException(ErrorAlMover.PILA_CARTAS_MISMO_COLOR);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
    }*/
}