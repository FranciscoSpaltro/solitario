import java.io.Serializable;

public class MovimientoACimientoSpiderDificil implements IMovimientoACimientoSpiderStrategy, Serializable {
    @Override
    public void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        if (pilaOrigen.cantidadCartasVisibles() < 13)
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        int tam = pilaOrigen.cantidadCartasVisibles();
        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 13);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 1);

        Palo palo = primeraCartaPilaOrigen.verPalo();

        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS && primeraCartaPilaOrigen.estaBocaArriba()))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        for (int i = 1; i < 13; i++)
            if (pilaOrigen.obtenerCarta(i).verPalo() != palo)
                throw new InvalidMovementException(ErrorAlMover.PILA_DIFERENTE_PALO_NO_PUEDE_IR_A_CIMIENTO);
    }
}
