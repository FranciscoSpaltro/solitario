import java.io.Serializable;

public class Cimiento extends ListaDeCartas {
    //Atributos
    private final int id;

    //Métodos
    public Cimiento(int id) {
        this.id = id;
    }

    public int verId() {
        return id;
    }

    public boolean estaCompleto(){
        return lista.size() == 13; //¿hardcodeado?
    }
}