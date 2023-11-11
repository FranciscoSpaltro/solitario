package controlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.VistaPrincipal;
public class ControladorVentana {
    private final VistaPrincipal vistaPrincipal;
    //TESTING
    private DatosMovimiento datosMovimiento;

    public ControladorVentana(VistaPrincipal vistaPrincipal, DatosMovimiento datosMovimiento) {
        this.vistaPrincipal = vistaPrincipal;
        this.datosMovimiento = datosMovimiento;
    }

    public void iniciar(){
        vistaPrincipal.obtenerNuevoJuegoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Nuevo Juego"
                System.out.println("Nuevo Juego seleccionado");
            }
        });

        vistaPrincipal.obtenerGuardarJuegoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Guardar Juego"
                System.out.println("Guardar Juego seleccionado");
            }
        });

        vistaPrincipal.obtenerKlondikeItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Klondike"
                System.out.println("Klondike seleccionado");
            }
        });

        vistaPrincipal.obtenerSpiderItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Spider"
                System.out.println("Spider seleccionado");
            }
        });

        vistaPrincipal.obtenerContactanosItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Contáctanos"
                System.out.println("Contáctanos seleccionado");
            }
        });

        vistaPrincipal.obtenerCreditosItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Créditos"
                System.out.println("Créditos seleccionado");
            }
        });

        vistaPrincipal.obtenerTestingItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para "Deseleccionar"
                datosMovimiento.resetear();
            }
        });

    }
}
