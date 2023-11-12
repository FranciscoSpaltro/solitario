import controlador.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaInicio;
import vista.VistaPrincipal;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControladorArchivos controladorArchivos = new ControladorArchivos();
        boolean hayJuegoGuardado = controladorArchivos.hayJuegoGuardado();
        VistaPrincipal vistaPrincipal = null;
        if (!hayJuegoGuardado) {
            VistaInicio vistaInicio = new VistaInicio(stage, vistaPrincipal);
        } else {
            Klondike klondike = (Klondike) controladorArchivos.abrirJuegoGuardado();
            vistaPrincipal = new VistaPrincipal(stage, klondike);
            vistaPrincipal.iniciar();

            ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vistaPrincipal, klondike);
            controladorPrincipal.actualizar();

            vistaPrincipal.mostrar();
        }
    }


    @Override
    public void stop() throws Exception {
        // Crear un botón de alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Solitario");
        alert.setHeaderText("¿Desea guardar la partida?");
        alert.setContentText("Selecciona una opción.");

        // Personalizar los botones de la alerta
        ButtonType buttonTypeSi = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");

        // Establecer los botones en la alerta
        alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

        // Mostrar la alerta y esperar a que se seleccione un botón
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeSi) {
                ControladorArchivos.guardarJuego();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}