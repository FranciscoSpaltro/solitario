import java.util.Stack;

abstract class PilaDeCartas {
    // CONSTANTES
    public static final String[] PALOS;
    public static final char[] VALORES;
    public int CANTIDAD_CARTAS = 52;

    static {
        PALOS = new String[]{"Corazones", "Diamantes", "Tréboles", "Picas"};
        VALORES = new char[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K'};
    }

    // ATRIBUTOS
    private Stack<Carta> pila = new Stack<>();

    // METODOS
    public int cantidadCartas(){
        return pila.size();
    }

    public boolean estaVacia(){
        return pila.isEmpty();
    }

    public Carta extraerUltima(){
        return pila.pop();
    }
}
