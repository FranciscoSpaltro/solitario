package controlador;
import modelo.Klondike;
import vista.VistaPrincipal;

public class ControladorMazo {
    private VistaPrincipal vistaPrincipal;
    private Klondike klondike;
    private DatosMovimiento datosMovimiento;

    public ControladorMazo(VistaPrincipal vistaPrincipal, Klondike klondike, DatosMovimiento datosMovimiento) {
        this.vistaPrincipal = vistaPrincipal;
        this.klondike = klondike;
        this.datosMovimiento = datosMovimiento;
        iniciar();
    }

    public void iniciar() {
        vistaPrincipal.obtenerVistaMazo().setOnMouseClicked(event -> {
            // Lógica para "Apretar Mazo"
            if(klondike.obtenerMazo().estaVacia()){
                klondike.moverBasuraAMazo();
                datosMovimiento.resetear();
            } else {
                klondike.moverMazoABasura();
                datosMovimiento.resetear();
            }
            ControladorPrincipal.actualizar();
        });
    }
}
