package solitario;

import java.util.ArrayList;
public class Basura extends ListaDeCartas {
    // Atributos

    // Métodos
    public ArrayList<Carta> mostrarCartasBasura(){
        ArrayList<Carta> ultimasN = new ArrayList<>();
        for (int i = 0; i < Constantes.CARTAS_VISIBLES_BASURA; i++) {
            ultimasN.add(super.extraerUltima());
        }
        return ultimasN;
    }

    @Override
    public void agregarCarta(Carta carta) {
            if (super.cantidadCartasVisibles() == Constantes.CARTAS_VISIBLES_BASURA){
                int primeraCartaVisible = super.cantidadCartas() - Constantes.CARTAS_VISIBLES_BASURA;
                super.darVueltaIndex(primeraCartaVisible);
            }
        // La carta viene del mazo boca abajo
        carta.darVuelta();
        lista.add(carta);
    }

}
