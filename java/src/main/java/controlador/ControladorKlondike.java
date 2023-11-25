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
    public void evaluarMovimiento() throws InvalidMovementException {
        if (!datosMovimiento.realizarMovimiento())
            return;
        ListaDeCartas listaOrigen = datosMovimiento.obtenerListaOrigen();
        ListaDeCartas listaDestino = datosMovimiento.obtenerListaDestino();
        if (listaOrigen.esBasura()) {
            if (listaDestino.esCimiento()) {
                // Lógica para "Mover de Basura a Cimiento"
                klondike.moverBasuraACimiento((Cimiento) listaDestino);
            } else if (listaDestino.esPilaDelTableau()) {
                // Lógica para "Mover de Basura a Pila"
                klondike.moverBasuraAPila((PilaDelTableau) listaDestino);
            }
        } else if (listaOrigen.esCimiento()) {
            if (listaDestino.esPilaDelTableau()) {
                // Lógica para "Mover de Cimiento a Pila"
                klondike.moverCimientoAPila((Cimiento) listaOrigen, (PilaDelTableau) listaDestino);
            }
        }
        super.evaluarMovimiento();
    }
}
