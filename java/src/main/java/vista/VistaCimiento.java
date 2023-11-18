package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.Cimiento;
import modelo.Variante;

public class VistaCimiento extends StackPane {
    private Cimiento cimiento;
    private VistaCarta vistaCarta;

    public VistaCimiento(Cimiento cimiento, int id, VistaCarta vistaCarta){
        this.cimiento = cimiento;
        setLayoutX(288 + id * 88);
        setLayoutY(49);
        this.vistaCarta = vistaCarta;
        actualizar();
    }

    public void actualizar(){
        this.getChildren().clear();
        if (cimiento.estaVacia()) {
            this.getChildren().add(new ImageView(vistaCarta.obtenerImagenNoCarta()));
        }
        for (Carta carta : cimiento) {
            vistaCarta.obtenerImagen(carta).setTranslateY(0);
            vistaCarta.obtenerImagen(carta).setTranslateX(0);
            this.getChildren().add(vistaCarta.obtenerImagen(carta));
        }
    }

    public ImageView obtenerUltimaCarta() {
        return (ImageView) this.getChildren().get(this.getChildren().size() - 1);
    }
}
