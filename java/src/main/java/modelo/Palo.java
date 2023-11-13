package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Palo {
    CORAZONES(ColorPalo.ROJO),
    DIAMANTES(ColorPalo.ROJO),
    TREBOLES(ColorPalo.NEGRO),
    PICAS(ColorPalo.NEGRO);

    public final ColorPalo color;

    Palo(ColorPalo color) {
        this.color = color;
    }

    public final boolean mismoColor(Palo paloComparar){
        return this.color == paloComparar.color;
    }

    public static ArrayList<Palo> obtenerDosAlAzar() {
        List<Palo> aux = Arrays.asList(Palo.values());
        Collections.shuffle(aux);

        var listaPalos = new ArrayList<Palo>();
        listaPalos.add(aux.get(0));
        listaPalos.add(aux.get(1));
        return listaPalos;
    }
}
