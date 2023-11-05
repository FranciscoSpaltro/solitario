package ui;

import solitario.Klondike;
import solitario.PilaDelTableau;

public class ClicCartaEvento {
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
            lanzarEventoMover(klondike);
        } else {
            origen = lugar;
            indiceOrigen = indice;
            clicAnterior = true;
            lanzarEventoSeleccion(klondike);
        }
    }

    private void lanzarEventoMover(Klondike klondike){
        klondike.moverPilaAPila(origen, destino, indiceOrigen);
    }
    private void lanzarEventoSeleccion(Klondike klondike){

    }
}
