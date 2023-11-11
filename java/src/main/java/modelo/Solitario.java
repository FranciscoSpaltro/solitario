package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import  java.util.List;
public abstract class Solitario implements Serializable {
    //Atributos
    public Variante tipoSolitario;
    public Mazo mazo;
    public List<Cimiento> cimientos;
    public List<PilaDelTableau> pilasTableau;
    public int puntos;

    public IMovimientoAPilaStrategy movimientoAPila;

    //Métodos

    public Solitario(Variante tipo){
        this.tipoSolitario = tipo;
        puntos = 0;
    }

    abstract void inicializarJuego();

    public boolean jugadorGano(){
        for (Cimiento cimiento : cimientos){
            if (!cimiento.estaCompleto())
                return false;
        }
        return true;
    }

    public Variante obtenerVariante(){
        return tipoSolitario;
    }
    public int obtenerPuntos() {
        return puntos;
    }

    public abstract void repartirCartas(Mazo mazo);

    public abstract void moverPilaACimiento(PilaDelTableau pila, Cimiento cimiento) throws InvalidMovementException;

    public int cantidadPilasDelTableau(){
        return pilasTableau.size();
    }

    public Cimiento obtenerCimiento(int index){
        return cimientos.get(index);
    }

    public PilaDelTableau obtenerPilaDelTableau(int index){
        return pilasTableau.get(index);
    }

    public int cantidadCimientos(){
        return cimientos.size();
    }

    public Mazo obtenerMazo(){
        return mazo;
    }

    public void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException {

        ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);
        try {
            movimientoAPila.validarMovimientoAPila(cartasAMover, pilaDestino);
        }catch (InvalidMovementException e){
            if(!pilaOrigen.estaVacia())
                pilaOrigen.darVueltaIndex(pilaOrigen.cantidadCartas() - 1);
            pilaOrigen.anexarCartas(cartasAMover);
            throw e;
        }
        // Llegado a este punto, el movimiento es válido

        if (pilaOrigen.cantidadCartasVisibles() == n)
            puntos += Constantes.PUNTOS_PILA_A_PILA;

        //ArrayList<Carta> cartasAMover = pilaOrigen.extraerUltimasN(n);

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= Constantes.PUNTOS_PILA_A_PILA;
            throw new InvalidMovementException(ErrorAlMover.ERROR_DE_PROGRAMA);
        }
    }
}
