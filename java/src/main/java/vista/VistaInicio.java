package vista;

import controlador.ControladorKlondike;
import controlador.ControladorSolitario;
import controlador.ControladorSpider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;

public class VistaInicio {
    Stage stage;
    VistaPrincipal vistaPrincipal;

    public VistaInicio(Stage stage, VistaPrincipal vistaPrincipal) throws Exception {
        this.stage = stage;
        this.vistaPrincipal = vistaPrincipal;
        armarVentana();
    }

    public void armarVentana() throws Exception {
        var loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
        Pane ventana = loader.load();

        ventana.setStyle("-fx-background-image: url('/fondo_inicio.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-position: center;");

        var sel_klondike = (ImageView) ventana.lookup("#sel_klondike");
        var sel_spider = (ImageView) ventana.lookup("#sel_spider");
        var sel_info = (ImageView) ventana.lookup("#sel_info");

        sel_klondike.setOnMouseClicked(event -> {
            Klondike klondike = new Klondike(new MovimientoAPilaKlondike(), true);
            klondike.inicializarJuego();
            vistaPrincipal = new VistaPrincipal(stage, klondike);
            vistaPrincipal.iniciar();

            var controladorKlondike = new ControladorKlondike(vistaPrincipal, klondike);
            controladorKlondike.actualizar();

            vistaPrincipal.mostrar();
        });

        sel_spider.setOnMouseClicked(event -> {
            ArrayList<Palo> palos = new ArrayList<Palo>();
            palos.add(Palo.CORAZONES);
            var spider = new Spider(palos, new MovimientoACimientoSpiderFacil(), new MovimientoAPilaSpiderFacil(), false);
            spider.inicializarJuego();
            vistaPrincipal = new VistaPrincipal(stage, spider);
            vistaPrincipal.iniciar();

            var controladorSpider = new ControladorSpider(vistaPrincipal, spider);
            controladorSpider.actualizar();

            vistaPrincipal.mostrar();
        });

        sel_info.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ayuda");
            alert.setHeaderText("Seleccione un modo de juego para comenzar a jugar");
            alert.setContentText("Créditos del fondo: Amanda Jones en Unsplash");
            alert.showAndWait();
        });

        var scene = new Scene(ventana, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Nuevo juego");

        stage.show();
    }
}
