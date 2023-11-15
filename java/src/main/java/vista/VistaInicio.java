package vista;

import controlador.ControladorArchivos;
import controlador.Handlers.AyudaEventHandler;
import controlador.Handlers.JuegoIniciadoException;
import controlador.Handlers.OpcionKlondikeEventHandler;
import controlador.Handlers.OpcionSpiderEventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaInicio {
    Stage stage;
    VistaSolitario vistaSolitario;
    Pane ventana;
    ControladorArchivos controladorArchivos;

    public VistaInicio(Stage stage, VistaSolitario vistaSolitario, ControladorArchivos controladorArchivos) throws JuegoIniciadoException, IOException {
        this.stage = stage;
        this.vistaSolitario = vistaSolitario;
        var loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
        ventana = loader.load();
        this.controladorArchivos = controladorArchivos;
        armarVentana();
    }

    public void armarVentana() throws JuegoIniciadoException {
        ventana.setStyle("-fx-background-image: url('/fondo_inicio.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;");

        var sel_klondike = (ImageView) ventana.lookup("#sel_klondike");
        var sel_spider = (ImageView) ventana.lookup("#sel_spider");
        var sel_info = (ImageView) ventana.lookup("#sel_info");

        sel_klondike.setOnMouseClicked(new OpcionKlondikeEventHandler(vistaSolitario, stage, controladorArchivos));

        sel_spider.setOnMouseClicked(new OpcionSpiderEventHandler(vistaSolitario, stage, controladorArchivos));

        sel_info.setOnMouseClicked(new AyudaEventHandler());
    }

    public void mostrar() {
        var scene = new Scene(ventana, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Nuevo juego");
        stage.setResizable(false);
        //VistaSolitario.centrarVentana(stage);
        stage.show();
    }
}
