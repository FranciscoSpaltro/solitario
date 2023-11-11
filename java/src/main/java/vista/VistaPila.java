package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.PilaDelTableau;

import java.util.ArrayList;

public class VistaPila extends StackPane {
    private PilaDelTableau pila;
    private ArrayList<ImageView> imagenesCartas;
    private int id;

    public VistaPila(PilaDelTableau pila, int id) {
        this.pila = pila;
        this.id = id;
        setLayoutX(24 + id * 88);
        setLayoutY(167);
        imagenesCartas = new ArrayList<>();
        actualizar();
    }

    public void actualizar(){
        this.getChildren().clear();
        imagenesCartas = new ArrayList<>();
        if(pila.estaVacia()) {
            var imagen = new ImageView(VistaCarta.obtenerImagenNoCarta());
            imagenesCartas.add(imagen);
            this.getChildren().addAll(imagenesCartas);
        } else {
            int i = 0;
            for (Carta carta : pila) {
                var imagen = new ImageView(VistaCarta.obtenerImagen(carta));
                imagen.setTranslateY(i * 15);
                imagenesCartas.add(imagen);
                i++;
            }
            this.getChildren().addAll(imagenesCartas);
        }
    }

    public ArrayList<ImageView> obtenerCartasVisibles() {
        var cartasVisibles = new ArrayList<ImageView>();
        if (pila.estaVacia()) {
            cartasVisibles.add(imagenesCartas.get(0));
            return cartasVisibles;
        }
        for (int i = 0; i < pila.cantidadCartasVisibles(); i++) {
            cartasVisibles.add(imagenesCartas.get(pila.cantidadCartas() - i - 1));
        }
        return cartasVisibles;
    }
}
