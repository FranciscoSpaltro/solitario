package vista;

import controlador.ControladorPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Klondike;
import modelo.MovimientoAPilaKlondike;

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
            vistaPrincipal = new VistaPrincipal(stage, klondike);
            vistaPrincipal.iniciar();

            ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vistaPrincipal, klondike);
            controladorPrincipal.actualizar();

            vistaPrincipal.mostrar();
        });

        sel_spider.setOnMouseClicked(event -> {
            System.out.println("Spider");
        });

        sel_info.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ayuda");
            alert.setHeaderText("Seleccione un modo de juego para comenzar a jugar");
            alert.setContentText("Créditos de la foto: Amanda Jones en Unsplash");
            alert.showAndWait();
        });

        var scene = new Scene(ventana, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Nuevo juego");

        stage.show();
    }
}
