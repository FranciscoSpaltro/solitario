import controlador.*;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Klondike;
import modelo.Variante;
import vista.VistaMazo;
import vista.VistaPrincipal;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Klondike klondike = new Klondike(Variante.KLONDIKE, false);
        klondike.inicializarJuego();

        VistaPrincipal vistaPrincipal = new VistaPrincipal(stage, klondike);
        vistaPrincipal.iniciar();

        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vistaPrincipal, klondike);
        controladorPrincipal.actualizar();

        vistaPrincipal.mostrar();
    }

    public static void main(String[] args) {
        launch();
    }
}