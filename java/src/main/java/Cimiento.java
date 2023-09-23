public class Cimiento extends PilaDeCartas{
    //Atributos
        //Extiende pila de PilaDeCartas

    //Métodos
    public void agregarCarta(Carta carta) {
        pila.add(carta);
    }

    public boolean estaCompleto(){
        return pila.size() == 12; //¿hardcodeado?
    }
}
