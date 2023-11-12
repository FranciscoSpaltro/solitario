import controlador.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaInicio;
import vista.VistaSolitario;

public class Main extends Application {
    static boolean seJugo = false;
    @Override
    public void start(Stage stage) throws Exception {
        ControladorArchivos controladorArchivos = new ControladorArchivos();
        boolean hayJuegoGuardado = controladorArchivos.hayJuegoGuardado();
        VistaSolitario vistaSolitario = null;
        if (!hayJuegoGuardado) {
            VistaInicio vistaInicio = new VistaInicio(stage, vistaSolitario);
            vistaInicio.mostrar();
        } else {
            seJugo = true;
            Solitario solitario = (Solitario) controladorArchivos.abrirJuegoGuardado();
            vistaSolitario = new VistaSolitario(stage, solitario);
            vistaSolitario.iniciar();

            if(solitario.obtenerVariante() == Variante.KLONDIKE){
                var controladorKlondike = new ControladorKlondike(vistaSolitario, (Klondike) solitario);
                controladorKlondike.actualizar();
                vistaSolitario.iniciar();
            } else {
                var controladorSpider = new ControladorSpider(vistaSolitario, (Spider) solitario);
                controladorSpider.actualizar();
                vistaSolitario.iniciar();
            }
        }
    }


    @Override
    public void stop() throws Exception {
        if(seJugo){
            guardarJuego();
        }
    }

    private void guardarJuego() {
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