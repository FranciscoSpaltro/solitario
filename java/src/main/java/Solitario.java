import  java.util.List;
abstract class Solitario {
    //Atributos
    protected Variante tipoSolitario;
    protected Mazo mazo;
    protected List<Cimiento> cimientos;
    protected List<PilaDelTableau> pilasTableau;
    protected Basura basura;
    protected int puntos;

    //Métodos
    //public Solitario(Variante tipo) {
    //    this.tipoSolitario = tipo;
    //    puntos = 0;
    //    mazo = new Mazo();
    //    mazo.mezclar();
    //}

    public Solitario(Variante tipo) {
        this.tipoSolitario = tipo;
        puntos = 0;
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

    public int obtenerPuntos() {
        return puntos;
    }
    protected abstract void reiniciar();
    protected abstract void repartirCartas(Mazo mazo);

    protected abstract void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException;

    protected abstract void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException;

    protected abstract void moverBasuraAPila(PilaDelTableau pila) throws InvalidMovementException;

    protected abstract void moverBasuraACimiento(Cimiento cimiento) throws InvalidMovementException;

    protected abstract void moverBasuraAMazo() throws InvalidMovementException;

    protected abstract void moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino) throws InvalidMovementException;

    protected abstract void validarMovimientoACimiento(Carta cartaAMover, Cimiento cimientoDestino) throws InvalidMovementException;

    protected abstract void validarMovimientoAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino) throws InvalidMovementException;

    protected abstract void moverMazoABasura() throws InvalidMovementException;

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

    protected Mazo obtenerMazo(){
        return mazo;
    }

    protected Basura obtenerBasura(){
        return basura;
    }
}
