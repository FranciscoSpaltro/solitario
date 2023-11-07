package modelo;

import java.util.ArrayList;

public interface IMovimientoAPilaSpiderStrategy {
    void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException;
}
