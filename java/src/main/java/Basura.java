import com.sun.tools.javac.util.List;

import java.util.ArrayList;
import java.util.Stack;

public class Basura extends ListaDeCartas {
    //Atributos
    private ArrayList<Carta> basura = new ArrayList<>();

    //Metodos
    public ArrayList<Carta> mostrarUltimasTres(){
        ArrayList<Carta> ultimasTres = new ArrayList<Carta>();
        for (int i = 0; i < 3; i++) {
            ultimasTres.add(super.extraerUltima());
        }
        return ultimasTres;
    }

    public void agregarCarta(Carta carta){
        basura.add(carta);
    }

}
