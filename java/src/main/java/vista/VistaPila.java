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
        imagenesCartas = new ArrayList<>();
        if(pila.estaVacia()) {
            this.getChildren().add(new ImageView(VistaCarta.obtenerImagenNoCarta()));
        }
        int i = 0;
        for(Carta carta : pila){
            var imagen = new ImageView(VistaCarta.obtenerImagen(carta));
            imagen.setTranslateY(i * 15);
            imagenesCartas.add(imagen);
            i++;
        }
        this.getChildren().addAll(imagenesCartas);
    }

    public ArrayList<ImageView> obtenerCartasVisibles() {
        var cartasVisibles = new ArrayList<ImageView>();
        for (int i = 0; i < pila.cantidadCartasVisibles(); i++) {
            cartasVisibles.add(imagenesCartas.get(pila.cantidadCartas() - i - 1));
        }
        return cartasVisibles;
    }
}
