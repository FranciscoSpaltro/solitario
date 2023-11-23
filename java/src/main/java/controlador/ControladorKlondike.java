package controlador;

import modelo.*;
import vista.VistaAlerta;
import vista.VistaSolitario;

public class ControladorKlondike extends ControladorSolitario {
    private static Klondike klondike;
    private static ControladorBasura controladorBasura;
    public ControladorKlondike(VistaSolitario vistaSolitario, Klondike klondike) {
        super(vistaSolitario, klondike);
        this.klondike = klondike;
        controladorMazo = new ControladorMazoKlondike(vistaSolitario, klondike, datosMovimiento, this);
        controladorBasura = new ControladorBasura(vistaSolitario, klondike.obtenerBasura(), datosMovimiento, this);
    }

    @Override
    public void actualizar(){
        super.actualizar();
        controladorBasura.actualizar(this);
    }

    @Override
    public void evaluarMovimiento() {
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
        }
        super.evaluarMovimiento();
    }
}
