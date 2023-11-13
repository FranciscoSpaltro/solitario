package controlador.Handlers;

import controlador.ControladorSpider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaSolitario;

import java.util.ArrayList;

public class ElegirSpiderDificilEventHandler implements EventHandler<MouseEvent> {
    private Stage stage;
    private VistaSolitario vistaSolitario;

    public ElegirSpiderDificilEventHandler(VistaSolitario vistaSolitario, Stage stage) {
        this.stage = stage;
        this.vistaSolitario = vistaSolitario;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        ArrayList<Palo> palos = new ArrayList<Palo>();
        palos.add(Palo.CORAZONES);
        palos.add(Palo.TREBOLES);
        var spider = new Spider(palos, new MovimientoACimientoSpiderDificil(), new MovimientoAPilaSpiderDificil(), false);
        spider.inicializarJuego();

        vistaSolitario = new VistaSolitario(stage, spider);
        vistaSolitario.iniciar();

        var controladorSpider = new ControladorSpider(vistaSolitario, spider);
        controladorSpider.actualizar();

        vistaSolitario.iniciar();
    }
}
