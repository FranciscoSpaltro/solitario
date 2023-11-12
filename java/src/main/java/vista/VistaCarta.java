package vista;
import javafx.scene.image.Image;
import modelo.Carta;
import modelo.Palo;
import modelo.Valor;
import modelo.Variante;

public class VistaCarta {

    public static Image obtenerImagenFondo(Variante variante){
        if(variante == Variante.KLONDIKE)
            return new Image("/cartas/bkg_klondike.png");
        else if(variante == Variante.SPIDER)
            return new Image("/cartas/bkg_spider.png");
        else
            return null;
    }

    public static Image obtenerImagenNoCarta(Variante variante){
        if(variante == Variante.KLONDIKE)
            return new Image("/cartas/vacio_klondike.png");
        else if(variante == Variante.SPIDER)
            return new Image("/cartas/vacio_spider.png");
        else
            return null;
    }

    public static Image obtenerImagen(Carta carta, Variante variante){
        if (carta == null) {
            return obtenerImagenNoCarta(variante);
        } else if (!carta.estaBocaArriba()) {
            return obtenerImagenFondo(variante);
        }
        return new Image("/cartas/" + obtenerNombreDeArchivo(carta.verValor(), carta.verPalo()) + ".png");
    }
    private static String obtenerNombreDeArchivo(Valor valor, Palo palo) {
        return valor.toString() + "_" + palo.toString();
    }
}
