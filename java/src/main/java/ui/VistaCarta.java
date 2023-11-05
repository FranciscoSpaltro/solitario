package ui;

import solitario.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class VistaCarta {
    private final Map<Carta, Image> catalogo = new HashMap<>();
    private final Image dorso_klondike = new Image("cartas/bkg_klondike.png");
    private final Image dorso_spider = new Image("cartas/bkg_spider.png");
    private final Image vacio_klondike = new Image("cartas/vacio_klondike.png");
    private final Image vacio_spider = new Image("cartas/vacio_spider.png");

    public VistaCarta(Mazo mazo) {
        // Cargar las imágenes de las cartas y asociarlas con las combinaciones de palo y valor
        for (Carta carta : mazo) {
            String nombreDeArchivo = obtenerNombreDeArchivo(carta.verValor(), carta.verPalo());
            Image imagen = new Image("cartas/" + nombreDeArchivo + ".png");
            catalogo.put(carta, imagen);
        }
    }

    private String obtenerNombreDeArchivo(Valor valor, Palo palo) {
        return valor.toString() + "_" + palo.toString();
    }

    public ImageView obtenerImagen(Carta carta, Variante variante) {
        if (carta == null)
            if (variante == Variante.KLONDIKE)
                return new ImageView(vacio_klondike);
            else if (variante == Variante.SPIDER)
                return new ImageView(vacio_spider);
            else
                return null;

        if (! carta.estaBocaArriba())
            if (variante == Variante.KLONDIKE)
                return new ImageView(dorso_klondike);
            else if (variante == Variante.SPIDER)
                return new ImageView(dorso_spider);
            else
                return null;

        return new ImageView(catalogo.get(carta));
    }

}