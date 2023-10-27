import java.util.ArrayList;

public class SpiderDificil extends Spider{
    public SpiderDificil(Variante tipo, ArrayList<Palo> palosElegidos) {
        super(tipo, palosElegidos);
    }

    public SpiderDificil(Variante tipo, ArrayList<Palo> palosElegidos, boolean prueba) {
        super(tipo, palosElegidos, prueba);
    }

    @Override
    protected void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        if (pilaOrigen.cantidadCartasVisibles() < 13)
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        int tam = pilaOrigen.cantidadCartasVisibles();
        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - tam);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 1);

        Palo palo = primeraCartaPilaOrigen.verPalo();

        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS && primeraCartaPilaOrigen.estaBocaArriba()))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        for (int i = 1; i < 13; i++)
            if (pilaOrigen.obtenerCarta(i).verPalo() != palo)
                throw new InvalidMovementException(ErrorAlMover.PILA_DIFERENTE_PALO_NO_PUEDE_IR_A_CIMIENTO);
    }

    @Override
    protected void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta primeraCartaAMover = cartasAMover.get(0);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        for(Carta carta : cartasAMover)
            if (carta.verPalo() != primeraCartaAMover.verPalo())
                throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_DISTINTO_PALO);

        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() != Valor.REY)
            throw new InvalidMovementException(ErrorAlMover.PILA_VACIA_NO_REY);

        if (pilaDestino.estaVacia() && primeraCartaAMover.verValor() == Valor.REY)
            return;

        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);

    }
}