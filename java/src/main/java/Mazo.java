import java.util.Collections;
import java.util.Stack;

public class Mazo {
    // CONSTANTES
    public static final String[] PALOS;
    public static final char[] VALORES;
    public int CANTIDAD_CARTAS = 52;

    static {
        PALOS = new String[]{"Corazones", "Diamantes", "Tréboles", "Picas"};
        VALORES = new char[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K'};
    }

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

    Carta extraerUltima(){
        return mazo.pop();
    }

    int cantidadCartas(){
        return mazo.size();
    }

    boolean estaVacia(){
        return mazo.isEmpty();
    }

}
