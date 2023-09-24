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
        if (this.cantidadCartasVisibles() == 3){
            int primeraCartaVisible = this.cantidadCartas() - 3;
            this.darVueltaIndex(primeraCartaVisible);
        }
        // La carta viene del mazo boca abajo
        carta.darVuelta();
        lista.add(carta);
    }

}
