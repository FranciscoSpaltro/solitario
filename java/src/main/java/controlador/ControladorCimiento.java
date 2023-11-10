package controlador;
import modelo.Cimiento;
import vista.VistaPrincipal;
public class ControladorCimiento {
    private VistaPrincipal vistaPrincipal;
    private Cimiento cimiento;
    private int id;

    public ControladorCimiento(VistaPrincipal vistaPrincipal, Cimiento cimiento, int id) {
        this.vistaPrincipal = vistaPrincipal;
        this.cimiento = cimiento;
        this.id = id;
        actualizar();
    }

    public void actualizar() {
        this.vistaPrincipal.obtenerVistaCimiento(id).setOnMouseClicked(event -> {
            // Lógica para "Apretar Cimiento"
            int idCimiento = id + 1;
            System.out.println("Ultima carta del cimiento " + idCimiento + " seleccionada");
            ControladorPrincipal.actualizar();
        });
    }



}
