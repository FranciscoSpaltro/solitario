import java.util.ArrayList;
import java.util.Stack;

public class Basura extends ListaDeCartas {
    // Atributos

    // Métodos
    public ArrayList<Carta> mostrarUltimasTres(){
        ArrayList<Carta> ultimasTres = new ArrayList<Carta>();
        for (int i = 0; i < 3; i++) {
            ultimasTres.add(super.extraerUltima());
        }
        return ultimasTres;
    }

    @Override
    public void agregarCarta(Carta carta) {
            if (super.cantidadCartasVisibles() == 3){
                int primeraCartaVisible = super.cantidadCartas() - 3;
                super.darVueltaIndex(primeraCartaVisible);
            }
        // La carta viene del mazo boca abajo
        carta.darVuelta();
        lista.add(carta);
    }

}
