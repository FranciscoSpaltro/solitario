package controlador;

import modelo.Klondike;
import vista.VistaPrincipal;

public class ControladorMazoKlondike extends ControladorMazo {
    private Klondike klondike;

    public ControladorMazoKlondike(VistaPrincipal vistaPrincipal, Klondike klondike, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        super(vistaPrincipal, klondike, datosMovimiento, controladorSolitario);
        this.klondike = klondike;
    }

    @Override
    public void iniciar(ControladorSolitario controladorSolitario) {
        vistaPrincipal.obtenerVistaMazo().setOnMouseClicked(event -> {
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
