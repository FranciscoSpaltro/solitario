package vista;
import javafx.scene.image.Image;
import modelo.Carta;
import modelo.Palo;
import modelo.Valor;

public class VistaCarta {

    public static Image obtenerImagenFondo(){
        return new Image("/cartas/bkg_klondike.png");
    }

    public static Image obtenerImagenNoCarta(){
        return new Image("/cartas/vacio_klondike.png");
    }

    public static Image obtenerImagen(Carta carta){
        if (carta == null) {
            return obtenerImagenNoCarta();
        } else if (!carta.estaBocaArriba()) {
            return obtenerImagenFondo();
        }
        return new Image("/cartas/" + obtenerNombreDeArchivo(carta.verValor(), carta.verPalo()) + ".png");
    }
    private static String obtenerNombreDeArchivo(Valor valor, Palo palo) {
        return valor.toString() + "_" + palo.toString();
    }
}
