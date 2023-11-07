package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import  java.util.List;
abstract class Solitario implements Serializable {
    //Atributos
    public Variante tipoSolitario;
    public Mazo mazo;
    public List<Cimiento> cimientos;
    public List<PilaDelTableau> pilasTableau;
    public int puntos;

    //Métodos
    public Solitario(Variante tipo) {
        this.tipoSolitario = tipo;
        puntos = 0;
    }

    public Solitario(Variante tipo, boolean prueba){
        this.tipoSolitario = tipo;
        puntos = 0;
        var palos = new ArrayList<Palo>(Arrays.asList(Palo.values()));
        mazo = new Mazo(palos, 1);
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

    public abstract void moverPilaAPila(PilaDelTableau pilaOrigen, PilaDelTableau pilaDestino, int n) throws InvalidMovementException;

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
}
