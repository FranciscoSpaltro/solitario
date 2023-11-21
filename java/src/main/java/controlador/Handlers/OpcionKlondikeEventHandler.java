package controlador.Handlers;

import controlador.ControladorArchivos;
import controlador.ControladorKlondike;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaCarta;
import vista.VistaSolitario;

import java.util.ArrayList;

public class OpcionKlondikeEventHandler implements EventHandler<MouseEvent> {
    private VistaSolitario vistaSolitario;
    private Stage stage;
    private ControladorArchivos controladorArchivos;

    public OpcionKlondikeEventHandler(VistaSolitario vistaSolitario, Stage stage, ControladorArchivos controladorArchivos) {
        this.vistaSolitario = vistaSolitario;
        this.stage = stage;
        this.controladorArchivos = controladorArchivos;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        Klondike klondike = new Klondike(new MovimientoAPilaKlondike(), true);
        controladorArchivos.configurarSolitario(klondike);
        VistaCarta vistaCarta = new VistaCarta(klondike);
        klondike.inicializarJuego();

        vistaSolitario = new VistaSolitario(stage, klondike, controladorArchivos, vistaCarta);
        vistaSolitario.iniciar();

        var controladorKlondike = new ControladorKlondike(vistaSolitario, klondike);
        controladorKlondike.actualizar();

        vistaSolitario.iniciar();
    }
}
