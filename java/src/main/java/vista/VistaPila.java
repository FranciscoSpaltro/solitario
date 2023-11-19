package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.PilaDelTableau;
import modelo.Variante;
import modelo.Constantes;

import java.io.Serializable;
import java.util.ArrayList;

public class VistaPila extends StackPane {
    private PilaDelTableau pila;
    private ArrayList<ImageView> imagenesCartas;
    private VistaCarta vistaCarta;

    public VistaPila(PilaDelTableau pila, int id, VistaCarta vistaCarta) {
        this.pila = pila;
        setLayoutX(Constantes.MARCO + id * (Constantes.ANCHO_CARTA + Constantes.MARCO));
        setLayoutY(Constantes.POSICION_Y_PILAS);
        imagenesCartas = new ArrayList<>();
        this.vistaCarta = vistaCarta;
        actualizar();
    }

    public void actualizar(){
        this.getChildren().clear();
        imagenesCartas = new ArrayList<>();
        if(pila.estaVacia()) {
            var imagen = new ImageView(vistaCarta.obtenerImagenNoCarta());
            imagenesCartas.add(imagen);
            this.getChildren().addAll(imagenesCartas);
        } else {
            int i = 0;
            for (Carta carta : pila) {
                var imagen = vistaCarta.obtenerImagen(carta);
                imagen.setTranslateX(0);
                imagen.setTranslateY(i * Constantes.ESPACIADO_ENTRE_CARTAS);
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
