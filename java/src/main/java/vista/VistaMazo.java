package vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Mazo;
import modelo.Variante;

public class VistaMazo extends ImageView {
    private final Mazo mazo;
    private final Image cartaDorso;
    private final Variante variante;

    public VistaMazo(Mazo mazo, Variante variante) {
        this.setLayoutX(24);
        this.setLayoutY(49);
        this.variante = variante;
        this.cartaDorso = VistaCarta.obtenerImagenFondo(this.variante);
        this.setImage(cartaDorso);
        this.mazo = mazo;
    }

    public void actualizar() {
        if(mazo.estaVacia()){
            this.setImage(VistaCarta.obtenerImagenNoCarta(this.variante));
        } else {
            this.setImage(cartaDorso);
        }
    }
}