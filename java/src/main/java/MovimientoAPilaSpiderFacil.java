import java.io.Serializable;
import java.util.ArrayList;

public class MovimientoAPilaSpiderFacil implements IMovimientoAPilaSpiderStrategy, Serializable {
    @Override
    public void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta primeraCartaAMover = cartasAMover.get(0);
        Carta ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        Valor valorUltimaCartaDestino = ultimaCartaDestino.verValor();

        if (primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
            throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
    }
}
