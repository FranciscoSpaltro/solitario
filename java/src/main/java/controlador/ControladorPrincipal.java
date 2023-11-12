package controlador;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.*;
import vista.VistaAlerta;
import vista.VistaPrincipal;

import java.util.ArrayList;

public class ControladorPrincipal {
    private static VistaPrincipal vistaPrincipal;
    private static ControladorVentana controladorVentana;
    private static ControladorMazo controladorMazo;
    private static ControladorBasura controladorBasura;
    private static ArrayList<ControladorPila> controladoresPila = new ArrayList<>();
    private static ArrayList<ControladorCimiento> controladoresCimiento = new ArrayList<>();
    private static Klondike klondike;
    private static DatosMovimiento datosMovimiento = new DatosMovimiento();

    public ControladorPrincipal(VistaPrincipal vistaPrincipal, Klondike klondike){
        this.vistaPrincipal = vistaPrincipal;
        this.klondike = klondike;
        controladorVentana = new ControladorVentana(vistaPrincipal, datosMovimiento);
        controladorMazo = new ControladorMazo(vistaPrincipal, klondike, datosMovimiento);
        controladorBasura = new ControladorBasura(vistaPrincipal, klondike.obtenerBasura(), datosMovimiento);
        for(int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++)
            controladoresCimiento.add(new ControladorCimiento(vistaPrincipal, klondike.obtenerCimiento(i), i, datosMovimiento));

        for(int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++)
            controladoresPila.add(new ControladorPila(vistaPrincipal, klondike.obtenerPilaDelTableau(i), i, datosMovimiento));
    }

    public static void actualizar(){
        evaluarMovimiento();
        evaluarGanador();
        vistaPrincipal.actualizar();
        controladorVentana.iniciar();
        controladorMazo.iniciar();
        controladorBasura.actualizar();
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar();
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar();
    }

    private static void evaluarGanador() {
        if (klondike.jugadorGano()) {
            StackPane root = new StackPane();
            // Configurar el fondo en verde
            root.setStyle("-fx-background-color: green;");

            // Crear un mensaje Label
            Label mensajeLabel = new Label("¡GANASTE!");
            mensajeLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");

            // Agregar el mensaje Label al StackPane
            root.getChildren().add(mensajeLabel);

            // Centrar el mensaje en el StackPane
            StackPane.setAlignment(mensajeLabel, Pos.CENTER);

            // Crear la escena
            Scene scene = new Scene(root, 640, 480, Color.GREEN);

            // Configurar la escena en el stage
            vistaPrincipal.configurarNuevaStage(scene);
        }
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
