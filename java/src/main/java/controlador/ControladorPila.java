package controlador;

import javafx.scene.image.ImageView;
import modelo.PilaDelTableau;
import vista.VistaPrincipal;

import java.util.ArrayList;

public class ControladorPila {
    private VistaPrincipal vistaPrincipal;
    private PilaDelTableau pila;
    private int id;

    public ControladorPila(VistaPrincipal vistaPrincipal, PilaDelTableau pila, int id) {
        this.vistaPrincipal = vistaPrincipal;
        this.pila = pila;
        this.id = id;
        actualizar();
    }

    public void actualizar() {
        ArrayList<ImageView> cartasVisibles = vistaPrincipal.obtenerVistaPila(pila.obtenerId()).obtenerCartasVisibles();
        for (ImageView imagen : cartasVisibles) {
            imagen.setOnMouseClicked(event -> {
                // Lógica para "Apretar Pila"
                int idPila = id + 1;
                System.out.println("Carta de la pila " + idPila + " seleccionada");
                ControladorPrincipal.actualizar();
            });
        }
    }
}
