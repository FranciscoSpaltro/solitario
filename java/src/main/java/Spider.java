import java.util.ArrayList;
public class Spider extends Solitario{
    public Spider(Variante tipo) {
        super(tipo);
        pilasTableau = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cimientos.add(new Cimiento(i));
        }
    }

    public Spider(Variante tipo, boolean prueba) {
        super(tipo, prueba);
    }

    @Override
    void inicializarJuego() {
        this.repartirCartas(super.mazo);
    }

    @Override
    protected void reiniciar() {

    }

    @Override
    protected void repartirCartas(Mazo mazo) {
        for (int i = 0; i < super.cantidadPilasDelTableau(); i++) {
            PilaDelTableau pila = super.obtenerPilaDelTableau(i);
            for (int j = 0; j < 4; j++) {
                pila.agregarCarta(mazo.extraerUltima());

                if (i < 4){
                    pila.agregarCarta(mazo.extraerUltima());
                }
            }
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    protected void sacarCartasMazo(){
        for (int i = 0; i < super.cantidadPilasDelTableau(); i++) {
            PilaDelTableau pila = super.obtenerPilaDelTableau(i);
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    @Override
    protected void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException {

    }

    @Override
    protected void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException {

    }


    @Override
    protected void moverCimientoAPila(Cimiento cimiento, PilaDelTableau pilaDestino) throws InvalidMovementException {

    }

    @Override
    protected void validarMovimientoACimiento(Carta cartaAMover, Cimiento cimientoDestino) throws InvalidMovementException {

    }

    @Override
    protected void validarMovimientoAPila(Carta primeraCartaAMover, PilaDelTableau pilaDestino) throws InvalidMovementException {

    }

}
