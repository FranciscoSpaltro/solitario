import java.util.ArrayList;
public class Spider extends Solitario{
    private final IMovimientoAPilaSpiderStrategy movimientoAPila;
    private final IMovimientoACimientoSpiderStrategy movimientoACimiento;

    public Spider(Variante tipo, Palo paloElegido, IMovimientoACimientoSpiderStrategy movimientoACimiento, IMovimientoAPilaSpiderStrategy movimientoAPila) {
        super(tipo);
        this.movimientoACimiento = movimientoACimiento;
        this.movimientoAPila = movimientoAPila;

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

    // Para más dificultad
    public Spider(Variante tipo, ArrayList<Palo> palosElegidos, IMovimientoACimientoSpiderStrategy movimientoACimiento, IMovimientoAPilaSpiderStrategy movimientoAPila) {
        super(tipo);
        this.movimientoACimiento = movimientoACimiento;
        this.movimientoAPila = movimientoAPila;

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }

        super.mazo = new Mazo(palosElegidos, 8);

        super.mazo.mezclar();
    }

    public Spider(Variante tipo, Palo paloElegido, IMovimientoACimientoSpiderStrategy movimientoACimiento, IMovimientoAPilaSpiderStrategy movimientoAPila, boolean prueba) {
        super(tipo, prueba);
        this.movimientoACimiento = movimientoACimiento;
        this.movimientoAPila = movimientoAPila;

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
        // Para test solo uso cuatro mazos por palo
        super.mazo = new Mazo(palos, 8);
    }

    // Para más dificultad
    public Spider(Variante tipo, ArrayList<Palo> palosElegidos, IMovimientoACimientoSpiderStrategy movimientoACimiento, IMovimientoAPilaSpiderStrategy movimientoAPila, boolean prueba) {
        super(tipo, prueba);
        this.movimientoACimiento = movimientoACimiento;
        this.movimientoAPila = movimientoAPila;

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }

        super.mazo = new Mazo(palosElegidos, 4);
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

        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);
        try {
            movimientoAPila.validarMovimientoAPila(cartasAMover, pilaDestino);
        } catch (InvalidMovementException e) {
            pilaOrigen.anexarCartas(cartasAMover);
            throw e;
        }
        // Llegado a este punto, el movimiento es válido

        if (pilaOrigen.cantidadCartasVisibles() == n)
            puntos += 5;

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= 5;
            throw new InvalidMovementException(ErrorAlMover.ERROR_DE_PROGRAMA);
        }
    }

    @Override
    protected void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException {

        movimientoACimiento.validarMovimientoACimiento(pila, cimiento); // Si pasa algo, lanza la excepción

        for (int i = 0; i < 13; i++) {
            cimiento.agregarCarta(pila.extraerUltima());
        }

        puntos += 10;
    }

}
