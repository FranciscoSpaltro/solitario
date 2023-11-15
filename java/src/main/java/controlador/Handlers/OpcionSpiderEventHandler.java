package controlador.Handlers;

import controlador.ControladorArchivos;
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
import java.util.ConcurrentModificationException;

public class OpcionSpiderEventHandler implements EventHandler<MouseEvent> {
    private VistaSolitario vistaSolitario;
    private Stage stage;
    private ControladorArchivos controladorArchivos;

    public OpcionSpiderEventHandler(VistaSolitario vistaSolitario, Stage stage, ControladorArchivos controladorArchivos){
        this.vistaSolitario = vistaSolitario;
        this.stage = stage;
        this.controladorArchivos = controladorArchivos;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            var vistaOpcionSpider = new VistaOpcionSpider(stage, vistaSolitario, controladorArchivos);
            vistaOpcionSpider.mostrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
