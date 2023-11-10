package controlador;

import modelo.Basura;
import vista.VistaBasura;
import vista.VistaPrincipal;

public class ControladorBasura {
    private VistaPrincipal vistaPrincipal;
    private Basura basura;

    public ControladorBasura(VistaPrincipal vistaPrincipal, Basura basura) {
        this.vistaPrincipal = vistaPrincipal;
        this.basura = basura;
        actualizar();
    }

    public void actualizar() {
        this.vistaPrincipal.obtenerVistaBasura().obtenerUltimaCarta().setOnMouseClicked(event -> {
            // Lógica para "Apretar Basura"
            System.out.println("Ultima carta de la basura seleccionada");
            ControladorPrincipal.actualizar();
        });
    }
}
