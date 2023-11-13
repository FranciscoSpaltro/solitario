package controlador.Handlers;

import controlador.ControladorSpider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.MovimientoACimientoSpiderFacil;
import modelo.MovimientoAPilaSpiderFacil;
import modelo.Palo;
import modelo.Spider;
import vista.VistaSolitario;

import java.util.ArrayList;

public class ElegirSpiderFacilEventHandler implements EventHandler<MouseEvent> {
    Palo paloElegido;
    private VistaSolitario vistaSolitario;
    private Stage stage;

    public ElegirSpiderFacilEventHandler(Palo palo, VistaSolitario vistaSolitario, Stage stage) {
        this.paloElegido = palo;
        this.vistaSolitario = vistaSolitario;
        this.stage = stage;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        ArrayList<Palo> palos = new ArrayList<Palo>();
        palos.add(paloElegido);
        var spider = new Spider(palos, new MovimientoACimientoSpiderFacil(), new MovimientoAPilaSpiderFacil(), false);
        spider.inicializarJuego();

        vistaSolitario = new VistaSolitario(stage, spider);
        vistaSolitario.iniciar();

        var controladorSpider = new ControladorSpider(vistaSolitario, spider);
        controladorSpider.actualizar();

        vistaSolitario.iniciar();
    }
}
