import com.sun.tools.javac.util.List;

import java.util.Stack;

public class Basura extends PilaDeCartas {
    //Atributos
        //Heredo la Pila de PilaDeCartas

    //Metodos
    public Basura() {
        pila = null;
    }

    public Stack<Carta> mostrarUltimasTres(){
        Stack<Carta> ultimasTres = new Stack<Carta>();
        for (int i = 0; i < 3; i++) {
            ultimasTres.add(super.extraerUltima());
        }
        return ultimasTres;
    }

    public void agregarCarta(Carta carta){
        pila.push(carta);
    }

}
