import java.util.ArrayList;

public class SpiderFacil extends Spider{
    public SpiderFacil(Variante tipo, Palo paloElegido) {
        super(tipo, paloElegido);
    }

    public SpiderFacil(Variante tipo, Palo paloElegido, boolean prueba) {
        super(tipo, paloElegido, prueba);
    }

    @Override
    protected void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        if (pilaOrigen.cantidadCartasVisibles() < 13)
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 13);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 1);

        // La unica condicion es que sea la pila completa del Rey al AS
        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS && primeraCartaPilaOrigen.estaBocaArriba()))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        // Si llega a este punto, el movimiento es válido
    }

    @Override
    protected void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta primeraCartaAMover = cartasAMover.get(0);
        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
    }
}
