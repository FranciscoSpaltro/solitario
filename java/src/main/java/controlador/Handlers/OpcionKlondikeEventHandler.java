package controlador.Handlers;

import controlador.ControladorKlondike;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaSolitario;

import java.util.ArrayList;

public class OpcionKlondikeEventHandler implements EventHandler<MouseEvent> {
    private VistaSolitario vistaSolitario;
    private Stage stage;

    public OpcionKlondikeEventHandler(VistaSolitario vistaSolitario, Stage stage) {
        this.vistaSolitario = vistaSolitario;
        this.stage = stage;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        Klondike klondike = new Klondike(new MovimientoAPilaKlondike(), true);
        klondike.inicializarJuego();

        vistaSolitario = new VistaSolitario(stage, klondike);
        vistaSolitario.iniciar();

        var controladorKlondike = new ControladorKlondike(vistaSolitario, klondike);
        controladorKlondike.actualizar();

        vistaSolitario.iniciar();
    }
}
