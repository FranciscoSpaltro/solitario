package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.Cimiento;
import modelo.Variante;

public class VistaCimiento extends StackPane {
    private Cimiento cimiento;
    private int id;
    private Variante variante;

    public VistaCimiento(Cimiento cimiento, int id, Variante variante){
        this.cimiento = cimiento;
        this.id = id;
        setLayoutX(288 + id * 88);
        setLayoutY(49);
        this.variante = variante;
        actualizar();
    }

    public void actualizar(){
        this.getChildren().clear();
        if (cimiento.estaVacia()) {
            this.getChildren().add(new ImageView(VistaCarta.obtenerImagenNoCarta(variante)));
        }
        for (Carta carta : cimiento) {
            this.getChildren().add(new ImageView(VistaCarta.obtenerImagen(carta, variante)));
        }
    }

    public ImageView obtenerUltimaCarta() {
        return (ImageView) this.getChildren().get(this.getChildren().size() - 1);
    }
}
