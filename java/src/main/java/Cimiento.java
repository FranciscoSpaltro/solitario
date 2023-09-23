import java.util.ArrayList;

public class Cimiento extends ListaDeCartas{
    //Atributos
    int id;

    //Métodos
    public Cimiento(int id) {
        this.id = id;
    }

    public boolean estaCompleto(){
        return lista.size() == 12; //¿hardcodeado?
    }
}