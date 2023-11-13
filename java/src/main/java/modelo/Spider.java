package modelo;

import java.util.ArrayList;
public class Spider extends Solitario{
    private final Variante tipoSolitario = Variante.SPIDER;;
    private final IMovimientoACimientoSpiderStrategy movimientoACimiento;

    public Spider(ArrayList<Palo> palosElegidos, IMovimientoACimientoSpiderStrategy movimientoACimiento, IMovimientoAPilaStrategy movimientoAPila,  boolean prueba) {
        super();
        this.movimientoACimiento = movimientoACimiento;
        super.movimientoAPila = movimientoAPila;

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_SPIDER; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_SPIDER; i++) {
            cimientos.add(new Cimiento(i));
        }

        super.mazo = new Mazo(palosElegidos, Constantes.REPETICIONES_POR_PALO_SPIDER/palosElegidos.size());
        if (!prueba)
            super.mazo.mezclar();
    }

    @Override
    public Variante obtenerVariante(){
        return tipoSolitario;
    }

    @Override
    public Basura obtenerBasura(){
        return null;
    }

    @Override
    public void inicializarJuego() {
        this.repartirCartas(super.mazo); // Hay que modificar como se genera el mazo
    }

    public void reiniciar(Palo paloElegido) {
        puntos = 0;
        // Al dejar sin referencia, la máquina virtual de Java elimina la memoria anterior
        var palos = new ArrayList<Palo>();
        palos.add(paloElegido);
        super.mazo = new Mazo(palos, Constantes.REPETICIONES_POR_PALO_SPIDER);
        super.mazo.mezclar();

        pilasTableau = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_SPIDER; i++) {
            pilasTableau.add(new PilaDelTableau(i));
        }
        cimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_SPIDER; i++) {
            cimientos.add(new Cimiento(i));
        }

        this.inicializarJuego();

    }

    @Override
    public void repartirCartas(Mazo mazo) {
        for (int i = 0; i < super.cantidadPilasDelTableau(); i++) {
            PilaDelTableau pila = super.obtenerPilaDelTableau(i);
            for (int j = 0; j < Constantes.CANTIDAD_CARTAS_OCULTAS_INICIAL_TABLEAU_GRUPO_1_SPIDER - 1; j++) {
                pila.agregarCarta(mazo.extraerUltima());
            }

            if (i < Constantes.CANTIDAD_PILAS_TABLEAU_GRUPO_1_SPIDER){
                pila.agregarCarta(mazo.extraerUltima());
            }
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    public void sacarCartasMazo() throws InvalidMovementException {
        // Chequeo que todas las pilas tengan al menos una carta
        for (PilaDelTableau pila : pilasTableau) {
            if (pila == null)
                throw new InvalidMovementException(ErrorAlMover.PILAS_VACIA_NO_PUEDE_SACAR_DEL_MAZO);
        }
        // Si todas las pilas tienen, puedo sacar del mazo
        for (PilaDelTableau pila : pilasTableau) {
            Carta cartaVisible = mazo.extraerUltima();
            cartaVisible.darVuelta();
            pila.agregarCarta(cartaVisible);
        }
    }

    @Override
    public void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException {
        if (cimiento.estaVacia()){
            throw new InvalidMovementException(ErrorAlMover.CIMIENTO_VACIO_NO_SACAR_CARTA);
        }

        movimientoACimiento.validarMovimientoACimiento(pila, cimiento); // Si pasa algo, lanza la excepción

        for (int i = 0; i < Constantes.CANTIDAD_CARTAS_POR_PALO; i++) {
            cimiento.agregarCarta(pila.extraerUltima());
        }

        puntos += Constantes.PUNTOS_PILA_A_CIMIENTO_SPIDER;
    }

}
