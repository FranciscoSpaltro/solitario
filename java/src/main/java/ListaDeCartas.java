import java.util.ArrayList;

abstract class ListaDeCartas {
    // CONSTANTES
    public static final String[] PALOS;
    public static final char[] VALORES;
    public int CANTIDAD_CARTAS = 52;

    static {
        PALOS = new String[]{"Corazones", "Diamantes", "Tréboles", "Picas"};
        VALORES = new char[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K'};
    }

    // ATRIBUTOS
    protected ArrayList<Carta> lista = new ArrayList<>();

    // METODOS
    public int cantidadCartas(){
        return lista.size();
    }

    public boolean estaVacia(){
        return lista.isEmpty();
    }

    public Carta extraerUltima(){
        return lista.remove(lista.size() - 1);
    }

    public Carta verUltima(){
        return lista.get(lista.size() - 1);
    }
}