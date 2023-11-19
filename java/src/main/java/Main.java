import controlador.*;
import javafx.application.Application;
import javafx.fxml.LoadException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaCarta;
import vista.VistaInicio;
import vista.VistaSolitario;

import java.io.IOException;

public class Main extends Application {
    private ControladorArchivos controladorArchivos = new ControladorArchivos();

    @Override
    public void start(Stage stage) throws Exception {
        boolean hayJuegoGuardado = controladorArchivos.hayJuegoGuardado();
        VistaSolitario vistaSolitario = null;
        if (!hayJuegoGuardado) {
            VistaInicio vistaInicio;
            // Este try/catch lo sacaría porque simplemente burbujea y lo agarra el start y no haces ningun manejo sobre la excepción.
            try {
                 vistaInicio = new VistaInicio(stage, vistaSolitario, controladorArchivos);
            } catch (LoadException e) { // Excepción que lanza si no encuentra el archivo
                throw e;
            }
            vistaInicio.mostrar();
        } else {
            Solitario solitario = (Solitario) controladorArchivos.abrirJuegoGuardado();
            VistaCarta vistaCarta = controladorArchivos.crearVistaCarta(solitario);
            vistaSolitario = new VistaSolitario(stage, solitario, controladorArchivos, vistaCarta);
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
        if(controladorArchivos.hayJuegoIniciado()){
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
                controladorArchivos.guardarJuego();
            } else {
                controladorArchivos.borrarJuegoGuardado();
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}