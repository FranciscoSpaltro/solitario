package controlador;
import modelo.Klondike;
import vista.VistaPrincipal;

public class ControladorMazo {
    private VistaPrincipal vistaPrincipal;
    private Klondike klondike;

    public ControladorMazo(VistaPrincipal vistaPrincipal, Klondike klondike) {
        this.vistaPrincipal = vistaPrincipal;
        this.klondike = klondike;
        iniciar();
    }

    public void iniciar() {
        vistaPrincipal.obtenerVistaMazo().setOnMouseClicked(event -> {
            // Lógica para "Apretar Mazo"
            if(klondike.obtenerMazo().estaVacia()){
                klondike.moverBasuraAMazo();
            } else {
                klondike.moverMazoABasura();
            }
            ControladorPrincipal.actualizar();
        });
    }
}
