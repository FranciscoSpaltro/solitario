import java.io.Serializable;
import java.util.ArrayList;

public class MovimientoAPilaSpiderDificil implements IMovimientoAPilaSpiderStrategy, Serializable {
    @Override
    public void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta primeraCartaAMover = cartasAMover.get(0);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        for(Carta carta : cartasAMover)
            if (carta.verPalo() != primeraCartaAMover.verPalo())
                throw new InvalidMovementException(ErrorAlMover.CARTAS_A_MOVER_DISTINTO_PALO);

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
