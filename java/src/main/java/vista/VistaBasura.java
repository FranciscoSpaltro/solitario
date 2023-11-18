package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Basura;
import modelo.Carta;
import modelo.Variante;

import java.util.ArrayList;

public class VistaBasura extends StackPane {
    private ImageView ultimaCarta;
    private Basura basura;
    Variante variante;
    VistaCarta vistaCarta;

    public VistaBasura(Basura basura, Variante variante, VistaCarta vistaCarta) {
        this.setLayoutX(136);
        this.setLayoutY(49);
        this.basura = basura;
        this.variante = variante;
        this.vistaCarta = vistaCarta;
        actualizar();
    }

    public void actualizar() {
        this.getChildren().clear();
        ArrayList<Carta> cartasVisibles = basura.mostrarCartasBasura();
        ImageView primeraCarta = vistaCarta.obtenerImagen(cartasVisibles.get(0));
        ImageView segundaCarta = vistaCarta.obtenerImagen(cartasVisibles.get(1));
        this.ultimaCarta = vistaCarta.obtenerImagen(cartasVisibles.get(2));

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
