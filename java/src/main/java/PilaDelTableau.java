import java.util.ArrayList;

public class PilaDelTableau extends ListaDeCartas{
    // Atributos
    int id;

    // Métodos
    public PilaDelTableau(int id) {
        this.id = id;
    }

    public boolean anexarCartas(ArrayList<Carta> cartas){
        return lista.addAll(cartas);
    }

    public boolean eliminarCartas(ArrayList<Carta> cartas){
        return lista.removeAll(cartas);
    }
}
