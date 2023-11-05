package solitario;

import java.io.Serializable;
import java.util.*;

public abstract class ListaDeCartas implements Serializable, Iterable<Carta> {
    // ATRIBUTOS
    protected ArrayList<Carta> lista = new ArrayList<>();

    // METODOS
    public Iterator<Carta> iterator() {
        return lista.iterator();
    }

    public int cantidadCartas(){
        return lista.size();
    }

    public boolean estaVacia(){
        return lista.isEmpty();
    }

    public Carta extraerUltima(){
        if (lista.isEmpty())
            return  null;
        if (lista.size() > 1) {
            Carta anteUltimaCarta = this.obtenerCarta(lista.size() - 2);
            if (!anteUltimaCarta.estaBocaArriba())
                this.darVueltaIndex(lista.size() - 2);
        }
        return lista.remove(lista.size() - 1);
    }

    public Carta verUltima(){
        return lista.get(lista.size() - 1);
    }

    public void agregarCarta(Carta carta) {
        lista.add(carta);
    }

    public Carta obtenerCarta(int index) {
        if (lista.isEmpty())
            return null;
        return lista.get(index);
    }

    public ArrayList<Carta> extraerUltimasN(int n){
        int comienzoExtraccion = this.cantidadCartas() - n;
        int finExtraccion = this.cantidadCartas();

        ArrayList<Carta> cartasAExtraer = new ArrayList<>(lista.subList(comienzoExtraccion, finExtraccion));
        lista.removeAll(cartasAExtraer);

        if (!this.estaVacia()) {
            if (!this.verUltima().estaBocaArriba())
                this.darVueltaIndex(this.cantidadCartas() - 1);
        }
        return cartasAExtraer;
    }

    public int cantidadCartasOcultas(){
        int i;
        if (this.estaVacia())
            return 0;

        for (i = 0; i < this.cantidadCartas(); i++) {
            if(lista.get(i).estaBocaArriba())
                break;
        }
        return i ;
    }

    public boolean anexarCartas(ArrayList<Carta> cartas){
        return lista.addAll(cartas);
    }

    public int cantidadCartasVisibles(){
        return this.cantidadCartas() - this.cantidadCartasOcultas();
    }

    public boolean darVueltaIndex(int n){
        Carta carta = lista.get(n);
        return carta.darVuelta();
    }
}