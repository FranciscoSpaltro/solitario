package modelo;

public interface IMovimientoACimientoSpiderStrategy {
    void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException;
}
