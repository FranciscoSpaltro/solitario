package modelo;

import java.io.Serializable;

public class MovimientoACimientoSpiderDificil implements IMovimientoACimientoSpiderStrategy, Serializable {
    @Override
    public void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException {
        if (pilaOrigen.cantidadCartasVisibles() < Constantes.CANTIDAD_CARTAS_POR_PALO)
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        int tam = pilaOrigen.cantidadCartasVisibles();
        Carta primeraCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - Constantes.CANTIDAD_CARTAS_POR_PALO);
        Carta ultimaCartaPilaOrigen = pilaOrigen.obtenerCarta(pilaOrigen.cantidadCartas() - 1);

        Palo palo = primeraCartaPilaOrigen.verPalo();

        if (!(primeraCartaPilaOrigen.verValor() == Valor.REY && ultimaCartaPilaOrigen.verValor() == Valor.AS && primeraCartaPilaOrigen.estaBocaArriba()))
            throw new InvalidMovementException(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO);

        for (int i = 1; i < Constantes.CANTIDAD_CARTAS_POR_PALO; i++)
            if (pilaOrigen.obtenerCarta(i).verPalo() != palo)
                throw new InvalidMovementException(ErrorAlMover.PILA_DIFERENTE_PALO_NO_PUEDE_IR_A_CIMIENTO);
    }
}
