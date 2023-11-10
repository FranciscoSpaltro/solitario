package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Basura;
import modelo.Carta;

import java.util.ArrayList;

public class VistaBasura extends StackPane {
    private ImageView ultimaCarta;
    private Basura basura;

    public VistaBasura(Basura basura) {
        this.setLayoutX(136);
        this.setLayoutY(49);
        this.basura = basura;
        actualizar();
    }

    public void actualizar() {
        this.getChildren().clear();
        ArrayList<Carta> cartasVisibles = basura.mostrarCartasBasura();
        ImageView primeraCarta = new ImageView(VistaCarta.obtenerImagen(cartasVisibles.get(0)));
        ImageView segundaCarta = new ImageView(VistaCarta.obtenerImagen(cartasVisibles.get(1)));
        this.ultimaCarta = new ImageView(VistaCarta.obtenerImagen(cartasVisibles.get(2)));

        primeraCarta.setTranslateX(-24);
        this.getChildren().add(primeraCarta);
        this.getChildren().add(segundaCarta);
        ultimaCarta.setTranslateX(24);
        this.getChildren().add(ultimaCarta);

    }

    public ImageView obtenerUltimaCarta() {
        return ultimaCarta;
    }
}
