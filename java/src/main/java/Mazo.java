import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Mazo extends ListaDeCartas{
    // Atributos

    // Métodos
    public Mazo(){
        for (Palo palo : Palo.values())
            for (Valor valor : Valor.values()) {
                super.agregarCarta(new Carta(valor, palo, false));
            }
    }

    public Mazo(Palo paloElegido){
        for (int i = 0; i < 8; i++) {
            for (Valor valor: Valor.values()){
                super.agregarCarta(new Carta(valor, paloElegido, false));
            }
        }
    }

    public void mezclar(){
        Collections.shuffle(lista);
    }

    @Override
    public Carta extraerUltima(){
        if (super.estaVacia())
            return  null;
        return lista.remove(lista.size() - 1);
    }
}
