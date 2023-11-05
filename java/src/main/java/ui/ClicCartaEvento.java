package ui;

import solitario.Klondike;
import solitario.ListaDeCartas;
import solitario.PilaDelTableau;

public class ClicCartaEvento {
    private boolean clic = false;
    private PilaDelTableau origen = null;
    private PilaDelTableau destino = null;
    private int indiceOrigen;

    public boolean huboClic(){
        return clic;
    }
    public boolean hacerClic(Klondike klondike, PilaDelTableau lugar, int indice){
        if (clic) {
            destino = lugar;
            clic = false;
            lanzarEventoMover(klondike);
        } else {
            origen = lugar;
            indiceOrigen = indice;
            clic = true;
            lanzarEventoSeleccion(klondike);
        }
    }

    private void lanzarEventoMover(Klondike klondike){
        klondike.moverPilaAPila(origen, destino, indiceOrigen);
    }
    private void lanzarEventoSeleccion(Klondike klondike){

    }
}
