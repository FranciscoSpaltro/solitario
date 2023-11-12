package controlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vista.VistaInicio;
import vista.VistaPrincipal;
public class ControladorVentana {
    private final VistaPrincipal vistaPrincipal;

    public ControladorVentana(VistaPrincipal vistaPrincipal, DatosMovimiento datosMovimiento) {
        this.vistaPrincipal = vistaPrincipal;
    }

    public void iniciar(){

        vistaPrincipal.obtenerNuevoJuegoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Nuevo Juego"
                try {
                    VistaInicio vistaInicio = new VistaInicio(vistaPrincipal.obtenerStage(), vistaPrincipal);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vistaPrincipal.obtenerContactanosItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Contáctanos"
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("Leandro Peña [email]\nFrancisco Spaltro [email]");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });

    }
}
