import java.util.Collections;
import java.util.Stack;

public class Mazo extends PilaDeCartas{
    // Atributos
    private Stack<Carta> mazo = new Stack<>();

    // Métodos
    public Mazo(){
        for (String palo : PALOS)
            for (char valor : VALORES) {
                mazo.push(new Carta(valor, palo, false));
            }
    }
    public void mezclar(){
        Collections.shuffle(mazo);
    }
}
