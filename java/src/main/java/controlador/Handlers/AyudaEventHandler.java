package controlador.Handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class AyudaEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Seleccione un modo de juego para comenzar a jugar");
        alert.setContentText("Créditos del fondo: Amanda Jones en Unsplash");
        alert.showAndWait();
    }
}
