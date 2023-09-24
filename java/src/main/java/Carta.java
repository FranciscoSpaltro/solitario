public class Carta {
    public static final String[] PALOS;
    public static final int[] VALORES;

    static {
        PALOS = new String[]{"Corazones", "Diamantes", "Tréboles", "Picas"};
        VALORES = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    }

    /*
    enum Palos {
        CORAZONES, DIAMANTES, TREBOLES, PICAS
    }

    enum Valores {
        AS, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, JOTA, REINA, REY
    }

    enum Colores {
        ROJO, NEGRO
    }
    */

    // Atributos
    private int valor;
    private String palo;
    private boolean bocaArriba;

    // Métodos
    public Carta(int valor, String palo, boolean bocaArriba){
        this.valor = valor;
        this.palo = palo;
        this.bocaArriba = bocaArriba;
    }

    public String verPalo(){
        return this.palo;
    }

    public int verValor(){
        return this.valor;
    }

    public boolean estaBocaArriba(){
        return bocaArriba;
    }

    public boolean darVuelta(){
        this.bocaArriba = !this.bocaArriba;
        return this.bocaArriba;
    }

    public char verColor(){
        if (palo.equals(PALOS[1]) || palo.equals(PALOS[2]))
            return 'r';
        else
            return 'n';
    }
}
