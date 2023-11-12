package controlador;
import modelo.Cimiento;
import vista.VistaPrincipal;
public class ControladorCimiento {
    private VistaPrincipal vistaPrincipal;
    private Cimiento cimiento;
    private int id;
    private DatosMovimiento datosMovimiento;

    public ControladorCimiento(VistaPrincipal vistaPrincipal, Cimiento cimiento, int id, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaPrincipal = vistaPrincipal;
        this.cimiento = cimiento;
        this.id = id;
        this.datosMovimiento = datosMovimiento;
        actualizar(controladorSolitario);
    }

    public void actualizar(ControladorSolitario controladorSolitario) {
        this.vistaPrincipal.obtenerVistaCimiento(id).setOnMouseClicked(event -> {
            // Lógica para "Apretar Cimiento"
            datosMovimiento.clic(cimiento, 1);
            controladorSolitario.actualizar();
        });
    }



}
