package controlador;

import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import modelo.PilaDelTableau;
import vista.VistaSolitario;

import java.util.ArrayList;

public class ControladorPila {
    private VistaSolitario vistaSolitario;
    private PilaDelTableau pila;
    private DatosMovimiento datosMovimiento;

    public ControladorPila(VistaSolitario vistaSolitario, PilaDelTableau pila, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaSolitario = vistaSolitario;
        this.pila = pila;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        ArrayList<ImageView> cartasVisibles = vistaSolitario.obtenerVistaPila(pila.obtenerId()).obtenerCartasVisibles();
        for (ImageView imagen : cartasVisibles) {
            imagen.setOnMouseClicked(event -> {
                // Lógica para "Apretar Pila"
                int posicionAbsoluta = pila.cantidadCartas() - cartasVisibles.indexOf(imagen);
                datosMovimiento.clic(pila, posicionAbsoluta);

                vistaSolitario.obtenerVistaCarta().configurarEfecto(imagen);

                controladorSolitario.actualizar();

            });
        }
    }
}
