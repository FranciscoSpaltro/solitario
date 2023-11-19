package modelo;

public interface Constantes {
    int CARTAS_VISIBLES_BASURA = 3;
    int CANTIDAD_CARTAS_POR_PALO = 13;
    int CANTIDAD_PILAS_TABLEAU_KLONDIKE = 7;
    int CANTIDAD_PILAS_TABLEAU_SPIDER = 10;
    int CANTIDAD_CIMIENTOS_KLONDIKE = 4;
    int CANTIDAD_CIMIENTOS_SPIDER = 8;
    int PUNTOS_PILA_A_PILA = 5;
    int PUNTOS_PILA_A_CIMIENTO_KLONDIKE = 10;
    int PUNTOS_BASURA_A_MAZO_KLONDIKE = 100;
    int PUNTOS_CIMIENTO_A_PILA_KLONDIKE = 15;
    int REPETICIONES_POR_PALO_SPIDER = 8;
    int PUNTOS_PILA_A_PILA_SPIDER = 5;
    int PUNTOS_PILA_A_CIMIENTO_SPIDER = 10;
    // Nota del programador: Grupo 1 hace referencia al conjunto de pilas de tableau en solitario.Spider que tienen más cartas que las otras
    int CANTIDAD_CARTAS_OCULTAS_INICIAL_TABLEAU_GRUPO_1_SPIDER = 5;
    int CANTIDAD_PILAS_TABLEAU_GRUPO_1_SPIDER = 4;

    String RUTA_POR_DEFECTO = "solitario.txt";
    int ANCHO_VENTANA_KLONDIKE = 640;
    int ALTO_VENTANA_KLONDIKE = 680;
    int ANCHO_VENTANA_SPIDER = 904;
    int ALTO_VENTANA_SPIDER = 680;
    double POSICION_Y_PILAS = 167;
    double MARCO = 24;
    double ANCHO_CARTA = 64;
    double ESPACIADO_ENTRE_CARTAS = 26;
    String MENSAJE_GANADOR = "    ¡Felicitaciones!\nGanaste con un puntaje de: ";
    String RUTA_VISTA_CARTA = "vistaCarta.txt";

    static int obtenerCantidadPilasTableau(Variante variante){
        if(variante == Variante.KLONDIKE)
            return CANTIDAD_PILAS_TABLEAU_KLONDIKE;
        else if(variante == Variante.SPIDER)
            return CANTIDAD_PILAS_TABLEAU_SPIDER;
        else
            return 0;
    }
    static int obtenerCantidadCimientos(Variante variante){
        if(variante == Variante.KLONDIKE)
            return CANTIDAD_CIMIENTOS_KLONDIKE;
        else if(variante == Variante.SPIDER)
            return CANTIDAD_CIMIENTOS_SPIDER;
        else
            return 0;
    }

    static int obtenerAncho(Variante variante){
        if(variante == Variante.KLONDIKE)
            return ANCHO_VENTANA_KLONDIKE;
        else if(variante == Variante.SPIDER)
            return ANCHO_VENTANA_SPIDER;
        else
            return 0;
    }

    static int obtenerAlto(Variante variante){
        if(variante == Variante.KLONDIKE)
            return ALTO_VENTANA_KLONDIKE;
        else if(variante == Variante.SPIDER)
            return ALTO_VENTANA_SPIDER;
        else
            return 0;
    }

    static boolean tieneBasura(Variante variante) {
        return variante == Variante.KLONDIKE;
    }
}
