package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import solitario.Klondike;
import solitario.PilaDelTableau;

public class ClicCartaEvento implements EventHandler<ActionEvent> {
    private boolean clicAnterior = false;
    private PilaDelTableau origen = null;
    private PilaDelTableau destino = null;
    private int indiceOrigen;

    public boolean huboClic(){
        return clicAnterior;
    }
    public boolean hacerClic(Klondike klondike, PilaDelTableau lugar, int indice){
        if (clicAnterior) {
            destino = lugar;
            clicAnterior = false;
            //lanzarEventoMover(klondike);
            return clicAnterior;
        }
        origen = lugar;
        indiceOrigen = indice;
        clicAnterior = true;
        klondike.moverPilaAPila(origen, destino, indiceOrigen);
        System.out.println("Se movio");
        return clicAnterior;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
