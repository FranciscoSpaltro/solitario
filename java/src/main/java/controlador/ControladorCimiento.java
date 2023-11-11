package controlador;
import modelo.Cimiento;
import vista.VistaPrincipal;
public class ControladorCimiento {
    private VistaPrincipal vistaPrincipal;
    private Cimiento cimiento;
    private int id;
    private DatosMovimiento datosMovimiento;

    public ControladorCimiento(VistaPrincipal vistaPrincipal, Cimiento cimiento, int id, DatosMovimiento datosMovimiento) {
        this.vistaPrincipal = vistaPrincipal;
        this.cimiento = cimiento;
        this.id = id;
        this.datosMovimiento = datosMovimiento;
        actualizar();
    }

    public void actualizar() {
        this.vistaPrincipal.obtenerVistaCimiento(id).setOnMouseClicked(event -> {
            // Lógica para "Apretar Cimiento"
            int idCimiento = id + 1;
            datosMovimiento.clic(cimiento, 1);
            ControladorPrincipal.actualizar();
        });
    }



}
