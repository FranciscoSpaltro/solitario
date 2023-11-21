package vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Mazo;
import modelo.Variante;

import java.io.Serializable;

public class VistaMazo extends ImageView {
    private final Mazo mazo;
    private final Image cartaDorso;

    private VistaCarta vistaCarta;

    public VistaMazo(Mazo mazo, VistaCarta vistaCarta) {
        this.setLayoutX(24);
        this.setLayoutY(49);
        this.vistaCarta = vistaCarta;
        this.cartaDorso = vistaCarta.obtenerImagenFondo();
        this.setImage(cartaDorso);
        this.mazo = mazo;
    }

    public void actualizar() {
        if(mazo.estaVacia()){
            this.setImage(vistaCarta.obtenerImagenNoCarta());
        } else {
            this.setImage(cartaDorso);
        }
    }
}