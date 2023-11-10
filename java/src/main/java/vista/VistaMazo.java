package vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Mazo;

public class VistaMazo extends ImageView {
    private Mazo mazo;
    private Image cartaDorso = VistaCarta.obtenerImagenFondo();

    public VistaMazo(Mazo mazo) {
        this.setLayoutX(24);
        this.setLayoutY(49);
        this.setImage(cartaDorso);
        this.mazo = mazo;
    }

    public void actualizar() {
        if(mazo.estaVacia()){
            this.setImage(VistaCarta.obtenerImagenNoCarta());
        } else {
            this.setImage(cartaDorso);
        }
    }
}