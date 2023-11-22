package vista;

import javafx.scene.control.Alert;
import modelo.ErrorAlMover;
import modelo.InvalidMovementException;

import java.io.Serializable;

public class VistaAlerta {
    public static void mostrarAlerta(InvalidMovementException e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(obtenerTitulo());
        alert.setHeaderText(null);
        alert.setContentText(ErrorAlMover.obtenerMensaje(e.obtenerMotivo()));
        alert.show();
    }

    private static String obtenerTitulo() {
        return "Movimiento inválido";
    }
}
