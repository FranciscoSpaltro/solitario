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

    // SOLO PARA TESTING (sin mezclar)
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
        //4 Cimientos, 7 pilas con 1, 2, 4 ... 7 cartas donde solo se ve la última;
        this.repartirCartas(super.mazo);

    }

    @Override
    protected void reiniciar(){
        puntos = 0;
        // Al dejar sin referencia, la máquina virtual de Java elimina la memoria anterior
        mazo = new Mazo();
        mazo.mezclar();
        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cimientos.add(new Cimiento(i));
        }
        basura = new Basura();
        this.inicializarJuego();
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

        ErrorAlMover movimientoPermitido = validarMovimientoAPila(primeraCartaOrigen, pilaDestino);

        if(movimientoPermitido != ErrorAlMover.MOVIMIENTO_PERMITIDO)
            return false;

        // Llegado a este punto, el movimiento es válido

        if (pilaOrigen.cantidadCartasVisibles() == n)
            puntos += 5;

        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= 5;
            return false;
        }

        return true;
    }

    @Override
    protected boolean moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) {
        Carta ultimaCartaPila = pila.extraerUltima();

        ErrorAlMover movimientoPermitido = validarMovimientoACimiento(ultimaCartaPila, cimiento);

        if (movimientoPermitido != ErrorAlMover.MOVIMIENTO_PERMITIDO){
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

        ErrorAlMover movimientoPermitido = validarMovimientoAPila(cartaAAgregar, pila);
        if(movimientoPermitido != ErrorAlMover.MOVIMIENTO_PERMITIDO){
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

        ErrorAlMover movimientoPermitido = validarMovimientoACimiento(cartaBasura, cimiento);
        if (movimientoPermitido != ErrorAlMover.MOVIMIENTO_PERMITIDO){
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
    protected void moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino) throws InvalidMovementException{
        Carta ultimaCartaCimiento = cimiento.extraerUltima();

        try{
            validarMovimientoAPila(ultimaCartaCimiento, pilaDestino);
        } finally {
            cimiento.agregarCarta(ultimaCartaCimiento);
        }

        // Llegado a este punto, el movimiento es válido
        pilaDestino.agregarCarta(ultimaCartaCimiento);

        if (puntos > 15)
            puntos -= 15;
        else
            puntos = 0;
    }

    // Validaciones
    protected void validarMovimientoACimiento(Carta cartaAMover, Cimiento cimientoDestino) throws InvalidMovementException{
        if (cimientoDestino.estaVacia() && cartaAMover.verValor() != Valor.AS)
            throw new InvalidMovementException(ErrorAlMover.CIMIENTO_VACIO_NO_AS);

        Carta ultimaCartaCimiento = cimientoDestino.verUltima();

        if (ultimaCartaCimiento.verPalo() != cartaAMover.verPalo())
            throw new InvalidMovementException(ErrorAlMover.CIMIENTO_CARTAS_DISTINTO_PALO);


        Valor valorUltimaCartaCimiento = ultimaCartaCimiento.verValor();
        if (cartaAMover.verValor() != Valor.values()[valorUltimaCartaCimiento.ordinal() + 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_ASCENDENTE);
    }
    protected void validarMovimientoAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() != Valor.REY)
            throw new InvalidMovementException(ErrorAlMover.PILA_VACIA_NO_REY);

        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        if (primeraCartaAMover.verColor() == ultimaCartaDestino.verColor())
            throw new InvalidMovementException(ErrorAlMover.PILA_CARTAS_MISMO_COLOR);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
    }
}