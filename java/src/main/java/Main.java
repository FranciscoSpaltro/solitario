import javafx.application.Application;
import javafx.stage.Stage;
import solitario.Klondike;
import solitario.Variante;
import ui.VistaCarta;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();
        VistaCarta vistaCarta = new VistaCarta(klondike.obtenerMazo());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
