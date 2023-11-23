package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class MovimientoAPilaSpiderFacil implements IMovimientoAPilaStrategy, Serializable {
    @Override
    public void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {
        Carta primeraCartaAMover = cartasAMover.get(0);
        Carta ultimaCartaDestino;
        Valor valorUltimaCartaDestino;


        if (!pilaDestino.estaVacia()) {
            ultimaCartaDestino = pilaDestino.obtenerCarta(pilaDestino.cantidadCartas() - 1);
            valorUltimaCartaDestino = ultimaCartaDestino.verValor();
            if (valorUltimaCartaDestino == Valor.AS || primeraCartaAMover.verValor() != Valor.values()[valorUltimaCartaDestino.ordinal() - 1])
                throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
        }

        if (!primeraCartaAMover.estaBocaArriba())
            throw new InvalidMovementException(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA);

        for (int i = 0; i < cartasAMover.size() - 1; i++) {
            if (cartasAMover.get(i).verValor().ordinal() != cartasAMover.get(i + 1).verValor().ordinal() + 1)
                throw new InvalidMovementException(ErrorAlMover.ORDEN_NO_DESCENDENTE);
        }
    }
}
