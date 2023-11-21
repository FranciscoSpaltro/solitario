package controlador;
import modelo.Cimiento;
import vista.VistaSolitario;
public class ControladorCimiento {
    private VistaSolitario vistaSolitario;
    private Cimiento cimiento;
    private int id;
    private DatosMovimiento datosMovimiento;

    public ControladorCimiento(VistaSolitario vistaSolitario, Cimiento cimiento, int id, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaSolitario = vistaSolitario;
        this.cimiento = cimiento;
        this.id = id;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        this.vistaSolitario.obtenerVistaCimiento(id).setOnMouseClicked(event -> {
            // Lógica para "Apretar Cimiento"
            datosMovimiento.clic(cimiento, 1);
            vistaSolitario.obtenerVistaCarta().configurarEfecto(vistaSolitario.obtenerVistaCimiento(id).obtenerUltimaCarta());
            controladorSolitario.actualizar();
        });
    }



}
