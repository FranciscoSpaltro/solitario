package controlador;

import modelo.Klondike;
import vista.VistaSolitario;

public class ControladorMazoKlondike extends ControladorMazo {
    private Klondike klondike;

    public ControladorMazoKlondike(VistaSolitario vistaSolitario, Klondike klondike, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        super(vistaSolitario, klondike, datosMovimiento, controladorSolitario);
        this.klondike = klondike;
    }

    @Override
    public void iniciar(ControladorSolitario controladorSolitario) {
        vistaSolitario.obtenerVistaMazo().setOnMouseClicked(event -> {
            // Lógica para "Apretar Mazo"
            if(klondike.obtenerMazo().estaVacia()){
                klondike.moverBasuraAMazo();
                datosMovimiento.resetear();
            } else {
                klondike.moverMazoABasura();
                datosMovimiento.resetear();
            }
            controladorSolitario.actualizar();
        });
    }
}
