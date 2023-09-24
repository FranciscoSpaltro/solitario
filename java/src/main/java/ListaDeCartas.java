import java.util.ArrayList;

abstract class ListaDeCartas {
    // CONSTANTES
    public int CANTIDAD_CARTAS = 52;

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

    public void agregarCarta(Carta carta) {
        lista.add(carta);
    }

    public Carta obtenerCarta(int index) {
        return lista.get(index);
    }

    public ArrayList<Carta> extraerUltimasN(int n){
        int comienzoExtraccion = this.cantidadCartas() - n;
        int finExtraccion = this.cantidadCartas(); // le saqué el -1

        ArrayList<Carta> cartasAExtraer = new ArrayList<Carta>(lista.subList(comienzoExtraccion, finExtraccion));
        lista.removeAll(cartasAExtraer);

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
        return i + 1;
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

    public ArrayList<Carta> mostrarVisibles(){
        int comienzoVisibles = this.cantidadCartasOcultas();
        return (ArrayList<Carta>) lista.subList(comienzoVisibles, this.cantidadCartas() - 1);
    }
}