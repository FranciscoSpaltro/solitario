import controlador.*;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaPrincipal;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControladorArchivos controladorArchivos = new ControladorArchivos();
        Klondike klondike = (Klondike) controladorArchivos.abrirJuegoGuardado();

        VistaPrincipal vistaPrincipal = new VistaPrincipal(stage, klondike);
        vistaPrincipal.iniciar();

        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vistaPrincipal, klondike);
        controladorPrincipal.actualizar();

        vistaPrincipal.mostrar();
    }

    @Override
    public void stop() throws Exception {
        ControladorArchivos.guardarJuego();
    }

    public static void main(String[] args) {
        launch();
    }
}