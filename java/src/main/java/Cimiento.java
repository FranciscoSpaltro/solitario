import java.util.ArrayList;

public class Cimiento extends ListaDeCartas{
    //Atributos
    private ArrayList<Carta> cimiento = new ArrayList<>();

    //Métodos
    public void agregarCarta(Carta carta) {
        cimiento.add(carta);
    }

    public boolean estaCompleto(){
        return cimiento.size() == 12; //¿hardcodeado?
    }
}