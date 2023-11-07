package modelo;

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
}
