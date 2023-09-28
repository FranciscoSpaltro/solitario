import java.util.ArrayList;
import  java.util.List;
import java.util.Stack;

abstract class Solitario {
    //Atributos
    protected Variante tipoSolitario;
    protected Mazo mazo;
    protected List<Cimiento> cimientos;
    protected List<PilaDelTableau> pilasTableau;
    protected Basura basura;
    protected int puntos;

    //Métodos
    public Solitario(Variante tipo) {
        this.tipoSolitario = tipo;
        puntos = 0;
        mazo = new Mazo();
        mazo.mezclar();
    }

    public Solitario(Variante tipo, boolean prueba){
        this.tipoSolitario = tipo;
        puntos = 0;
        mazo = new Mazo();
    }

    abstract void inicializarJuego();

    public boolean jugadorGano(){
        for (Cimiento cimiento : cimientos){
            if (!cimiento.estaCompleto())
                return false;
        }
        return true;
    }

    // protected abstract boolean jugadorPerdio(); // ¿Se puede implementar?

    void reiniciar(){
        puntos = 0;
        inicializarJuego();
    }

    protected abstract void repartirCartas(Mazo mazo);

    protected abstract boolean moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n);

    //protected abstract boolean moverPilaACimiento(Stack<Carta>pila, Cimiento cimiento);
    protected abstract boolean moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento);

    protected abstract boolean moverBasuraAPila(PilaDelTableau pila);

    protected abstract boolean moverBasuraACimiento(Cimiento cimiento);

    protected abstract boolean moverBasuraAMazo();

    protected abstract boolean moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino);

    protected abstract boolean moverMazoABasura();

    protected int cantidadPilasDelTableau(){
        return pilasTableau.size();
    }

    protected Cimiento obtenerCimiento(int index){
        return cimientos.get(index);
    }

    protected PilaDelTableau obtenerPilaDelTableau(int index){
        return pilasTableau.get(index);
    }

    protected int cantidadCimientos(){
        return cimientos.size();
    }

}
