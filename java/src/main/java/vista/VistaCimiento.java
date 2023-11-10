package vista;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.Cimiento;

public class VistaCimiento extends StackPane {
    private Cimiento cimiento;
    private int id;

    public VistaCimiento(Cimiento cimiento, int id){
        this.cimiento = cimiento;
        this.id = id;
        setLayoutX(288 + id * 88);
        setLayoutY(49);
        actualizar();
    }

    public void actualizar(){
        this.getChildren().clear();
        if (cimiento.estaVacia()) {
            this.getChildren().add(new ImageView(VistaCarta.obtenerImagenNoCarta()));
        }
        for (Carta carta : cimiento) {
            this.getChildren().add(new ImageView(VistaCarta.obtenerImagen(carta)));
        }
    }

    public ImageView obtenerUltimaCarta() {
        return (ImageView) this.getChildren().get(this.getChildren().size() - 1);
    }
}
