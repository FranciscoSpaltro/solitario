import java.util.ArrayList;
public class Spider extends Solitario{
    public Spider(Variante tipo, Palo paloElegido) {
        super(tipo);

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }

        var palos = new ArrayList<Palo>();
        palos.add(paloElegido);
        super.mazo = new Mazo(palos, 8);

        super.mazo.mezclar();
    }

    public Spider(Variante tipo, Palo paloElegido, boolean prueba) {
        super(tipo);

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }

        var palos = new ArrayList<Palo>();
        palos.add(paloElegido);
        super.mazo = new Mazo(palos, 8);
    }

    @Override
    void inicializarJuego() {
        this.repartirCartas(super.mazo); // Hay que modificar como se genera el mazo
    }

    protected void reiniciar(Palo paloElegido) {
        puntos = 0;
        // Al dejar sin referencia, la máquina virtual de Java elimina la memoria anterior
        var palos = new ArrayList<Palo>();
        palos.add(paloElegido);
        super.mazo = new Mazo(palos, 8);
        super.mazo.mezclar();

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }

        this.inicializarJuego();

    }

    @Override
    protected void repartirCartas(Mazo mazo) {
        for (int i = 0; i < super.cantidadPilasDelTableau(); i++) {
            PilaDelTableau pila = super.obtenerPilaDelTableau(i);
            for (int j = 0; j < 4; j++) {
                pila.agregarCarta(mazo.extraerUltima());
            }

            if (i < 4){
                pila.agregarCarta(mazo.extraerUltima());
            }
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    protected void sacarCartasMazo() throws InvalidMovementException {
        // Chequeo que todas las pilas tengan al menos una carta
        for (PilaDelTableau pila : pilasTableau) {
            if (pila == null)
                throw new InvalidMovementException(ErrorAlMover.PILAS_VACIA_NO_PUEDE_SACAR_DEL_MAZO);
        }
        // Si todas las pilas tienen, puedo sacar del mazo
        for (PilaDelTableau pila : pilasTableau) {
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    @Override
    protected void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException {
        int comienzoSegmento = pilaOrigen.cantidadCartas() - n;
        Carta primeraCartaOrigen = pilaOrigen.obtenerCarta(comienzoSegmento);

        try {
            this.validarMovimientoAPila(primeraCartaOrigen, pilaDestino);
        } catch (InvalidMovementException e) {
            throw e;
        }
        // Llegado a este punto, el movimiento es válido

        if (pilaOrigen.cantidadCartasVisibles() == n)
            puntos += 5;

        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= 5;
            throw new InvalidMovementException(ErrorAlMover.ERROR_DE_PROGRAMA);
        }
    }

    @Override
    protected void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException {
        try{
            validarMovimientoACimiento(pila, cimiento); // Si pasa algo, lanza la excepción
        } catch (InvalidMovementException e){
            throw e;
        }

        for (int i = 0; i < 13; i++) {
            cimiento.agregarCarta(pila.extraerUltima());
        }

        puntos += 10;
    }

    protected void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(0);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(12);

        // La unica condicion es que sea la pila completa del Rey al AS
        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        // Si llega a este punto, el movimiento es válido
    }

    @Override
    protected void validarMovimientoAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        if (primeraCartaAMover.verColor() != ultimaCartaDestino.verColor())
            throw new InvalidMovementException(ErrorAlMover.PILA_CARTAS_MISMO_COLOR);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
    }

}
