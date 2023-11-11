package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Basura extends ListaDeCartas {
    // Atributos

    // Métodos
    public ArrayList<Carta> mostrarCartasBasura(){
        ArrayList<Carta> ultimasN = new ArrayList<>();
        if (super.cantidadCartas() < Constantes.CARTAS_VISIBLES_BASURA){
            int i = 0;
            var auxiliar = new ArrayList<Carta>();
            for (; i < Constantes.CARTAS_VISIBLES_BASURA - super.cantidadCartas(); i++) {
                ultimasN.add(null);
            }
            for (int j = 0; i < Constantes.CARTAS_VISIBLES_BASURA; i++, j++) {
                auxiliar.add(super.obtenerCarta(this.cantidadCartas() - j - 1));
            }
            if (!auxiliar.isEmpty()) {
                Collections.reverse(auxiliar);
                ultimasN.addAll(auxiliar);
            }
        } else {
            for (int i = 0; i < Constantes.CARTAS_VISIBLES_BASURA; i++) {
                ultimasN.add(super.obtenerCarta(this.cantidadCartas() - i - 1));
            }
            Collections.reverse(ultimasN);
        }
        return ultimasN;
    }

    @Override
    public void agregarCarta(Carta carta) {
        /*
        if (super.cantidadCartasVisibles() == Constantes.CARTAS_VISIBLES_BASURA){
            int primeraCartaVisible = super.cantidadCartas() - Constantes.CARTAS_VISIBLES_BASURA;
            super.darVueltaIndex(primeraCartaVisible);
        }
        */
        // La carta viene del mazo boca abajo
        //carta.darVuelta();
        lista.add(carta);
    }

}
