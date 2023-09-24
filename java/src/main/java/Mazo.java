import java.util.ArrayList;
import java.util.Collections;

public class Mazo extends ListaDeCartas{
    // Atributos

    // Métodos
    public Mazo(){
        for (Palo palo : Palo.values())
            for (Valor valor : Valor.values()) {
                super.agregarCarta(new Carta(valor, palo, false));
            }
    }
    public void mezclar(){
        Collections.shuffle(lista);
    }
}
