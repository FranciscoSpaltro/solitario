package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import  java.util.List;
public abstract class Solitario implements Serializable {
    //Atributos
    public Mazo mazo;
    public List<Cimiento> cimientos;
    public List<PilaDelTableau> pilasTableau;
    public int puntos;

    public IMovimientoAPilaStrategy movimientoAPila;

    //Métodos

    public Solitario(){
        puntos = 0;
    }

    public abstract void inicializarJuego();

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
    public abstract Variante obtenerVariante();
    public abstract Basura obtenerBasura();

    public void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException {

        if (pilaOrigen.estaVacia()){
            throw new InvalidMovementException(ErrorAlMover.PILA_VACIA_NO_SACAR_CARTA);
        }

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

        if (!pilaDestino.anexarCartas(cartasAMover)) {
            pilaOrigen.anexarCartas(cartasAMover);
            puntos -= Constantes.PUNTOS_PILA_A_PILA;
            throw new InvalidMovementException(ErrorAlMover.ERROR_DE_PROGRAMA);
        }
    }
}
