package controlador.Handlers;

import controlador.ControladorArchivos;
import controlador.ControladorSpider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.*;
import vista.VistaCarta;
import vista.VistaSolitario;

public class ElegirSpiderDificilEventHandler implements EventHandler<MouseEvent> {
    private Stage stage;
    private VistaSolitario vistaSolitario;
    private ControladorArchivos controladorArchivos;

    public ElegirSpiderDificilEventHandler(VistaSolitario vistaSolitario, Stage stage, ControladorArchivos controladorArchivos) {
        this.stage = stage;
        this.vistaSolitario = vistaSolitario;
        this.controladorArchivos = controladorArchivos;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        var spider = new Spider(Palo.obtenerDosAlAzar(), new MovimientoACimientoSpiderDificil(), new MovimientoAPilaSpiderDificil(), false);
        controladorArchivos.configurarSolitario(spider);
        VistaCarta vistaCarta = new VistaCarta(spider);
        spider.inicializarJuego();

        vistaSolitario = new VistaSolitario(stage, spider, controladorArchivos, vistaCarta);
        vistaSolitario.iniciar();

        var controladorSpider = new ControladorSpider(vistaSolitario, spider);
        controladorSpider.actualizar();

        vistaSolitario.iniciar();
    }
}
