package controlador;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.*;
import vista.VistaAlerta;
import vista.VistaPrincipal;

public class ControladorKlondike extends ControladorSolitario {
    private static Klondike klondike;
    private static ControladorBasura controladorBasura;
    public ControladorKlondike(VistaPrincipal vistaPrincipal, Klondike klondike) {
        super(vistaPrincipal, klondike);
        controladorMazo = new ControladorMazoKlondike(vistaPrincipal, klondike, datosMovimiento, this);
        controladorBasura = new ControladorBasura(vistaPrincipal, klondike.obtenerBasura(), datosMovimiento, this);
    }

    @Override
    public void actualizar(){
        evaluarMovimiento();
        evaluarGanador();
        vistaPrincipal.actualizar();
        controladorVentana.iniciar();
        controladorMazo.iniciar(this);
        controladorBasura.actualizar(this);
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar(this);
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar(this);
    }

    public static void evaluarMovimiento() {
        if (!datosMovimiento.realizarMovimiento())
            return;
        if (datosMovimiento.esBasura(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Basura a Cimiento"
                try {
                    klondike.moverBasuraACimiento((Cimiento) datosMovimiento.obtenerListaDestino());
                } catch (InvalidMovementException e) {
                    VistaAlerta.mostrarAlerta(e);
                }
            } else if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Basura a Pila"
                try {
                    klondike.moverBasuraAPila((PilaDelTableau) datosMovimiento.obtenerListaDestino());
                } catch (InvalidMovementException e) {
                    VistaAlerta.mostrarAlerta(e);
                }
            }
        } else if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Cimiento a Pila"
                try {
                    klondike.moverCimientoAPila((Cimiento) datosMovimiento.obtenerListaOrigen(), (PilaDelTableau) datosMovimiento.obtenerListaDestino());
                } catch (InvalidMovementException e) {
                    VistaAlerta.mostrarAlerta(e);
                }
            }
        } else if (datosMovimiento.esPila(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Pila"
                try {
                    klondike.moverPilaAPila((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (PilaDelTableau) datosMovimiento.obtenerListaDestino(), datosMovimiento.obtenerListaOrigen().cantidadCartas() - datosMovimiento.obtenerIndiceOrigen() + 1);
                    datosMovimiento.resetear();
                } catch (InvalidMovementException e) {
                    VistaAlerta.mostrarAlerta(e);
                }
            } else if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Cimiento"
                if (datosMovimiento.obtenerIndiceOrigen() == datosMovimiento.obtenerListaOrigen().cantidadCartas()) {
                    try {
                        klondike.moverPilaACimiento((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (Cimiento) datosMovimiento.obtenerListaDestino());
                    } catch (InvalidMovementException e) {
                        VistaAlerta.mostrarAlerta(e);
                    }
                }
            }
        }

    }
}
