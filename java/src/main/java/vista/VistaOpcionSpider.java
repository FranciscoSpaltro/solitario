package vista;

import controlador.Handlers.ElegirSpiderDificilEventHandler;
import controlador.Handlers.ElegirSpiderFacilEventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.*;

public class VistaOpcionSpider {
    Stage stage;
    VistaSolitario vistaSolitario;
    Pane ventana;

    public VistaOpcionSpider(Stage stage, VistaSolitario vistaSolitario) throws Exception {
        this.stage = stage;
        this.vistaSolitario = vistaSolitario;
        var loader = new FXMLLoader(getClass().getResource("/ventana_spider.fxml"));
        ventana = loader.load();
        armarVentana();
    }

    public void armarVentana() throws Exception {
        ventana.setStyle("-fx-background-image: url('/fondo_spider.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;");

        var btn_corazones = (ImageView) ventana.lookup("#btn_corazones");
        var btn_diamantes = (ImageView) ventana.lookup("#btn_diamantes");
        var btn_treboles = (ImageView) ventana.lookup("#btn_treboles");
        var btn_picas = (ImageView) ventana.lookup("#btn_picas");
        var btn_dificil = (ImageView) ventana.lookup("#btn_dificil");

        btn_corazones.setOnMouseClicked(new ElegirSpiderFacilEventHandler(Palo.CORAZONES, vistaSolitario, stage));

        btn_picas.setOnMouseClicked(new ElegirSpiderFacilEventHandler(Palo.PICAS, vistaSolitario, stage));

        btn_treboles.setOnMouseClicked(new ElegirSpiderFacilEventHandler(Palo.TREBOLES, vistaSolitario, stage));

        btn_diamantes.setOnMouseClicked(new ElegirSpiderFacilEventHandler(Palo.DIAMANTES, vistaSolitario, stage));

        btn_dificil.setOnMouseClicked(new ElegirSpiderDificilEventHandler(vistaSolitario, stage));

    }

    public void mostrar() {
        var scene = new Scene(ventana, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Spider");
        VistaSolitario.centrarVentana(stage);
        stage.show();
    }
}
