package controlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vista.VistaInicio;
import vista.VistaSolitario;
public class ControladorMenu {
    private final VistaSolitario vistaSolitario;

    public ControladorMenu(VistaSolitario vistaSolitario) {
        this.vistaSolitario = vistaSolitario;
    }

    public void iniciar(){
        vistaSolitario.obtenerNuevoJuegoItem().setOnAction(event -> {
            // Lógica para "Nuevo Juego"
            try {
                VistaInicio vistaInicio = new VistaInicio(vistaSolitario.obtenerStage(), vistaSolitario);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        vistaSolitario.obtenerContactanosItem().setOnAction(event -> {
            // Lógica para "Contáctanos"
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText("Leandro Peña [email]\nFrancisco Spaltro [email]");
            alert.setContentText(null);
            alert.showAndWait();
        });

    }
}
