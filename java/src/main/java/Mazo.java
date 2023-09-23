import java.util.ArrayList;
import java.util.Collections;

public class Mazo extends ListaDeCartas{
    // Atributos
    private ArrayList<Carta> mazo = new ArrayList<>();

    // Métodos
    public Mazo(){
        for (String palo : PALOS)
            for (char valor : VALORES) {
                mazo.add(new Carta(valor, palo, false));
            }
    }
    public void mezclar(){
        Collections.shuffle(mazo);
    }
}
