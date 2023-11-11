package modelo;

import java.util.ArrayList;

public interface IMovimientoAPilaStrategy {
    void validarMovimientoAPila(ArrayList<Carta> cartasAMover, PilaDelTableau pilaDestino) throws InvalidMovementException;
}
