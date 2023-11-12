package controlador;

import modelo.Basura;
import vista.VistaPrincipal;

public class ControladorBasura {
    private VistaPrincipal vistaPrincipal;
    private Basura basura;
    private DatosMovimiento datosMovimiento;

    public ControladorBasura(VistaPrincipal vistaPrincipal, Basura basura, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaPrincipal = vistaPrincipal;
        this.basura = basura;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        this.vistaPrincipal.obtenerVistaBasura().obtenerUltimaCarta().setOnMouseClicked(event -> {
            // Lógica para "Apretar Basura"
            datosMovimiento.clic(basura,1);
            controladorSolitario.actualizar();
        });
    }
}
