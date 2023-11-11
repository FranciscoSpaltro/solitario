package controlador;

import modelo.*;
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
        controladorMazo = new ControladorMazo(vistaPrincipal, klondike);
        controladorBasura = new ControladorBasura(vistaPrincipal, klondike.obtenerBasura(), datosMovimiento);
        for(int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++)
            controladoresCimiento.add(new ControladorCimiento(vistaPrincipal, klondike.obtenerCimiento(i), i, datosMovimiento));

        for(int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++)
            controladoresPila.add(new ControladorPila(vistaPrincipal, klondike.obtenerPilaDelTableau(i), i, datosMovimiento));
    }

    public static void actualizar(){
        evaluarMovimiento();
        vistaPrincipal.actualizar();
        controladorVentana.iniciar();
        controladorMazo.iniciar();
        controladorBasura.actualizar();
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar();
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar();
    }

    public static void evaluarMovimiento() {
        if (!datosMovimiento.realizarMovimiento())
            return;
        if (datosMovimiento.esBasura(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Basura a Cimiento"
                try {
                    klondike.moverBasuraACimiento((Cimiento) datosMovimiento.obtenerListaDestino());
                    System.out.println("Mover de Basura a Cimiento");
                } catch (InvalidMovementException e) {
                    System.out.println(e.obtenerMotivo());
                }
            } else if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Basura a Pila"
                try {
                    klondike.moverBasuraAPila((PilaDelTableau) datosMovimiento.obtenerListaDestino());
                    System.out.println("Mover de Basura a Pila");
                } catch (InvalidMovementException e) {
                    System.out.println(e.obtenerMotivo());
                }
            }
        } else if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Cimiento a Pila"
                try {
                    klondike.moverCimientoAPila((Cimiento) datosMovimiento.obtenerListaOrigen(), (PilaDelTableau) datosMovimiento.obtenerListaDestino());
                    System.out.println("Mover de Cimiento a Pila");
                } catch (InvalidMovementException e) {
                    System.out.println(e.obtenerMotivo());
                }
            }
        } else if (datosMovimiento.esPila(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Pila"
                try {
                    System.out.println("Mover de Pila a Pila");
                    klondike.moverPilaAPila((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (PilaDelTableau) datosMovimiento.obtenerListaDestino(), datosMovimiento.obtenerListaOrigen().cantidadCartas() - datosMovimiento.obtenerIndiceOrigen() + 1);
                    datosMovimiento.resetear();
                } catch (InvalidMovementException e) {
                    System.out.println(e.obtenerMotivo());
                }
            } else if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Cimiento"
                if (datosMovimiento.obtenerIndiceOrigen() == datosMovimiento.obtenerListaOrigen().cantidadCartas()) {
                    try {
                        System.out.println("Mover de Pila a Cimiento");
                        klondike.moverPilaACimiento((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (Cimiento) datosMovimiento.obtenerListaDestino());
                    } catch (InvalidMovementException e) {
                        System.out.println(e.obtenerMotivo());
                    }
                }
            }
        }

    }
}
