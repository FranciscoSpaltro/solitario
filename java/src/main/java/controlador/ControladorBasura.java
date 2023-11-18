package controlador;

import modelo.Basura;
import vista.VistaSolitario;

public class ControladorBasura {
    private VistaSolitario vistaSolitario;
    private Basura basura;
    private DatosMovimiento datosMovimiento;

    public ControladorBasura(VistaSolitario vistaSolitario, Basura basura, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaSolitario = vistaSolitario;
        this.basura = basura;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        this.vistaSolitario.obtenerVistaBasura().obtenerUltimaCarta().setOnMouseClicked(event -> {
            // Lógica para "Apretar Basura"
            datosMovimiento.clic(basura,1);
            //vistaSolitario.obtenerVistaCarta().configurarEfecto(vistaSolitario.obtenerVistaBasura().obtenerUltimaCarta());
            controladorSolitario.actualizar();
        });
    }
}
