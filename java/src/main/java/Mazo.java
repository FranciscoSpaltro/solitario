import java.util.ArrayList;
import java.util.Collections;

public class Mazo extends ListaDeCartas{
    // Atributos

    // Métodos
    public Mazo(){
        for (String palo : Carta.PALOS)
            for (char valor : Carta.VALORES) {
                super.agregarCarta(new Carta(valor, palo, false));
            }
    }
    public void mezclar(){
        Collections.shuffle(lista);
    }
}
