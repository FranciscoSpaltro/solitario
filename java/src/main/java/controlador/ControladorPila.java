package controlador;

import javafx.scene.image.ImageView;
import modelo.PilaDelTableau;
import vista.VistaPrincipal;

import java.util.ArrayList;

public class ControladorPila {
    private VistaPrincipal vistaPrincipal;
    private PilaDelTableau pila;
    private int id;
    private DatosMovimiento datosMovimiento;

    public ControladorPila(VistaPrincipal vistaPrincipal, PilaDelTableau pila, int id, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaPrincipal = vistaPrincipal;
        this.pila = pila;
        this.id = id;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        ArrayList<ImageView> cartasVisibles = vistaPrincipal.obtenerVistaPila(pila.obtenerId()).obtenerCartasVisibles();
        for (ImageView imagen : cartasVisibles) {
            imagen.setOnMouseClicked(event -> {
                // Lógica para "Apretar Pila"
                int posicionAbsoluta = pila.cantidadCartas() - cartasVisibles.indexOf(imagen);
                datosMovimiento.clic(pila, posicionAbsoluta);
                controladorSolitario.actualizar();
            });
        }
    }
}
