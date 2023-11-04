import java.io.Serializable;
import java.util.ArrayList;

public interface IMovimientoACimientoSpiderStrategy {
    void validarMovimientoACimiento(PilaDelTableau pilaOrigen, Cimiento cimientoDestino) throws InvalidMovementException;
}
