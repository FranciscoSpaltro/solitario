package controlador;

import modelo.Basura;
import vista.VistaBasura;
import vista.VistaPrincipal;

public class ControladorBasura {
    private VistaPrincipal vistaPrincipal;
    private Basura basura;
    private DatosMovimiento datosMovimiento;

    public ControladorBasura(VistaPrincipal vistaPrincipal, Basura basura, DatosMovimiento datosMovimiento) {
        this.vistaPrincipal = vistaPrincipal;
        this.basura = basura;
        this.datosMovimiento = datosMovimiento;
        actualizar();
    }

    public void actualizar() {
        this.vistaPrincipal.obtenerVistaBasura().obtenerUltimaCarta().setOnMouseClicked(event -> {
            // Lógica para "Apretar Basura"
            datosMovimiento.clic(basura,1);
            ControladorPrincipal.actualizar();
        });
    }
}
