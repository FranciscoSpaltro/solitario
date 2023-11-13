package controlador.Handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.MovimientoACimientoSpiderFacil;
import modelo.MovimientoAPilaSpiderFacil;
import modelo.Palo;
import modelo.Spider;
import vista.VistaOpcionSpider;
import vista.VistaSolitario;

import java.util.ArrayList;

public class OpcionSpiderEventHandler implements EventHandler<MouseEvent> {
    private VistaSolitario vistaSolitario;
    private Stage stage;

    public OpcionSpiderEventHandler(VistaSolitario vistaSolitario, Stage stage) {
        this.vistaSolitario = vistaSolitario;
        this.stage = stage;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            var vistaOpcionSpider = new VistaOpcionSpider(stage, vistaSolitario);
            vistaOpcionSpider.mostrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
