import java.io.Serializable;
import java.util.ArrayList;

public class MovimientoACimientoSpiderFacil implements IMovimientoACimientoSpiderStrategy, Serializable {
    public void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        if (pilaOrigen.cantidadCartasVisibles() < 13)
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 13);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 1);

        // La unica condicion es que sea la pila completa del Rey al AS
        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS && primeraCartaPilaOrigen.estaBocaArriba()))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        // Si llega a este punto, el movimiento es válido
    }
}
